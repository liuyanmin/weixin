package com.lym.utils.messagetype;

import com.lym.utils.messagetype.base.BaseMessage;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
public class LinkMessage extends BaseMessage {
    private String Title;
    private String Description;
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
