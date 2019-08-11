package ui.plugin.websites;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Item implements Serializable {
    @JSONField(name = "URL")
    String url = null;

    @JSONField(name = "TITLE")
    String title = null;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
