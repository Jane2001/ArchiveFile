import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if(args.length < 2){
            System.out.println("Wrong number of arguments!");
        }
        final File file = new File(args[0]);
        if(!file.exists()){
            System.out.println("File is not exist: " + file);
        }
        final File zipFile = new File(args[1]);
        if(!zipFile.isFile() || !zipFile.exists()){
            System.out.println("Archive file does not exist: " + zipFile);
        }
        FileArchiving a = new FileArchiving();
        try {
            a.archive(file, zipFile);
        } catch (IOException e) {
            System.out.println("Failed with error: " + e);
        }
    }
}
