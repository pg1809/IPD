package ftims.ipd.zad1;

import java.util.List;

/**
 * Created by Piotr on 2017-03-19.
 */
public interface Trainer {

    TrainingResult train(List<TrainingSample> trainingSet, Neuron neuron);
}
