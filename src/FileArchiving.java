import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileArchiving {
    private static byte[] buffer = new byte[4000096];
    public void archive(File file, File zipFile) throws IOException {
        try(ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(zipFile))){
            if(file.isDirectory()){
                for(File item: Objects.requireNonNull(file.listFiles())){
                    if(item.isFile()){
                        addFileToArchive(zip, item);
                    }
                    else{
                        addFolderToArchive(item, zip);
                    }
                    System.out.println("Archived" + " " + item.getName());
                }
            }
            else{
                addFileToArchive(zip, file);
                System.out.println("Archived" + " " + file.getName());
            }

            zip.closeEntry();

        }
    }

    private void addFolderToArchive(File folder, ZipOutputStream zipFile) throws IOException {
        for(File item: Objects.requireNonNull(folder.listFiles())){
            if(item.isDirectory()){
                addFolderToArchive(item, zipFile);
            }
            zipFile.putNextEntry(new ZipEntry(item.getName()));
            try(FileInputStream f = new FileInputStream(item)){
                while (f.available() > 0) {
                    int readCount = f.read(buffer);
                    zipFile.write(buffer, 0, readCount);
                }
                zipFile.closeEntry();
            }
        }
    }
    private static void addFileToArchive(ZipOutputStream zip, File file) throws IOException {
        try(FileInputStream f = new FileInputStream(file)){
            zip.putNextEntry(new ZipEntry(file.getName()));
            while (f.available() > 0) {
                int readCount = f.read(buffer);
                zip.write(buffer, 0, readCount);
            }
            zip.closeEntry();
        }
    }
}
