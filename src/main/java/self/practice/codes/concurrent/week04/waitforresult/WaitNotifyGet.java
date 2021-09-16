package self.practice.codes.concurrent.week04.waitforresult;

public class WaitNotifyGet {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final int[] result = new int[1];

        Object o = new Object();
        Thread thread = new Thread(() -> {
            synchronized (o) {
                result[0] = AddMethod.add();
                try {
                    Thread.sleep(2000); // sleep 一段时间验证结果
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o.notify(); // 唤醒等待线程, 就是主线程
            }

        });
        thread.start();

        synchronized (o) {
            o.wait(); // 释放锁, 等待异步计算出结果
        }
        System.out.println("结果：" + result[0] + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
