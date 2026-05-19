import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Sum {

    static class SumTask implements Runnable {
        private final String path;
        private long result;
        private Exception error;

        public SumTask(String path) {
            this.path = path;
            this.result = -1;
        }

        @Override
        public void run() {
            try {
                this.result = Sum.sum(this.path);
            } catch (Exception e) {
                this.error = e;
            }
        }

        public String getPath() {
            return path;
        }

        public long getResult() {
            return result;
        }

        public Exception getError() {
            return error;
        }
    }

    public static int sum(FileInputStream fis) throws IOException {
        int byteRead;
        int sum = 0;

        while ((byteRead = fis.read()) != -1) {
            sum += byteRead;
        }

        return sum;
    }

    public static long sum(String path) throws IOException {
        Path filePath = Paths.get(path);

        if (Files.isRegularFile(filePath)) {
            try (FileInputStream fis = new FileInputStream(filePath.toString())) {
                return sum(fis);
            }
        }

        throw new RuntimeException("Non-regular file: " + path);
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: java Sum filepath1 filepath2 filepathN");
            System.exit(1);
        }

        List<SumTask> tasks = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (String path : args) {
            SumTask task = new SumTask(path);
            Thread thread = new Thread(task);

            tasks.add(task);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (SumTask task : tasks) {
            if (task.getError() == null) {
                System.out.println(task.getPath() + " : " + task.getResult());
            }
        }
    }
}