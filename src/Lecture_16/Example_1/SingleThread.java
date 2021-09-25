package Lecture_16.Example_1;

// Класс, описывающий поток
public class SingleThread implements Runnable {
    private Account account; // Ссылка на объект класса Account
    private String login;
    private long password;
    private int sum;

    public SingleThread(Account account) {
        super();
        this.account = account;
    }

    public void getMoneyFromAccount(String login, long password, int sum) {
        this.password = password;
        this.login = login;
        this.sum = sum;
        Thread thread = new Thread(this);
        thread.start();
    }

    // В параллельном потоке происходит процедура снятия денег для объекта Account, полученного по ссылке
    @Override
    public void run() {
        account.takeMoney(this.login, this.password, this.sum);
    }
}
