import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;


public class GuiInterface extends JFrame implements ActionListener {
    //Define Constant
    private JFrame frame;
    private JPanel plotPanle;
    private JPanel textPanle;
    private JPanel plot;
    private JPanel buttonPanle;
    private JButton readButton, closeButton, clearButton, makeDir;
    private JPanel acknowledgePanle;
    private JLabel hyperlink;
    private JTextArea ta_showText;
    private JTextArea ta_showProperty;    //定义显示文件内容的文本域
    private JScrollPane scroller;
    private String my_plotName; // The name of the CSV File
    private String myJavaFilePath; // Where to put the Jar file in the content folder
    private File myJarFile; // this is the Jar file I want to use in the app application
    private File myIconFile; // this is the customer defined icon
    private File myMacOSFile; // this is the STUB file I want to use in the app application
    private String myMainClassName; // This is the main Class name of the Jar file;
    private String myBoundleName; // The name of the app file
    private String userdir = FolderAction.VerifyPath(System.getProperty("user.dir"));
    private String path2icons; // this is the path to the user selected icons


    public void start() {
        frame = new JFrame("Please choose the Jar file you want to convert to APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1) declair the objects in the frame
        textPanle = new JPanel();
        plotPanle = new JPanel();
        buttonPanle = new JPanel();
        acknowledgePanle = new JPanel();
        frame.add(textPanle, BorderLayout.NORTH);
        frame.add(buttonPanle, BorderLayout.CENTER);
        frame.add(plotPanle, BorderLayout.SOUTH);


        //2) declair the objects in each panel
        // 先是 文本框
        ta_showText = new JTextArea(15, 50);
        scroller = new JScrollPane(ta_showText);
        //设置滚动条
        scroller.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textPanle.add(scroller);

        // 然后是图片框
        JLabel label = new JLabel("Acknowledgement");
        label.setFont(new Font("Serif", Font.PLAIN, 25));
        //JLabel label = new JLabel("Current Working Directory = " + System.getProperty("user.dir") + "\n");
        hyperlink = new JLabel("Default icons are from this great Website: https://www.flaticon.com/free-icons/picture/" + "\n");
        hyperlink.setForeground(Color.BLUE.darker());
        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hyperlink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI("https://www.flaticon.com/free-icons/picture/"));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });
        JLabel hyperlink2 = new JLabel("Use this website to convert PNG to Apple Icons: https://cloudconvert.com/png-to-icns/" + "\n");
        hyperlink2.setForeground(Color.BLUE.darker());
        hyperlink2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hyperlink2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI("https://cloudconvert.com/png-to-icns/"));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });
        JLabel hyperlink3 = new JLabel("The BASH Script is from https://github.com/tofi86/universalJavaApplicationStub" + "\n");
        hyperlink3.setForeground(Color.BLUE.darker());
        hyperlink3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hyperlink3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI("https://github.com/tofi86/universalJavaApplicationStub"));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

        });


        System.out.println(System.getProperty("user.dir"));
        plotPanle.setLayout(new GridLayout(4, 1));
        plotPanle.add(label, BorderLayout.NORTH);
        plotPanle.add(hyperlink, BorderLayout.CENTER);
        plotPanle.add(hyperlink2, BorderLayout.SOUTH);
        plotPanle.add(hyperlink3, BorderLayout.SOUTH);


        // 最后是按钮框
        buttonPanle.setLayout(new GridLayout(1, 4)); // 按钮个数 一行 4 列
        JButton mybuttonzhu = new JButton();
        JButton mybuttonniu = new JButton();
        JButton mybuttonxiong = new JButton();
        makeDir = new JButton("Make Dir");

        try {
            // 不知道为什么，给 button load 图片必须要用local variable, 否则找不到图片路径
            Image iconxiong = ImageIO.read(getClass().getResource("/picture/xiong.png"));
            Image iconzhu = ImageIO.read(getClass().getResource("/picture/zhu.png"));
            Image iconniu = ImageIO.read(getClass().getResource("/picture/niu.png"));

            mybuttonxiong.setIcon(new ImageIcon(iconxiong));
            mybuttonxiong.setText("Close");
            mybuttonzhu.setIcon(new ImageIcon(iconzhu));
            mybuttonzhu.setText("Choose Icon");
            mybuttonniu.setIcon(new ImageIcon(iconniu));
            mybuttonniu.setText("Choose Jar");


            buttonPanle.add(mybuttonxiong);
            buttonPanle.add(mybuttonzhu);
            buttonPanle.add(mybuttonniu);
            //buttonPanle.add(makeDir);

            readButton = mybuttonniu;
            closeButton = mybuttonxiong;
            clearButton = mybuttonzhu;

            readButton.addActionListener(this);
            closeButton.addActionListener(this);
            clearButton.addActionListener(this);
            makeDir.addActionListener(this);
        } catch (Exception ex) {
            System.out.println("Can not find a picture");
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.out.println(ex);
        }

        // 3) End, now pack and show everyting
        frame.pack();
        frame.setVisible(true);
        ta_showText.setText("First, Please click to select a icns file for your App" + "\n"
                + "(If you skip this step, a default icon will be used for your APP)"
                + "\n" + "Then, please select your Jar file" + "\n" + "\n" + "The Max. supporting dimension for the icons file is 512x512.");


    }


    // 设计按钮的动作
    public void actionPerformed(ActionEvent e) {
        Object buttonclicked = e.getSource();
        if (buttonclicked == closeButton) {
            System.exit(0);
        } else if (buttonclicked == clearButton) {
            myIconFile = OpenTextFile.start();


            //Clear the text area
            ta_showText.setText("");
            ta_showText.setText("The Icon you choosed is: " + myIconFile.getName() + "\n" + "Please select your Jar File now! ");
            System.out.println("clearbutton");


        } else if (buttonclicked == makeDir) {


        } else if (buttonclicked == readButton) {
            ta_showText.setText("");

            myJarFile = OpenTextFile.start();
            // 去掉文件名里的extension
            myBoundleName = myJarFile.getName().replaceFirst("[.][^.]+$", "");
//            System.out.println(myBoundleName);
            // 拿到 JAR 文件的入口class
            JARDecompressionTool.decompress(myJarFile.toString(), System.getProperty("user.dir") + "/decomp/");
            System.out.println(JARDecompressionTool.MANIFESTFile);
            File file = new File(JARDecompressionTool.MANIFESTFile);

            if (file != null) {
                //获得文件的绝对路径
                ta_showText.append("The Absolute Path of the File is：" + file.getAbsolutePath() + "\n");
                //是否为隐藏文件
                ta_showText.append("Is this File a Hidden File？" + file.isHidden() + "\n");
                String filename = file.getName();
                ta_showText.append("The file name is: " + filename + "\n");

                FileReader reader;    //声明字符流
                BufferedReader in;    //声明字符缓冲流
                try {
                    reader = new FileReader(file);    //创建字符流
                    in = new BufferedReader(reader);    //创建字符缓冲流
                    String info = in.readLine();    //从文件中读取一行信息
//                    info = in.readLine();    //从文件中读取一行信息
                    while (info != null) {
                        //判断是否读到内容
                        ta_showText.append(info + "\n");    //把读到的信息追加到文本域中
                        if (info.startsWith("Main-Class: ")) {
                            myMainClassName = info.substring(12);
                            ta_showText.append(myMainClassName);
                        }

                        info = in.readLine();    //继续读下一行信息
                    }
                    in.close();    //关闭字符缓冲流
                    reader.close();    //关闭字符流
                } catch (Exception ex) {
                    ex.printStackTrace();    //输出栈踪迹
                }
            }


            // 开始做出 APP的 文件结构
            String path = userdir + "/" + myBoundleName;
            // if exist a old app folder, delete it
            String appPath = path + ".app";
            File oldfile = new File(appPath);
            oldfile.mkdir();
            remove.remove(oldfile);
            // Now Create the new folder and make the folder structure
            File newfile = new File(path);
            newfile.mkdir();

            String contentPath = path + "/Contents";
            // Creating a new file object
            File contentfile = new File(contentPath);
            contentfile.mkdir();

            String JavaPath = contentPath + "/Java";
            String myJavaFilePath = JavaPath;
            String MacOSPath = contentPath + "/MacOS";
            String myMacOSPath = MacOSPath;
            String ResourcesPath = contentPath + "/Resources";
            String myResourcePath = ResourcesPath;
            String MyInfoPath = contentPath + "/info.plist";


            File javafile = new File(JavaPath);
            File MacOSfile = new File(MacOSPath);
            File Resourcesfile = new File(ResourcesPath);
            File myInfoFile = new File(MyInfoPath);


            javafile.mkdir();
            MacOSfile.mkdir();
            Resourcesfile.mkdir();
            // Move the Jar File to the Content Folder
            // This is to get a File from file chooser
            // and then Move it to somewhere on the hard disk
//            myJarFile = OpenTextFile.start();
            JARCopyTool.copyFileFromFileChooser(myJarFile.toString(), myJavaFilePath);
//            myJarFile.renameTo(new File(myJavaFilePath + "/" + myJarFile.getName()));


            // Move the STUB script file to the MacOS folder=====The code here is Copy a file from
            // A JAR FILE to hard disk
            JARCopyTool.copyFileFromJarExec("/picture/universalJavaApplicationStub", myMacOSPath);
            JARCopyTool.copyFileFromJarExec("/picture/app.icns", myResourcePath);
//            System.out.println(myIconFile.toString());
            if (myIconFile != null) {
                System.out.println(myIconFile.toString());
                JARCopyTool.copyIconsFromFileChooser(myIconFile.toString(), myResourcePath);
            }

            // Move the universalJavaApplicationStub file to the MacOS Folder
            //File Stub = new File();

            // 把文本框里的内容写到 Info.plist 里面去
            ta_showText.setText("");
//            String newStr = JARDecompressionTool.DecompressedFile.toString();
//            file = new File(newStr + "/picture/info.plist");

            int linecounter = 0;
            try {
                InputStream is = getClass().getResourceAsStream("/picture/info.plist"); // get from Jar file
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                while (reader.ready()) {
                    String line = reader.readLine();
//                    ta_showText.append(linecounter + "\n");    //把读到的信息追加到文本域中
                    if (linecounter == 21) {
                        line = "        <string>" + myMainClassName + "</string>";
                    }
                    if (linecounter == 15) {
                        line = "        <string>" + myBoundleName + "</string>";
                    }
                    ta_showText.append(line + "\n");    //把读到的信息追加到文本域中
                    linecounter += 1;
                    //        <string > GuiInterface </string > Line21 -> MainClassName
                    //        <string > MYAPP </string > Line15 -> BoundleName
                    //        <string > app </string ><!--relative to Contents/Resources-- > ->Line11CFBundleIconFile
                }
                is.close();
                reader.close();
            } catch (IOException exx) {
                System.out.println(exx);
            }

            try (PrintWriter out = new PrintWriter(MyInfoPath)) {
                out.println(ta_showText.getText());
            } catch (IOException ex) {
                System.out.println("can not create file!!!");
                System.out.println(ex);
            }


            // change the name of the folder to .app
//            System.out.println("I am going to rename the folder");
            newfile.renameTo(new File(appPath));
            remove.remove(JARDecompressionTool.DecompressedFile); //拿到main class name 之后 删除解压缩文件
            System.out.println(myMainClassName);

//            JARCopyTool.copyFileFromJar("/picture/universalJavaApplicationStub", System.getProperty("user.dir"));
//            File newnewfile = new File(System.getProperty("user.dir") + "/universalJavaApplicationStub");
//            newnewfile.setExecutable(true, true);

        }
    }

    public static void main(String[] args) {
        GuiInterface gui = new GuiInterface();
        gui.start();
    }

}
