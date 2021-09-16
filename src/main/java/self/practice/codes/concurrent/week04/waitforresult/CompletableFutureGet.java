package self.practice.codes.concurrent.week04.waitforresult;

import java.util.concurrent.*;

public class CompletableFutureGet {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> AddMethod.add());
        Integer integer = integerCompletableFuture.get();

        System.out.println("结果：" + integer + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
