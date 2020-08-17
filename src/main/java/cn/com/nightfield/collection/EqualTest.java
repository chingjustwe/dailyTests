package cn.com.nightfield.collection;

import java.util.ArrayList;
import java.util.HashSet;

public class EqualTest {
	public static void main(String[] args) {
    	String[] arr1 = {"12", "23"};
    	String[] arr2 = {"12", "23"};
    	
    	System.out.println(arr1.equals(arr2));
    	ArrayList<String> list1 = new ArrayList<>();
    	ArrayList<String> list2 = new ArrayList<>();
    	
    	list1.add("12");
    	list2.add("23");
    	
    	list1.add("23");
    	list2.add("12");
    	
    	HashSet<String> set1 = new HashSet<>();
    	HashSet<String> set2 = new HashSet<>();

    	set1.add("12");
    	set2.add("23");
    	
    	set1.add("23");
    	set2.add("12");
    	
    	System.out.println(list1.equals(list2));
    	System.out.println(set1.equals(set2));
	}
}
