/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PeakTripTime;

import java.io.EOFException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author nirmal
 */
public class PeakTripTimeReducer extends Reducer<Text,PTTWritable,Text,PTTWritable>{

    private PTTWritable pttw=new PTTWritable();
    
    @Override
    protected void reduce(Text key, Iterable<PTTWritable> values, Context context) throws IOException, InterruptedException ,EOFException{
        
        HashMap<String,Integer> ptHash=new HashMap<String,Integer>();
        for(PTTWritable ptt:values){
            if(ptHash.containsKey(ptt.getTime())){
                ptHash.put(ptt.getTime(), ptHash.get(ptt.getTime()) + 1);
            }else{
                ptHash.put(ptt.getTime(), ptt.getCount());
            }
        }
        
        List list = new LinkedList(ptHash.entrySet());
        
       // Defined Custom Comparator here
       Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue()) * -1;
            }
       });

       // Here I am copying the sorted list in HashMap
       // using LinkedHashMap to preserve the insertion order
       HashMap sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       }
       
       String str=(String) sortedHashMap.keySet().iterator().next();
       int val=(int) sortedHashMap.get(str);
       pttw.setTime(str);
       pttw.setCount(val);
       
       context.write(key, pttw);
        
    }
    
    
    
}
