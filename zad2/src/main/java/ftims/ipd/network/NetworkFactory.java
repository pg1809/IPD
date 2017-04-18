package ftims.ipd.network;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NetworkFactory {

    public MadalineNetwork createNetwork(List<double[][]> patterns) {
        List<double[]> patternsAsVectors = flattenPatterns(patterns);
        List<double[]> weightsVectors = normalizePatterns(patternsAsVectors);

        return new MadalineNetwork(weightsVectors);
    }

    private List<double[]> normalizePatterns(List<double[]> patternsAsVectors) {
        return patternsAsVectors.stream().map(pattern -> {
            double patternVectorLength = Arrays.stream(pattern).sum();
            return Arrays.stream(pattern).map(v -> v / Math.sqrt(patternVectorLength)).toArray();
        }).collect(Collectors.toList());
    }

    private List<double[]> flattenPatterns(List<double[][]> patterns) {
        return patterns.stream()
                .map(pattern -> Arrays.stream(pattern).flatMapToDouble(Arrays::stream).toArray())
                .collect(Collectors.toList());
    }
}
