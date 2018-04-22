package com.lym.utils.messagetype;

import com.lym.utils.messagetype.base.BaseMessage;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
public class TextMessage extends BaseMessage {
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
