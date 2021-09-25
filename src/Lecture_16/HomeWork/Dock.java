package Lecture_16.HomeWork;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Класс Фабрика
public class Dock {
    private int dockNumber;
    private boolean isBusy;
    // Синхронизатор типа Lock и условие, им порождённое
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Dock(int dockNumber) {
        super();
        this.dockNumber = dockNumber;
        isBusy = false;
    }

    public boolean isDockLocked() {
//        return lock.isLocked();
        return !lock.tryLock();
    }

    public int getNumber() {
        return dockNumber;
    }

    // Метод, блокирующий объект при добавлении пси
    public void unloadShip(Ship ship) throws InterruptedException {
        System.out.println("Unloading Ship " + ship.getShipNumber() + " in Dock " + dockNumber);
        try {
            System.out.println("locked");
            lock.lock();
            for (; ship.getBoxesCount() != 0; ) { // Проверяем условие
                /*try {
                    System.out.println("await");
//                    condition.await(); // Вызывая метод await() объекта условия, блокируем поток по условию
                    // Активация блокированных по условию потоков
//                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                int boxesCountOnShip = ship.getBoxesCount();
                System.out.println("boxesCountOnShip: " + boxesCountOnShip);
                for (int i = 0; i < boxesCountOnShip; i++) {
                    Thread.sleep(500);
                    System.out.println("Unloaded " + (i + 1) + " boxes from ship " + ship.getShipNumber());
                    ship.setBoxesCount(ship.getBoxesCount() - 1);
                }
                System.out.println("unlocked");
                lock.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
