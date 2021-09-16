package self.practice.codes.jvm;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * GC log 分析
 * -Xms128m -Xmx128m
 * -Xloggc:gc.log -XX:+PrintGCDetails
 * -XX:+UseXXXXGC
 */
public class GCLogAnalysis {
    private static Random random = new Random();

    public GCLogAnalysis() {
    }

    public static void main(String[] args) {
        long startMillis = System.currentTimeMillis();
        long timeoutMillis = TimeUnit.SECONDS.toMillis(1L);
        long endMillis = startMillis + timeoutMillis;
        LongAdder counter = new LongAdder();
        System.out.println("正在执行...");
        int cacheSize = 2000;
        Object[] cachedGarbage = new Object[cacheSize];

        while(System.currentTimeMillis() < endMillis) {
            Object garbage = generateGarbage(1024 * 100);
            counter.increment();
            int randomIndex = random.nextInt(2 * cacheSize);
            if (randomIndex < cacheSize) {
                cachedGarbage[randomIndex] = garbage;
            }
        }


        System.out.println("执行结束!共生成对象次数:" + counter.longValue());
        while (true) {}
    }

    private static Object generateGarbage(int max) {
        int randomSize = random.nextInt(max);
        int type = randomSize % 4;
        Object result = null;
        switch(type) {
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder builder = new StringBuilder();
                String randomString = "randomString-Anything";

                while(builder.length() < randomSize) {
                    builder.append(randomString);
                    builder.append(max);
                    builder.append(randomSize);
                }

                result = builder.toString();
        }

        return result;
    }
}

