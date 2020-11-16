public class FolderAction {
    /*
     * Folder Action will check if the dirctory where the Jar file is
     *  1) running is under a APP package
     *  2) or is directly on the System's file system
     *
     * If it is running under a APP package, the inputdirectory will be ends with "/Contents/Java"
     * /Users/Chris/Desktop/Project/GuiProject/Jar2App/Jar2APP.app/Contents/Java
     * If it is running under System's file system, inputdirectory will be:
     * /Users/Chris/Desktop/Project/GuiProject/Jar2App/
     *
     * In either case, we return the directory of where the Jar2APP located.
     */


    public static String VerifyPath(String inputPath) {

        // seperate the input Sting by "/"
        String seperator = "/";
        String[] temp = inputPath.split(seperator);
        int myindex1 = temp.length - 1;
        if (temp[myindex1].equals("Java")) {
//            if it is inside of a package, end with Java ,use regular expression to return
//            the directory where the Jar2App.app file is
            int myindex2 = temp.length - 2;
            int myindex3 = temp.length - 3;
            String myregEx = "/" + temp[myindex3] + "/" + temp[myindex2] + "/" + temp[myindex1] + "$";
            String nst = inputPath.replaceFirst(myregEx, "");
            return nst;
        } else {
            return inputPath;
        }

    }

    public static void main(String[] args) {
        String test = "/Users/Chris/Desktop/Project/GuiProject/Jar2App/Jar2APP.app/Contents/Java";
        System.out.println(VerifyPath(test));

    }
}
