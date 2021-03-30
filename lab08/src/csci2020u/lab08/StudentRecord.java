package csci2020u.lab08;

public class StudentRecord {
    private final String studId;
    //private final String studName;
    private final float assignGrade;
    private final float examGrade;
    private final float midtermGrade;
    private final float finalGrade;
    private final char letterGrade;

    public StudentRecord(String studId, float assignGrade, float examGrade, float midtermGrade) {
        this.studId = studId;
        //this.studName = studName;
        this.assignGrade = assignGrade;
        this.examGrade = examGrade;
        this.midtermGrade = midtermGrade;
        this.finalGrade = (float) (0.2*assignGrade + 0.3*midtermGrade +0.5*examGrade);
        if (this.finalGrade >= 80 && this.finalGrade <= 100){
            this.letterGrade = 'A';
        }else if (this.finalGrade >= 70 && this.finalGrade < 80){
            this.letterGrade = 'B';
        }else if (this.finalGrade >= 60 && this.finalGrade < 70){
            this.letterGrade = 'C';
        }else if (this.finalGrade >= 50 && this.finalGrade < 60){
            this.letterGrade = 'D';
        }else{
            this.letterGrade = 'F';
        }
    }

    public String getStudentId() {
        return studId;
    }
    //public void setStudentId(String newId) {
    //    studId = newId;
    //}

    //public String getStudentName() {
    //    return studName;
    //}

    public float getAssignmentGrade() {
        return assignGrade;
    }

    public float getExamGrade() {
        return examGrade;
    }

    public float getMidtermGrade() {
        return midtermGrade;
    }

    public float getFinalGrade() {
        return finalGrade;
    }

    public char getLetterGrade() {
        return letterGrade;
    }




}
