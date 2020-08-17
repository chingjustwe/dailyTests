package cn.com.nightfield.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author Rocky Chi
 * check ConcurrentModificationException of each cn.com.nightfield.collection
 *
 */
public class ListCompare {
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        final List<Integer> cowList = new CopyOnWriteArrayList<Integer>();//success
        final List<Integer> list = new ArrayList<Integer>();//ConcurrentModificationException
        final List<Integer> vector = new Vector<Integer>();//ConcurrentModificationException
        final List<Integer> syncList = Collections.synchronizedList(list);//ConcurrentModificationException
        final Map<Integer, Integer> ccHashMap = new ConcurrentHashMap<Integer, Integer>();//success
        final Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();//ConcurrentModificationException
        final Map<Integer, Integer> syncHashMap = Collections.synchronizedMap(hashMap);//ConcurrentModificationException
        final Map<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();//ConcurrentModificationException

        for (int i = 0; i < 100; i++)
        {
            cowList.add(i);
            list.add(i);
            vector.add(i);
            hashMap.put(i, i);
            ccHashMap.put(i, i);
            treeMap.put(i, i);
        }

        // query thread
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (Integer i : treeMap.keySet()) {
                    System.out.println(i);
                }
            }
        });
        // modify thread
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (Integer i : treeMap.keySet()) {
                    treeMap.remove(i);
                }
            }
        });
        
        t2.start();
        t1.start();
    }

}