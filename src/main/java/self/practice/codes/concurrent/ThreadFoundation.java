package self.practice.codes.concurrent;

public class ThreadFoundation {
    
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName());
        };

        Thread thread = new Thread(task);
        thread.setName("666");
        thread.setDaemon(false);
        thread.start();
    }
}
