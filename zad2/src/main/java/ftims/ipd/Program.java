package ftims.ipd;

import ftims.ipd.detection.DetectionOutput;
import ftims.ipd.detection.SignDetectionAlgorithm;
import ftims.ipd.detection.TrainingSet;
import ftims.ipd.io.DataIO;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public class Program {

    public static void main(String[] args) throws IOException {
        String trainingSetFile = args[0];
        String testSetFile = args[1];

        TrainingSet trainingSet = DataIO.loadTrainingSetFromFile(trainingSetFile);
        SignDetectionAlgorithm signDetectionAlgorithm = new SignDetectionAlgorithm(trainingSet);

        List<double[][]> testSet = DataIO.loadTestSetFromFile(testSetFile);
        for (int i = 1; i <= testSet.size(); ++i) {
            double[][] testPattern = testSet.get(i - 1);
            System.out.println("Test example nr " + i);
            DetectionOutput detectionOutput = signDetectionAlgorithm.detectSign(testPattern);
            for (Map.Entry<String, Double> outputsEntry : detectionOutput.getPatternsWithConfidence().entrySet()) {
                System.out.println("Network recognized pattern: " + outputsEntry.getKey()
                        + " with confidence: " + NumberFormat.getNumberInstance().format(outputsEntry.getValue()));
            }
            System.out.println("Pattern recognized by network: " + detectionOutput.getRecognizedPattern() + "\n");
        }
    }
}
