package ftims.ipd.zad1;

import java.util.List;

/**
 * Created by grzelak on 20-3-17.
 */
public interface TrainingSetGenerator {

    List<TrainingSample> generateTrainingSet(int inputDimensions, int setSize);
}
