package HangmanProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class Hangman {

    private static ArrayList<String> LoseT = new ArrayList<String>();
    private static String[] words = {"BASEBALL", "BASKETBALL", "POLO", "SOCCER", "TENNIS"};
    private static String RandWord;
    private static String[] Spliter;
    private static String[] cloneSplit;
    private static int count=0;

    public static void main(String[] args) {

        Random RandGen = new Random();

        int index = RandGen.nextInt(words.length);

        RandWord = words[index];


        System.out.println("Welcome to Hang Man!\nGuess the word! You have 6 chances before the man hangs!:");

        System.out.println("Words are related sports!");

        Spliter = RandWord.split("");

        cloneSplit = Spliter.clone();

        for (int i = 0; i < cloneSplit.length; i++) {
            cloneSplit[i] = cloneSplit[i].replaceAll("[A-Za-z]", "_");
            //System.out.print(cloneSplit[i] + " ");
        }

        while (count < 6) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Please guess a letter, in order to complete the word. ");
            for(int i = 0;i<cloneSplit.length;i++) {
                System.out.print(cloneSplit[i] + " ");
            }

            String letter = sc.nextLine();
            letter = letter.toUpperCase();
            /*
            for (int j = 0; j < Spliter.length; j++) {
                System.out.print(cloneSplit[j]+ " ");

            }
            */

            boolean c = false;
            for (int i = 0; i < Spliter.length; i++) {
               // String h = Spliter[i];
                if (Spliter[i].equals(letter)) {
                    cloneSplit[i] = letter;
                    c = true;

                }
            }

                if (c==false) {
                    count++;
                    LoseT.add(letter);
                }
                System.out.println("\n"+(6 - count) + " lives left");

            if (Arrays.equals(cloneSplit, Spliter)) {
                System.out.println("Congratulations! You won after guessing " + count + " wrong letter's!");
                sc.close();
                break;
            }

            }
        if(count == 6){
            System.out.println("You suck... It was suppose to be easy fool. The hidden word is " + RandWord+".");


        }


        }

    }