/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.apache.hadoop.fs.Path
 *  org.apache.hadoop.io.BytesWritable
 *  org.apache.hadoop.io.NullWritable
 *  org.apache.hadoop.mapreduce.InputSplit
 *  org.apache.hadoop.mapreduce.JobContext
 *  org.apache.hadoop.mapreduce.RecordReader
 *  org.apache.hadoop.mapreduce.TaskAttemptContext
 *  org.apache.hadoop.mapreduce.lib.input.FileInputFormat
 */
package sequence;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;


public class FullFileInputFormat
extends FileInputFormat<NullWritable, BytesWritable> {
    protected boolean isSplitable(JobContext context, Path file) {
        return false;
    }

    public RecordReader<NullWritable, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        FullFileRecordReader reader = new FullFileRecordReader();
        reader.initialize(split, context);
        return reader;
    }
}

