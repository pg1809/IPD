package ftims.ipd.zad1;

import java.util.Collections;
import java.util.List;

/**
 * Created by Piotr on 2017-03-19.
 */
public class DeltaRuleTrainer implements Trainer {

    private final double learningRate;

    private final int epochsNum;

    public DeltaRuleTrainer(double learningRate, int epochsNum) {
        this.learningRate = learningRate;
        this.epochsNum = epochsNum;
    }

    @Override
    public void train(List<TrainingSample> trainingSet, Neuron neuron) {
        for (int i = 0; i < epochsNum; i++) {
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
        }
    }
}
