package Lecture_16.Example_4;

public class SingleThread implements Runnable {
    private SomeClass someClass;

    public SingleThread(SomeClass someClass) {
        super();
        this.someClass = someClass;
    }

    // В параллельном потоке сначала изменяется значение, а потом выводится на экран
    @Override
    public void run() {
        int x = someClass.volume.getAndIncrement();
        System.out.print(" " + x);
    }
}
