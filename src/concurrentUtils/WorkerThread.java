package concurrentUtils;

/**
 * Created by Alex on 24.03.2017.
 */
public class WorkerThread implements Runnable {
    private Thread thread;
    private ThreadPool threadPool;
    private Runnable currentTask;
    private final Object lock = new Object();

    public WorkerThread(ThreadPool threadPool) {
        currentTask = null;
        this.threadPool = threadPool;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (true) {
                while (currentTask == null)
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Error has occurred while thread was waiting in concurrentUtils.WorkerThread");
                    }

                try {
                    currentTask.run();
                }
                catch (RuntimeException e)
                {
                    e.printStackTrace();
                }
                finally {
                    currentTask = null;
                    threadPool.onTaskCompletetd(this);
                }
                //Если нет задачи для выполнения, ждем
                //Если есть, выполняем
            }
        }
    }

    public void execute(Runnable task) {
        synchronized (lock) {
            if (currentTask != null)
                throw new IllegalStateException("Current task is not null");
            currentTask = task;
            lock.notifyAll();
        }
    }
}
