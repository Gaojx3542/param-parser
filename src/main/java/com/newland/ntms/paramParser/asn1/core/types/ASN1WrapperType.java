package com.newland.ntms.paramParser.asn1.core.types;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;

import java.io.IOException;

public abstract class ASN1WrapperType extends ASN1Type {

	public ASN1WrapperType(String name) { super(name); }

	@Override
	public void encode(Object obj, ASN1OutputStream derStream) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object decodeConstructedValue(ASN1InputStream derStream, int length)
			throws IOException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
