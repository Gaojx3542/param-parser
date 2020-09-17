package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.objects.ASN1ISO646String;

public class ASN1ISO646StringType extends ASN1WrappedStringType<ASN1ISO646String> {

	public ASN1ISO646StringType() { super("ASN1ISO646StringType"); }

	public boolean isTypeFor(Object obj) {
		return obj instanceof ASN1ISO646String;
	}

	@Override
	public int asn1Tag() { return 26; }

	@Override
	public Class<ASN1ISO646String> octetsClass() {
		// TODO Auto-generated method stub
		return ASN1ISO646String.class;
	}

}
