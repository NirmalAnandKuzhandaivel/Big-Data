
package combined;

/**
 *
 * @author nirmal
 */

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CombineFileInput
extends Configured
implements Tool {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, Exception {
        int exitcode = ToolRunner.run((Tool)new CombineFileInput(), (String[])args);
        System.exit(exitcode);
    }

    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("mapreduce.max.split.size", "1048");
        Job job = Job.getInstance((Configuration)conf, (String)"Combine small files");
        job.setJarByClass(CombineFileInput.class);
        job.setMapperClass(CombineFileInputMapper.class);
        job.setReducerClass(CombineFileInputReducer.class);
        job.setInputFormatClass(CombineInput.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath((Job)job, (Path)new Path(args[0]));
        FileOutputFormat.setOutputPath((Job)job, (Path)new Path(args[1]));
        return job.waitForCompletion(true) ? 0 : 1;
    }
}

