import java.util.Arrays;

public class DataRow {
    double[] numericAttributes;
    String resultAttribute;

    public DataRow(double[] numericAttributes, String resultAttribute) {
        this.numericAttributes = numericAttributes;
        this.resultAttribute = resultAttribute;
    }
    public DataRow(String raw){
        String[] split = raw.split("\t");
        numericAttributes= new double[split.length-1];
        for(int i=0; i<split.length-1; i++){
            numericAttributes[i]=Double.parseDouble(split[i].replaceAll(",","."));
        }
        resultAttribute=split[split.length-1];
    }
    public DataRow(String raw, boolean isOwnInput){
        String[] split = raw.split("\t");
        numericAttributes= new double[split.length];
        for(int i=0; i<split.length; i++){
            numericAttributes[i]=Double.parseDouble(split[i].replaceAll(",","."));
        }
    }

    @Override
    public String toString() {
        return "DataRow{" +
                "numericAttributes=" + Arrays.toString(numericAttributes) +
                ", resultAttribute='" + resultAttribute + '\'' +
                '}';
    }
}
