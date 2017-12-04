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
        Test test = new Test("100", 10);

        Test result = gson.fromJson(test.toString(), Test.class);
        System.out.print(result.toString());

    }

    public void test2() {
        class Foo<T> {
            T value;
        }
        Gson gson = new Gson();
        Foo<Bar> foo = new Foo<Bar>();
        String gooStr = gson.toJson(foo); // May not serialize foo.value correctly

        gson.fromJson(gooStr, foo.getClass()); // Fails to deserialize foo.value as Bar

    }

    class Bar {
        String name;
    }



    class Test {
        String name;
        int age;

        public Test(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
