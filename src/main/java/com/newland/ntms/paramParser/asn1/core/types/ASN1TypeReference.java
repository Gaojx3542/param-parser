package com.newland.ntms.paramParser.asn1.core.types;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;

import java.io.IOException;

public class ASN1TypeReference extends ASN1Type {

	public ASN1TypeReference(String name) { super(name); }

	@Override
	public int asn1Tag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void encode(Object obj, ASN1OutputStream derStream) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encodeValue(Object obj, ASN1OutputStream derStream) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object decodeValue(ASN1InputStream derStream, int length)
			throws IOException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object decodeConstructedValue(ASN1InputStream derStream, int length)
			throws IOException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
