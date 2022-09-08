package chapter_29;

import chapter_28.NineTailModel;

import java.util.List;

public class Ex_05 {
    public static void main(String[] args) {
        NineTailModel nineTailModel = new NineTailModel();
        WeightedNineTailModel weightedNineTailModel = new WeightedNineTailModel();
        List<Integer> path1, path2;
        String result = "NineTailModel and WeightedNineTailModel both result in the same shorted path.";
        for (int i = 0; i < 511; i++) {
            path1 = nineTailModel.getShortestPath(i);
            path2 = weightedNineTailModel.getShortestPath(i);
            System.out.println(i + ": " + path1.size() + " - " + path2.size());
            if (path1.size() != path2.size()) {
                result = "NineTailModel and WeightedNineTailModel don't result in the same shorted path.";
                break;
            }
        }
        System.out.print(result);
    }
}
