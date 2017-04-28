/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 *
 * @author nirmal
 */
public class PatternPartitioner extends Partitioner<IntWritable,Text>{

    @Override
    public int getPartition(IntWritable key, Text value, int i) {
        return (key.get()%i);
    }
    
}
