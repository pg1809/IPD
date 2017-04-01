package ftims.ipd.zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by grzelak on 20-3-17.
 */
public class LinearTrainingSetGenerator implements TrainingSetGenerator {

    private final double slopeRandomDeviation;

    private final double inputLowerBound;

    private final double inputUpperBound;

    private final double interceptsSpan;

    private Random random = new Random();

    public LinearTrainingSetGenerator(double slopeRandomDeviation, double inputLowerBound, double inputUpperBound,
            double interceptsSpan) {
        this.slopeRandomDeviation = slopeRandomDeviation;
        this.inputLowerBound = inputLowerBound;
        this.inputUpperBound = inputUpperBound;
        this.interceptsSpan = interceptsSpan;
    }

    @Override
    public List<TrainingSample> generateTrainingSet(int inputDimensions, int setSize) {
        double[] intercepts = random.doubles(inputDimensions, -interceptsSpan / 2, interceptsSpan / 2)
                .toArray();

        List<TrainingSample> trainingSet = new ArrayList<>(setSize);
        for (int i = 0; i < setSize; i++) {
            TrainingSample trainingSample = generateTrainingSample(intercepts);
            trainingSet.add(trainingSample);
        }
        return trainingSet;
    }

    private TrainingSample generateTrainingSample(double[] intercepts) {
        double[] inputs = random.doubles(intercepts.length, inputLowerBound, inputUpperBound).toArray();
        double output = 0;
        for (int i = 0; i < inputs.length; i++) {
            output = inputs[i] * intercepts[i];
        }
        output += random.nextDouble() * slopeRandomDeviation * 2;

        return new TrainingSample(inputs, output);
    }
}
