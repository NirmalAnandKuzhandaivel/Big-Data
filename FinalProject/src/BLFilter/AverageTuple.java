/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLFilter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

/**
 *
 * @author nirmal
 */
public class AverageTuple implements Writable{
    
    private int count;
    private double tip;
    
    public AverageTuple(int c,double d){
        this.count=c;
        this.tip=d;
    }

    public AverageTuple() {
        count=0;
        tip=0;
       
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

   
    
    

    @Override
    public void write(DataOutput d) throws IOException {
        d.writeDouble(tip);
        d.writeInt(count);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        
        count=di.readInt();
        tip=di.readDouble();
        
    }
    
    
    
}
