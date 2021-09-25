package Lecture_16.HomeWork;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Класс Фабрика
public class Port {
    private int docksCount; // Количество доков в порту
    private Dock[] docks;
    private DocksBase docksBase;
    private boolean dockFound;
    // Синхронизатор типа Lock и условие, им порождённое
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Port(int docksCount) {
        super();
        this.docksCount = docksCount;
        docks = new Dock[docksCount];
        for (int i = 0; i < docksCount; i++) {
            docks[i] = new Dock(i + 1);
        }
    }

    public Dock getDock() {
        // Проверка условия, и если оно не выполнено, блокировка потока по условию. Блокировку по условию нужно осуществлять в теле цикла
        dockFound = false;
        while (!dockFound) {
            for (int i = 0; i < docksCount; i++) {
                if (!docks[i].isDockLocked()) {
                    dockFound = true;
                    return docks[i];
                }
            }
        }
        System.out.println("There is no free Dock!");
        return null;
    }
}
