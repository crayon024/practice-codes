package self.practice.codes.concurrent.week04.waitforresult;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CheckContainerEmpty {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Set<Integer> result = new HashSet<>(2);
        Thread thread = new Thread(() -> result.add(AddMethod.add()));
        thread.start();

        while (result.isEmpty()) {}

        Optional<Integer> i = result.stream().findAny();
        System.out.println("结果：" + i.get() + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
