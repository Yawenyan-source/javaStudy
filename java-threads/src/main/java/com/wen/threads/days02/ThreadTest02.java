package com.wen.threads.days02;

/**
 * wait和notify==>生产者，消费者模型
 */
public class ThreadTest02 {
    /**
     * 当前对象为共享对象
     */
    class Res {
        public String username;
        public char sex;
        /**
         * flag值默认为false
         * flag为：false 输入线程 为：true 输出线程
         */
        public boolean flag = false;
    }

    /**
     * 输入线程
     * 必须先输入，才能输出
     */
    class InputThread extends Thread {
        private Res res;

        public InputThread(Res res) {
            this.res = res;
        }

        public InputThread() {

        }

        @Override
        public void run() {
            int count = 0;
            while (true) {
                synchronized (res) {
                    if (res.flag) {
                        try {
                            res.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (count == 0) {
                        res.username = "wen";
                        res.sex = '男';
                    } else {
                        res.username = "lan";
                        res.sex = '女';
                    }
                    //输出线程 输出值
                    res.flag = true;
                    //唤醒输出线程
                    res.notify();
                }
                // 1%2= 2%2= 3%2=      ————————————————>count值为1，0，1，0
                count = (count + 1) % 2;

            }
        }
    }

    /**
     * 输出线程
     * 输出完成后，不能拿着锁，一直输出，需要释放锁给输入
     */
    class outputThread extends Thread {
        private Res res;

        public outputThread(Res res) {
            this.res = res;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (res) {
                    if (!res.flag) {
                        //如果res.flag = false,输出线程抢到了锁，则输出线程主动释放锁，同时会阻塞该线程
                        try {
                            res.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(res.username + "," + res.sex);
                    //输出完毕，交给我们的输入线程继续输入
                    res.flag = false;
                    res.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        new ThreadTest02().print();
    }

    public void print() {
        //全局对象
        Res res = new Res();
        // 输入线程
        InputThread inputThread = new InputThread(res);
        // 输出线程
        outputThread outputThread = new outputThread(res);
        //输入线程启动
        inputThread.start();
        //输出线程启动
        outputThread.start();
    }
}
