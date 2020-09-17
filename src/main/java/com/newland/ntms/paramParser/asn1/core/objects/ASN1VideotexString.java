package com.newland.ntms.paramParser.asn1.core.objects;

public class ASN1VideotexString extends ASN1AbstractString<ASN1VideotexString> {

	public ASN1VideotexString() {
		this("");
	}
	public ASN1VideotexString(String string) {
		super(string);
	}

	public int asn1Tag() { return 21; }
}
