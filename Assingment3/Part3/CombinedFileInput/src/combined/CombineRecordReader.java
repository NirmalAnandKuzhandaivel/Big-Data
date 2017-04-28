
package combined;

/**
 *
 * @author nirmal
 */

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

public class CombineRecordReader
extends RecordReader<LongWritable, Text> {
    private LineRecordReader lineRecordReader = new LineRecordReader();

    public CombineRecordReader(CombineFileSplit split, TaskAttemptContext context, Integer index) throws IOException {
        FileSplit fileSplit = new FileSplit(split.getPath(index.intValue()), split.getOffset(index.intValue()), split.getLength(index.intValue()), split.getLocations());
        this.lineRecordReader.initialize((InputSplit)fileSplit, context);
    }

    public void initialize(InputSplit is, TaskAttemptContext tac) throws IOException, InterruptedException {
    }

    public boolean nextKeyValue() throws IOException, InterruptedException {
        return this.lineRecordReader.nextKeyValue();
    }

    public LongWritable getCurrentKey() throws IOException, InterruptedException {
        return this.lineRecordReader.getCurrentKey();
    }

    public Text getCurrentValue() throws IOException, InterruptedException {
        return this.lineRecordReader.getCurrentValue();
    }

    public float getProgress() throws IOException, InterruptedException {
        return this.lineRecordReader.getProgress();
    }

    public void close() throws IOException {
        this.lineRecordReader.close();
    }
}

