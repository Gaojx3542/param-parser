package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.objects.ASN1BMPString;

public class ASN1BMPStringType extends ASN1WrappedStringType<ASN1BMPString> {

	public ASN1BMPStringType() { super("ASN1BMPStringType"); }

	public boolean isTypeFor(Object obj) {
		return obj instanceof ASN1BMPString;
	}

	@Override
	public int asn1Tag() { return 30; }

	@Override
	public Class<ASN1BMPString> octetsClass() {
		// TODO Auto-generated method stub
		return ASN1BMPString.class;
	}

}
