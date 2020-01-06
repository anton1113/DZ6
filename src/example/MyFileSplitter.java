package example;

import java.util.Arrays;

class MyFileSplitter {

    private static final MyFileReader MY_FILE_READER = new MyFileReader();
    private static final MyFileWriter MY_FILE_WRITER = new MyFileWriter();

    void splitFile(String filePath, int numberOfParts) {
        byte[] fileContent = MY_FILE_READER.readFile(filePath);
        int partSize = fileContent.length / numberOfParts;
        for (int i = 0; i <= numberOfParts; i++) {
            int startByteIndex = i * partSize;
            int endByteIndex = (i + 1) * partSize;
            byte[] partialContent = copyOfRange(fileContent, startByteIndex, endByteIndex);
            String partFilename = getFilePartName(filePath, i);
            MY_FILE_WRITER.write(partFilename, partialContent);
        }
    }

    private byte[] copyOfRange(byte[] origin, int from, int to) {
        if (to > origin.length) {
            to = origin.length;
        }
        return Arrays.copyOfRange(origin, from, to);
    }

    private String getFilePartName(String originFileName, int index) {
        int indexOfDot = originFileName.indexOf(".");
        return originFileName.substring(0, indexOfDot) + index + originFileName.substring(indexOfDot);
    }
}
