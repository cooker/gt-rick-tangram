package com.github.cooker.collect;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * grant
 * 3/8/2020 6:07 下午
 * 描述：
 */
public class ListTest {
    public static void main(String[] args) {

        ArrayList da = new ArrayList<String>(100);
        for (int i = 0; i < 100; i++) {
            da.add(i+"");
        }

        Iterator<String> das = da.iterator();
        while (das.hasNext()){
            if (das.next().equals("50"))
                das.remove();
        }
    }
}
