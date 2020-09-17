package com.newland.ntms.paramParser.asn1.core.objects;

public class ASN1IA5String extends ASN1AbstractString<ASN1IA5String> {

	public ASN1IA5String() {
		this("");
	}
	public ASN1IA5String(String string) {
		super(string);
	}

	public int asn1Tag() { return 22; }
}
