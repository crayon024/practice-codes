package self.practice.codes.concurrent.week04.waitforresult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinGet {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool(1);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new MyForkJoin());
        Integer integer = submit.get();
        System.out.println("结果：" + integer + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }


    static class MyForkJoin extends RecursiveTask<Integer> {
        @Override
        protected Integer compute() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return AddMethod.add();
        }
    }
}
