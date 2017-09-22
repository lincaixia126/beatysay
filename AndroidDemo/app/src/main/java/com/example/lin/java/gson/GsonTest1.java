package com.example.lin.java.gson;

import com.google.gson.Gson;

/**
 * Created by lin on 17/9/21.
 */

public class GsonTest1 {

    public static void main(String[] args) {
        new GsonTest1().test();
    }

    public void test() {
        Gson gson = new Gson();
        gson.toJson(1);            // ==> 1
        gson.toJson("abcd");       // ==> "abcd"
        gson.toJson(new Long(10)); // ==> 10
        int[] values = {1};
        gson.toJson(values);       // ==> [1]

        // Deserialization
        int one = gson.fromJson("1", int.class);
        Integer oneInteger = gson.fromJson("1", Integer.class);
        Long oneLong = gson.fromJson("1", Long.class);
        Boolean falseBoolean = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
    }
}
