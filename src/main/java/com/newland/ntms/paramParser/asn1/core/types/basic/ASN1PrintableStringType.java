package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.objects.ASN1PrintableString;

public class ASN1PrintableStringType extends ASN1WrappedStringType<ASN1PrintableString> {

	public ASN1PrintableStringType() { super("ASN1PrintableStringType"); }

	public boolean isTypeFor(Object obj) {
		return obj instanceof ASN1PrintableString;
	}

	@Override
	public int asn1Tag() { return 19; }

	@Override
	public Class<ASN1PrintableString> octetsClass() {
		// TODO Auto-generated method stub
		return ASN1PrintableString.class;
	}

}
