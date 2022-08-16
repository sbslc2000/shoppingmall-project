package org.cau.shoppingmall.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ListUtil {

    /*
       List .toString 으로 생성한 문자열을 다시 List로 바꾸는 메서드 입니다.
    */
    public static List<String> parse(String s1) {
        String replace = s1.replace("[","");
        String replace1 = replace.replace("]","");
        List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));

        return myList;
    }
}
