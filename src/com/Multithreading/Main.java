package com.Multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static AtomicInteger IA = new AtomicInteger(0); // static int i=0;

    public static void main(String[] args) {
        // Наш класс счётчик
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Run());
        // запуск потока после определённого кол-ва выполнений

        // Наши потоки
        for (int i=0; i<20; i++)
            new MyThread(cyclicBarrier);
    }

    // Класс поток, который надо будет запустить
    static class Run extends Thread{
        @Override
        public void run() {
            System.out.println("Запустился нужный метод после "+ IA +" других");
        }
    }

    // поток который мы запускаем и считаем егшо
    static class MyThread extends Thread{
        CyclicBarrier cyclicBarrier;

        public MyThread(CyclicBarrier cyclicBarrier){ // передаем Класс счётчик
            this.cyclicBarrier = cyclicBarrier;
            start();
        }

        @Override
        public void run() {
            IA.incrementAndGet(); // i++;
            System.out.println("Запустил действие "+IA); // выводим на экран наше действие
            try {
                cyclicBarrier.await(); // говорим что всё отлично! Наш счётчик++
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
