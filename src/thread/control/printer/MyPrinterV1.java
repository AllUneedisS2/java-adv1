package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV1 {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("input String for print. exit : q");
            String input = userInput.nextLine();
            if (input.equals("q")) {
                printer.work = false;
                break;
            }
            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {
        volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>(); // 동시성

        @Override
        public void run() {
            log("printer run");
            while (work) {
                if (jobQueue.isEmpty()) {
                    continue;
                }
                String job = jobQueue.poll();
                log("print start : " + job);
                log("waiting job : " + jobQueue);
                sleep(3000);
                log("print end");
            }
            log("printer exit");
        }

        public void addJob(String input) {
            jobQueue.offer(input);
        }
    }

}
