package com.wen.jdk8;

import com.wen.jdk8.pojo.User;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description
 * @since 2023/2/20 21:43:48
 */
public class Test02 {
    @Test
    public void testLamdba() {
        User user = new User();
        user.setUsername("wen");
        String helloSay = user.say(name -> "hello" + name);
        Function<String, String> function = (username) -> "hello" + username;
        String say = user.say(function.andThen(s -> "你好" + s));
        System.out.println(say);
    }

    @Test
    public void testLamdba02() {
        User user = new User();
        user.setUsername("wen");
        UnaryOperator<String> func = username -> "hello " + username;
        String say = user.say(func);
        System.out.println(say);
    }

    @Test
    public void testLamdba03() {
        List<String> names = Arrays.asList("张三", "里斯", "张五");
        List<String> list = names.stream().filter(s -> s.startsWith("张")).collect(Collectors.toList());
        for (String s : list) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void testLamdba04() {
        List<String> names = Arrays.asList("张三", "里斯", "张五");
        names.stream().forEach(s -> System.out.println(s));
    }

    @Test
    public void test02() {
        Random random = new Random();
        Supplier<Integer> supplier = () -> random.nextInt(100);
        Stream<Integer> stream = Stream.generate(supplier).limit(5);
        stream.forEach(System.out::println);
    }

    @Test
    public void test03() {
        String[] arr = {"1", "2", "3"};
        Stream<String> s1 = Arrays.stream(arr).parallel();
        Stream<Integer> s2 = s1.map(Integer::valueOf);
        s2.forEach(System.out::println);
    }

    @Test
    public void testReduce() {
        Optional<Integer> reduce = Stream.of(1, 2, 3, 4).reduce(Integer::sum);
        System.out.println(reduce.orElse(-1));
    }

    @Test
    public void testFindFirstOrAny() {
        for (int i = 0; i < 10; i++) {
            Optional<Integer> first = Stream.of(1, 2, 3, 4).parallel().findFirst();
            System.out.println("first.get() = " + first.get());
        }
        System.out.println("=============");
        for (int i = 0; i < 50; i++) {
            Optional<Integer> first = Stream.of(1, 2, 3, 4).parallel().findAny();
            System.out.println("first.get() = " + first.get());
        }
    }

    @Test
    public void testParaller() {
        new Random().ints().limit(50).parallel().forEach(i -> {
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        });
    }

    @Test
    public void testFlow() {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                //向数据发布者请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("接收到 publisher 发来的消息了：" + item);
                //接收完成后，可以继续接收或者不接收
                //this.subscription.cancel();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                //出现异常，就会来到这个方法，此时直接取消订阅即可
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                //发布者的所有数据都被接收，并且发布者已经关闭
                System.out.println("数据接收完毕");
            }
        };
        //配置发布者和订阅者
        publisher.subscribe(subscriber);
        for (int i = 0; i < 500; i++) {
            System.out.println("i==>" + i);
            //发送数据
            publisher.submit("hello:" + i);
        }
        //关闭发布者
        publisher.close();
        new Scanner(System.in).next();
    }

    @Test
    public void testProcessor() {
        class DataFilter extends SubmissionPublisher<String> implements Flow.Processor<String, String> {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                this.submit("{这是一条被处理的数据}" + item);
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                this.close();
            }
        }

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        DataFilter dataFilter = new DataFilter();

        publisher.subscribe(dataFilter);

        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                //向数据发布者请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("接收到 publisher 发来的消息了：" + item);
                //接收完成后，可以继续接收或者不接收
                //this.subscription.cancel();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                //出现异常，就会来到这个方法，此时直接取消订阅即可
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                //发布者的所有数据都被接收，并且发布者已经关闭
                System.out.println("数据接收完毕");
            }
        };
        dataFilter.subscribe(subscriber);
        for (int i = 0; i < 500; i++) {
            System.out.println("发送消息 i--------->" + i);
            publisher.submit("hello:" + i);
        }
        //关闭发布者
        publisher.close();
        new Scanner(System.in).next();
    }
}
