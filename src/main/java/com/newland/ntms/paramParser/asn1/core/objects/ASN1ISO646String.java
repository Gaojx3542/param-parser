package com.newland.ntms.paramParser.asn1.core.objects;

public class ASN1ISO646String extends ASN1AbstractString<ASN1ISO646String> {

	public ASN1ISO646String() {
		this("");
	}
	public ASN1ISO646String(String string) {
		super(string);
	}

	public int asn1Tag() { return 26; }
}
