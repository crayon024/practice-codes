package self.practice.codes.concurrent.week04.waitforresult;

/**
 * @author Crayon
 */
public class JoinGet {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final int[] result = new int[1];

        Thread thread = new Thread(() -> result[0] = AddMethod.add());
        thread.start();

        thread.join();
        System.out.println("结果：" + result[0] + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
