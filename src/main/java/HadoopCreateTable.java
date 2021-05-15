//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.BlockLocation;
//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FSDataOutputStream;
//import org.apache.hadoop.fs.FileStatus;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//
//public class HadoopCreateTable {
//    public static void main(String[] args) {
//
//
//        JavaClient jc = new JavaClient();
//        Configuration config = new Configuration();
//        config.addResource(new Path("/usr/local/hadoop-2.7.1/etc/hadoop/core-site.xml"));
//        config.addResource(new Path("/usr/local/hadoop-2.7.1/etc/hadoop/hdfs-site.xml"));
//
//        config.set("fs.hdfs.impl",
//                org.apache.hadoop.hdfs.DistributedFileSystem.class.getName()
//        );
//        config.set("fs.file.impl",
//                org.apache.hadoop.fs.LocalFileSystem.class.getName()
//        );
//        FileSystem dfs = FileSystem.get(config);
//        String dirName = "TestDirectory";
//        System.out.println(dfs.getWorkingDirectory() +" this is from /n/n");
//        Path src = new Path(dfs.getWorkingDirectory()+"/"+dirName);
//
//        dfs.mkdirs(src);
//
//
//
//        System.out.println("created dir");
//
//        dfs.close();
//
//    }
//}
