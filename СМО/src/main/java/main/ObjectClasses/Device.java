package main.ObjectClasses;

import main.Main;

//прибор
public class Device {
    private static int countRequest = 0;  //количество заявок обработанных всеми приборами
    private static double allTime;       //время обработки всех заявок всеми приборами
    private static double lambda = Main.lambda;

    private Source.Request request;     //заявка, которая находится на приборе
    private int number;                 //номер прибора
    private double timeEmpty;           //время простоя (сумма timeAdd текущей заявки - timeOut предыдущей заявки
    private double timeAdd;             //время поступения заявки
    private double timeOut;             //время ухода из прибора
    private double timeInDevice;        //время обработки заявок этим прибором
    private double timeToTreatment;     //время, которое нужно на обработку
    private double tForSource;
    private int numberSource;
    private int countRequestThis;

    public Device(int number){
        this.number = number;
        lambda = Main.lambda;
    }

    public void add(Source.Request request, int numberSource){
        this.request = request;
        this.numberSource = numberSource;
        request.setInDevice(true);
        timeAdd = Main.systemTime;
        timeEmpty = timeAdd - timeOut;

        StringBuilder forTableCount = Draw.draw("-", 22);
        StringBuilder forTableCountRefus = Draw.draw("-",22);
        StringBuilder forTableAddTime = Draw.draw(timeAdd, 12);
        String action = "поступление " + numberSource + "." + request.getNumber();
        StringBuilder forTableAction = Draw.draw(action, 22);
        System.out.println("|     П" + getNumber() +"    " + forTableAddTime + forTableAction + forTableCount + forTableCountRefus+ "|");
        System.out.println("____________|_____________|______________________|______________________|______________________|");
        treatment();
    }

    public void delete() {
        StringBuilder forTableCount = Draw.draw("-", 22);
        StringBuilder forTableCountRefus = Draw.draw("-", 22);
        StringBuilder forTableAddTime = Draw.draw(timeAdd, 12);
        String action = "удаление " + numberSource + "." + request.getNumber();
        StringBuilder forTableAction = Draw.draw(action, 22);
        System.out.println("|     П" + getNumber() +"    " + forTableAddTime + forTableAction + forTableCount + forTableCountRefus+ "|");
        System.out.println("____________|_____________|______________________|______________________|______________________|");

        forTableCount = Draw.draw("-", 22);
        forTableCountRefus = Draw.draw("-", 22);
        forTableAddTime = Draw.draw(timeAdd, 12);
        forTableAction = Draw.draw("ожидает", 22);
        System.out.println("|     П" + getNumber() +"    " + forTableAddTime + forTableAction + forTableCount + forTableCountRefus+ "|");
        System.out.println("____________|_____________|______________________|______________________|______________________|");

        timeOut = Main.systemTime;
        timeInDevice += timeOut - timeAdd;
        allTime += timeOut - timeAdd;
        tForSource = timeOut - timeAdd;
        countRequest++;
        countRequestThis++;
        request.setInDevice(false);
        request = null;
        numberSource = 0;
    }

    public void clear(){
        this.request = null;
        this.number = 0;
        this.timeEmpty = 0;
        this.timeAdd = 0;
        this.timeOut = 0;
        this.timeInDevice = 0;
        this.timeToTreatment = 0;
        this.tForSource = 0;
        this.numberSource = 0;
        this.countRequestThis = 0;
        countRequest = 0;
        allTime = 0;
    }

    public boolean isEmpty(){
       return request == null;   //находится ли прибор в простое
    }

    /* Обработка заявки: вычисляем время, когда прибор должен закончить обработку */
    public void treatment(){
          timeToTreatment = timeAdd + Math.log(1 - Math.random()) / (- lambda);
    }

    public double gettForSource() {
        return tForSource;
    }

    public int getNumberSource() {
        return numberSource;
    }

    public double getTimeToTreatment() {
        return timeToTreatment;
    }

    public void setTimeToTreatment(double timeToTreatment) {
        this.timeToTreatment = timeToTreatment;
    }

    public static int getCountRequest() {
        return countRequest;
    }

    public static void setCountRequest(int countRequest) {
        Device.countRequest = countRequest;
    }

    public static double getAllTime() {
        return allTime;
    }

    public static void setAllTime(double allTime) {
        Device.allTime = allTime;
    }

    public Source.Request getRequest() {
        return request;
    }

    public void setRequest(Source.Request request) {
        this.request = request;
    }

    public int getNumber() {
        return number;
    }

    public int getCountRequestThis() {
        return countRequestThis;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getTimeEmpty() {
        return timeEmpty;
    }

    public void setTimeEmpty(double timeEmpty) {
        this.timeEmpty = timeEmpty;
    }

    public double getTimeAdd() {
        return timeAdd;
    }

    public void setTimeAdd(double timeAdd) {
        this.timeAdd = timeAdd;
    }

    public double getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(double timeOut) {
        this.timeOut = timeOut;
    }

    public double getTimeInDevice() {
        return timeInDevice;
    }

    public void setTimeInDevice(double timeInDevice) {
        this.timeInDevice = timeInDevice;
    }
}
