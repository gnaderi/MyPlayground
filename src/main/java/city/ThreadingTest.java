package city;

public class ThreadingTest implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("Main Thread");

        Thread t1 = new Thread(new ThreadingTest(), "t1");
        Thread t2 = new Thread(new ThreadingTest(), "t2");
        Thread t3 = new Thread(new ThreadingTest(), "t3");
        Thread t4 = new Thread(new ThreadingTest(), "t4");
        Thread t5 = new Thread(new ThreadingTest(), "t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t1.join();
        t4.join();

    }


    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.printf("%s%n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
        }
    }
}
