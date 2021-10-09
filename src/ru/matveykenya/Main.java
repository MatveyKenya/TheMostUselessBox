package ru.matveykenya;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ЭТО --- Основное решение ---
 * Работает неверно - не понимаю вследствии чего
 * Если правильно понял задачу, то sleep можно использовать только у игрока
 * а у Игрушки нельзя, игрушка должна максимально быстро реагировать
 * но получается что вывод в итоге не строго последовательный
 * как "Игрок включи - Игрушка выключил - Игрок включи - Игрушка выключил"
 * на самомо деле так --- "Игрок включил - Игрок включил"
 * Что неверно?
 *
 * 2й вариант решения --- в Main_2var
 * но там просто sleep-ом в Игрушке решается и обычными переменными не volatile
 */

public class Main {

    final static int COUNT_ITERATOR = 10;
    final static int TIME_OUT_GAMER = 3000;
    //final static int TIME_OUT_TOY = TIME_OUT_GAMER / 3;
    static AtomicBoolean tumbler = new AtomicBoolean(false);

    public static void main(String[] args) {

        Thread toy = new Thread(Main::toyBox, "Игрушка");
        toy.setDaemon(true);
        toy.start();

        new Thread(Main::gamer, "Игрок").start();

    }

    static void gamer() {
        int i = 0;
        while (i != COUNT_ITERATOR) {
            if (tumbler.compareAndSet(false, true)) {
                System.out.println(Thread.currentThread().getName() + " включил тумблер!!!!");
                i++;
            }
            try {
                Thread.sleep(TIME_OUT_GAMER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void toyBox() {
        while (true){
            if (tumbler.compareAndSet(true, false)) {
                System.out.println(Thread.currentThread().getName() + " Выключил тумблер!!!");
            }
        }
    }
}
