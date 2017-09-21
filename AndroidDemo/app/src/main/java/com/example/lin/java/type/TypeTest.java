package com.example.lin.java.type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 17/9/21.
 */

public class TypeTest {

    public static void main(String[] args) {
        new TypeTest().test();
    }

    public void test() {
        List<? super Double> nums = new ArrayList<>();  // valid if List<Integer> were a subtype of List<Number> according to substitution rule.
        Double douNum = new Double(1.23);
        nums.add(douNum);
    }
}
