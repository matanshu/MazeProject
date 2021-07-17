package IO;
import java.io.*;

/**
 * MyDecompressorInputStream Class
 * The Class extends the abstract class InputStream
 * responsible on reading and decompressing data
 */
public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    /**
     * Constructor
     * @param inputStream source for reading bytes stream
     */
    public  MyDecompressorInputStream (InputStream inputStream){
        this.in = inputStream;
    }

    /**
     * override method from InputStream Class
     * must implement this method
     * @throws IOException
     */
    @Override
    public int read() throws IOException {
        return 0;
    }

    /**
     * method which responsible on decompressing and reading bytes from in InputStream
     * @param b array of bytes to read
     * @throws IOException
     */
    @Override
    public int read(byte[] b) throws IOException {
        if (b != null) {
            BufferedReader input = new BufferedReader(new InputStreamReader(in));
            String line = input.readLine();
            for(int i=0;i<12;i++){
                b[i] = (byte)Integer.parseInt(line);
               line = input.readLine();
            }
            String[] array = new String[2];
            int i = 12;
            while (line!=null) {
                array = line.split(",");
                int first = Integer.parseInt(array[0]);
                int count = Integer.parseInt(array[1]);
                for (int j = 0; j < count; j++) {
                    b[i] = (byte) first;
                    i++;
                }
                line=input.readLine();
            }
            input.close();
            in.close();
            return 1;
        }
        return 0;
    }
}
