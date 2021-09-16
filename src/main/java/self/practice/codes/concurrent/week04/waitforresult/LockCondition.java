package self.practice.codes.concurrent.week04.waitforresult;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCondition {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final int[] result = new int[1];

        Lock o = new ReentrantLock();
        Condition condition = o.newCondition();
        new Thread(() -> {
            o.lock();
            result[0] = AddMethod.add();
            try {
                Thread.sleep(1000); // sleep 一段时间验证结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                condition.signal();
                o.unlock();
            }

        }).start();

        o.lock();
        condition.await();
        o.unlock();

        System.out.println("结果：" + result[0] + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
