package com.newland.ntms.paramParser.core.format;

/**
 * @author Gaojx
 */
public enum FormatType {

    JSON(1),

    XML(2),

    ASN1(3);

    private int code;

    FormatType(int code){
        this.code = code;
    }

    public static final FormatType valueOf(int code) {
        switch (code) {
            case 1:
                return JSON;
            case 2:
                return XML;
            case 3:
                return ASN1;
            default:
                throw new IllegalArgumentException("not supported code:["+code+"]");
        }
    }

    public int getCode() {
        return code;
    }
}
