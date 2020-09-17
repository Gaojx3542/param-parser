package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.objects.ASN1T61String;

public class ASN1T61StringType extends ASN1WrappedStringType<ASN1T61String> {

	public ASN1T61StringType() { super("ASN1T61StringType"); }

	public boolean isTypeFor(Object obj) {
		return obj instanceof ASN1T61String;
	}

	@Override
	public int asn1Tag() { return 20; }

	@Override
	public Class<ASN1T61String> octetsClass() {
		// TODO Auto-generated method stub
		return ASN1T61String.class;
	}

}
