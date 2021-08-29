package factory;

import generator.Student;
import generator.SubjectName;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Random;

public class Cabinet {
    private int numberInCabinet;
    private ConcurrentLinkedQueue<Student> queue;
    private int studentCounter;
    private int numberToGenerate;

    public Cabinet(int numberToGenerate) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.numberInCabinet = 0;
        this.numberToGenerate = numberToGenerate;
    }

    public synchronized void fillCabinet(int id) {

        while (queue.size() >= 10) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }

        int labsCount = randomNumberLabs();
        SubjectName subjectName = randomSubjectName();
        queue.add(new Student(labsCount, subjectName, id));
        studentCounter++;
        numberInCabinet++;
        notify();
    }

    public synchronized Student getFromQueue(SubjectName subjectName) {
        if (queue.size() > 0) {
            if (queue.peek().getSubjectName() == subjectName) {
                notifyAll();
                Student student = queue.peek();
                numberInCabinet--;
                System.out.println(subjectName + " взял на проверку: " + queue.peek().getLabsCount() + " лаб по предмету " + queue.poll().getSubjectName());
                if (queue.size() != 0) {
                    System.out.println(" В очереди теперь " + queue.peek().getId() + " ближайший теперь " + queue.peek().getSubjectName());
                }
                return student;
            }
        }
        return null;
    }

    synchronized public boolean isEnd() {
        return (queue.size() == 0 && (studentCounter == numberToGenerate));
    }

    private int randomNumberLabs() {
        Random random = new Random();
        int numberRandom = random.nextInt(3);
        int numberLabs = 0;
        switch (numberRandom) {
            case 0:
                numberLabs = 10;
                break;
            case 1:
                numberLabs = 20;
                break;
            case 2:
                numberLabs = 100;
                break;
        }
        return numberLabs;
    }

    private SubjectName randomSubjectName() {
        Random random = new Random();
        SubjectName subjectName = null;
        int numberOfSubject = random.nextInt(SubjectName.values().length);
        switch (numberOfSubject) {
            case 0:
                subjectName = SubjectName.MATH;
                break;
            case 1:
                subjectName = SubjectName.OOP;
                break;
            case 2:
                subjectName = SubjectName.PHYSIC;
                break;
        }
        return subjectName;
    }

}