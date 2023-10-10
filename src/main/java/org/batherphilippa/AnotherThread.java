package org.batherphilippa;

import static org.batherphilippa.ThreadColour.ANSI_BLUE;

public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(ANSI_BLUE +  "Hello from " + currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch(InterruptedException ie) {
            // exception only thrown if thread wakes the instance up
            System.out.println(ANSI_BLUE + "Another thread woke me up.");
            // terminate when interrupted
            return;
        }
        System.out.println(ANSI_BLUE + "Three seconds have passed and I'm awake.");
    }
}
