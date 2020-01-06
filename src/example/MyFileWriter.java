package example;

import java.io.FileOutputStream;
import java.io.IOException;

class MyFileWriter {

    boolean write(String filePath, byte[] content) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(content);
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
