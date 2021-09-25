package Lecture_16.HomeWork;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DocksBase {
    Dock[] docks;
    private int docksCount;
    // Синхронизатор типа Lock и условие, им порождённое
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public DocksBase(int docksCount) {
        this.docksCount = docksCount;
        docks = new Dock[docksCount];
        for (int i = 0; i < docksCount; i++) {
            docks[i] = new Dock(i + 1);
        }
    }

    public int getDocksCount() {
        try {
            System.out.println("locked");
            lock.lock();
            for (; docksCount == 0; ) { // Проверяем условие
                try {
                    System.out.println("await");
                    condition.await(); // Вызывая метод await() объекта условия, блокируем поток по условию
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        return docksCount;
    }

    public void setDocksCount(int docksCount) {
        this.docksCount = docksCount;
        if (this.docksCount > 0) {
            // Активация блокированных по условию потоков
            condition.signalAll();
        }
    }
}
