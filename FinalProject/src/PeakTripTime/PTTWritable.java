/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PeakTripTime;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

/**
 *
 * @author nirmal
 */
public class PTTWritable implements Writable{
    
    private String time;
    private int count;
    
    public PTTWritable(){
        time=new String();
        count=0;
    }

    public PTTWritable(String time, int count) {
        this.time = time;
        this.count = count;
    }
    
    

    public String getTime() {
        return time;
    }

    public int getCount() {
        return count;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCount(int count) {
        this.count = count;
    }
   
    

    @Override
    public String toString() {
        return (new StringBuilder().append("Peak Hour:").append(time)
                .append("-").append("Total Rides").append(count).toString());
    }

    @Override
    public void write(DataOutput d) throws IOException {
        WritableUtils.writeString(d, time);
      WritableUtils.writeVInt(d, count);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        this.time = WritableUtils.readString(di);
       this.count = WritableUtils.readVInt(di); 
    }


    
    
    
    


    
    
    
    
}
