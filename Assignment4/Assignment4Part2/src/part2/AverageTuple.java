/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

/**
 *
 * @author nirmal
 */
public class AverageTuple implements Writable,WritableComparable<AverageTuple>{
    
    private IntWritable count;
    private FloatWritable avg;
    
    public AverageTuple(IntWritable c,FloatWritable a){
        this.count=c;
        this.avg=a;
        
    }
    
    public AverageTuple(){
        count=new IntWritable();
        avg=new FloatWritable();
        
    }

    public IntWritable getCount() {
        return count;
    }

    public void setCount(IntWritable count) {
        this.count = count;
    }

    public FloatWritable getAvg() {
        return avg;
    }

    public void setAvg(FloatWritable avg) {
        this.avg = avg;
    }

    
    
    
    

    @Override
    public void write(DataOutput d) throws IOException {
        count.write(d);
        avg.write(d);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        count.readFields(di);
        avg.readFields(di);
    }
    
    public String toString()
    {
        return (new StringBuilder().append(count).append(",").append(avg).toString());
                                    
    }

    @Override
    public int compareTo(AverageTuple o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
