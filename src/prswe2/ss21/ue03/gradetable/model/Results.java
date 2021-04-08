package prswe2.ss21.ue03.gradetable.model;

public class Results {

    public static final int NR_ASSIGNMENTS = 9;
    public static final int NR_VALID = 8;
    public static final int POINTS_VALID = 8;
    public static final int MIN_POINTS = 0;
    public static final int MAX_POINTS = 24;
    public static final int UNDEFINED = -1;

    private final Student student;
    private final int[] assignments = new int[NR_ASSIGNMENTS];

    public Results(Student student) {
        this.student = student;
        for (int i = 0; i < NR_ASSIGNMENTS; i++) {
            assignments[i] = Results.UNDEFINED;
        }
    }

    public void setPoints(int idx, int ps) {
        if (idx >= NR_ASSIGNMENTS || idx < 0 || ps > MAX_POINTS || ps < MIN_POINTS) {
            return;
        }
        this.assignments[idx] = ps;
    }

    public Student getStudent() {
        return this.student;
    }

    public int[] getAssignments() {
        return this.assignments;
    }

    public int getAssignment(int index) {
        return this.assignments[index];
    }

    public int getGradePoints() {
        int sum = 0;
        for (int i = 0; i < assignments.length; i++) {
            if (!(assignments[i] == Results.UNDEFINED)) {
                sum += assignments[i];
            }
        }
        return sum;
    }

    public String getGrade() {
        int resultsValid = 0;
        for (int i = 0; i < assignments.length; i++) {
            if (assignments[i] == Results.UNDEFINED) {
                return "-";
            }
            if (assignments[i] >= POINTS_VALID) {
                resultsValid++;
            }
        }
        if (resultsValid < NR_VALID) {
            return "Nicht genügend";
        }
        int maxPoints = NR_ASSIGNMENTS * MAX_POINTS;
        int studentResult = this.getGradePoints();
        if (studentResult >= (maxPoints * 0.875)) {
            return "Sehr gut";
        } else if (studentResult >= (maxPoints * 0.750)) {
            return "Gut";
        } else if (studentResult >= (maxPoints * 0.625)) {
            return "Befriedigend";
        } else if (studentResult >= (maxPoints * 0.500)) {
            return "Gut";
        } else {
            return "Sehr gut";
        }
    }
}