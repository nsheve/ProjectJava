package robots;

import factory.Cabinet;
import generator.Student;
import generator.SubjectName;

public class Robot implements Runnable{
    SubjectName subjectName;
    Cabinet cabinet;


    public Robot(SubjectName subjectName, Cabinet cabinet) {
        this.subjectName = subjectName;
        this.cabinet = cabinet;
    }

    @Override
    public void run() {

        while (!cabinet.isEnd()) {
            try {
                Thread.currentThread().setName("Robot" + subjectName);

                Student student = cabinet.getFromQueue(subjectName);
                if (student != null) {
                    while (student.getLabsCount() > 0) {

                        System.out.println("Роботу " + Thread.currentThread().getName() + " осталось принять " + student.getLabsCount() + " работ" + " у студента :" + student.getId());
                        student.setLabsCount(student.getLabsCount() - 5);
                    }

                    System.out.println("Робот " + Thread.currentThread().getName() + " освободился");

                }
                else if (cabinet.isEnd()) {
                    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
