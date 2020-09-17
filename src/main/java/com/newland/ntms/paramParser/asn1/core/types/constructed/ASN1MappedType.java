package com.newland.ntms.paramParser.asn1.core.types.constructed;

import com.newland.ntms.paramParser.asn1.core.ASN1InputStream;
import com.newland.ntms.paramParser.asn1.core.ASN1Module;
import com.newland.ntms.paramParser.asn1.core.ASN1OutputStream;
import com.newland.ntms.paramParser.asn1.core.types.ASN1EndOfIndefiniteLengthMarker;
import com.newland.ntms.paramParser.asn1.core.types.ASN1StructureElement;
import com.newland.ntms.paramParser.asn1.core.types.basic.ASN1EndOfIndefiniteLengthType;

import java.io.IOException;

public abstract class ASN1MappedType<T> extends ASN1StructuredType<ASN1StructureElement> {

	public Class<T> mappingClass;

	public ASN1MappedType(String name, Class<T> mapClass) { super(name); this.mappingClass = mapClass; }

	@Override
	public ASN1StructureElement newElement() {
		// TODO Auto-generated method stub
		return new ASN1StructureElement();
	}
	public boolean isTypeFor(Object obj) {
		if(mappingClass != null) {
			return mappingClass.isInstance(obj);
		} else {
			return asn1Tag() == ASN1Module.tagForObject(obj);
		}
	}

	public void encodeValue(Object obj, ASN1OutputStream derStream) throws IOException {
		for(ASN1StructureElement element : elements) {
			element.encode(obj, derStream);
		}
	}
	@SuppressWarnings("deprecation")
	@Override
	public Object decodeValue(ASN1InputStream derStream, int length) throws IOException, InstantiationException, IllegalAccessException {
		if(length == -1) {
			return decodeValueIndefiniteLength(derStream);
		}
		int stopPosition = derStream.getPosition() + length;
		T target = (T) mappingClass.newInstance();
		
		int markIndex = 0;
		for(int index = 0; (index < elements.size()) && (derStream.getPosition() < stopPosition); index++) {
			ASN1StructureElement element = elements.get(index);
			element.decode(target, derStream);
			markIndex = index + 1;
		}
		for(int index = markIndex; index < elements.size(); index++) {
			ASN1StructureElement element = elements.get(index);
			if(element.hasDefault()) {
				element.valueInto(element.defaultValue, target);
			}
			if(!element.hasDefaultOrIsOptional()) {
				throw new IOException("missing element");
			}
		}
		if(derStream.getPosition() < stopPosition) { throw new IOException("invalid length"); }
		return target;
	}
	@SuppressWarnings("deprecation")
	private Object decodeValueIndefiniteLength(ASN1InputStream derStream) throws InstantiationException, IllegalAccessException, IOException {
		T target = (T) mappingClass.newInstance();

		Object value = null;
		int markIndex = 0;
		do {
			if(derStream.peekTag() == 0) {
				value = new ASN1EndOfIndefiniteLengthType().decode(derStream);
			} else {
				elements.get(markIndex).decode(target, derStream);
				markIndex++;
			}
		} while(! (value instanceof ASN1EndOfIndefiniteLengthMarker));

		for(int index = markIndex; index < elements.size(); index++) {
			ASN1StructureElement element = elements.get(index);
			if(element.hasDefault()) {
				element.valueInto(element.defaultValue, target);
			}
			if(!element.hasDefaultOrIsOptional()) {
				throw new IOException("missing element");
			}
		}
		return target;
	}

	public ASN1StructureElement addTypeStringDefault(String elementName, String typeString, Object defaultVal) {
		ASN1StructureElement newElement = addTypeString(elementName, typeString);
		newElement.defaultValue = defaultVal;
		return newElement;
	}
	public ASN1StructureElement addTypeStringExplicitDefault(String elementName, String typeString, int tag, Object defaultVal) {
		ASN1StructureElement newElement = addTypeString(elementName, typeString);
		newElement.explicitTag(tag);
		newElement.defaultValue = defaultVal;
		return newElement;
	}
	public ASN1StructureElement addTypeStringImplicitDefault(String elementName, String typeString, int tag, Object defaultVal) {
		ASN1StructureElement newElement = addTypeString(elementName, typeString);
		newElement.implicitTag(tag);
		newElement.defaultValue = defaultVal;
		return newElement;
	}
	public ASN1StructureElement addTypeStringOptionalDefault(String elementName, String typeString, Object defaultVal) {
		ASN1StructureElement newElement = newElement();
		newElement.symbol = elementName;
		newElement.type = typeFromString(typeString);
		newElement.defaultValue = defaultVal;
		newElement.optional = true;
		return newElement;
	}
	public ASN1StructureElement addTypeStringOptional(String elementName, String typeString) {
		ASN1StructureElement newElement = newElement();
		newElement.symbol = elementName;
		newElement.type = typeFromString(typeString);
		newElement.optional = true;
		elements.add(newElement);
		return newElement;
	}
	public ASN1StructureElement addTypeStringExplicitOptional(String elementName, String typeString, int tag) {
		ASN1StructureElement newElement = addTypeString(elementName, typeString);
		newElement.explicitTag(tag);
		newElement.optional = true;
		return newElement;
	}
	public ASN1StructureElement addTypeStringImplicitOptional(String elementName, String typeString, int tag) {
		ASN1StructureElement newElement = addTypeString(elementName, typeString);
		newElement.implicitTag(tag);
		newElement.optional = true;
		return newElement;
	}
}
