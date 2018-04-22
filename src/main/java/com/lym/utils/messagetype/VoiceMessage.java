package com.lym.utils.messagetype;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
public class VoiceMessage extends TextMessage {
    private String MediaId;
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
