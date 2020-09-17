package com.newland.ntms.paramParser.asn1.core;


import com.newland.ntms.paramParser.asn1.core.types.ASN1Type;
import com.newland.ntms.paramParser.asn1.util.IntUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ASN1OutputStream extends OutputStream {
	public ByteArrayOutputStream stream;

	public ASN1OutputStream() { stream = new ByteArrayOutputStream(100); }

	public Object virtualize(Object obj) { return obj; }
	public ASN1OutputStream newStream() { return new ASN1OutputStream(); }
	public byte[] encode(Object obj) throws IOException { return encode(obj, ASN1Module.typeForObject(obj)); }
	public byte[] encode(Object obj, ASN1Type type) throws IOException { type.encode(obj, this); return toByteArray(); }
	public int size() { return stream.size(); }
	public byte[] toByteArray() { return stream.toByteArray(); }

	@Override
	public void write(int b) throws IOException { stream.write(b); }
	public void nextPutTag(int tag) throws IOException { write(tag); }
	public void nextPutLength(Integer size) throws IOException {
		if(size <= 127) {
			write(size); return; 
		} else {
			byte[] sizeArray = IntUtil.intToByteArray(size);
			int sizeIndex = -1;
			for(int i = 0; i < sizeArray.length; i++) {
				if(sizeArray[i] != 0) {
					sizeIndex = i;
					break;
				}
			}
			if(sizeIndex == -1) { throw new RuntimeException("bad size"); }
			int byteCount = sizeArray.length - sizeIndex;
			write(byteCount | 0x80);

			for(int i = sizeIndex; i < sizeArray.length; i++) {
				write(sizeArray[i]);
			}
		}
	}

	public static byte[] encodeObject(Object obj) throws IOException { try(ASN1OutputStream stream = new ASN1OutputStream()) { return stream.encode(obj); } }
	public static byte[] encodeObject(Object obj, ASN1Type type) throws IOException { try(ASN1OutputStream stream = new ASN1OutputStream()) { return stream.encode(obj, type); } }
}
