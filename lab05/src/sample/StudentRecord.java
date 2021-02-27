package sample;

public class StudentRecord {
    private String studId;
    private String studName;
    private float assignGrade;

    public StudentRecord(String studId, String studName, float assignGrade) {
        this.studId = studId;
        this.studName = studName;
        this.assignGrade = assignGrade;
    }

    public String getStudentId() {
        return studId;
    }

    public String getStudentName() {
        return studName;
    }

    public float getAssignmentGrade() {
        return assignGrade;
    }


}
