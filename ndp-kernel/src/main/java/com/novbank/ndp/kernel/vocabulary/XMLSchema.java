package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFResource;
import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class XMLSchema extends RDFVocabulary{
    public static final String NAMESPACE = "http://www.w3.org/2001/XMLSchema#";
    public static final String PREFIX = "xsd";
    public static final XMLSchema INSTANCE = new XMLSchema();

    public final RDFResource xfloat;
    public final RDFResource xdouble;
    public final RDFResource xint;
    public final RDFResource xlong;
    public final RDFResource xshort;
    public final RDFResource xbyte;
    public final RDFResource xboolean;
    public final RDFResource xstring;
    public final RDFResource unsignedByte;
    public final RDFResource unsignedShort;
    public final RDFResource unsignedInt;
    public final RDFResource unsignedLong;
    public final RDFResource decimal;
    public final RDFResource integer;
    public final RDFResource nonPositiveInteger;
    public final RDFResource nonNegativeInteger;
    public final RDFResource positiveInteger;
    public final RDFResource negativeInteger;
    public final RDFResource normalizedString;
    public final RDFResource anyURI;
    public final RDFResource token;
    public final RDFResource Name;
    public final RDFResource QName;
    public final RDFResource language;
    public final RDFResource NMToken;
    public final RDFResource Entities;
    public final RDFResource NMTokens;
    public final RDFResource Entity;
    public final RDFResource Id;
    public final RDFResource NCName;
    public final RDFResource IDREF;
    public final RDFResource IDREFS;
    public final RDFResource Notation;
    public final RDFResource hexBinary;
    public final RDFResource base64Binary;
    public final RDFResource date;
    public final RDFResource time;
    public final RDFResource dateTime;
    public final RDFResource duration;
    public final RDFResource gDay;
    public final RDFResource gMonth;
    public final RDFResource gYear;
    public final RDFResource gYearMonth;
    public final RDFResource gMonthDay;

    private XMLSchema() {
        super(NAMESPACE,PREFIX);
        xfloat = addResource("float");
        xdouble = addResource("double");
        xint = addResource("int");
        xlong = addResource("long");
        xshort = addResource("short");
        xbyte = addResource("byte");
        xboolean = addResource("boolean");
        xstring = addResource("string");
        unsignedByte = addResource("unsignedByte");
        unsignedShort = addResource("unsignedShort");
        unsignedInt = addResource("unsignedInt");
        unsignedLong = addResource("unsignedLong");
        decimal = addResource("decimal");
        integer = addResource("integer");
        nonPositiveInteger = addResource("nonPositiveInteger");
        nonNegativeInteger = addResource("nonNegativeInteger");
        positiveInteger = addResource("positiveInteger");
        negativeInteger = addResource("negativeInteger");
        normalizedString = addResource("normalizedString");
        anyURI = addResource("anyURI");
        token = addResource("token");
        Name = addResource("Name");
        QName = addResource("QName");
        language = addResource("language");
        NMToken = addResource("NMTOKEN");
        Entities = addResource("ENTITIES");
        NMTokens = addResource("NMTOKENS");
        Entity = addResource("ENTITY");
        Id = addResource("ID");
        NCName = addResource("NCName");
        IDREF = addResource("IDREF");
        IDREFS = addResource("IDREFS");
        Notation = addResource("NOTATION");
        hexBinary = addResource("hexBinary");
        base64Binary = addResource("base64Binary");
        date = addResource("date");
        time = addResource("time");
        dateTime = addResource("dateTime");
        duration = addResource("duration");
        gDay = addResource("gDay");
        gMonth = addResource("gMonth");
        gYear = addResource("gYear");
        gYearMonth = addResource("gYearMonth");
        gMonthDay = addResource("gMonthDay");
        lock();
    }

    public static final RDFResource FLOAT = INSTANCE.xfloat;
    public static final RDFResource DOUBLE = INSTANCE.xdouble;
    public static final RDFResource INT = INSTANCE.xint;
    public static final RDFResource LONG = INSTANCE.xlong;
    public static final RDFResource SHORT = INSTANCE.xshort;
    public static final RDFResource BYTE = INSTANCE.xbyte;
    public static final RDFResource BOOLEAN = INSTANCE.xboolean;
    public static final RDFResource STRING = INSTANCE.xstring;
    public static final RDFResource UNSIGNED_BYTE = INSTANCE.unsignedByte;
    public static final RDFResource UNSIGNED_SHORT = INSTANCE.unsignedShort;
    public static final RDFResource UNSIGNED_INT = INSTANCE.unsignedInt;
    public static final RDFResource UNSIGNED_LONG = INSTANCE.unsignedLong;
    public static final RDFResource DECIMAL = INSTANCE.decimal;
    public static final RDFResource INTEGER = INSTANCE.integer;
    public static final RDFResource NON_POSITIVE_INTEGER = INSTANCE.nonPositiveInteger;
    public static final RDFResource NON_NEGATIVE_INTEGER = INSTANCE.nonNegativeInteger;
    public static final RDFResource POSITIVE_INTEGER = INSTANCE.positiveInteger;
    public static final RDFResource NEGATIVE_INTEGER = INSTANCE.negativeInteger;
    public static final RDFResource NORMALIZED_STRING = INSTANCE.normalizedString;
    public static final RDFResource ANY_URI = INSTANCE.anyURI;
    public static final RDFResource TOKEN = INSTANCE.token;
    public static final RDFResource NAME = INSTANCE.Name;
    public static final RDFResource QNAME = INSTANCE.QName;
    public static final RDFResource LANGUAGE = INSTANCE.language;
    public static final RDFResource NMTOKEN = INSTANCE.NMToken;
    public static final RDFResource ENTITIES = INSTANCE.Entities;
    public static final RDFResource NMTOKENS = INSTANCE.NMTokens;
    public static final RDFResource ENTITY = INSTANCE.Entity;
    public static final RDFResource ID = INSTANCE.Id;
    public static final RDFResource NC_NAME = INSTANCE.NCName;
    public static final RDFResource ID_REF = INSTANCE.IDREF;
    public static final RDFResource ID_REFS = INSTANCE.IDREFS;
    public static final RDFResource NOTATION = INSTANCE.Notation;
    public static final RDFResource HEX_BINARY = INSTANCE.hexBinary;
    public static final RDFResource BASE64_BINARY = INSTANCE.base64Binary;
    public static final RDFResource DATE = INSTANCE.date;
    public static final RDFResource TIME = INSTANCE.time;
    public static final RDFResource DATE_TIME = INSTANCE.dateTime;
    public static final RDFResource DURATION = INSTANCE.duration;
    public static final RDFResource GDAY = INSTANCE.gDay;
    public static final RDFResource GMONTH = INSTANCE.gMonth;
    public static final RDFResource GYEAR = INSTANCE.gYear;
    public static final RDFResource GYEAR_MONTH = INSTANCE.gYearMonth;
    public static final RDFResource GMONTH_DAY = INSTANCE.gMonthDay;
}
