package Lecture_16.Example_2;

// Поток, который попытается захватить объект класса SynchClass
public class SingleThread implements Runnable {
    private SynchClass synchClass;

    public SingleThread(SynchClass synchClass) {
        super();
        this.synchClass = synchClass;
    }

    // Этот поток просто попытается вызвать метод, синхронизированный с помощью ReentrantLock.tryLock()
    @Override
    public void run() {
        System.out.println(synchClass.getCount());
    }
}
