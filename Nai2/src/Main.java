import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("please supply the attributes, amount:"+rows.get(0).numericAttributes.length);
            String row = scanner.nextLine();
            DataRow dataRow = new DataRow(row,true);
            String s = perceptor.testOwnInput(dataRow);
            System.out.println("result for these attributes is:"+s);

            System.out.println("would you like to continue 1-yes,not 1-no");
            int i = scanner.nextInt();
            if(i!=1) return;

        }


    }
}