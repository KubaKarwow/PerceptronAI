import java.util.Arrays;
import java.util.List;

public class Perceptor {
    List<DataRow> training;
    double[] weights;

    @Override
    public String toString() {
        return "Perceptor{" +
                "weights=" + Arrays.toString(weights) +
                '}';
    }

    // do wektora wag na koniec dodajemy odrazu próg
    // mamy wektory X na końcu -1
    // wzor to W'= W - (d-y)L*X
    // L=1
    Perceptor(List<DataRow> training) {
        this.training = training;
        weights = new double[training.get(0).numericAttributes.length + 1];
    }

    public boolean test(DataRow row) {
        double skalar = 0.0;
        for (int j = 0; j < weights.length - 1; j++) {
            skalar += weights[j] * row.numericAttributes[j];
        }
        if (skalar >= weights[weights.length - 1]){
            return row.resultAttribute.strip().equals("Iris-setosa");
        } else {
            return !row.resultAttribute.strip().equals("Iris-setosa");
        }

    }
    public String testOwnInput(DataRow row) {
        double skalar = 0.0;
        for (int j = 0; j < weights.length - 1; j++) {
            skalar += weights[j] * row.numericAttributes[j];
        }
        if (skalar >= weights[weights.length - 1]){
            return "Iris-Setosa";
        } else {
            return "Not Iris-Setosa";
        }

    }
    public void train() {
        for (int i = 0; i < 500; i++) {
            boolean isEverythingGood = true;
            for (DataRow row : training) {
                boolean b = processRow(row);
                if (!b) isEverythingGood = false;
            }
            if (isEverythingGood) {
                break;
            }
        }
    }

    public boolean processRow(DataRow row) {
        double skalar = 0.0;
        int actualResult = -1;
        int properResult = -1;
        for (int i = 0; i < row.numericAttributes.length; i++) {
            skalar += weights[i] * row.numericAttributes[i];
        }
        skalar += weights[weights.length - 1] * -1;
        actualResult = skalar >= weights[weights.length - 1] ? 1 : 0;
        properResult = row.resultAttribute.equals("Iris-setosa") ? 1 : 0;
        adjustWeights(properResult, actualResult, row);
        if (!(actualResult == properResult)) processRow(row);
        return actualResult == properResult;

    }

    public void adjustWeights(int properResult, int actualResult, DataRow dataRow) {
        if (properResult == actualResult) return;
        double[] newWeights = new double[weights.length];
        for (int i = 0; i < weights.length - 1; i++) {
            newWeights[i] = weights[i] + (properResult - actualResult) * dataRow.numericAttributes[i];
        }
        newWeights[newWeights.length - 1] = weights[weights.length - 1] + (properResult - actualResult) * -1;
        weights = newWeights;
    }

}
