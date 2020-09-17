package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.objects.ASN1UniversalString;

public class ASN1UniversalStringType extends ASN1WrappedStringType<ASN1UniversalString> {

	public ASN1UniversalStringType() { super("ASN1UniversalStringType"); }

	public boolean isTypeFor(Object obj) {
		return obj instanceof ASN1UniversalString;
	}

	@Override
	public int asn1Tag() { return 28; }

	@Override
	public Class<ASN1UniversalString> octetsClass() {
		// TODO Auto-generated method stub
		return ASN1UniversalString.class;
	}

}
