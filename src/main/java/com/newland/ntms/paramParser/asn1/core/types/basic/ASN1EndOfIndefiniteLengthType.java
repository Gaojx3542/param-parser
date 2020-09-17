package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;
import com.newland.ntms.paramParser.asn1.core.types.ASN1EndOfIndefiniteLengthMarker;

import java.io.IOException;

public class ASN1EndOfIndefiniteLengthType extends ASN1BasicType {

	public ASN1EndOfIndefiniteLengthType() { super("ASN1EndOfIndefiniteLengthType"); }

	public boolean isTypeFor(Object obj) {
		return false;
	}

	@Override
	public Integer sizeOfObject(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int asn1Tag() { return 0; }

	@Override
	public void encodeValue(Object obj, ASN1OutputStream derStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object decodeValue(ASN1InputStream derStream, int length) {
		// TODO Auto-generated method stub
		return new ASN1EndOfIndefiniteLengthMarker();
	}

	@Override
	public Object decodeConstructedValue(ASN1InputStream derStream, int length)
			throws IOException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
