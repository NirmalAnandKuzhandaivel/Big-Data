/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.apache.hadoop.conf.Configuration
 *  org.apache.hadoop.fs.FSDataInputStream
 *  org.apache.hadoop.fs.FileSystem
 *  org.apache.hadoop.fs.Path
 *  org.apache.hadoop.io.BytesWritable
 *  org.apache.hadoop.io.IOUtils
 *  org.apache.hadoop.io.NullWritable
 *  org.apache.hadoop.mapreduce.InputSplit
 *  org.apache.hadoop.mapreduce.RecordReader
 *  org.apache.hadoop.mapreduce.TaskAttemptContext
 *  org.apache.hadoop.mapreduce.lib.input.FileSplit
 */
package sequence;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class FullFileRecordReader
extends RecordReader<NullWritable, BytesWritable> {
    private FileSplit fileSplit;
    private Configuration conf;
    private BytesWritable value = new BytesWritable();
    private boolean processed = false;

    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        this.fileSplit = (FileSplit)split;
        this.conf = context.getConfiguration();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (!this.processed) {
            byte[] contents = new byte[(int)this.fileSplit.getLength()];
            Path file = this.fileSplit.getPath();
            FileSystem fs = file.getFileSystem(this.conf);
            FSDataInputStream in = null;
            try {
                in = fs.open(file);
                IOUtils.readFully((InputStream)in, (byte[])contents, (int)0, (int)contents.length);
                this.value.set(contents, 0, contents.length);
            }
            finally {
                IOUtils.closeStream((Closeable)in);
            }
            this.processed = true;
            return true;
        }
        return false;
    }

    public NullWritable getCurrentKey() throws IOException, InterruptedException {
        return NullWritable.get();
    }

    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return this.value;
    }

    public float getProgress() throws IOException {
        return this.processed ? 1.0f : 0.0f;
    }

    public void close() throws IOException {
    }
}

