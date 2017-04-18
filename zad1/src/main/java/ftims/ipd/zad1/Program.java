package ftims.ipd.zad1;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class Program {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        int inputDimensions = scanner.nextInt();
        double weightsLowerBound = scanner.nextDouble();
        double weightsUpperBound = scanner.nextDouble();

        Random random = new Random();
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

        Trainer trainer = new DeltaRuleTrainer(learningRate, epochsNum, 1);
        TrainingResult result = trainer.train(trainingSet, neuron);

        for (TrainingSample sample : trainingSet) {
            double neuronOutput = neuron.neuronOutput(sample.input);
            System.out.println("Neuron output: " + NumberFormat.getNumberInstance().format(neuronOutput) +
                    "; expected output: " + NumberFormat.getNumberInstance().format(sample.expectedOutput));
        }

        System.out.println("MSE for training set: " + NumberFormat.getNumberInstance().format(result.meanSquaredError));
        System.out.println("The number of epochs it took to train neuron: " + result.epochsNum);
    }
}
