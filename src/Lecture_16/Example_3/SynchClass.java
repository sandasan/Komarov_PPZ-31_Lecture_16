package Lecture_16.Example_3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Класс Фабрика
public class SynchClass {
    private long pylons; // Количество пси
    // Синхронизатор типа Lock и условие, им порождённое
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public SynchClass(long pylons) {
        super();
        this.pylons = pylons;
    }

    public long getPylons(long unitEnergy) {
        try {
            lock.lock(); // Получение блокировки. Поток, вызывающий этот метод, заблокирует объект
            // Проверка условия, и если оно не выполнено, блокировка потока по условию. Блокировку по условию нужно осуществлять в теле цикла
            for (; this.pylons < unitEnergy; ) { // Проверяем условие
                try {
                    System.out.println("You Must Construct Additional Pylons!");
                    condition.await(); // Вызывая метод await() объекта условия, блокируем поток по условию
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.pylons -= unitEnergy;
            System.out.println("Unit return");
        } finally { // Снятие блокировки. Разблокировка потока
            lock.unlock();
        }
        return pylons;
    }

    // Метод, блокирующий объект при добавлении пси
    public void setPylons(long pylons) {
        try {
            lock.lock();
            this.pylons = pylons;
            System.out.println("Constructed " + pylons + " pylons");
            // Активация блокированных по условию потоков
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
