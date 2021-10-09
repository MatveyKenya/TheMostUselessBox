package ru.matveykenya;

/**
 *  Вариант №2 --- решение обычными переменными
 */

public class Main_2var {

    final static int COUNT_ITERATOR = 10;
    final static int TIME_OUT_GAMER = 3000;
    final static int TIME_OUT_TOY = TIME_OUT_GAMER / 3;

    static boolean tumbler = false;

    public static void main(String[] args) {

        Thread toy = new Thread(Main_2var::toyBox, "Игрушка");
        toy.setDaemon(true);
        toy.start();

        new Thread(Main_2var::gamer, "Игрок").start();

    }

    static void gamer() {
        int i = 0;
        while (i != COUNT_ITERATOR) {
            if (!tumbler) {
                tumbler = true;
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
            if (tumbler) {
                System.out.println(Thread.currentThread().getName() + " Выключил тумблер!!!");
                tumbler = false;
            }
            try {
                Thread.sleep(TIME_OUT_TOY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
