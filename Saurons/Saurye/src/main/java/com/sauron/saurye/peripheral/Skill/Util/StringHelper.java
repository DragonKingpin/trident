package com.sauron.saurye.peripheral.Skill.Util;

public class StringHelper {
    public static String safeFmtString( String bad ){
        return bad.replaceAll( "%", "%%" );
    }

    public static String safeFmtString( String bad, String with ){
        return bad.replaceAll( "%", with );
    }
}
