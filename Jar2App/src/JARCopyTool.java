import java.io.*;

public class JARCopyTool {
    /**
     * 复制一个文件到另外的位置
     * <p>
     * 注意，对于inputStream 来说，要用 ClassName.class.getResourceAsSteam
     * 而不是 getClass().getResourceAsSteeam!!! 这个真是太难了！！！
     */
    private static InputStream is;

    public static synchronized void copyFileFromJar(String inputPath, String outputPath) {
        File infile = new File(inputPath);

        try {

            InputStream is = JARCopyTool.class.getResourceAsStream(infile.toString()); // get from Jar file


            OutputStream os = new FileOutputStream(outputPath + "/" + infile.getName());
            byte[] buffer = new byte[4096];
            int length;
            while ((length = is.read(buffer)) > 0)
                os.write(buffer, 0, length);
            os.close();
            is.close();
        } catch (IOException exx) {
            System.out.println(exx);
        }
    }

    public static synchronized void copyFileFromJarExec(String inputPath, String outputPath) {
        File infile = new File(inputPath);

        try {

            InputStream is = JARCopyTool.class.getResourceAsStream(infile.toString()); // get from Jar file


            OutputStream os = new FileOutputStream(outputPath + "/" + infile.getName());
            byte[] buffer = new byte[4096];
            int length;
            while ((length = is.read(buffer)) > 0)
                os.write(buffer, 0, length);
            os.close();
            is.close();
            // Chmod +x 保证文件是 linux 可执行文件
            File outfile = new File(outputPath + "/" + infile.getName());
            outfile.setExecutable(true, true);

        } catch (IOException exx) {
            System.out.println(exx);
        }
    }

    public static synchronized void copyFileFromFileChooser(String inputPath, String outputPath) {
        File infile = new File(inputPath);

        try {

            InputStream is = new FileInputStream(infile); // get from filerchooser


            OutputStream os = new FileOutputStream(outputPath + "/" + infile.getName());
            byte[] buffer = new byte[4096];
            int length;
            while ((length = is.read(buffer)) > 0)
                os.write(buffer, 0, length);
            os.close();
            is.close();
        } catch (IOException exx) {
            System.out.println(exx);
        }
    }

    public static synchronized void copyIconsFromFileChooser(String inputPath, String outputPath) {
        File infile = new File(inputPath);

        try {

            InputStream is = new FileInputStream(infile); // get from filerchooser


            OutputStream os = new FileOutputStream(outputPath + "/app.icns");
            byte[] buffer = new byte[4096];
            int length;
            while ((length = is.read(buffer)) > 0)
                os.write(buffer, 0, length);
            os.close();
            is.close();
        } catch (IOException exx) {
            System.out.println(exx);
        }
    }

    public static void main(String[] args) {
        JARCopyTool.copyFileFromJarExec("/picture/app.icns", System.getProperty("user.dir"));
//        JARCopyTool.copyPicture("/picture/pig.png", System.getProperty("user.dir"));
//        JARCopyTool.copyFile("/Users/Chris/Desktop/Project/GuiProject/Jar2App/untitled.jar", "/Users/Chris/Desktop/Project/GuiProject/Jar2App/MYAPP/Contents/Java");
    }
}
