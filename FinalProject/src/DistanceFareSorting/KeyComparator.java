/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistanceFareSorting;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 *
 * @author nirmal
 */
public class KeyComparator extends WritableComparator{
    
    protected KeyComparator(){
        super(DistanceFare.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        
        DistanceFare df1 = (DistanceFare) a;
        DistanceFare df2 = (DistanceFare) b;
        int cmp = df1.getDistance().compareTo(df2.getDistance());
            if (cmp != 0) {
                return cmp; 
             }  
        return df1.getFare().compareTo(df2.getFare());
        
    }
    
    
    
}
