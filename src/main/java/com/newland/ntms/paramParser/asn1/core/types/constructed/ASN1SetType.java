package com.newland.ntms.paramParser.asn1.core.types.constructed;

import com.newland.ntms.paramParser.asn1.core.objects.OrderedSet;

import java.util.Set;

public class ASN1SetType extends ASN1UnmappedType {

	public ASN1SetType() { super(); }

	@Override
	public int asn1Tag() { return 49; }

	@SuppressWarnings("rawtypes")
	@Override
	protected Set<?> newContainer() {
		// TODO Auto-generated method stub
		return new OrderedSet();
	}
}
