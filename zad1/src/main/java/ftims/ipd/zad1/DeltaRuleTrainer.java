package ftims.ipd.zad1;

import java.util.Collections;
import java.util.List;

/**
 * Created by Piotr on 2017-03-19.
 */
public class DeltaRuleTrainer implements Trainer {

    private final double learningRate;

    private final int epochsNum;

    private final double errorThreshold;

    public DeltaRuleTrainer(double learningRate, int epochsNum, double errorThreshold) {
        this.learningRate = learningRate;
        this.epochsNum = epochsNum;
        this.errorThreshold = errorThreshold;
    }

    @Override
    public TrainingResult train(List<TrainingSample> trainingSet, Neuron neuron) {
        double error = 0;
        for (int k = 1; k <= epochsNum; k++) {
            Collections.shuffle(trainingSet);

            for (TrainingSample trainingSample : trainingSet) {
                double neuronOutput = neuron.neuronOutput(trainingSample.input);
                double delta = trainingSample.expectedOutput - neuronOutput;

                for (int w = 0; w < neuron.numberOfWeights(); ++w) {
                    double currentWeight = neuron.getWeight(w);
                    double updatedWeight = currentWeight + learningRate * delta * neuron.getInput(w);
                    neuron.updateWeight(w, updatedWeight);
                }
            }

            error = 0;
            for (TrainingSample trainingSample : trainingSet) {
                double neuronOutput = neuron.neuronOutput(trainingSample.input);
                double delta = trainingSample.expectedOutput - neuronOutput;

                error += Math.pow(delta, 2);
            }

            error /= trainingSet.size();
            if (error <= errorThreshold) {
                return new TrainingResult(k, error);
            }
        }

        return new TrainingResult(epochsNum, error);
    }
}
