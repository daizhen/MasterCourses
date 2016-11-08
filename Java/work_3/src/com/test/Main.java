package com.test;

import java.util.HashMap;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        // write your code here
        KeyWordCount keyWordCount = new KeyWordCount("C:\\Users\\daizhen\\Downloads\\MasterCourses-master\\MasterCourses-master\\Java\\work_3\\src\\com\\test\\KeyWordCount.java");
        HashMap<String, Integer> countResult = keyWordCount.count();
        Iterator<String> iterator_Key = countResult.keySet().iterator();
        while (iterator_Key.hasNext()) {
            String key = iterator_Key.next();
            int value = countResult.get(key);

            System.out.println(key + ":" + value);
        }
    }
}
