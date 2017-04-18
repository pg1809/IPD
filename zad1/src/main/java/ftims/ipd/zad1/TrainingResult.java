package ftims.ipd.zad1;

/**
 * Created by Piotr on 2017-04-02.
 */
public class TrainingResult {

    public final int epochsNum;

    public final double meanSquaredError;

    public TrainingResult(int epochsNum, double meanSquaredError) {
        this.epochsNum = epochsNum;
        this.meanSquaredError = meanSquaredError;
    }
}
