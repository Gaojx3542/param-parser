package com.newland.ntms.paramParser.asn1.core.types.constructed;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;
import com.newland.ntms.paramParser.asn1.core.types.ASN1EndOfIndefiniteLengthMarker;
import com.newland.ntms.paramParser.asn1.core.types.ASN1Type;
import com.newland.ntms.paramParser.asn1.core.types.basic.ASN1EndOfIndefiniteLengthType;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public abstract class ASN1UnstructuredType extends ASN1ConstructedType {

	public ASN1Type elementType;

	public ASN1UnstructuredType() { super(""); }

	@SuppressWarnings("rawtypes")
	public void encodeValue(Object obj, ASN1OutputStream derStream) throws IOException {
		for(Object o : (Collection)obj) {
			elementType.encode(o, derStream);
		}
	}

	@SuppressWarnings("rawtypes")
	protected abstract Collection newContainer();
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Override
	public Object decodeValue(ASN1InputStream derStream, int length) throws IOException, InstantiationException, IllegalAccessException {
		if(length == -1) {
			return decodeValueIndefiniteLength(derStream);
		}
		int stopPosition = derStream.getPosition() + length;
		Collection target = (Collection) newContainer();
		for(int index = 0; derStream.getPosition() < stopPosition; index++) {
			Object obj = elementType.decode(derStream);
			target.add(obj);
		}
		if(derStream.getPosition() < stopPosition) { throw new IOException("invalid length"); }
		return target;
	}
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private Object decodeValueIndefiniteLength(ASN1InputStream derStream) throws InstantiationException, IllegalAccessException, IOException {
		List target = (List) newContainer();

		Object value = null;
		int markIndex = 0;
		do {
			if(derStream.peekTag() == 0) {
				value = new ASN1EndOfIndefiniteLengthType().decode(derStream);
			} else {
				target.add(elementType.decode(derStream));
			}
		} while(! (value instanceof ASN1EndOfIndefiniteLengthMarker));
		return target;
	}
}
