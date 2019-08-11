package ui.plugin.movie.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ui.plugin.interfaces.p532Download;
import util.control.Errorer;

import java.io.IOException;

public class VideoItem {
    public int number; //532上每个影片都有自己的编号
    public Document video;
    public String name; //影片名称
    public String director;//导演
    public String screenwriter;//编剧
    public String actor;//主演
    public String region;//地区
    public String language;//语言
    public String epiTime;//片长
    public String showTime;//上映
    public String refreshTime;//更新
    public String tag;//标签 待补全
    public String score;//评分
    public String picUrl;//图片网址(绝对网址)
    public String[] gallerys;//剧照  待补全
    public String[] summary;//剧情简介
    public String summaryShort;
    public CommentItem[] commentItems;//评论
    public String episode;
    public int videoCount;
    public String scoreer;
    public String[] indexUrl;//播放真实网址（相对网址）
    public static final String INDEX = "http://532movie.bnu.edu.cn";
    public static final String HOST_IP = "http://172.16.215.40:5320/";

    public VideoItem(int idNumber){
        this(INDEX+"/movie/"+idNumber+".html");
    }

    public VideoItem(String videoUrl){ //传入单集影片主页的网址
        String[] cache = videoUrl.split("/");
        this.number = Integer.parseInt(cache[cache.length-1].split("\\.")[0]);
        this.video = null;
        try {
            video = Jsoup.connect(videoUrl).timeout(50000).get();
        } catch (IOException e) {
            e.printStackTrace();
            p532Download.label.setText("错误");
            Errorer.error(p532Download.index,e);
        }

        this.name = video.select("h3").get(0).text();
        Elements info = video.select(".unstyled").get(0).select("li");
        this.director = info.get(0).select("a").get(0).text().replaceAll(" ","/");
        this.screenwriter = info.get(1).select("a").get(0).text().replaceAll(" ","/");
        this.actor = info.get(2).select("a").text().replaceAll(" ","/"); //有些影片没有集数的概念。这里的标签实际上已经无效。
        this.region = info.get(3).text();
        this.language = info.get(4).text()                                                                                                                                                                                                                                                                                                                                                                                                ;
        this.episode = info.get(5).text();
        this.epiTime = info.get(6).text();
        this.showTime = info.get(7).text();
        try {
            this.refreshTime = info.get(8).text();
        }catch (Exception e){

        }
        this.tag = video.getElementById("tags").select("a").text();
        this.summaryShort = video.select(".summary").text();
        this.summary = this.summaryShort.split(" ");
        this.score = video.getElementById("Scorenum").text();
        this.scoreer = video.getElementById("Scoreer").text();
        this.picUrl = INDEX+video.getElementById("Gallery1").select("a").attr("href");
        this.gallerys = video.getElementById("Gallery2").select("a").attr("href").split(" ");
        Elements labels = video.select(".well").get(0).select("li");
        String getMoviePageUrl = INDEX + labels.get(0).select("a").get(0).attr("href");
        Document playpage = null;
        try {
            playpage = Jsoup.connect(getMoviePageUrl).timeout(50000).get();
        } catch (Exception e) {
            e.printStackTrace();
            Errorer.error(p532Download.index,e);
        }
        Elements scripts = playpage.getElementsByTag("script");
        String trueUrlList = "";
        for (Element script :
                scripts) {
            if (script.toString().contains("var $playlist=")) {
                trueUrlList = script.toString().split("playlist=\"")[1];
                trueUrlList = trueUrlList.split("\";")[0];
            }
        }
        this.indexUrl = trueUrlList.split("\\+\\+\\+");
        this.videoCount = this.indexUrl.length;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public String getActor() {
        return actor;
    }

    public String getRegion() {
        return region;
    }

    public String getLanguage() {
        return language;
    }

    public String getEpiTime() {
        return epiTime;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public String getTag() {
        return tag;
    }

    public String getScore() {
        return score;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String[] getGallerys() {
        return gallerys;
    }

    public String[] getSummary() {
        return summary;
    }

    public CommentItem[] getCommentItems() {
        return commentItems;
    }

    public String getEpisode() {
        return episode;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public String getScoreer() {
        return scoreer;
    }

    public String[] getIndexUrl() {
        return indexUrl;
    }

    public String getSummaryShort() {
        return summaryShort;
    }

    public void setSummaryShort(String summaryShort) {
        this.summaryShort = summaryShort;
    }

    public static String getINDEX() {
        return INDEX;
    }

    public static String getHostIp() {
        return HOST_IP;
    }

    public static void main(String[] args) {
        VideoItem videoItem = new VideoItem(2833);
        //System.out.println(videoItem.showTime+videoItem.videoCount+videoItem.score+videoItem.actor);
        System.out.println(videoItem.showTime);
        System.out.println(videoItem.tag);
        System.out.println(videoItem.gallerys);
        System.out.println(videoItem.actor);
        System.out.println(videoItem.director);System.out.println(videoItem.epiTime);
        System.out.println(videoItem.episode);
        System.out.println(videoItem.language);
        System.out.println(videoItem.name);
        System.out.println(videoItem.picUrl);
        System.out.println(videoItem.refreshTime);
        System.out.println(videoItem.region);
        System.out.println(videoItem.videoCount);System.out.println(videoItem.getScreenwriter());
        System.out.println(videoItem.score);
        System.out.println(videoItem.scoreer);System.out.println(videoItem.indexUrl[1]);
        System.out.println(videoItem);
        System.out.println(videoItem);
        System.out.println(videoItem);



    }

}
