package com.newland.ntms.paramParser.asn1.core.types.constructed;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;
import com.newland.ntms.paramParser.asn1.core.types.ASN1Type;

import java.io.IOException;

public abstract class ASN1ConstructedType extends ASN1Type {

	public ASN1ConstructedType() { this(""); }
	public ASN1ConstructedType(String name) { super(name); }
	public boolean isConstructed() { return true; }

	public void encode(Object obj, ASN1OutputStream derStream) throws IOException {
		derStream.nextPutTag(asn1Tag());
		ASN1OutputStream tempStream = derStream.newStream();
		encodeValue(obj, tempStream);
		derStream.nextPutLength(tempStream.stream.size());
		derStream.write(tempStream.stream.toByteArray(), 0, tempStream.stream.size());
	}
	public Object decode(ASN1InputStream derStream) throws IOException, InstantiationException, IllegalAccessException {
		derStream.nextTag();
		return decodeValue(derStream, derStream.nextLength());
	}
	@Override
	public Object decodeConstructedValue(ASN1InputStream derStream, int length)
			throws IOException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
