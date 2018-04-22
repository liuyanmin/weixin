package com.lym.utils.messagetype;
import com.lym.utils.messagetype.base.BaseMessage;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
public class ImageMessage extends BaseMessage {
    private String PicUrl;
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
