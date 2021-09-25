package Lecture_16.Example_3;

// Класс, генерирующий пси
public class BuildPylons implements Runnable {
    private SynchClass synchClass;
    private long pylons;

    public BuildPylons(SynchClass synchClass, long pylons) {
        super();
        this.synchClass = synchClass;
        this.pylons = pylons;
    }

    @Override
    public void run() {
        synchClass.setPylons(pylons);
    }
}
