package com.newland.ntms.paramParser.asn1.core.types.basic;


import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;

import java.io.IOException;

public class ASN1BooleanType extends ASN1BasicType {

	public ASN1BooleanType() { super("ASN1BooleanType"); }

	public boolean isTypeFor(Object obj) { return obj instanceof Boolean; }
	public int asn1Tag() { return 1; }
	public Integer sizeOfObject(Object obj) { return 1; }

	public void encodeValue(Object obj, ASN1OutputStream derStream) throws IOException {
		if((boolean) obj) {
			derStream.write(255);
		} else {
			derStream.write(0);
		}		
	}
	public Object decodeValue(ASN1InputStream derStream, int length) throws IOException {
		return derStream.read() > 0;
	}

	@Override
	public Object decodeConstructedValue(ASN1InputStream derStream, int length)
			throws IOException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return decodeValue(derStream, length);
	}
}
