package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;

public class MyPrinterV3 {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("input String for print. exit : q");
            String input = userInput.nextLine();
            if (input.equals("q")) {
                printerThread.interrupt();
                break;
            }
            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>(); // 동시성

        @Override
        public void run() {
            log("printer run");
            while (!Thread.interrupted()) {
                if (jobQueue.isEmpty()) {
                    continue;
                }
                try {
                    String job = jobQueue.poll();
                    log("print start : " + job);
                    log("waiting job : " + jobQueue);
                    Thread.sleep(3000);
                    log("print end");
                } catch (InterruptedException e) {
                    log("interrupt!");
                    log("thread state : " + Thread.currentThread().getState());
                    break;
                }
            }
            log("printer exit");
        }

        public void addJob(String input) {
            jobQueue.offer(input);
        }
    }

}
