package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;
import com.newland.ntms.paramParser.asn1.core.types.ASN1Type;

import java.io.IOException;

public abstract class ASN1BasicType extends ASN1Type {

	public ASN1BasicType(String name) { super(name); }

	public void encode(Object obj, ASN1OutputStream derStream) throws IOException {
		derStream.nextPutTag(asn1Tag());
		derStream.nextPutLength(sizeOfObject(obj));
		encodeValue(obj, derStream);
	}
	public abstract Integer sizeOfObject(Object obj) throws IOException;
	@Override
	public Object decodeConstructedValue(ASN1InputStream derStream, int length)
			throws IOException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
