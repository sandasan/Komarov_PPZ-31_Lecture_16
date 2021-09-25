package Lecture_16.Example_1;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int money;
    private String login;
    private long password;

    private ReentrantLock lock = new ReentrantLock(); // Объект класса ReentrantLock для синхронизации

    public Account(int money, String login, long password) {
        super();
        this.money = money;
        this.login = login;
        this.password = password;
    }

    public void takeMoney(String login, long password, int sum) {
        try {
            lock.lock(); // Блокировка потока для синхронизации. Код будет, таким образом, синхронизирован по объекту. Теперь выполнение этого метода аналогично выполнению synchronized-метода

            if (!checkPassAndLogin(login, password)) {
                System.out.println("Wrong login or password");
                return;
            }
            if (!checkMoney(sum)) {
                System.out.println("You have not enough money");
                return;
            }
            transaction();
            changeBalance(sum);
            System.out.println(this);
        }
        finally {
            lock.unlock(); // Разблокировка потока. За счёт того, что разблокировка происходит в блоке "finally", поток будет разблокирован не зависимо от того, сгенерируется исключение или нет
        }
    }

    // Методы проверок и снятия денег. Метод "transaction()" специально задерживает основной поток, имитируя долгую работу
    private boolean checkPassAndLogin(String login, long password) {
        return (login.equals(this.login) && this.password == password);
    }

    private boolean checkMoney(int money) {
        return this.money >= money;
    }

    private void transaction() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    private void changeBalance(int money) {
        this.money -= money;
    }

    @Override
    public String toString() {
        return "Account [money = " + money + "]";
    }
}

/*
* Подробнее о ReentrantLock
* В наиболее простом случае объект класса ReentrantLock можно использовать как замену synchronized-метода. Однако он обладает более широкими возможностями.
*
* Например: метод tryLock() только пытается захватить блокировку, и если это удалось, метод вернёт true, а если нет, то поток можно переключить на выполнение других действий. То есть, такой метод значительно снизит вероятность взаимной блокировки.
* Существует перегруженная версия этого метода, которая позволяет попробовать захватить блокировку в течение указанного времени: tryLock(time, TimeUnit) - пробовать time единиц времени, выраженных с помощью TimeUnit.
*
* Полезной особенностью метода tryLock() и lockInterruptibly() является возможность прерывания потока, находящегося в режиме ожидания.
* */