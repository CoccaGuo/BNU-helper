package test;

import java.io.File;
import java.util.ArrayList;

public class JIYou {
    public static void main(String[] args) {
        ArrayList<File> files = new ArrayList<>();
        getAllFiles(new File("E:\\番剧\\[VCB-Studio] Sword Art Online\\[VCB-Studio] Sword Art Online II [Ma10p_1080p]"),files);
        for (File f:
             files) {
            if (f.getName().endsWith(".ass")) f.delete();
        }

    }

    private static void getAllFiles(File file, ArrayList<File> getFiles) {
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isFile())        //若是文件，直接打印
                getFiles.add(f);
        }
    }
}
