package com.newland.ntms.paramParser.asn1.core.objects;

public class ASN1NumericString extends ASN1AbstractString<ASN1NumericString> {

	public ASN1NumericString() {
		this("");
	}
	public ASN1NumericString(String string) {
		super(string);
	}

	public int asn1Tag() { return 18; }
}
