import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Downloader extends Thread {
    private Data data;
    private String path;

    public Downloader(Data data, String path) {
        this.data = data;
        this.path = path;
    }

    @Override
    public void run() {
        byte[] buf;

        System.out.println("Download the data... (message from downloader)");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try(FileInputStream fis = new FileInputStream(path)){
            buf = new byte[fis.available()];
            fis.read(buf,0,buf.length);

            System.out.println("Download complete! (message from downloader)");

            synchronized (data){
                data.setData(buf);
                data.notifyAll();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
