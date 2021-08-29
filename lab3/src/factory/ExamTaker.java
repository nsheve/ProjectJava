package factory;

import generator.StudentsGenerator;
import generator.SubjectName;
import robots.Robot;

public class ExamTaker {
    Cabinet cabinet;
    StudentsGenerator studentsGenerator;

    public ExamTaker(int studentsNumber) {
        cabinet = new Cabinet(studentsNumber);
        studentsGenerator = new StudentsGenerator(studentsNumber, cabinet);
    }

    public void takeExam() {
        Thread mathRobotThread = new Thread(new Robot(SubjectName.MATH, cabinet));
        Thread physicRobotThread = new Thread(new Robot(SubjectName.PHYSIC, cabinet));
        Thread oopRobotThread = new Thread(new Robot(SubjectName.OOP, cabinet));
        Thread generatorThread = new Thread(studentsGenerator);

        mathRobotThread.start();
        physicRobotThread.start();
        oopRobotThread.start();
        generatorThread.start();
    }
}
