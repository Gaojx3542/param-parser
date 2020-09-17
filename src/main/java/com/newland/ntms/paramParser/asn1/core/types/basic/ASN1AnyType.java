package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1Module;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;

import java.io.IOException;

public class ASN1AnyType extends ASN1BasicType {

	public ASN1AnyType() { super("ASN1AnyType"); }

	public boolean isTypeFor(Object obj) {
		return ASN1Module.typeForObject(obj) != null;
	}

	public void encode(Object obj, ASN1OutputStream derStream) throws IOException {
		derStream.encode(obj);
	}

	@Override
	public Integer sizeOfObject(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void encodeValue(Object obj, ASN1OutputStream derStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int asn1Tag() { return -1; }

	@Override
	public Object decodeValue(ASN1InputStream derStream, int length) {
		// TODO Auto-generated method stub
		return null;
	}
}
