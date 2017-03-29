/**
 * Created by Alex on 24.03.2017.
 */
public class WorkerThread implements Runnable {
    private Thread thread;
    private ThreadPool threadPool;
    private Runnable currentTask;
    private Object lock = new Object();

    public WorkerThread(ThreadPool threadPool)
    {
        currentTask = null;
        this.threadPool = threadPool;
        this.thread = new Thread(this);
        this.thread.start();
    }
    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                while (currentTask == null)
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            currentTask.run();
            //Если нет задачи для выполнением, ждем
            //Если есть выполняем
            currentTask = null;
            threadPool.onTaskCompletetd(this);
        }
    }

    public void execute(Runnable task)
    {
        synchronized (lock) {
            currentTask = task;
            lock.notifyAll();
        }
    }
}
