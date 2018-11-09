package com.neusoft.util;

import java.util.HashMap;
import java.util.Map;

public class WordCount {
    public static Map<String, Integer> wc(String line){
        line = line.replace("?","");
        Map<String,Integer> map = new HashMap<>();
        for (String word :line.split(" ")){
            if (map.get(word) == null){
                map.put(word,1);
            }else {
                map.put(word,map.get(word)+1);
            }
        }
        return map;
    }
}
