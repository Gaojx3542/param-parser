package com.newland.ntms.paramParser.asn1.core.types.constructed;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;

public class ASN1MappedSetType<T> extends ASN1MappedType<T> {

	public ASN1MappedSetType(String name, Class<T> mapClass) { super(name, mapClass); }

	@Override
	public int asn1Tag() { return 49; }

	@Override
	public void encodeValue(Object obj, ASN1OutputStream derStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object decodeValue(ASN1InputStream derStream, int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
