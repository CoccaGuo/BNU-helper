package ui.plugin.movie.Scenes;


import org.apache.commons.io.FileUtils;
import util.control.Alerter;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownFileUtil {

    private static String rootPath = "532movie\\bin\\downloading\\cache";
    private static String outputPath = "532movie\\output";

    public static void download(String indexPath,String name,String path) throws InterruptedException {
        if( path != null){
            outputPath = path+"\\532movie\\output";
            rootPath = path+"\\532movie\\bin\\downloading\\cache";
        }

        try {
            File rp = new File(rootPath);
            if(!rp.exists())rp.mkdirs();
            FileUtils.cleanDirectory(rp);
        } catch (IOException e) {

        }
        String prePath = indexPath.substring(0,indexPath.lastIndexOf("/")+1);
        //System.out.println(prePath);
        //下载索引文件
        String indexStr = getIndexFile(indexPath);
        //解析索引文件
        List videoUrlList = null;
        try {
            videoUrlList = analysisIndex(indexStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成文件下载目录
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String fileRootPath = rootPath+File.separator+uuid;
        File fileDir = new File(fileRootPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        //下载视频片段，分成50个线程切片下载
        HashMap keyFileMap = new HashMap();
        int downForThreadCount = videoUrlList.size()/50;
        for(int i=0;i<videoUrlList.size();i+=downForThreadCount){
            int end = i+downForThreadCount-1;
            if(end>videoUrlList.size()){
                end = videoUrlList.size()-1;
            }
            new DownFileUtil().new downLoadNode(videoUrlList,i,end,keyFileMap,prePath,fileRootPath).start();
        }
        //等待下载
        int videoSize = videoUrlList.size();
        while (keyFileMap.size()<videoSize){
            int fileMapSize = keyFileMap.size();
            DLFrame.info = "《"+name+"》下载进度:"+fileMapSize*100/videoSize+"% ( "+fileMapSize+"/"+videoSize+" )";
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        DLFrame.info = "请稍等...";
        //合成视频片段
        composeFile(fileRootPath+File.separator+uuid+".mp4",keyFileMap);
        try {
            String foldName = name.split("_")[0];
            FileUtils.copyFile(new File(fileRootPath+File.separator+uuid+".mp4"),new File(outputPath+"/"+foldName+"/"+name+".mp4"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DLFrame.info ="qqq"+name+"下载完成。保存在"+outputPath+"目录下。";
        Alerter alerter = new Alerter(Alerter.SYSTEM_CALL,null);
        alerter.setRoastTime("5");
        alerter.alert(name+"下载完成。保存在"+outputPath+"目录下。");
        System.out.println(name+"下载完成。保存在"+outputPath+"目录下。");



    }


    /**
     * 视频片段合成
     * @param fileOutPath
     * @param keyFileMap
     */
    public static void composeFile(String fileOutPath,HashMap<Integer,String> keyFileMap){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileOutPath));
            byte[] bytes = new byte[1024];
            int length = 0;
            for(int i=0;i<keyFileMap.size();i++){
                String nodePath = keyFileMap.get(i);
                File file = new File(nodePath);
                if(!file.exists())
                    continue;
                FileInputStream fis = new FileInputStream(file);
                while ((length = fis.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, length);
                }
            }
        }catch (Exception e){

        }finally {
//            try {
//                FileUtils.copyFileToDirectory(new File(fileOutPath),new File(outputPath));
//                FileUtils.cleanDirectory(new File(rootPath));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }




    public static List analysisIndex(String content) throws IOException {
        Pattern pattern = Pattern.compile(".*ts");
        Matcher ma = pattern.matcher(content);

        List<String> list = new ArrayList<String>();

        while(ma.find()){
            String s = ma.group();
            s = s.substring(s.lastIndexOf("/")+1);
            list.add(s);
            //System.out.println(s);
        }
        return list;
    }


    class downLoadNode extends Thread{
        private List<String> list ;
        private  int start;
        private  int end;
        public  HashMap keyFileMap ;
        private  String preUrlPath ;
        private  String fileRootPath ;

        public  downLoadNode(List list,int start,int end,HashMap keyFileMap,String preUrlPath,String fileRootPath){
            this.list = list;
            this.end = end;
            this.start = start;
            this.keyFileMap = keyFileMap;
            this.preUrlPath = preUrlPath;
            this.fileRootPath = fileRootPath;
        }
        @Override
        public void run(){
            try{
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                for( int i = start;i<=end;i++){
                    String urlpath = list.get(i);
                    URL url = new URL(preUrlPath+urlpath);
                    //下在资源
                    DataInputStream dataInputStream = new DataInputStream(url.openStream());
                    String fileOutPath = fileRootPath+File.separator+urlpath;
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(fileOutPath));
                    byte[] bytes = new byte[1024];
                    int length = 0;
                    while ((length = dataInputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, length);
                    }
                    dataInputStream.close();
                    keyFileMap.put(i,fileOutPath);
                }
               // System.out.println("第"+start/(end-start)+"组完成，"+"开始位置"+start+",结束位置"+end);
            }catch (Exception e){
            }
        }
    }

    public static String getIndexFile(String urlpath){
        try{
            URL url = new URL(urlpath);
            //下在资源
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
            String content = "" ;
            String line;
            while ((line = in.readLine()) != null) {
                content += line + "\n";
            }
            in.close();
            //System.out.println(content);
            return content;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}