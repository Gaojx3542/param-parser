package com.newland.ntms.paramParser.asn1.core.types;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;

public class ASN1WrapperConstructedType extends ASN1WrapperType {

	public ASN1WrapperConstructedType() { super(""); }
	public ASN1WrapperConstructedType(String name) { super(name); }

	@Override
	public void encodeValue(Object obj, ASN1OutputStream derStream) {
		// TODO Auto-generated method stub

	}

	@Override
	public int asn1Tag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object decodeValue(ASN1InputStream derStream, int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
