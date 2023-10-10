package org.batherphilippa;

import static org.batherphilippa.ThreadColour.*;

public class Main {
    public static void main(String[] args) {
        // This line will be presented first.
        // Execution order of the subsequent threads depends on the Scheduler.
        System.out.println(ANSI_PURPLE + "\nHello from the main thread!");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName(" == Another Thread ==");
        anotherThread.start();  // JVM runs the run method for thread
//        anotherThread.run(); will run on the same thread that called the run() method, not the anotherThread instance

        new Thread() {
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from the anonymous class thread.");
            }
        }.start();

        // or as lambda
        new Thread(() -> System.out.println(ANSI_RED + "Hello from the anonymous lambda thread.")).start();

        // create a new thread based on MyRunnable class
        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();

        Thread myRunnableAnonymousThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                // super.run(); run run() from MyRunnable class
                System.out.println(ANSI_RESET + "Hello from the anonymous class' implementation of Runnable's run().");
                try {
                    anotherThread.join(2000); // get AnotherThread to timeout
                    System.out.println(ANSI_RED + "AnotherThread terminated or timed out, so myRunnableAnonymousThread is running again.");
                } catch(InterruptedException ie) {
                    System.out.println(ANSI_RESET + " I couldn't wait; I was interrupted.");
                }
            }
        });

        myRunnableAnonymousThread.start();
//        anotherThread.interrupt();

        System.out.println(ANSI_PURPLE + "Hello again from the main thread.");
    }
}