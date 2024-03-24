import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("iris_training 3.txt"));
        List<DataRow> rows = strings.stream().map(s -> new DataRow(s)).toList();
        Perceptor perceptor = new Perceptor(rows);
        perceptor.train();
    }
}