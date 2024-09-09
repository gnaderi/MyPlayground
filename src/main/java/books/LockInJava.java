package books;

import java.util.stream.IntStream;


public class LockInJava {
    public void main(String[] args) {
        LockInJava d = new LockInJava();
        IntStream.range(0, 10).forEach(i -> new LockInJava.myThread(d, "T" + i).start());
    }

    public void wish(String name) {
        System.out.println(name + ": Inside wish");
        synchronized (this) {
            System.out.println(name + ": Inside synchronized wish");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        try {
            Thread.sleep(1000);
            System.out.println(name + ": I am steeling playing!");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    class myThread extends Thread {
        LockInJava lockInJava;
        String name;

        myThread(LockInJava d, String name) {
            this.lockInJava = d;
            this.name = name;
        }

        public void run() {
            lockInJava.wish(name);
        }
    }
}

