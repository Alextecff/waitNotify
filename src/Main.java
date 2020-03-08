public class Main {
    public static void main(String[] args) {
        Data data = new Data();

        for(int i = 0; i < 5; i++){
            Writer writer = new Writer(data,"src/NewFile.txt");
            writer.start();
        }

        Downloader downloader = new Downloader(data, "src/file.txt");
        downloader.start();
    }
}
