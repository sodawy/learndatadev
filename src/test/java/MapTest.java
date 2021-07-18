import org.junit.Test;
import soda.homework.runs.TrafficBean;

import java.io.*;

public class MapTest {
    @Test
    public void test() {
        String filepath = "/Users/soda/HTTP_20130313143750.dat";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
        ) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                TrafficBean trafficBean = new TrafficBean(line);
                System.out.println(trafficBean.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
