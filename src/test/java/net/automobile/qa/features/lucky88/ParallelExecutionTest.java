package net.automobile.qa.features.lucky88;

import net.automobile.AndroidObject;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ParallelExecutionTest extends AndroidObject {

    //@Test
    public void WhenInstallingTheAppStore(){

        Thread[] threads = new Thread[devices.length];

        for (int i = 0; i < devices.length; i++) {
            String deviceName = devices[i][0];
            String udid = devices[i][1];
            int systemPort = Integer.parseInt(devices[i][2]);

            threads[i] = new Thread(new ApplicationInstaller(deviceName, udid, systemPort));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void WhenRegisteringAnAccountStory(){

        Thread[] threads = new Thread[devices.length];

        for (int i = 0; i < devices.length; i++) {
            String deviceName = devices[i][0];
            String udid = devices[i][1];
            int systemPort = Integer.parseInt(devices[i][2]);

            threads[i] = new Thread(new RegisterAccount(deviceName, udid, systemPort));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
