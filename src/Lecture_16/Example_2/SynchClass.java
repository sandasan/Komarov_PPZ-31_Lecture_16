package Lecture_16.Example_2;

import java.util.concurrent.locks.ReentrantLock;

// Пример использования ReentrantLock.tryLock()
public class SynchClass {

    private long count = 12;
    private ReentrantLock lock = new ReentrantLock();

    public long getCount() {
        // Если объект ещё не заблокирован, то выполнится эта часть (if)
        if (lock.tryLock()) { // Попытка получить блокировку. Если получилось, работаем. Если нет, - делаем что-то ещё
            try {
                // Специальное торможение потока для получения эффекта блокировки
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " - getValue");
                return this.count;
            } finally {
                lock.unlock();
            }
        } else { // Эта часть выполнится, если объект уже заблокирован
            System.out.println(Thread.currentThread() + " - do someThing");
            return -1;
        }
    }
}
