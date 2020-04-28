package ue06.map.app;


import java.util.Optional;

import ue06.list.List;
import ue06.map.ArrayMap;
import ue06.map.Map;
import ue06.map.Map.Entry;

public class AssignmentRecordsMain {

    public static void main(String[] args) {
        Map<String, List<Integer>> records = new ArrayMap<>();

        records.put("Huber", List.of(8, 12, 22, 21, 20, 0, 17, 19, 3, 18, 16));
        records.put("Maier", List.of(19, 12, 22, 21, 20, 24, 17, 19, 12, 18, 16));
        records.put("Berger", List.of(19, 0, 22, 21, 20, 0, 17, 19, 0, 18, 0));
        records.put("Mueller", List.of(16, 21, 22, 20, 17, 24, 17, 23, 18, 17, 19));
        records.put("Reiter", List.of(24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24));
        records.put("Hofer", List.of(17, 15, 3, 7, 8, 0, 0, 0, 0, 0, 0));
        records.put("Kaiser", List.of(21, 22, 21, 23, 24, 24, 21, 22, 19, 22, 24));
        records.put("Fischer", List.of(23, 23, 22, 22, 20, 23, 19, 19, 20, 18, 19));
        records.put("Schauer", List.of(24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24));

        // TODO:  Number solved
        Map<String, Integer> nrSolved;
        nrSolved = records.map((k, v) -> v.filter(a -> a >= 8).size());
        System.out.println("nrSolved: " + nrSolved.toString());

        // TODO:  Sum of points total
        Map<String, Integer> pointsTotal;
        pointsTotal = records.map((k, v) -> v.filter(a -> a >= 8).reduce(0, Integer::sum));
        System.out.println("pointsTotal: " + pointsTotal.toString());

        // TODO:  Average points
        Map<String, Double> avrgPoints;
        avrgPoints = records.map((k, v) -> v.filter(a -> a >= 8)).map((k, v) -> v.reduce(0.0, (a, b) -> a + b) / v.size());
        System.out.println("avrgPoints: " + avrgPoints.toString());

        // TODO:  Find a student which has 24 points for all assignments
        Optional<Entry<String, List<Integer>>> all24;
        all24 = records.find((k, v) -> v.all(a -> a == 24));
        System.out.println("all24: " + all24.toString());

        // TODO:  Negative results
        Map<String, List<Integer>> negative;
        negative = records.filter((k, v) -> {
            List<Integer> posSolved = v.filter(x -> x >= 8);
            int avgPoints = (v.filter(x -> x >= 8).reduce(0, Integer::sum)) / posSolved.size();
            return (posSolved.size() < 9 || avgPoints < 12);
        });
        System.out.println("negative: " + negative.toString());

        // TODO:  Grades
        Map<String, Grade> grades;
        grades = records.map((k, v) -> v.filter(val -> val >= 8)).map((k, v) -> {
            double avgPoints = v.reduce(0, Integer::sum) / v.size();
            if (avgPoints < 12 || v.size() < 9) {
                return Grade.INSUFFICIENT;
            } else if (avgPoints < 15) {
                return Grade.SUFFICIENT;
            } else if (avgPoints < 18) {
                return Grade.SATISFACTORY;
            } else if (avgPoints < 21) {
                return Grade.GOOD;
            }
            return Grade.EXCELLENT;
        });
        System.out.println("grades: " + grades.toString());

        // TODO:  Grouping students by grades
        Map<Grade, List<String>> gradesDistr;
        gradesDistr = null;
        // System.out.println("gradesDistr: " + gradesDistr.toString());
    }
}