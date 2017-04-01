package ftims.ipd.zad1;

/**
 * Created by Piotr on 2017-03-19.
 */
public class Neuron {

    private final double[] weights;

    private final double[] inputValues;

    public Neuron(double[] weights) {
        this.weights = weights;
        inputValues = new double[weights.length];
    }

    public double neuronOutput(double[] input) {
        if (weights.length != input.length) {
            throw new IllegalArgumentException("Input vector must be of the same dimension as weights num");
        }

        double weightedSum = 0;
        for (int i = 0; i < weights.length; i++) {
            inputValues[i] = input[i];
            weightedSum += weights[i] * input[i];
        }

        return weightedSum;
    }

    int numberOfWeights() {
        return weights.length;
    }

    void updateWeight(int weightNum, double newValue) {
        weights[weightNum] = newValue;
    }

    double getWeight(int weightNum) {
        return weights[weightNum];
    }

    public double getInput(int w) {
        return inputValues[w];
    }
}
