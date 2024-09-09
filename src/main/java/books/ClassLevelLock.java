package books;

public class ClassLevelLock {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new AnotherThread("T" + i).start();
        }
    }

    public static void wish(String name) {
        synchronized (ClassLevelLock.class) {
            System.out.println(name + ": Inside synchronized wish");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public synchronized static void anotherStaticMethod(String name) {
        System.out.println(name + ": anotherStaticMethod");
    }


}

class AnotherThread extends Thread {
    String name;

    AnotherThread(String name) {
        this.name = name;
    }

    public void run() {
        if (Integer.parseInt(name.substring(1)) % 2 == 0) {
            ClassLevelLock.wish(name);
        } else {
            ClassLevelLock.anotherStaticMethod(name);
        }
    }
}
