import java.io.File;
import java.nio.file.Files;
import java.util.*;


class FileSorter implements Comparable<FileSorter>{

    String fileName;
    long creationTimeStamp;


    FileSorter(String fileName,long creationTimeStamp){
        this.fileName = fileName;
        this.creationTimeStamp = creationTimeStamp;
    }

    @Override
    public String toString() {
        return this.fileName +" "+this.creationTimeStamp;
    }

    @Override
    public int compareTo(FileSorter obj) {
        return (int) (this.creationTimeStamp - ((FileSorter)obj).creationTimeStamp);
    }
}

public class FileOperations {
    public static void main(String[] args) {
        try {

            String dir = "C:\\Users\\IN0213\\Desktop\\archive";

            File[] files = new File(dir).listFiles();

            TreeMap<Long,String> map=new TreeMap<Long,String>();

            ArrayList <FileSorter> items = new ArrayList<FileSorter>() ;


            items.add(new FileSorter("a",1));
            items.add(new FileSorter("b",2));
            items.add(new FileSorter("c",1));
            items.add(new FileSorter("d",3));
            items.add(new FileSorter("e",3));
            items.add(new FileSorter("f",2));
            items.add(new FileSorter("g",5));
            items.add(new FileSorter("h",5));
            items.add(new FileSorter("i",6));
            items.add(new FileSorter("j",1));




//            for(File file:files){
////                System.out.println(file);
////                System.out.println(new Date(file.lastModified()));
//
////                map.put(file.lastModified(),file.toString());
////                String f
//
//                items.add(new FileSorter(file.getName(),file.lastModified()));
//
//            }

            Collections.sort(items);


            for(FileSorter item: items){
                System.out.println(item.toString());
            }


//
//            for(Map.Entry m:map.entrySet()){
//                System.out.println(m.getKey()+" "+m.getValue());
//            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
