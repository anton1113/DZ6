package example;

import java.io.FileInputStream;
import java.io.IOException;

class MyFileReader {

    byte[] readFile(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            byte[] result = new byte[fileInputStream.available()];
            int fileSize = fileInputStream.read(result);
            System.out.printf("Прочитано %d байт из файла %s", fileSize, filePath);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
