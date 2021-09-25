package Lecture_16.HomeWork;

public class Main {
    public static void main(String[] args) {
        // Создаём порт с 2 доками и переменной количества кораблей. Док имеет свой номер
        int shipsCount = 3;
        int docksCount = 2;
        Port port = new Port(docksCount);
        for (int i = 0; i < shipsCount; i++) {
            Thread thread = new Thread(new Ship(i + 1, 10, port));
            thread.start();
        }
        // Корабль запрашивает доступность дока в порту
        // Порт выдаёт кораблю при наличии док с номером, при этом у порта количество доступных доков уменьшается на 1.
        // Док разгружает корабль. Во время разгрузки док заблокирован. А потом освобождается. Количество свободных доков в порту увел. на 1

    }
}
