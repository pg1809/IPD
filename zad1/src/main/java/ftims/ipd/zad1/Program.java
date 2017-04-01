package ftims.ipd.zad1;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class Program {

    private static final long SEED = 1000;

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        int inputDimensions = scanner.nextInt();
        double weightsLowerBound = scanner.nextDouble();
        double weightsUpperBound = scanner.nextDouble();

        Random random = new Random(SEED);
        double[] initialNeuronWeights = random.doubles(inputDimensions, weightsLowerBound, weightsUpperBound).toArray();

        Neuron neuron = new Neuron(initialNeuronWeights);

        int trainingSetSize = scanner.nextInt();
        double inputLowerBound = scanner.nextDouble();
        double inputUpperBound = scanner.nextDouble();

        TrainingSetGenerator generator;
        String trainingSetType = scanner.next();
        if (trainingSetType.equals("linear")) {
            double interceptSpan = scanner.nextDouble();
            double slopeRandomDeviation = scanner.nextDouble();
            generator = new LinearTrainingSetGenerator(slopeRandomDeviation, inputLowerBound, inputUpperBound, interceptSpan);
        } else {
            double expectedOutputLowerBound = scanner.nextDouble();
            double expectedOutputUpperBound = scanner.nextDouble();
            generator = new RandomTrainingSetGenerator(inputLowerBound, inputUpperBound, expectedOutputLowerBound, expectedOutputUpperBound);
        }

        double learningRate = scanner.nextDouble();
        int epochsNum = scanner.nextInt();

        List<TrainingSample> trainingSet = generator.generateTrainingSet(inputDimensions, trainingSetSize);

        Trainer trainer = new DeltaRuleTrainer(learningRate, epochsNum);
        trainer.train(trainingSet, neuron);

        double mse = 0;
        for (TrainingSample sample : trainingSet) {
            double neuronOutput = neuron.neuronOutput(sample.input);
            mse += Math.pow(neuronOutput - sample.expectedOutput, 2);
            System.out.println("Neuron output: " + NumberFormat.getNumberInstance().format(neuronOutput) +
                    "; expected output: " + NumberFormat.getNumberInstance().format(sample.expectedOutput));
        }

        mse /= trainingSet.size();
        System.out.println("MSE for training set: " + NumberFormat.getNumberInstance().format(mse));
    }
}
