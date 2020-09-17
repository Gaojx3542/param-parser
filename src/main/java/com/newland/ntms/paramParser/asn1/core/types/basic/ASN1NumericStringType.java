package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.objects.ASN1NumericString;

public class ASN1NumericStringType extends ASN1WrappedStringType<ASN1NumericString> {

	public ASN1NumericStringType() { super("ASN1NumericStringType"); }

	public boolean isTypeFor(Object obj) {
		return obj instanceof ASN1NumericString;
	}

	@Override
	public int asn1Tag() { return 18; }

	@Override
	public Class<ASN1NumericString> octetsClass() {
		// TODO Auto-generated method stub
		return ASN1NumericString.class;
	}

}
