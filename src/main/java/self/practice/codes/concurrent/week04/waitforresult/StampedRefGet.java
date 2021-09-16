package self.practice.codes.concurrent.week04.waitforresult;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * http://tutorials.jenkov.com/java-util-concurrent/atomicstampedreference.html
 *
 */
public class StampedRefGet {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        int result = 0;
        AtomicStampedReference stampedReference = new AtomicStampedReference(result, 2);
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000); // sleep 一段时间验证结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stampedReference.set(AddMethod.add(), 3);
        });
        thread.start();
        // 等待版本号更新
        while (stampedReference.getStamp() != 3) {}
        Object reference = stampedReference.getReference();
        System.out.println("结果：" + (Integer) reference + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
