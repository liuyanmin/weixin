package com.lym.utils.messagetype.base;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
public abstract class BaseMessage {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;

    /**
     * LinkMessage          link
     * LocationMessage      location
     * ShortVideoMessage    shortvideo
     * TextMessage          text
     * VideoMessage         video
     * VoiceMessage         voice
     */
    private String MsgType;
    private Long MsgId;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }
}
