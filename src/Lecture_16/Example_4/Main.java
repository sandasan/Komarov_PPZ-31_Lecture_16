package Lecture_16.Example_4;

// Пример синхронизации прямого доступа в многопоточном режиме с использованием Atomic переменных. При этом синхронизированы даже не атомарные операции (в данном примере инкремент)
// В случае использования Atomic переменных синхронизация прямого доступа к свойству класса выполняется автоматически. Это может существенно уменьшить размер кода.
// Главный класс проекта
public class Main {
    public static void main(String[] args) {
        SomeClass someClass = new SomeClass(); // Создание одного объекта

        // Использование объекта в 10 потоках
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SingleThread(someClass));
            thread.start();
        }
    }
}
/*
Итоги урока

Синхронизация потоков - приведение двух или нескольких потоков к такому их протеканию, когда определённые стадии разных потоков совершаются в определённом порядке либо одновременно.
Синхронизация чаще всего выполняется:
- Для предоставления очерёдности использования потоками объекта (синхронизация по объекту) - используются synchronized методы или оператор synchronized().
- Для синхронизации по какому-нибудь событию - используются методы wait(), notify(), notifyAll().
- Для синхронизации прямого доступа к свойству класса: проще всего использовать Atomic переменные.

При неправильном планировании многопоточного приложения может возникнуть взаимная блокировка потоков, что приведёт к неработоспособности приложения.
 */