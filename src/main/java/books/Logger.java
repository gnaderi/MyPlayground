package books;

import java.time.LocalDateTime;

// Implements a simple logging class using a singleton.
public class Logger {
    // Create and store the singleton.
    private static volatile Logger instance = null; // no longer final

    // Prevent anyone else from creating this class.
    private Logger() {
    }

    // Return the singleton instance.
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    //Log a string to the console.
    // example: Logger.getInstance().log("this is a test"); //
    public void log(String msg) {
        System.out.println(LocalDateTime.now() + ": " + Thread.currentThread().getName() + ": " + msg);
    }
}

class Logger2 {
    public static Logger2 getInstance() {
        return LoggerHolder.instance;
    }

    public void log(String msg) {
        System.out.println(LocalDateTime.now() + ": " + Thread.currentThread().getName() + ": " + msg);
    }

    private static class LoggerHolder {
        public static final Logger2 instance = new Logger2();
    }
}

class LoggerThread {
    public static void main(String[] args) {
        Logger2.getInstance().log("Hello World");
    }
}