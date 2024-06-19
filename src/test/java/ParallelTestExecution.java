import net.automobile.qa.features.search.ParallelTestRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(SerenityRunner.class)
public class ParallelTestExecution {

    @Test
    public void testRun(){
        String[][] devices = {
                {"Device1", "emulator-5554", "8200"},
                {"Device2", "emulator-5556", "8201"},
        };

        Thread[] threads = new Thread[devices.length];

        for (int i = 0; i < devices.length; i++) {
            String deviceName = devices[i][0];
            String udid = devices[i][1];
            int systemPort = Integer.parseInt(devices[i][2]);

            threads[i] = new Thread(new ParallelTestRunner(deviceName, udid, systemPort));
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
