package ftims.ipd.network;

import java.util.List;
import java.util.stream.Collectors;


public class MadalineNetwork {

    private List<Neuron> neurons;

    MadalineNetwork(List<double[]> weightsOfNeurons) {
        neurons = weightsOfNeurons.stream().map(Neuron::new).collect(Collectors.toList());
    }

    public double[] networkOutput(double[] input) {
        return neurons.stream().mapToDouble(n -> n.neuronOutput(input)).toArray();
    }
}
