package IO;
import java.io.*;

/**
 * MyCompressorOutputStream Class
 * The Class extends the abstract class OutputStream
 * responsible on writing compressed data
 */
public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    /**
     * Constructor
     * @param outputStream destination to write bytes stream
     */
    public MyCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    /**
     * override method from OutputStream Class
     * must implement this method
     * @param b array of bytes to write
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
    }

    /**
     * method which responsible on compressing and writing bytes into out OutputStream
     * @param b
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        if (b != null) {
            OutputStreamWriter outWriter = new OutputStreamWriter(out);
            String[] str = new String[b.length];
            for (int i = 0; i < b.length; i++) {
                str[i] = "" + b[i];
            }
            for (int i = 0; i < 12; i++) {
                outWriter.write(str[i]);
                outWriter.write(System.getProperty("line.separator"));//get down a line
            }
            String newbyte = str[12];
            int count = 1;
            for (int i = 13; i < b.length; i++) {
                if (str[i].equals(newbyte)) {
                    count++;
                    if (count > 255) {
                        count = 1;
                        outWriter.write(newbyte);
                        outWriter.write(',');
                        outWriter.write(String.valueOf(255));
                        outWriter.write(System.getProperty("line.separator"));//get down a line
                    }
                    if (i == b.length - 1) {
                        outWriter.write(newbyte);
                        outWriter.write(',');
                        outWriter.write(String.valueOf(count));
                    }
                } else {
                    outWriter.write(newbyte);
                    outWriter.write(',');
                    outWriter.write(String.valueOf(count));
                    outWriter.write(System.getProperty("line.separator"));//get down a line
                    newbyte = str[i];
                    count = 1;
                    if (i == b.length - 1) {
                        outWriter.write(newbyte);
                        outWriter.write(',');
                        outWriter.write(String.valueOf(count));
                    }
                }
            }
            outWriter.close();
            out.close();
        }
    }
}

