package ftims.ipd.zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by grzelak on 20-3-17.
 */
public class RandomTrainingSetGenerator implements TrainingSetGenerator {

    private final double inputLowerBound;

    private final double inputUpperBound;

    private final double outputLowerBound;

    private final double outputUpperBound;

    private final Random random = new Random();

    public RandomTrainingSetGenerator(double inputLowerBound, double inputUpperBound, double outputLowerBound,
            double outputUpperBound) {
        this.inputLowerBound = inputLowerBound;
        this.inputUpperBound = inputUpperBound;
        this.outputLowerBound = outputLowerBound;
        this.outputUpperBound = outputUpperBound;
    }

    @Override
    public List<TrainingSample> generateTrainingSet(int inputDimensions, int setSize) {
        List<TrainingSample> trainingSet = new ArrayList<>(setSize);
        for (int i = 0; i < setSize; i++) {
            double[] sampleInput = random.doubles(inputDimensions, inputLowerBound, inputUpperBound).toArray();
            double expectedOutput = random.nextDouble()
                    * (outputUpperBound - outputLowerBound) + outputLowerBound;
            trainingSet.add(new TrainingSample(sampleInput, expectedOutput));
        }

        return trainingSet;
    }
}
