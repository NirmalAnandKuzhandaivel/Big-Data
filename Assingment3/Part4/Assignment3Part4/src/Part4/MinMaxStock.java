/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

/**
 *
 * @author nirmal
 */
public class MinMaxStock implements Writable,WritableComparable<MinMaxStock>{
    
    private Text maxDate;
    private Text minDate;
    private IntWritable maxVolume;
    private IntWritable minVolume;
    private FloatWritable adjClose;
    
    public MinMaxStock(){
        maxVolume=new IntWritable();
        minVolume=new IntWritable();
        adjClose=new FloatWritable();
        maxDate=new Text();
        minDate=new Text();
    }
    public MinMaxStock(IntWritable a,IntWritable b,FloatWritable c,Text s1,Text s2){
        this.maxVolume=a;
        this.adjClose=c;
        this.minVolume=b;
        this.minDate=s1;
        this.maxDate=s2;
    }

    public IntWritable getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(IntWritable maxVolume) {
        this.maxVolume = maxVolume;
    }

    public IntWritable getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(IntWritable minVolume) {
        this.minVolume = minVolume;
    }

    public Text getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Text maxDate) {
        this.maxDate = maxDate;
    }

    public Text getMinDate() {
        return minDate;
    }

    public void setMinDate(Text minDate) {
        this.minDate = minDate;
    }
    
    public FloatWritable getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(FloatWritable adjClose) {
        this.adjClose = adjClose;
    }

    

    
    @Override
    public void write(DataOutput out) throws IOException {
       maxVolume.write(out);
       minVolume.write(out);
       adjClose.write(out);
       maxDate.write(out);
       minDate.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        maxVolume.readFields(in);
        minVolume.readFields(in);
        adjClose.readFields(in);
        maxDate.readFields(in);
        minDate.readFields(in);
          
    }
    
    public String toString()
    {
        return (new StringBuilder().append(maxDate).append(":").append(maxVolume).append(",").
                                    append(minDate).append(":").append(minVolume).append(",").
                                    append(adjClose).toString());
                                    
    }

    @Override
    public int compareTo(MinMaxStock o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
