package ivoice;

import ivoice.servers.Ivoice;
import java.util.Scanner;


public class Program {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Boolean nextQuest = true;
        String question = "";
        String answer = "";
        String askNextQuest = "";

        while(nextQuest){

            System.out.println("please, ask your question");

            question = scanner.nextLine();

            answer = Ivoice.askQuestion(question);

            System.out.println(answer);

            System.out.println("Ask next question? Y/n");

            askNextQuest = scanner.nextLine();

            switch (askNextQuest.toLowerCase()){

                case "y"  : nextQuest = true;
                break;
                case "n" : nextQuest = false;
                break;
                default : nextQuest = true;
            }

        }

    }

}
