package self.practice.codes.concurrent.week04.waitforresult;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchGet {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        final int[] result = new int[1];

        Thread thread = new Thread(() -> {
                result[0] = AddMethod.add();
                try {
                    Thread.sleep(1500); // sleep 一段时间验证结果
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
        });
        thread.start();

        countDownLatch.await();
        System.out.println("结果：" + result[0] + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
