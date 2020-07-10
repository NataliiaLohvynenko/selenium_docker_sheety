package chromeTests;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class DockerComposeRun {

    public void DockerComposeRunToOutput(String file, String succsessString) throws IOException, InterruptedException {
        boolean flag = false;
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start " + file);


        String fileLoc = "output.txt";

        Thread.sleep(2000);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 45);

        long stopNow = cal.getTimeInMillis();

        while (System.currentTimeMillis() < stopNow) {

            if (flag) {
                break;
            }
            BufferedReader reader = new BufferedReader(new FileReader(fileLoc));
            String currentLine = reader.readLine();

            while (currentLine != null && !flag) {

                if (currentLine.contains(succsessString)) {
                    flag = true;


                    break;


                }
                currentLine = reader.readLine();
            }
            reader.close();
        }


    }
}
