package com.example.lin.rx;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * http://blog.csdn.net/lzyzsd/article/details/41833541
 * Created by lin on 17/9/26.
 *
 * 1.Observable和Subscriber可以做任何事情
 Observable可以是一个数据库查询，Subscriber用来显示查询结果；Observable可以是屏幕上的点击事件，Subscriber用来响应点击事件；Observable可以是一个网络请求，Subscriber用来显示请求结果。

 2.Observable和Subscriber是独立于中间的变换过程的。
 在Observable和Subscriber中间可以增减任何数量的map。整个系统是高度可组合的，操作数据是一个很简单的过程。

 我感觉rxjava真的要搭配retroLambda....服用更佳。
 *
 */

public class HelloRx {

    public static void main(String[] args) {
        HelloRx instance = new HelloRx();
        instance.test();
        instance.testJust();
    }

    public void test () {
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("call me...call me ...");
                //call 时候如果没有手动掉subsriber，不会主动触发

            }
        });

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("I'm completed.....");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("I'm on error.....");
            }

            @Override
            public void onNext(String s) {
                System.out.println("I'm on next...on..next.....");
            }
        };

        myObservable.subscribe(mySubscriber);
    }

    /**
     * 。RxJava内置了很多简化创建Observable对象的函数，比如Observable.just就是用来创建只发出一个事件就结束的Observable对象，上面创建Observable对象的代码可以简化为一行
     Observable<String> myObservable = Observable.just("Hello, world!");
     接下来看看如何简化Subscriber，上面的例子中，我们其实并不关心OnComplete和OnError，我们只需要在onNext的时候做一些处理，这时候就可以使用Action1类。
     */
    public void testJust() {
        Observable<String> myObservable = Observable.just("Hello, world...");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Im Action 1....call..me....call..me..." + s);
            }
        };

        myObservable.subscribe(onNextAction);

        /**
         * 上面代码的简化版本
         */
        Observable.just("Hello, world!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });



    }

    /**
     * 这种方式仍然不能让人满意，因为我希望我的Subscribers越轻量越好，因为我有可能会在mainThread中运行subscriber。
     * 另外，根据响应式函数编程的概念，Subscribers更应该做的事情是“响应”，
     * 响应Observable发出的事件，而不是去修改。如果我能在某些中间步骤中对“Hello World！”进行变换是不是很酷？
     */
    public void testMap() {
        Observable.just("hello,,world...")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "lcx";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        System.out.println("Im the map operater..." + o);
                    }
                });
    }

    /**
     * map操作符进阶
     map操作符更有趣的一点是它不必返回Observable对象返回的类型，你可以使用map操作符返回一个发出新的数据类型的observable对象。
     比如上面的例子中，subscriber并不关心返回的字符串，而是想要字符串的hash值
     再增加一个map操作符.......
     */

    public void testMap2() {
        Observable.just("hell0..world")
                .map(new Func1<String, Integer>() {

                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return integer + "hello i love this....";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String integer) {
                        System.out.println("Im the map operater...I can make a String to a int value...and be...a String to ...." + integer);
                    }
                });
    }


    /**
     * 救星来了,他就是flatMap()。
     Observable.flatMap()接收一个Observable的输出作为输入，同时输出另外一个Observable。直接看代码：
     query("Hello, world!")
     .flatMap(new Func1<List<String>, Observable<String>>() {
             @Override
                 public Observable<String> call(List<String> urls) {
                      return Observable.from(urls);
     }
     })
     .subscribe(url -> System.out.println(url));

     atMap()是不是看起来很奇怪？为什么它要返回另外一个Observable呢？
     理解flatMap的关键点在于，flatMap输出的新的Observable正是我们在Subscriber想要接收的。
     现在Subscriber不再收到List<String>，而是收到一些列单个的字符串，就像Observable.from()的输出一样。
     */



}
