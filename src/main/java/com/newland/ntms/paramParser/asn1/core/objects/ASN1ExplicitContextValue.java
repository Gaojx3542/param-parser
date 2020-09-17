package com.newland.ntms.paramParser.asn1.core.objects;

public class ASN1ExplicitContextValue extends ASN1Value {

	public boolean tagIsPrimitive = true;
	public int tagIndex = 0;
	public byte[] tagValue = new byte[0];

	public int asn1Tag() {
		if(tagIsPrimitive) {
			return 128 | tagIndex;
		} else {
			return 160 | tagIndex;
		}
	}
}
