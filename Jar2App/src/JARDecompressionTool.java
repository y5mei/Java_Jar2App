import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JARDecompressionTool {
    /**
     * 解压并删除jar文件
     * <p>
     * Copied from https://blog.csdn.net/qbg19881206/article/details/8608244
     */

    public static String MANIFESTFile;
    public static File DecompressedFile;

    public static synchronized void decompress(String fileName, String outputPath) {

        if (!outputPath.endsWith(File.separator)) {
            outputPath += File.separator;
        }
        File dir = new File(outputPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        JarFile jf = null;
        try {
            jf = new JarFile(fileName);
            for (Enumeration<JarEntry> e = jf.entries(); e.hasMoreElements(); ) {
                JarEntry je = (JarEntry) e.nextElement();
                String outFileName = outputPath + je.getName();
                File f = new File(outFileName);

                // Get the String to the file that holds the Main Class Name
                MANIFESTFile = dir.toString() + "/META-INF/MANIFEST.MF";
                // Get the file of the decompressed file
                DecompressedFile = dir;
                if (je.isDirectory()) {
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                } else {
                    File pf = f.getParentFile();
                    if (!pf.exists()) {
                        pf.mkdirs();
                    }
                    InputStream in = jf.getInputStream(je);
                    OutputStream out = new BufferedOutputStream(
                            new FileOutputStream(f));
                    byte[] buffer = new byte[2048];
                    int nBytes = 0;
                    while ((nBytes = in.read(buffer)) > 0) {
                        out.write(buffer, 0, nBytes);
                    }
                    out.flush();
                    out.close();
                    in.close();
                }

            }
        } catch (Exception e) {
            System.out.println("decompress" + fileName + "has a error---" + e.getMessage());
        } finally {
            if (jf != null) {
                try {
                    jf.close();
                    File jar = new File(jf.getName());
                    if (jar.exists()) {
                        //jar.delete();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        decompress("/Users/Chris/Desktop/Project/GuiProject/Jar2App/src/Jar2App.jar", "/Users/Chris/Desktop/Project/GuiProject/Jar2App/src/output");
    }
}
