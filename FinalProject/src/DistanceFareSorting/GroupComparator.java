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
public class GroupComparator extends WritableComparator{
    
    protected GroupComparator()
    {
        super(DistanceFare.class,true);
    }
    
    @Override
    public int compare(WritableComparable w1, WritableComparable w2){
        DistanceFare cw1 = (DistanceFare) w1;
        DistanceFare cw2 = (DistanceFare) w2;
        
        return cw1.getDistance().compareTo(cw2.getDistance());
    }
    
}
