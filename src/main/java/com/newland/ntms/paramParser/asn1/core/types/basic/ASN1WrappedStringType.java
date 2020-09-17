package com.newland.ntms.paramParser.asn1.core.types.basic;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;
import com.newland.ntms.paramParser.asn1.core.objects.ASN1AbstractString;

import java.io.IOException;

public abstract class ASN1WrappedStringType<S extends ASN1AbstractString<S>> extends ASN1OctetsType {

	public ASN1WrappedStringType(String name) { super(name); }

	public abstract Class<S> octetsClass();

	@Override
	public Integer sizeOfObject(Object obj) throws IOException {
		ASN1OutputStream out = new ASN1OutputStream();
		encodeValue(obj, out);
		return out.stream.toByteArray().length;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void encodeValue(Object obj, ASN1OutputStream derStream) throws IOException {
		derStream.write(((ASN1AbstractString)obj).string.getBytes());
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object decodeValue(ASN1InputStream derStream, int length) throws IOException, InstantiationException, IllegalAccessException {
		byte[] bytes = new byte[length];
		derStream.read(bytes);
		ASN1AbstractString<?> mine = octetsClass().newInstance();
		mine.string = new String(bytes);
		return mine;
	}
	public Object decode(ASN1InputStream derStream) throws IOException, InstantiationException, IllegalAccessException {
		Object obj = null;
		int tag = derStream.peekTag();
		int numericTag = tag & 0x1F;
		if(numericTag != (asn1Tag() & 0x1F)) {
			throw new IOException("bad tag");
		}
		derStream.nextTag();
		if((tag & 0x20) > 0) {
			try {
				obj = decodeConstructedValue(derStream, derStream.nextLength());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			obj = decodeValue(derStream, derStream.nextLength());
		}
		return obj;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public Object decodeConstructedValue(ASN1InputStream derStream, int length) throws InstantiationException, IllegalAccessException, IOException {
		if(length == -1) {
			return decodeValueIndefiniteLength(derStream);
		}
		S octets = octetsClass().newInstance();
		int stopPosition = derStream.getPosition() + length;
		while(derStream.getPosition() < stopPosition) {
			octets = octets.concat(((S) decode(derStream)).string.getBytes());
		}
		return octets;
	}

	@SuppressWarnings("deprecation")
	private Object decodeValueIndefiniteLength(ASN1InputStream derStream) throws InstantiationException, IllegalAccessException, IOException {
		S octets = octetsClass().newInstance();
		byte[] bytes = new byte[0];
		boolean loopTest = true;
		do {
			octets = octets.concat(bytes);
			if(derStream.peekTag() == 0) {
				new ASN1EndOfIndefiniteLengthType().decode(derStream);
				loopTest = false;
			} else {
				bytes = (byte[]) decode(derStream);
			}
		} while(loopTest);
		return octets;
	}
}
