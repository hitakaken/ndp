package com.novbank.ndp.kernel.util;

/**
 * Created by hp on 2015/5/18.
 */
public class HexUtils {
    public static boolean isHexString( String hexadecimal ) {
        int len = hexadecimal.length();
        for (int i = 0; i < len; ++i) {
            if (!isHexCharacter(hexadecimal.charAt(i))) return false;
        }
        return true;
    }

    public static boolean isHexCharacter( char c ) {
        return ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'));
    }
}
