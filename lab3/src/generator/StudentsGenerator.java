package generator;

import factory.Cabinet;

public class StudentsGenerator implements Runnable {

    Cabinet cabinet;
    final private int numberToGenerate;

    public StudentsGenerator(int numberToGenerate, Cabinet cabinet) {
        this.numberToGenerate = numberToGenerate;
        this.cabinet = cabinet;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberToGenerate; i++) {
            cabinet.fillCabinet(i);
        }
    }

    public int getLastId() {
        return numberToGenerate - 1;
    }

}