import java.util.Random;    //This package is used for generating Random Number.
import java.util.Scanner;  

class RandomNumberGenerator {

    private Random rand = new Random();
    private int randomNumber = rand.nextInt(100); //(Generating random numbers between 1 - 100)
    
    void guess() {
        Scanner sc = new Scanner(System.in);
        
        for(int i = 0; i < 10; i++) {
            System.out.print("Enter Your Guess: ");
            int guessedNumber = sc.nextInt();
            
            System.out.println("Your Guessed Number is " + guessedNumber);
            System.out.println("Random Number is " + randomNumber);

            if(randomNumber == guessedNumber) {
                System.out.println("Hurrayyy! Your guess was correct!!!");
                break;
            } else if(guessedNumber > randomNumber) {
                System.out.println("Sorry, your guess was too high!!!");
            } else {
                System.out.println("Sorry, your guess was too low!!!\n");
            }
            System.out.println("Try again--\n");
        }
        
        sc.close();
        System.out.println("Game over. The random number was: " + randomNumber);
    }
}
class NumberGame {
    public static void main(String[] args) {
        RandomNumberGenerator obj = new RandomNumberGenerator();
        obj.guess();
    }
}