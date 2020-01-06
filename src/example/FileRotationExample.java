package example;

public class FileRotationExample {

    public static void main(String[] args) {
        MyFileSplitter myFileSplitter = new MyFileSplitter();
        myFileSplitter.splitFile("big.txt", 4);
    }

}
