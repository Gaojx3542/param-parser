package com.newland.ntms.paramParser.core.format;

/**
 * @author Gaojx
 */
public class ParamZipParserManager {

    public static ParamZipParser factory(FormatType type) {
        switch (type){
            case JSON:
                return ParamZipParserJSON.getInstance();
            case XML:
                return ParamZipParserXML.getInstance();
            case ASN1:
                return ParamZipParserASN1.getInstance();
            default:
                throw new IllegalArgumentException("not supported formatType:["+type+"]");
        }
    }



}
