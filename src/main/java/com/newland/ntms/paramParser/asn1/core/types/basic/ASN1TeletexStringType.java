package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.objects.ASN1TeletexString;

public class ASN1TeletexStringType extends ASN1WrappedStringType<ASN1TeletexString> {

	public ASN1TeletexStringType() { super("ASN1TeletexStringType"); }

	public boolean isTypeFor(Object obj) {
		return obj instanceof ASN1TeletexString;
	}

	@Override
	public int asn1Tag() { return 20; }

	@Override
	public Class<ASN1TeletexString> octetsClass() {
		// TODO Auto-generated method stub
		return ASN1TeletexString.class;
	}

}
