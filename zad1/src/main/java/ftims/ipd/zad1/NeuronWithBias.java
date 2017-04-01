package ftims.ipd.zad1;

/**
 * Created by grzelak on 20-3-17.
 */
public class NeuronWithBias extends Neuron {

    private double bias;

    public NeuronWithBias(double[] weights, double bias) {
        super(weights);
        this.bias = bias;
    }

    @Override
    int numberOfWeights() {
        return super.numberOfWeights() + 1;
    }

    @Override
    double getWeight(int weightNum) {
        if (weightNum == 0) {
            return bias;
        }

        return super.getWeight(weightNum - 1);
    }

    @Override
    public double neuronOutput(double[] input) {
        return super.neuronOutput(input) + bias;
    }

    @Override
    void updateWeight(int weightNum, double newValue) {
        if (weightNum == 0) {
            bias = newValue;
        } else {
            super.updateWeight(weightNum - 1, newValue);
        }
    }

    @Override
    public double getInput(int w) {
        if (w == 0) {
            return 1;
        } else {
            return super.getInput(w - 1);
        }
    }
}
