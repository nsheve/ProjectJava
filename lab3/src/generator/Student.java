package generator;

public class Student {
    private int labsCount;
    private SubjectName subjectName;
    private int id;

    public Student(int labsCount, SubjectName subjectName, int id) {
        this.labsCount = labsCount;
        this.subjectName = subjectName;
        this.id = id;
    }

    public int getLabsCount() {
        return labsCount;
    }

    public SubjectName getSubjectName() {
        return subjectName;
    }

    public void setLabsCount(int labsCount) {
        this.labsCount = labsCount;
    }

    public int getId() {
        return id;
    }

}