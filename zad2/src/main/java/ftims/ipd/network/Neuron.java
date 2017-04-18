package ftims.ipd.network;

class Neuron {

    private final double[] weights;

    Neuron(double[] weights) {
        this.weights = weights;
    }

    double neuronOutput(double[] input) {
        if (weights.length != input.length) {
            throw new IllegalArgumentException("Input vector must be of the same dimension as weights num");
        }

        double weightedSum = 0;
        for (int i = 0; i < weights.length; i++) {
            weightedSum += weights[i] * input[i];
        }

        return weightedSum;
    }
}
