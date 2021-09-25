package Lecture_16.Example_1;

public class Main {

    // Главный метод программы
    public static void main(String[] args) {
	    // Создание объекта класса Account
        Account account = new Account(200, "login", 123);

        // Двум потокам по ссылке передаётся один и тот же объект
        SingleThread stOne = new SingleThread(account);
        SingleThread stTwo = new SingleThread(account);

        // Старт обоих потоков
        stOne.getMoneyFromAccount("login", 123, 150);
        stTwo.getMoneyFromAccount("login", 123, 150);
    }
}
