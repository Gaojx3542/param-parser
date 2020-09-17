package com.newland.ntms.paramParser.asn1.core.objects;

public class ASN1PrintableString extends ASN1AbstractString<ASN1PrintableString> {

	public ASN1PrintableString() {
		this("");
	}
	public ASN1PrintableString(String string) {
		super(string);
	}

	public int asn1Tag() { return 19; }
}
