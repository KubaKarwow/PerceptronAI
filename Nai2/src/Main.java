import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> training = Files.readAllLines(Paths.get("iris_training 3.txt"));
        List<DataRow> rows = training.stream().map(s -> new DataRow(s)).toList();

        List<String> test = Files.readAllLines(Paths.get("iris_test 3.txt"));
        List<DataRow> testRows = test.stream().map(s -> new DataRow(s)).collect(Collectors.toList());

        Perceptor perceptor = new Perceptor(rows);
        perceptor.train();
        int amountOfCheckedOnes=0;
        for(DataRow row: testRows){
            boolean isGood = perceptor.test(row);
            if(isGood) amountOfCheckedOnes++;
        }
        System.out.println("out of "+testRows.size() + " test rows, "+amountOfCheckedOnes+" were correct");
        System.out.println("algorithm works " + (amountOfCheckedOnes*100.0)/testRows.size()+"% of the time");
    }
}