package com.newland.ntms.paramParser.asn1.core.types.constructed;

import com.newland.ntms.paramParser.asn1.core.objects.OrderedSet;

import java.util.Collection;

public class ASN1SetOfType extends ASN1UnstructuredType {

	public ASN1SetOfType() { super(); }

	@Override
	public int asn1Tag() { return 49; }

	@SuppressWarnings("rawtypes")
	@Override
	protected Collection newContainer() {
		// TODO Auto-generated method stub
		return new OrderedSet();
	}
}
