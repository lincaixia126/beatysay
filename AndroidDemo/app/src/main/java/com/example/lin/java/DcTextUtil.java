package com.example.lin.java;

public class DcTextUtil {

    public static void main(String[] args) {
        new DcTextUtil().test();
    }

    public void test() {

        setText("123:123", ":");

        setText("123123", ":");

        setText("123123:", ":");

        setText("", ":");

    }

    /**
     * 这里的文本以 ： 分割
     *
     * @param text
     */
    public void setText(String text, String splitCh) {

        String[] splitTextStrs = text.split(splitCh);

        int index = text.indexOf(":");

        System.out.println("index: " + index + "splitTextStrs: " + splitTextStrs.toString()
                + splitTextStrs.length
        );

    }


}
