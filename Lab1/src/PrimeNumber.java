import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {

    static Integer firstNumber = 0;
    static Integer secondNumber = 0;

    Scanner in = new Scanner(System.in);

    public void readFirstNumbers() {
        System.out.print("Введите с какого числа, начать искать: ");
        while (!in.hasNextInt()) {
            in.next();
            System.out.print("Введите число пожалуйста: ");
        }
        firstNumber = in.nextInt();

        while (firstNumber <= 1) {
            System.out.print("Введите число пожалуйста: ");
            firstNumber = in.nextInt();
        }
    }

    public void readSecondNumbers() {
        System.out.print("Введите по какое число, закончить искать: ");
        while (!in.hasNextInt()) {
            in.next();
            System.out.print("Введите число пожалуйста: ");
        }
        secondNumber = in.nextInt();
    }

    private void findPrimes(int firstNumber, int secondNumber, List<Integer> primes) {
        for (; firstNumber <= secondNumber; firstNumber++) {
            boolean isPrimeNumber = true;
            for (int j = 2; j < firstNumber; j++) {
                if (firstNumber % j == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }
            if (isPrimeNumber) {
                primes.add(firstNumber);
            }
        }
    }

    private void listOutput(List<Integer> primes) {
       System.out.println("Колличество простых чисел: " + primes.size());
        for (int prime : primes)
            System.out.print(prime + ", ");
    }

    public static void main(String[] args) {
        //список для простых чисел
        PrimeNumber primeNumber = new PrimeNumber();
        List<Integer> primes = new ArrayList<>();
        primeNumber.readFirstNumbers();
        primeNumber.readSecondNumbers();
        primeNumber.findPrimes(firstNumber, secondNumber, primes);
        primeNumber.listOutput(primes);
    }
}

