import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Writer extends Thread {
    private Data data;
    private String path;

    public Writer(Data data, String path){
        this.data = data;
        this.path = path;
    }

    @Override
    public void run() {
        System.out.println("Waiting for the data... (message from Writer)");

        synchronized (data){
            try {
                data.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("We got the data!!! (message from Writer)");

        byte[] buf = data.getData();

        /*for (int i = 0; i < buf.length; i++){
            buf[i]++;
        }*/

        try(FileOutputStream fos = new FileOutputStream(path, true)) {
            fos.write(buf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Modyfication complete! You can see a new file. (message from Writer)");
    }

}
