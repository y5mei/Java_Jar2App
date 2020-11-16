import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class FileWriter {
    public static void writeto(File is, String writetodir) {
        String line;

        try {

            Scanner scan = new Scanner(is);
//            PrintStream commentsFile = new PrintStream(writetodir);
            PrintStream codeFile = new PrintStream(writetodir);

            while (scan.hasNextLine()) {
                line = scan.nextLine();

//                // if line starts with two slashes
//                if (line.indexOf("//") == 0)
//                    commentsFile.println(line);
//
//                    //if there are slashes elsewhere in a line
//                else if (line.indexOf("//") > 0) {
//                    // write whole line to code file
//                    codeFile.println(line);
//
//                    // write comment part of line to comments file
//                    commentsFile.println(line.substring(line.indexOf("//")));
//                } else

                // write line to code file
                codeFile.println(line);
            }

            // close files
            scan.close();
//            commentsFile.close();
            codeFile.close();
        } catch (IOException e) {
            System.out.println("*** I/O Error ***\n" + e);
        }
    }
}
