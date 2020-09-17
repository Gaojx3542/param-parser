package com.newland.ntms.paramParser.asn1.core.types;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;

import java.io.IOException;

public class ASN1ExplicitSubType extends ASN1SubType {

	public ASN1ExplicitSubType() { super(""); }
	public ASN1ExplicitSubType(ASN1Type parent) { super("", parent); }

	public void encode(Object obj, ASN1OutputStream derStream) throws IOException {
		derStream.nextPutTag(asn1Tag() | 160);
		ASN1OutputStream tempStream = derStream.newStream();
		getParent().encodeValue(obj, tempStream);
		derStream.nextPutLength(tempStream.stream.size());
		derStream.write(tempStream.stream.toByteArray());
	}

	@Override
	public void encodeValue(Object obj, ASN1OutputStream derStream) {
		// TODO Auto-generated method stub
		
	}
	public Object decode(ASN1InputStream derStream) throws IOException, InstantiationException, IllegalAccessException {
		derStream.nextTag();
		return getParent().decodeValue(derStream, derStream.nextLength());
	}
	@Override
	public Object decodeValue(ASN1InputStream derStream, int length) {
		// TODO Auto-generated method stub
		return null;
	}
}
