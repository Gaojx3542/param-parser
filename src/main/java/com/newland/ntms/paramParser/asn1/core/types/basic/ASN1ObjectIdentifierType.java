package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1Module;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;
import com.newland.ntms.paramParser.asn1.core.objects.ASN1ObjectId;

import java.io.IOException;

public class ASN1ObjectIdentifierType extends ASN1BasicType {

	public ASN1ObjectIdentifierType() { super("ASN1ObjectIdentifierType"); }

	public boolean isTypeFor(Object obj) { return obj instanceof ASN1ObjectId; }
	public Integer sizeOfObject(Object obj) { return ((ASN1ObjectId) obj).toByteArray().length; }
	public int asn1Tag() { return 6; }

	public void encode(Object obj, ASN1OutputStream derStream) throws IOException {
		if(obj instanceof ASN1ObjectId) {
			ASN1ObjectId oid = (ASN1ObjectId) obj;
			derStream.nextPutTag(ASN1Module.tagForObject(oid));
			byte[] bytes = oid.toByteArray();
			derStream.nextPutLength(bytes.length);
			derStream.write(bytes, 0, bytes.length);
		} else {
			throw new IOException("not ObjectId");
		}
	}
	@Override
	public void encodeValue(Object obj, ASN1OutputStream derStream) throws IOException {
		derStream.write(((ASN1ObjectId) obj).toByteArray());
	}

	@Override
	public Object decodeValue(ASN1InputStream derStream, int length) throws IOException {
		byte b = (byte) derStream.read();
		String oid = "" + (b / 40) + "." + (b % 40);
		int len = 0;
		for(int i = 1; i < length; i++) {
			b = (byte) derStream.read();
			if((b & 0x80) == 0x80) {
				len = (len << 7) + (b & 0x7F);
			} else {
				len = len << 7 + b;
				oid = oid + "." + b;
				len = 0;
			}
		}
		ASN1ObjectId myOid = new ASN1ObjectId();
		myOid.oid = oid;
		return myOid;
	}
}
