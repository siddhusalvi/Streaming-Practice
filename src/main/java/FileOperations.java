import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class FileOperations {
    public static void main(String[] args) {
        try {

            String dir = "src/main/scala/com/flutura/practice";

            File[] files = new File(dir).listFiles();

            TreeMap<Long,String> map=new TreeMap<Long,String>();

            for(File file:files){
                System.out.println(file);
                System.out.println(new Date(file.lastModified()));
                map.put(file.lastModified(),file.toString());
            }

            for(Map.Entry m:map.entrySet()){
                System.out.println(m.getKey()+" "+m.getValue());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
