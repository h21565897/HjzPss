package com.junzhou.infop.controller;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class DataControllerTest {
    String TestString="{\"content\":\"hehehe\",\"url\":\"\",\"title\":\"hehe\",\"sendType\":\"\",\"picUrl\":\"\"}";
    @Test
    public void TestFastJson(){
        Map<String,String> mm= JSON.parseObject(TestString,Map.class);
        Map<String,String> m1=new HashMap<>(){{
                put("1","2");
        }};
        System.out.println(mm.toString());
    }
    int[] boxes=new int[50];

    @Test
    public void Test22(int i){
        HashMap<Integer,Integer> mm=new HashMap<>();
        LinkedList<Integer> ll=new LinkedList<>();
        Arrays.fill(boxes,0);
        int ans=0;
        Integer tmp=Integer.valueOf(i);
        String s=tmp.toString();
        int he=0;
        for(int j=0;j<s.length();j++){
            he+=s.charAt(j)-'0';
        }
        
        ans=Math.max(++boxes[he],ans);
    }

}