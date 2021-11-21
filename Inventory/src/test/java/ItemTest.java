/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Juan Guarnizo
 */

import com.example.inventory.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ItemTest {

    @Test
    public void isLetterTest(){

        Item item = new Item();

        char character = '!';

        assertEquals( false , item.isLetter(character));


    }

}
