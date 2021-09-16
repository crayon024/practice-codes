package self.practice.codes.concurrent.week04.waitforresult;

import java.util.concurrent.*;

public class CallableSubmit {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = () -> {
                Thread.sleep(1000);
                return AddMethod.add();
            };
        Future<Integer> submit = executorService.submit(callable);
        Integer integer = submit.get();

        System.out.println("结果：" + integer + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
        executorService.shutdown();
    }
}
