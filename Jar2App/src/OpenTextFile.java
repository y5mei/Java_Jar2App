import javax.swing.*;
import java.io.File;

public class OpenTextFile extends JFrame {


    public static File start() {

        String dir = System.getProperty("user.dir");
        dir = FolderAction.VerifyPath(dir);
        File directory = new File(dir);
        final JFileChooser fc = new JFileChooser(directory);//创建文件选择对话框
        fc.setVisible(true);
        int returnValue = fc.showOpenDialog(null);//打开文件选择对话框
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            //判断用户是否选择了文件
            File file = fc.getSelectedFile();    //获得文件对象
            return file;
        } else {
            return null;
        }


    }

    public static void view() {
        JFrame frame = new JFrame();

        JFileChooser fc = new JFileChooser();


        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        fc.setFileFilter(new FileFilter() {
//
//            @Override
//            public boolean accept(File f) {
//                return f.isDirectory();
//            }
//
//            @Override
//            public String getDescription() {
//                return "Any folder";
//            }
//
//        });

        fc.setDialogType(JFileChooser.SAVE_DIALOG);
        fc.setApproveButtonText("Select");

        frame.getContentPane().add(fc);

        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        OpenTextFile gui = new OpenTextFile();
//        gui.start();
        gui.view();
    }

}

