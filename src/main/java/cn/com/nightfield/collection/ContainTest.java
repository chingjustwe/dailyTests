package cn.com.nightfield.collection;

import java.util.ArrayList;
import java.util.List;

public class ContainTest {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        List<String> strSubList = new ArrayList<>();
        strList.add("1"); strList.add("2"); strList.add("3");
        
        strSubList.add("1"); strSubList.add("24");
        
        System.out.println(strList.containsAll(new ArrayList<>()));
    }
}
