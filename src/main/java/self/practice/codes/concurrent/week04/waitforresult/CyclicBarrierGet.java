package self.practice.codes.concurrent.week04.waitforresult;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierGet {

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        long start = System.currentTimeMillis();
        final int[] result = new int[1];

        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, () -> {
            System.out.println("回调执行开始");
            result[0] = AddMethod.add();
            try {
                Thread.sleep(1500); // sleep 一段时间验证结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("回调执行结束");
        });

        cyclicBarrier.await();
        System.out.println("结果：" + result[0] + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
