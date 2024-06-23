package net.automobile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class DevicesManager {

    private static final Gson gson = new Gson();

    public static ManagerBuilder load(String resource) {
        return new ManagerBuilder(resource);
    }

    public static class ManagerBuilder {

        private Map<String, Map<String, String>> records;

        private final String resource;

        public ManagerBuilder(String resource) {
            this.resource = resource;
            this.records = getAllISP();
        }

        public Map<String, Map<String, String>> getAllISP() {
            if (records == null) {
                records = read(System.getProperty("user.dir") + "/src/test/resources/" + this.resource,
                        new TypeToken<Map<String, Map<String, String>>>() {
                        }.getType());
            }

            return records;
        }

        public Map<String, String> getISPByKey(String keyValue) {
            return records.get(keyValue);
        }
    }

    @SuppressWarnings("unchecked")
    static <T extends Map<?, ?>> T read(String prefixName, Type type) {
        Map<String, Object> data;
        try {
            data = readData(prefixName, type);
            return (T) data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static Map<String, Object> readData(String resourceName, Type type) throws FileNotFoundException {

        InputStream is = new FileInputStream(resourceName);
        Reader reader = new InputStreamReader(is, Charset.defaultCharset());
        return gson.fromJson(reader, type);
    }

}
