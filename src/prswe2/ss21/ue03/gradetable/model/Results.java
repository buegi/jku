package prswe2.ss21.ue03.gradetable.model;

import java.util.HashMap;
import java.util.Map;

public class Results {

    public static final int NR_ASSIGNMENTS = 9;
    public static final int NR_VALID = 8;
    public static final int POINTS_VALID = 8;
    public static final int MIN_POINTS = 0;
    public static final int MAX_POINTS = 24;
    public static final int UNDEFINED = -1;

    private Student student;
    private final Map<Integer, Integer> pointMap = new HashMap<>();

    public Results(Student student) {
        this.student = student;
    }

    public void setPoints(int idx, int ps) {
          // TODO
//        if (this.pointMap.size() >= NR_ASSIGNMENTS || idx >= NR_ASSIGNMENTS
//                || ps > MAX_POINTS || this.pointMap.containsKey(idx)) {
//            return;
//        }
        this.pointMap.put(idx, ps);
    }
}
