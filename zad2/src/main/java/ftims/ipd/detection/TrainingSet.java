package ftims.ipd.detection;

import java.util.Map;

public class TrainingSet {

    private Map<String, double[][]> patternsWithNames;

    public TrainingSet(Map<String, double[][]> patternsWithNames) {
        this.patternsWithNames = patternsWithNames;
    }

    public String[] getAllPatternsNames() {
        return patternsWithNames.keySet().toArray(new String[0]);
    }

    public double[][] getPattern(String patternName) {
        return patternsWithNames.get(patternName);
    }
}
