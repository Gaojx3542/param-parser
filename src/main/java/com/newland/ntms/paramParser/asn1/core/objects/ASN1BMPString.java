package com.newland.ntms.paramParser.asn1.core.objects;

public class ASN1BMPString extends ASN1AbstractString<ASN1BMPString> {

	public ASN1BMPString() {
		this("");
	}
	public ASN1BMPString(String string) {
		super(string);
	}

	public int asn1Tag() { return 30; }
}
