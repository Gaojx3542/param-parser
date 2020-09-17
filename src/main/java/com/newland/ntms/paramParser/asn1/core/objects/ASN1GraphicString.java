package com.newland.ntms.paramParser.asn1.core.objects;

public class ASN1GraphicString extends ASN1AbstractString<ASN1GraphicString> {

	public ASN1GraphicString() {
		this("");
	}
	public ASN1GraphicString(String string) {
		super(string);
	}

	public int asn1Tag() { return 25; }
}
