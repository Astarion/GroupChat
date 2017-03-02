import java.io.IOException;

/**
 * Created by Alex on 17.02.2017.
 */
public class ThreadTest implements Runnable {
    int threadNumber;
    public ThreadTest(int threadNumber)
    {
        this.threadNumber = threadNumber;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println("thread " + threadNumber);
            }
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}
