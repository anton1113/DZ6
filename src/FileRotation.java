import java.io.*;
import java.util.*;

public class FileRotation {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        String pathFile = "big.txt";

// RandomAccessFile работает с файлом как с большим массивом байтов.
// Он использует курсор с помощью которого можно переместить указатель файла в определенную позицию.
// При создании экземпляра RandomAccessFile, нужно выбрать режим файла
// Например "r" если нужно прочитать данные с файла или "rw" — если нучно читать с файла и писать в файл.
// Мне нужно только читать, поэтому выбираю "r".

        RandomAccessFile file = new RandomAccessFile (pathFile, "r");
        System.out.print("Файл нужно разбить на N равных частей. Введите N:  ");
        int N = SCANNER.nextInt();
        long sizeFile = file.length(); // Размер файла
        long perSplit = sizeFile/N ; // Размер частей на которые будет биться файл
        long leftBytes = sizeFile % N; // Остаток файла, который нужно поделить
        int maxReadBufferSize = 8 * 1024; //Размер буфера - 8KB
        System.out.println("Размер файла равен : " + sizeFile);
        System.out.println("Размеры частей равны " + perSplit);

        for(int i=1; i <= N; i++) {
            // Создаем новый буферизированный поток
            // который будет записывать данные в файл у которого в имени в конце будет дописываться номер части
            BufferedOutputStream bufferWrite = new BufferedOutputStream(new FileOutputStream(pathFile + i));
            if(perSplit > maxReadBufferSize) {
                long numReads = perSplit/maxReadBufferSize;
                long numRemainingRead = perSplit % maxReadBufferSize;
                for(int j=0; j<numReads; j++) {
                    readWrite(file, bufferWrite, maxReadBufferSize);
                }
                if(numRemainingRead > 0) {
                    readWrite(file, bufferWrite, numRemainingRead);
                }
            }else {
                readWrite(file, bufferWrite, perSplit);
            }
            bufferWrite.close();
        }
        if(leftBytes > 0) {
            // Запись последней части (которая по размеру может быть меньше остальных)
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(pathFile + (N+1)));
            readWrite(file, bw, leftBytes);
            bw.close(); // Закрываем во избежание проблем
        }
        file.close(); // Закрываем во избежание проблем
    }

    static void readWrite(RandomAccessFile file, BufferedOutputStream bufferWrite, long numBytes) throws IOException {
        // Данный метод либо считывает данные из старого файла либо записывайт данные в новый файл
        byte[] buff = new byte[(int) numBytes];
        int val = file.read(buff);
        if (val != -1) {
            bufferWrite.write(buff);
        }
    }
}