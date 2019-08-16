package com.qmtec.common.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class OrderContextIdFactory {

    public static Long getRandom() {
        return Long.valueOf(createRandomNumber(13));
    }

    public static String createRandomNumber(int length){
        String number ="";
        for (int i = 0; i<length; i++){
            int randomNumber = new Random().nextInt(10);
            number += randomNumber+"";
        }
        return number;
    }
}
