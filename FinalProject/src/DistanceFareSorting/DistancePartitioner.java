/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistanceFareSorting;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 *
 * @author nirmal
 */
public class DistancePartitioner extends Partitioner<DistanceFare,NullWritable>{

    @Override
    public int getPartition(DistanceFare key, NullWritable value, int i) {
         //To change body of generated methods, choose Tools | Templates.
         return  (key.getDistance() % i);
    }
    
}
