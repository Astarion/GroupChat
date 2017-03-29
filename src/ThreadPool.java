import javax.management.relation.RelationNotFoundException;
import java.util.LinkedList;

/**
 * Created by Alex on 24.03.2017.
 */
public class ThreadPool {
    private LinkedList<Runnable> allWorkers;
    private Channel freeWorkers;
    private Integer maxSize;
    private Object lock = new Object();

    public ThreadPool(Integer maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException();
        }
        allWorkers = new LinkedList<Runnable>();
        this.maxSize = maxSize;
        this.freeWorkers = new Channel(this.maxSize);
        WorkerThread workerThread = new WorkerThread(this);
        allWorkers.addLast(workerThread);
        freeWorkers.put(workerThread);
    }

    public void execute(Runnable task) {
        if (freeWorkers.getSize() == 0) {
            synchronized (lock) {
                if (freeWorkers.getSize() == 0) {
                    if (allWorkers.size() < maxSize) {
                        WorkerThread workerThread = new WorkerThread(this);
                        allWorkers.addLast(workerThread);
                        freeWorkers.put(workerThread);
                    }
                }
            }
        }

        //Already synchronized in Channel
        ((WorkerThread) freeWorkers.take()).execute(task);
    }

    void onTaskCompletetd(WorkerThread workerThread) {
        freeWorkers.put(workerThread);
    }
}
