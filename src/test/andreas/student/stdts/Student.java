package test.andreas.student.stdts;

public class Student {
    private String firstName;
    private String lastName;
    private String fullName;
    private int matNr;
    private int[] points = new int[6];

    public Student(String firstName, String lastName, int matNr){
        setName(firstName, lastName);

        if(matNr <= 0){ this.matNr = -1;}
        else this.matNr = matNr;
    }

    public void addPoints(int points, int homeworkNr){
        if((homeworkNr <= 0 || homeworkNr > 6) && (points < 0 || points > 10)){ return; }
        this.points[homeworkNr -1] = points;
    }
    public int getIndexPoints(int homeworkNr){
        if(homeworkNr <= 0 || homeworkNr >= 6){ return -1; }
        return this.points[homeworkNr -1];
    }
    public int getStudentsGrade(){
        if(getPoints() <= 30){ return 5; }
        if(getPoints() > 30 && getPoints() <= 37){ return 4; }
        if(getPoints() > 37 && getPoints() <= 45){ return 3; }
        if(getPoints() > 45 && getPoints() <= 52){ return 2; }
        if(getPoints() > 52 && getPoints() <= 60){ return 1; }
        else return -1;
    }
    public String getName(){ return this.fullName; }
    public String getFirstName(){ return this.firstName; }
    public String getLastName(){ return this.lastName; }
    public int getMatNr(){ return this.matNr; }
    public int getPoints(){
        int sum = 0;
        for(int i = 0; i < points.length; i++){
            sum += this.points[i];
        }
        return sum;
    }
    public void setPointsArray(int[] points){
        for(int i = 0; i < this.points.length; i++){
            this.points[i] = points[i];
        }
    }
    public void setName(String firstName, String lastName){
        if(firstName == null){ this.fullName += ""; }
        else{
            this.fullName = firstName + " ";
            this.firstName = firstName;
        }
        if(lastName == null){ this.fullName += ""; }
        else{
            this.fullName += lastName;
            this.lastName = lastName;
        }
    }
    public void setMatNr(int matNr){
        if(matNr <= 0){ this.matNr = -1;}
        else this.matNr = matNr;
    }
    public String toString(){ return getName() + ", " + getMatNr() + ", " + getPoints() + ", Note: " + getStudentsGrade(); }
}
