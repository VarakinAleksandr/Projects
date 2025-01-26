import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class Program {
    public static void main(String[] args) {
   //     String puthRead = "C:\\Work\\Programming\\Study";
        String puthRead = ".";
        System.out.println(Arrays.toString(readNames(puthRead)));
        writeFile ("file.txt",puthRead);

    }
    public static String[] readNames(String puthRead) {
        File file = new File(puthRead);
        String[] names = new String[0];
        if (file.isFile()){
            System.out.println("Файл не является папкой");
        }else {
            File[] fileArray = file.listFiles();
            names = new String[fileArray.length];
            for (int i = 0; i < fileArray.length; i++) {
                names[i] = fileArray[i].getName();
            }
        }
        System.out.println(names.length);
        return names;
    }

    public static void writeFile(String puthWrite, String puthRead) {
        try {
            FileWriter fw =new FileWriter(puthWrite);
            String[] names = readNames(puthRead);
            for (int i = 0; i < names.length; i++) {
                fw.write(names[i] + "\n");
            }
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
