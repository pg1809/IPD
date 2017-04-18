package ftims.ipd.detection;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Piotr on 2017-04-17.
 */
public class DetectionOutput {

    private Map<String, Double> patternsWithConfidence;

    private String recognizedPattern;

    public DetectionOutput(Map<String, Double> patternsWithConfidence, String recognizedPattern) {
        this.patternsWithConfidence = patternsWithConfidence;
        this.recognizedPattern = recognizedPattern;
    }

    public Map<String, Double> getPatternsWithConfidence() {
        return Collections.unmodifiableMap(patternsWithConfidence);
    }

    public String getRecognizedPattern() {
        return recognizedPattern;
    }
}
