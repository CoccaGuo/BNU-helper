package ui.plugin.movie.scene;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ui.plugin.movie.util.VideoItem;

import java.io.IOException;

public class Banners {
    public String bannerImg;
    public String bannerName;
    public VideoItem videoItem;


    public Banners(){
        Document index = null;
        try {
            index = Jsoup.connect("http://532movie.bnu.edu.cn/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
         this.bannerImg = index.select(".item").select("a").select("img").attr("src");
         this.bannerName = index.select(".item").select("h3").text().split(" ")[0];
         this.videoItem = new VideoItem(VideoItem.getINDEX()+index.select(".item").select("a").attr("href"));
    }

//    public static void main(String[] args) {
//        Document index = null;
//        try {
//            index = Jsoup.connect("http://532movie.bnu.edu.cn/").get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(index.select(".item").select("img").attr("src"));
//        System.out.println(index.select(".item").select("h3").text().split(" ")[0]);
//        System.out.println(index.select(".item").select("a").attr("href"));
//
//    }

}
