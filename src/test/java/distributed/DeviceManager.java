package distributed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DeviceManager {

    public static List<String> getConnectedDevices() throws IOException {
        List<String> devices = new ArrayList<>();
        Process process = Runtime.getRuntime().exec("adb devices");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.contains("List of devices attached")) {
                    devices.add(line.split("\\s")[0]);
                }
            }
        }

        return devices;
    }

    // Main method
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> urlList = new ArrayList<>();
        urlList.add("https://google.com");
        urlList.add("https://facebook.com");
        urlList.add("https://google.com");
        urlList.add("https://facebook.com");
        urlList.add("https://google.com");
        urlList.add("https://facebook.com");
        urlList.add("https://google.com");
        urlList.add("https://facebook.com");
        List<String> devices = getConnectedDevices();
        Thread[] threads = new Thread[devices.size()];

        while (!urlList.isEmpty()) {

            boolean threadFound = false;
            for (int i = 0; i < devices.size(); i++) {
                if (threads[i] == null || threads[i].isAlive()) {
                    threads[i] = new Thread(new DistributedTestRunner(devices.get(i), urlList.getFirst()));
                    threads[i].start();
                    urlList.removeFirst();
                    threadFound = true;
                    break;
                }
            }
            if (!threadFound) {
                System.out.println("Waiting for a free device...");
                Thread.sleep(10000); // Wait longer before retrying
            }

        }
    }
}
