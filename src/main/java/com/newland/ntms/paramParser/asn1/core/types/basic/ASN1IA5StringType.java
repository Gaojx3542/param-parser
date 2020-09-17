package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.objects.ASN1IA5String;

public class ASN1IA5StringType extends ASN1WrappedStringType<ASN1IA5String> {

	public ASN1IA5StringType() { super("ASN1IA5StringType"); }

	public boolean isTypeFor(Object obj) {
		return obj instanceof ASN1IA5String;
	}

	@Override
	public int asn1Tag() { return 22; }

	@Override
	public Class<ASN1IA5String> octetsClass() {
		// TODO Auto-generated method stub
		return ASN1IA5String.class;
	}

}
