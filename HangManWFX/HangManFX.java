package HangManWFX;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class HangManFX extends Application {

    private static ArrayList<String> LooseT = new ArrayList<String>();
    private static String[] words = {"BASEBALL", "BASKETBALL", "POLO", "SOCCER", "TENNIS", "HANDBALL","FOOTBALL","KARATE",
            "JUDO","VOLLEYBALL","CRICKET","SKATEBOARD","SWIMMING","JIU-JITSU","AIKIDO","MMA","BOXING","WING CHUN"};
    private static String RandWord;
    private static String[] Spliter; //words from the array words
    private static String[] cloneSplit; //substitutes letters into _
    private static int count=0;
    private static int countA=0;

    @Override
    public void start(Stage primaryStage) {
        //Text hangText = new Text(220,100,"Hangman Game");
        //hangText.setTextAlignment(TextAlignment.CENTER);
        //hangText.setFont(Font.font("Avenir Next",40));

        //MUSIC/*
        Media testMedia = new Media(Paths.get("FMAttack-SleeplessNights.mp3").toUri().toString());
        MediaPlayer testMediaPlayer = new MediaPlayer(testMedia);
        testMediaPlayer.setAutoPlay(true);
        MediaView mediaview = new MediaView(testMediaPlayer);

        //TITLE GAME IMAGE
        Image titleGame = new Image(getClass().getResource("titleGame.png").toExternalForm());
        ImageView iVTitleGame = new ImageView(titleGame);
        iVTitleGame.setX(200);
        iVTitleGame.setY(30);
        iVTitleGame.setFitWidth(550);
        iVTitleGame.setFitHeight(90);

        //LOGO HANGMAN
        Image hangManL = new Image(getClass().getResource("hangmainMain.png").toExternalForm());
        ImageView iV = new ImageView(hangManL);
        iV.setX(300);
        iV.setY(140);
        iV.setFitWidth(420);
        iV.setFitHeight(420);

        //BUTTON TO PLAY
        Image btnPlay = new Image(getClass().getResource("playbtn.png").toExternalForm());
        ImageView btnplay = new ImageView(btnPlay);
        //btnplay.setX(100);
        //btnplay.setY(300);
        btnplay.setFitHeight(65);
        btnplay.setFitWidth(290);
        Button toPlay = new Button("",btnplay);
        toPlay.setStyle("-fx-background-color: white");
        toPlay.setLayoutY(580);
        toPlay.setLayoutX(150);

        //BUTTON CREDIT
        Image btnCredit = new Image(getClass().getResource("creditbtn.png").toExternalForm());
        ImageView btncredit = new ImageView(btnCredit);
        //btnplay.setX(100);
        //btnplay.setY(300);
        btncredit.setFitHeight(65);
        btncredit.setFitWidth(290);
        Button creditBtn = new Button("",btncredit);
        creditBtn.setStyle("-fx-background-color: white");
        creditBtn.setLayoutY(580);
        creditBtn.setLayoutX(525);

        //BUTTON EXIT
        Image btnExit = new Image(getClass().getResource("exitbtn.png").toExternalForm());
        ImageView btnexit = new ImageView(btnExit);
        //btnplay.setX(100);
        //btnplay.setY(300);
        btnexit.setFitHeight(65);
        btnexit.setFitWidth(290);
        Button exitBtn = new Button("",btnexit);
        exitBtn.setStyle("-fx-background-color: white");
        exitBtn.setLayoutY(660);
        exitBtn.setLayoutX(325);



        //BUTTON to go BACK CREDIT
        Image btnBack = new Image(getClass().getResource("backbtn.png").toExternalForm());
        ImageView btnback = new ImageView(btnBack);
        //btnplay.setX(100);
        //btnplay.setY(300);
        btnback.setFitHeight(65);
        btnback.setFitWidth(290);
        Button backToMenu = new Button("",btnback);
        backToMenu.setStyle("-fx-background-color: white");
        backToMenu.setLayoutY(660);
        backToMenu.setLayoutX(325);
        /*
        SCENE 1 DESIGN -> PLAY
         */
        //BUTTON TO GO BACK GAME
        Image btnBackG = new Image(getClass().getResource("backbtn.png").toExternalForm());
        ImageView btnbackG= new ImageView(btnBackG);
        //btnplay.setX(100);
        //btnplay.setY(300);
        btnbackG.setFitHeight(65);
        btnbackG.setFitWidth(290);
        Button backToMenuG = new Button("",btnbackG);
        backToMenuG.setStyle("-fx-background-color: white");
        backToMenuG.setLayoutY(660);
        backToMenuG.setLayoutX(225);

        //BUTTON TO RESTART
        Image btnPlayAgain = new Image(getClass().getResource("playagain.png").toExternalForm());
        ImageView btnplayA = new ImageView(btnPlayAgain);
        btnplayA.setFitHeight(65);
        btnplayA.setFitWidth(290);
        Button playAgain = new Button("",btnplayA);
        playAgain.setStyle("-fx-background-color: white");
        playAgain.setLayoutY(660);
        playAgain.setLayoutX(525);

        //HANGMAN NO BODY
        Image hangNoBody = new Image(getClass().getResource("hangmanNOBODY.png").toExternalForm());
        ImageView iVNO = new ImageView(hangNoBody);
        iVNO.setX(560);
        iVNO.setY(100);
        iVNO.setFitWidth(420);
        iVNO.setFitHeight(420);

        //HANGMAN BODY PART
        ArrayList<Image> hangImage = new ArrayList<>();
        for(int i =1;i<=7;i++){
            hangImage.add(new Image(getClass().getResource("hangman-"+i+".png").toExternalForm()));
        }

        ImageView im1 = new ImageView(hangImage.get(0));
        ImageView im2 = new ImageView(hangImage.get(1));
        ImageView im3 = new ImageView(hangImage.get(2));
        ImageView im4 = new ImageView(hangImage.get(3));
        ImageView im5 = new ImageView(hangImage.get(4));
        ImageView im6 = new ImageView(hangImage.get(5));
        ImageView im7 = new ImageView(hangImage.get(6));

        //TEXT
        Text text = new Text();
        text.setText("Write one letter and press Enter.\n If you guessed the whole word, write it all !\n " +
                "You only have 6 tries.\n Good Luck !");
        text.setFont(Font.font("Avenir Next",20));
        text.setLayoutX(100);
        text.setLayoutY(80);


        //FIELD
        final TextField wordN = new TextField(); //TEXT FIELD TO WRITE THE LETTER
        wordN.setLayoutX(100);
        wordN.setLayoutY(200);

        //GUESS
        Random RandGen = new Random();

        int index = RandGen.nextInt(words.length);//Takes a random number Max = words.length

        RandWord = words[2];//Random Word // Now SOCCER.

        Spliter = RandWord.split("");//Split the word RandWord into Letters and put them into Spliter Array
        cloneSplit = Spliter.clone(); // Create a clone to the Spliter to transform the words into _


        //Rectangle underRec = new Rectangle();
        for (int i = 0; i < cloneSplit.length; i++) {
            cloneSplit[i] = cloneSplit[i].replaceAll("[A-Za-z]", "_");
        }
        String unders = ""; // put _ into this String
        //Show text with underscore
        Text tX = new Text(); //To display
        Text won_lose = new Text(); // Tell if the person lost or won
        Text lives = new Text();

        for(int i =0; i<cloneSplit.length;i++){
            unders+=cloneSplit[i]+" ";

        }

        System.out.print(unders);
        tX.setText(unders);
        tX.setFont(Font.font("Avenir Next",50));
        tX.setLayoutY(590);
        tX.setLayoutX(100);

        //LETTER THAT HAS BEEN CHOSEN
        Text alpha = new Text(); //TO DISPLAY ALPHABET
        Text alphaT = new Text();
        alphaT.setText("Letters already played :");
        alphaT.setFont(Font.font("Avenir Next",25));
        alphaT.setY(550);
        alphaT.setX(550);
        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O",
                "P","Q","R","S","T","U","V","W","X","Y","Z"};
        String [] cloneAlph = cloneAlph(alphabet);
        /*String space = "";
        for(int j = 0;j<cloneAlph.length;j++){
            space+=cloneAlph[j]+" ";
        }*/

        //WHEN ENTER IS PRESSED
        wordN.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                String letter = wordN.getText();
                letter = letter.toUpperCase();
                wordN.setText("");
                //String unders1 = "";
                boolean c = false; //Check
                System.out.print(RandWord);
                for (int i = 0; i < Spliter.length; i++) {
                    if (Spliter[i].equals(letter)) {
                        cloneSplit[i]=letter;
                        c = true;
                    }
                }

                for(int l = 0; l<alphabet.length;l++){
                    if(alphabet[l].equals(letter)){
                        cloneAlph[l]=letter;
                    }
                }

                printSpaceAlphabet(cloneAlph,alpha);//METHOD
                String unders1 = printUnderscor(cloneSplit);

                tX.setText(unders1);
                tX.setFont(Font.font("Avenir Next",50));
                tX.setLayoutY(590);
                tX.setLayoutX(100);

                String [] test;
                test = letter.split("");

                if(c==false){
                    count++;
                    LooseT.add(letter);
                    int countT = 6-count;
                    lives.setText("\n"+countT + " lives left");
                    lives.setFont(Font.font("Avenir Next", 15));
                    lives.setLayoutX(100);
                    lives.setLayoutY(230);
                    if(count==1){
                        iVNO.setImage(im1.getImage());
                    } else if(count ==2 ){
                        /*im1.setX(560);
                        im1.setY(100);
                        im1.setFitWidth(420);
                        im1.setFitHeight(420);*/
                        iVNO.setImage(im2.getImage());
                    } else if(count == 3){
                        iVNO.setImage(im3.getImage());
                    } else if(count==4){
                        iVNO.setImage(im4.getImage());
                    } else if(count==5){
                        iVNO.setImage(im5.getImage());
                    } else if(count ==6) {
                        iVNO.setImage(im6.getImage());
                    }
                }


                if (Arrays.equals(cloneSplit, Spliter) || Arrays.equals(test,Spliter)) {
                    //System.out.println("Congratulations! You won after guessing " + count + " wrong letter's!");
                    iVNO.setImage(im7.getImage());
                    won_lose.setText("Congratulations! \nYou won after guessing " + count + " wrong letter's!");
                    won_lose.setFont(Font.font("Avenir Next", 20));
                    won_lose.setLayoutY(400);
                    won_lose.setLayoutX(100);
                    wordN.setDisable(true);

                }
                if(count==6){
                    won_lose.setText("You suck... It was suppose to be easy fool.\n The hidden word is " + RandWord+".");
                    won_lose.setFont(Font.font("Avenir Next", 20));
                    won_lose.setLayoutY(400);
                    won_lose.setLayoutX(100);
                    wordN.setDisable(true);
                }
            }
        });

        //CREDIT SCENE
        Text textCredit = new Text();
        textCredit.setText("Game created by Bengt \nHangman Game ©");
        textCredit.setFont(Font.font("Avenir Next",20));
        textCredit.setX(100);
        textCredit.setY(100);
        /*
        Text easterEgg = new Text();
        easterEgg.setText("Ygr{z2&i-kyz&suo&Gtgy4&P-go&goj\u0003&\u0003&ix\u0003kx&ik&pk{4&Nono");
        easterEgg.setFont(Font.font("Avenir Next",10));
        easterEgg.setX(100);
        easterEgg.setY(300);
        //Ygr{z2&i-kyz&suo&Gtgy4&P-go&goj&&ixkx&ik&pk{4&Nono
*/
        //GROUP FOR SCENE 1
        Group root1 = new Group();
        root1.getChildren().addAll(backToMenuG,text,wordN,tX,won_lose,lives,iVNO,playAgain,mediaview,alpha,alphaT);

        //GROUP FOR MAIN SCENE
        Group root = new Group();
        root.getChildren().addAll(iVTitleGame,toPlay,iV,creditBtn,exitBtn,mediaview);

        Group root2 = new Group();
        root2.getChildren().addAll(backToMenu,textCredit);

        Scene scene = new Scene(root,1000,750);
        Scene scene1 = new Scene(root1, 1000,750);
        Scene scene2 = new Scene(root2,1000,750); //credit

        primaryStage.setTitle("Hangman Game");//Main Scene
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        testMediaPlayer.play();

        exitBtn.setOnAction(event -> {
            System.exit(0);
        });

        creditBtn.setOnAction(event -> {
            primaryStage.close();
            primaryStage.setScene(scene2);
            primaryStage.setTitle("Hangman Game - Credit");
            primaryStage.setResizable(false);
            primaryStage.show();
        });

        //WHEN BUTTON IS CLICKED IT GOES TO THE NEXT SCENE
        toPlay.setOnAction(event -> {
            primaryStage.close();
            primaryStage.setScene(scene1);
            primaryStage.setTitle("Hangman Game - Play");
            primaryStage.setResizable(false);
            primaryStage.show();

        });
        //BUTTON TO GO BACK TO THE MAIN MENU //RETOUR
        backToMenu.setOnAction(event -> {
            primaryStage.close();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hangman Game");
            primaryStage.setResizable(false);
            primaryStage.show();
        });

        backToMenuG.setOnAction(event -> {
            primaryStage.close();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hangman Game");
            primaryStage.setResizable(false);
            primaryStage.show();
        });


        //BUTTON TO PLAY AGAIN
        playAgain.setOnAction(event -> {
            //root1.getChildren().clear();
            primaryStage.close();

            countA=0;



            //HANGMAN NO BODY
            Image hangNoBodyA = new Image(getClass().getResource("hangmanNOBODY.png").toExternalForm());
            ImageView iVNOA = new ImageView(hangNoBodyA);
            iVNOA.setX(560);
            iVNOA.setY(100);
            iVNOA.setFitWidth(420);
            iVNOA.setFitHeight(420);
            //HANGMAN BODY PART
            ArrayList<Image> hangImageA = new ArrayList<>();
            for(int i =1;i<=7;i++){
                hangImageA.add(new Image(getClass().getResource("hangman-"+i+".png").toExternalForm()));
            }

            ImageView im1A = new ImageView(hangImageA.get(0));
            ImageView im2A = new ImageView(hangImageA.get(1));
            ImageView im3A = new ImageView(hangImageA.get(2));
            ImageView im4A = new ImageView(hangImageA.get(3));
            ImageView im5A = new ImageView(hangImageA.get(4));
            ImageView im6A = new ImageView(hangImageA.get(5));
            ImageView im7A = new ImageView(hangImageA.get(6));

            //TEXT
            Text textA = new Text();
            textA.setText("Write one letter and press enter.\n If you guessed the whole word, write it all !\n " +
                    "You only have 6 tries.\n Good Luck !");
            textA.setFont(Font.font("Avenir Next",20));
            textA.setLayoutX(100);
            textA.setLayoutY(80);


            //FIELD
            final TextField wordNA = new TextField(); //TEXT FIELD TO WRITE THE LETTER
            wordNA.setLayoutX(100);
            wordNA.setLayoutY(200);

            //GUESS
            //Random RandGenA = new Random();

            //int indexA = RandGenA.nextInt(words.length);//Takes a random number Max = words.length

            RandWord = words[2];//Random Word //Not anymore for manual testing. Should be the word POLO.

            Spliter = RandWord.split("");//Split the word RandWord into Letters and put them into Spliter Array
            cloneSplit = Spliter.clone(); // Create a clone to the Spliter to transform the words into _


            //Rectangle underRec = new Rectangle();
            for (int i = 0; i < cloneSplit.length; i++) {
                cloneSplit[i] = cloneSplit[i].replaceAll("[A-Za-z]", "_");
            }
            String undersA = ""; // put _ into this String
            //Show text with underscore
            Text tXA = new Text(); //To display
            Text won_loseA = new Text(); // Tell if the person lost or won
            Text livesA = new Text();

            for(int i =0; i<cloneSplit.length;i++){
                undersA+=cloneSplit[i]+" ";

            }

            System.out.print(undersA);
            tXA.setText(undersA);
            tXA.setFont(Font.font("Avenir Next",50));
            tXA.setLayoutY(590);
            tXA.setLayoutX(100);

            Text alphaA = new Text(); //TO DISPLAY ALPHABET
            Text alphaTA = new Text();
            alphaTA.setText("Letters already played :");
            alphaTA.setFont(Font.font("Avenir Next",25));
            alphaTA.setY(550);
            alphaTA.setX(550);
            String[] alphabetA = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O",
                    "P","Q","R","S","T","U","V","W","X","Y","Z"};
            String [] cloneAlphA = cloneAlph(alphabetA);
            String spaceA = "";
            for(int j = 0;j<cloneAlphA.length;j++){
                spaceA+=cloneAlphA[j]+" ";
            }


            //WHEN ENTER IS PRESSED
            wordNA.setOnKeyPressed(eventA -> {
                if(eventA.getCode() == KeyCode.ENTER){
                    String letterA = wordNA.getText();
                    letterA = letterA.toUpperCase();
                    wordNA.setText("");
                    //String unders1A = "";
                    boolean c = false; //Check
                    System.out.print(RandWord);
                    for (int i = 0; i < Spliter.length; i++) {
                        if (Spliter[i].equals(letterA)) {
                            cloneSplit[i]=letterA;
                            c = true;
                        }
                    }

                    for(int l = 0; l<alphabetA.length;l++){
                        if(alphabetA[l].equals(letterA)){
                            cloneAlphA[l]=letterA;
                        }
                    }

                    printSpaceAlphabet(cloneAlphA,alphaA);//METHOD
                    String unders1A = printUnderscor(cloneSplit);
                    tXA.setText(unders1A);
                    tXA.setFont(Font.font("Avenir Next",50));
                    tXA.setLayoutY(590);
                    tXA.setLayoutX(100);
                    //String space1A = "";
                    /*for (int o =0;o<cloneAlphA.length;o++){
                        space1A+=cloneAlphA[o]+" ";
                        alphaA.setText(space1A);
                        alphaA.setFont(Font.font("Avenir Next",15));
                        alphaA.setX(550);
                        alphaA.setY(590);
                    }*/

                    /*for (int k = 0; k<cloneSplit.length;k++){
                        unders1A+= cloneSplit[k]+" ";
                        tXA.setText(unders1A);
                        tXA.setFont(Font.font("Avenir Next",50));
                        tXA.setLayoutY(590);
                        tXA.setLayoutX(100);
                    }*/

                    String [] testA;//Array string to put the whole letters of the word
                    testA = letterA.split(""); //Split the word

                    if(c==false){
                        countA++;
                        LooseT.add(letterA);
                        int countP = 6-countA;
                        livesA.setText("\n"+countP + " lives left");
                        livesA.setFont(Font.font("Avenir Next", 15));
                        livesA.setLayoutX(100);
                        livesA.setLayoutY(230);
                        if(countA==1){
                            iVNOA.setImage(im1A.getImage());
                        } else if(countA ==2 ){
                            iVNOA.setImage(im2A.getImage());
                        } else if(countA == 3){
                            iVNOA.setImage(im3A.getImage());
                        } else if(countA==4){
                            iVNOA.setImage(im4A.getImage());
                        } else if(countA==5){
                            iVNOA.setImage(im5A.getImage());
                        } else if(countA ==6) {
                            iVNOA.setImage(im6A.getImage());
                        }
                    }


                    if (Arrays.equals(cloneSplit, Spliter) || Arrays.equals(testA,Spliter)) {
                        iVNOA.setImage(im7A.getImage());
                        won_loseA.setText("Congratulations! \nYou won after guessing " + countA + " wrong letter's!");
                        won_loseA.setFont(Font.font("Avenir Next", 20));
                        won_loseA.setLayoutY(400);
                        won_loseA.setLayoutX(100);
                        wordNA.setDisable(true);

                    }
                    if(countA==6){
                        won_loseA.setText("You suck... It was suppose to be easy fool.\n The hidden word is " + RandWord+".");
                        won_loseA.setFont(Font.font("Avenir Next", 20));
                        won_loseA.setLayoutY(400);
                        won_loseA.setLayoutX(100);
                        wordNA.setDisable(true);
                    }
                }
            });

            Group root4 = new Group();
            root4.getChildren().addAll(backToMenuG,textA,wordNA,tXA,won_loseA,livesA,iVNOA,playAgain,alphaA,alphaTA);
            Scene scene4 = new Scene(root4,1000,750);
            primaryStage.setScene(scene4);
            primaryStage.setTitle("Hangman Game - Play");
            primaryStage.setResizable(false);
            primaryStage.show();

            //WHEN BUTTON IS CLICKED IT GOES TO THE NEXT SCENE
            toPlay.setOnAction(eventA -> {
                primaryStage.close();
                primaryStage.setScene(scene4);
                primaryStage.setTitle("Hangman Game - Play");
                primaryStage.setResizable(false);
                primaryStage.show();

            });
        });
    }


    //TRANSFORM ALPHABET TO SPACE
    public static String [] cloneAlph(String[] clnA){
        String [] cloneAlphA = clnA.clone();
        for (int i = 0; i < cloneAlphA.length; i++) {
            cloneAlphA[i] = cloneAlphA[i].replaceAll("[A-Za-z]", " ");
        }
        return cloneAlphA;
    }


    //Print Space String
    public static String printSpaceAlphabet(String[] cln, Text alpha){
        String space1 = "";
        for (int o =0;o<cln.length;o++){
            space1+=cln[o]+" ";
            alpha.setText(space1);
            alpha.setFont(Font.font("Avenir Next",15));
            alpha.setX(550);
            alpha.setY(590);
        }
        return space1;
    }

    public static String printUnderscor(String[] clonesplit){
        String unders1 = "";
        for (int k = 0; k<clonesplit.length;k++){
            unders1+= clonesplit[k]+" "; //BUG
        }
        return unders1;
    }


    public static void main(String[] args) {

        launch(args);
    }
}
