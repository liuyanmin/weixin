package com.lym.utils.messagetype;


import com.lym.utils.messagetype.base.BaseMessage;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
public class LocationMessage extends BaseMessage {
    private Double Location_X;
    private Double Location_Y ;
    private Float Scale;
    private String Label;

    public Double getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(Double location_X) {
        Location_X = location_X;
    }

    public Double getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(Double location_Y) {
        Location_Y = location_Y;
    }

    public Float getScale() {
        return Scale;
    }

    public void setScale(Float scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
