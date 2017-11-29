package com.example.lin.recyclerview.wrap;

/**
 * Created by lin on 17/11/28.
 */

public class DataItem1 {

    private String dishName;

    private String dishCount;

    private String dishAmount;

    public DataItem1(String dishName, String dishCount, String dishAmount) {
        this.dishName = dishName;
        this.dishCount = dishCount;
        this.dishAmount = dishAmount;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDishCount() {
        return dishCount;
    }

    public String getDishAmount() {
        return dishAmount;
    }


}
