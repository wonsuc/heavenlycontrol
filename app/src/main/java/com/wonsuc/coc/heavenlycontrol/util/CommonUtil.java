package com.wonsuc.coc.heavenlycontrol.util;

public class CommonUtil {

    public CommonUtil(){};

    public static String decimalFormat(float f)
    {
        double d = (double) f;
        if(d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }
}
