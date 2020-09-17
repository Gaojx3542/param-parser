package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.objects.ASN1GeneralString;

public class ASN1GeneralStringType extends ASN1WrappedStringType<ASN1GeneralString> {

	public ASN1GeneralStringType() { super("ASN1GeneralStringType"); }

	public boolean isTypeFor(Object obj) {
		return obj instanceof ASN1GeneralString;
	}

	@Override
	public int asn1Tag() { return 27; }

	@Override
	public Class<ASN1GeneralString> octetsClass() {
		// TODO Auto-generated method stub
		return ASN1GeneralString.class;
	}

}
