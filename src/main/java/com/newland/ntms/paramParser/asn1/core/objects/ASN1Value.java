package com.newland.ntms.paramParser.asn1.core.objects;


import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;

import java.io.IOException;

public abstract class ASN1Value {

	public abstract int asn1Tag();
	public byte[] asAsn1DerBytes() throws IOException { return ASN1OutputStream.encodeObject(this); }
}
