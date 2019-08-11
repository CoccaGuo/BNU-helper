package ui.plugin.movie.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Hot {
   public Set<String> urlList;


    public Hot(){
        urlList = new HashSet<>();
        Document index = null;
        try {
            index = Jsoup.connect("http://532movie.bnu.edu.cn/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
       Elements e = index.select("[rel=popover]");
        for (Element it:
             e) {
            urlList.add(it.attr("href"));
        }
    }
}
