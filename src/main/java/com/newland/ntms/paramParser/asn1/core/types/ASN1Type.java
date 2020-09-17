package com.newland.ntms.paramParser.asn1.core.types;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1Module;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;

import java.io.IOException;

public abstract class ASN1Type extends ASN1Entity {

	public ASN1Type() { this(""); }
	public ASN1Type(String name) { super(name); }
	public abstract int asn1Tag();

	public ASN1Type explicitSubType() {
		ASN1SubType sub = new ASN1ExplicitSubType();
		sub.setParent(this);
		return sub;
	}
	public ASN1Type implicitSubType() {
		ASN1SubType sub = new ASN1ImplicitSubType();
		sub.setParent(this);
		return sub;
	}

	public abstract void encode(Object obj, ASN1OutputStream derStream) throws IOException;

	public void encode(Object obj, ASN1OutputStream derStream, Object owner, ASN1Type ownerType) throws IOException {
		encode(obj, derStream);
	}
	public abstract void encodeValue(Object obj, ASN1OutputStream derStream) throws IOException;

	public Object decode(ASN1InputStream derStream) throws IOException, InstantiationException, IllegalAccessException {
		int tag = derStream.nextTag();
		if(!matchesTag(tag)) {
			throw new RuntimeException("bad asn1 tag");
		}
		return decodeValue(derStream, derStream.nextLength());
	}
	public Object decode(ASN1InputStream derStream, Object owner, ASN1Type ownerType) throws IOException, InstantiationException, IllegalAccessException {
		return decode(derStream);
	}
	public abstract Object decodeValue(ASN1InputStream derStream, int length) throws IOException, InstantiationException, IllegalAccessException;
	public abstract Object decodeConstructedValue(ASN1InputStream derStream, int length) throws IOException, InstantiationException, IllegalAccessException;
	
	public boolean matchesTag(int tag) { return ((asn1Tag() ^ tag) & 0x1F) == 0; }
	public boolean isTypeFor(Object obj) {	return ((asn1Tag() ^ ASN1Module.tagForObject(obj)) & 0x1F) == 0; }
	public boolean isConstructed() { return false; }
}
