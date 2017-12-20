package com.example.lin.java.classloader;

class Par {

    public int x = 5;
    public void f(){
        System.out.println("par static");
    }
}
class Sub extends Par{
    public int x = 6;
    public void f() {
        System.out.println("Sub static");
    }
}

public class Test {

    public static void main(String[] args) {
        Par par = new Sub();
        System.out.println(par.x);
        par.f();
    }
}
// 5....Substaic....