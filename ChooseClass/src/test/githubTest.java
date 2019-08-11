package test;

import org.jsoup.Jsoup;

import java.io.IOException;

public class githubTest {

    public static void main(String[] args) throws IOException {
        System.out.println(Jsoup.connect("https://coccaguo.github.io/").get().body().text());
    }

}
