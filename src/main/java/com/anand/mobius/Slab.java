package com.anand.mobius;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slab {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("num")
    @Expose
    private Integer num;
    @SerializedName("min")
    @Expose
    private Double min;
    @SerializedName("max")
    @Expose
    private Double max;
    @SerializedName("wagered_percent")
    @Expose
    private Double wageredPercent;
    @SerializedName("wagered_max")
    @Expose
    private Double wageredMax;
    @SerializedName("otc_percent")
    @Expose
    private Double otcPercent;
    @SerializedName("otc_max")
    @Expose
    private Double otcMax;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getWageredPercent() {
        return wageredPercent;
    }

    public void setWageredPercent(Double wageredPercent) {
        this.wageredPercent = wageredPercent;
    }

    public Double getWageredMax() {
        return wageredMax;
    }

    public void setWageredMax(Double wageredMax) {
        this.wageredMax = wageredMax;
    }

    public Double getOtcPercent() {
        return otcPercent;
    }

    public void setOtcPercent(Double otcPercent) {
        this.otcPercent = otcPercent;
    }

    public Double getOtcMax() {
        return otcMax;
    }

    public void setOtcMax(Double otcMax) {
        this.otcMax = otcMax;
    }

}
