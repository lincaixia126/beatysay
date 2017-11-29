package com.example.lin.recyclerview.wrap;

/**
 * Created by lin on 17/11/28.
 */

public class DataItem2 {

    private boolean isLeftImageVisible;

    private String campainName;

    private String campainValue;

    private boolean isRightImageVisible;

    public DataItem2(boolean isLeftImageVisible, String campainName, String campainValue, boolean isRightImageVisible) {
        this.isLeftImageVisible = isLeftImageVisible;
        this.campainName = campainName;
        this.campainValue = campainValue;
        this.isRightImageVisible = isRightImageVisible;
    }

    public boolean isLeftImageVisible() {
        return isLeftImageVisible;
    }

    public String getCampainName() {
        return campainName;
    }

    public String getCampainValue() {
        return campainValue;
    }

    public boolean isRightImageVisible() {
        return isRightImageVisible;
    }
}

