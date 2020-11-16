import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

// The remove class will delete a folder
public class remove {
    public static void remove(File dir) {
        File files[] = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                remove(files[i]);
            } else {
                files[i].delete();
            }
        }
        dir.delete();
    }

    public static void show(File dir) {
        File files[] = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }

    public static void copyFile(File from, File to) throws IOException {
        Files.copy(from.toPath(), to.toPath());
    }

}

