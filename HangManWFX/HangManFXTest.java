package HangManWFX;

import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangManFXTest {

    @Test
    public void shouldReturnAlphabettoSpace(){
        HangManFX RAS = new HangManFX();

        String [] EmptyAlph = {};
        String [] SpaceEmpty = {};
        String [] MethodEmpty = RAS.cloneAlph(EmptyAlph);

        String [] Alph = {"A","B","C","D","F"};
        String [] SpaceAlph = {" ", " ", " ", " ", " "};
        String [] MethodAlp = RAS.cloneAlph(Alph);

        assertArrayEquals(SpaceAlph,MethodAlp);
        assertArrayEquals(SpaceEmpty,MethodEmpty);
    }

    @Test
    public void shouldReturnLetterinAlphabetandSpace(){
        HangManFX RLAS = new HangManFX();

        Text text = new Text();

        String [] Wow = {"", "A", "", "B"};
        String ExpecWow = " A  B ";
        String MethodWow = RLAS.printSpaceAlphabet(Wow,text);

        String [] WowEmpty = {};
        String ExpecWowEmpty = "";
        String MethodWowEmpty = RLAS.printSpaceAlphabet(WowEmpty,text);

        assertEquals(ExpecWow,MethodWow);
        assertEquals(ExpecWowEmpty, MethodWowEmpty);

    }

    @Test
    public void showLettersorWordsBug(){
        HangManFX BUG = new HangManFX();

        String [] Split= {"A"};
        String ExpecSplit = "A";
        String Methodsplit = BUG.printUnderscor(Split);

        assertEquals(ExpecSplit, Methodsplit);


    }
}