package Lecture_16.Example_2;

// Главный класс проекта (реализованного в виде пакета "Example_2")
public class Main {
    public static void main(String[] args) {
        SynchClass synchClass = new SynchClass();

        // Один объект получили оба потока
        SingleThread sThreadOne = new SingleThread(synchClass);
        SingleThread sThreadTwo = new SingleThread(synchClass);

        // Создание и запуск потоков
        Thread threadOne = new Thread(sThreadOne);
        Thread threadTwo = new Thread(sThreadTwo);

        threadOne.start();
        threadTwo.start();

        // По выводу в консоль видно, что один поток блокирует поток, и второй поток не может получить блокировку и выполняет другое действие.
    }
}
