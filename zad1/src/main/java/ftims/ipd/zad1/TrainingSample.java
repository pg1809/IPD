package ftims.ipd.zad1;

import java.io.Serializable;

/**
 * Created by Piotr on 2017-03-19.
 */
public class TrainingSample implements Serializable {

    public final double[] input;

    public final double expectedOutput;

    public TrainingSample(double[] input, double expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }
}
