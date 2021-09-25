package Lecture_16.Example_3;

// Класс, потребляющий пси
public class BuildUnit implements Runnable {
    private SynchClass synchClass;
    private long pylons;

    public BuildUnit(SynchClass synchClass, long pylons) {
        super();
        this.synchClass = synchClass;
        this.pylons = pylons;
    }

    @Override
    public void run() {
        synchClass.getPylons(this.pylons);
    }
}
