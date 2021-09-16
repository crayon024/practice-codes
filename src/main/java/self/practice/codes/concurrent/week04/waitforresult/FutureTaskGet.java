package self.practice.codes.concurrent.week04.waitforresult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskGet {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Callable<Integer> callable = () -> {
            Thread.sleep(3000);
            return AddMethod.add();
        };

        FutureTask<Integer> integerFutureTask = new FutureTask<>(callable);
        new Thread(integerFutureTask).start();
        Integer integer = integerFutureTask.get();

        System.out.println("结果：" + integer + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
