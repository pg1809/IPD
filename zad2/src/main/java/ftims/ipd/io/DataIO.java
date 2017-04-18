package ftims.ipd.io;

import ftims.ipd.detection.TrainingSet;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataIO {

    private static final char PATTERN_PIXEL = '#';

    public static TrainingSet loadTrainingSetFromFile(String trainingSetFile) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(trainingSetFile))) {
            int patternsNum = Integer.parseInt(scanner.nextLine());
            int patternWidth = Integer.parseInt(scanner.nextLine());
            int patternHeight = Integer.parseInt(scanner.nextLine());

            Map<String, double[][]> patternsWithNames = new HashMap<>();
            for (int i = 0; i < patternsNum; i++) {
                String patternName = scanner.nextLine();
                double[][] pattern = loadPattern(scanner, patternHeight, patternWidth);
                patternsWithNames.put(patternName, pattern);
            }

            return new TrainingSet(patternsWithNames);
        }
    }

    public static List<double[][]> loadTestSetFromFile(String testSetFile) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(testSetFile))) {
            int patternsNum = Integer.parseInt(scanner.nextLine());
            int patternWidth = Integer.parseInt(scanner.nextLine());
            int patternHeight = Integer.parseInt(scanner.nextLine());

            List<double[][]> testSet = new ArrayList<>(patternsNum);
            for (int i = 0; i < patternsNum; i++) {
                double[][] pattern = loadPattern(scanner, patternHeight, patternWidth);
                testSet.add(pattern);
            }
            return testSet;
        }
    }

    private static double[][] loadPattern(Scanner scanner, int patternHeight, int patternWidth) {
        double[][] pattern = new double[patternHeight][patternWidth];

        for (int h = 0; h < patternHeight; h++) {
            String line = scanner.nextLine();
            for (int w = 0; w < patternWidth; w++) {
                char pixel = line.charAt(w);
                if (pixel == PATTERN_PIXEL) {
                    pattern[h][w] = 1;
                } else {
                    pattern[h][w] = 0;
                }
            }
        }

        return pattern;
    }
}
