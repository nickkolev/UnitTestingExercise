package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import javax.xml.crypto.Data;

import java.util.Arrays;

import static org.junit.Assert.*;
public class DatabaseTest {

    private Database database;
    private final static Integer [] NUMBERS = {8, 40, 32, 11, 98, 21};

    @Before
    public void setUpDatabase() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    //1. конструктор
    //1.1 създаваме правилен обект
    @Test
    public void testConstructorCreatesValidObject() {
        //Проверяваме дали елементи са същите като зададените
        Integer[] elements = database.getElements();
        Assert.assertArrayEquals("Arrays are not the same",NUMBERS, elements);
    }

    //1.2 елементите > 16
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenMoreThanSixteenElements() throws OperationNotSupportedException{
        Integer[] numbers = new Integer[17];
        new Database(numbers);
    }

    //1.3 елементите < 1
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenLessThanOneElement() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        new Database(numbers);
    }

    //2. add
    //2.1 успешно добавяне на елемент
    //2.2 добавяне на Null
}
