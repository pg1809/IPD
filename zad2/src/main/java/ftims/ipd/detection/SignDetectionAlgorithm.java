package ftims.ipd.detection;

import ftims.ipd.network.MadalineNetwork;
import ftims.ipd.network.NetworkFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SignDetectionAlgorithm {

    private MadalineNetwork network;

    private String[] patternsNames;

    public SignDetectionAlgorithm(TrainingSet trainingSet) {
        patternsNames = trainingSet.getAllPatternsNames();
        List<double[][]> patterns = Arrays.stream(patternsNames)
                .map(trainingSet::getPattern).collect(Collectors.toList());

        NetworkFactory networkFactory = new NetworkFactory();
        network = networkFactory.createNetwork(patterns);
    }

    public DetectionOutput detectSign(double[][] signMatrix) {
        double[] featureVector = Arrays.stream(signMatrix).flatMapToDouble(Arrays::stream).toArray();
        double featureValuesSum = Arrays.stream(featureVector).sum();

        double[] featureVectorNormalized = Arrays.stream(featureVector)
                .map(feature -> feature / Math.sqrt(featureValuesSum))
                .toArray();

        double[] networkOutput = network.networkOutput(featureVectorNormalized);
        Map<String, Double> patternsWithConfidence = new HashMap<>();

        int recognizedPatternIdx = 0;
        double maxConfidence = Double.MIN_VALUE;

        for (int i = 0; i < networkOutput.length; i++) {
            double confidence = networkOutput[i];
            if (confidence > maxConfidence) {
                maxConfidence = confidence;
                recognizedPatternIdx = i;
            }
            patternsWithConfidence.put(patternsNames[i], confidence);
        }

        String recognizedPattern = patternsNames[recognizedPatternIdx];

        return new DetectionOutput(patternsWithConfidence, recognizedPattern);
    }
}
