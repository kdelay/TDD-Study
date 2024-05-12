package chap01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
public class StringTest {

    @Test
    void substring(){
        String str = "abcde";
        System.out.println(str.substring(2,4));
        assertEquals("cd",str.substring(2,4));



    }
}
