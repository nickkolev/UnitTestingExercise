package p03_ListIterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p03_IteratorTest.ListIterator;

import javax.naming.OperationNotSupportedException;
import java.rmi.UnexpectedException;

public class ListIteratorTest {

    private ListIterator listIterator;
    private static final String[] DATA = {"Niki", "Gabi", "Valio"};

    @Before
    public void setup() throws OperationNotSupportedException {
        listIterator = new ListIterator(DATA);
    }

    //1. constructor
    //1.1 empty list
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWithNull() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    //2. hasNext + move
    @Test
    public void testHasNextAndMove() {
        // {"Niki", "Gabi", "Valio"} -> current = 0
        Assert.assertTrue(listIterator.hasNext());
        Assert.assertTrue(listIterator.move());
        // {"Niki", "Gabi", "Valio"} -> current = 1
        Assert.assertTrue(listIterator.hasNext());
        Assert.assertTrue(listIterator.move());
        // {"Niki", "Gabi", "Valio"} -> current = 2
        Assert.assertFalse(listIterator.hasNext());
        Assert.assertFalse(listIterator.move());
    }

    //3. print
    //3.1 empty list
    @Test(expected = IllegalStateException.class)
    public void testPrintEmptyList() throws OperationNotSupportedException {
        listIterator = new ListIterator();
        listIterator.print();
    }

    //3.2 list with elements
    @Test
    public void testPrintCorrectly(){
        int index = 0;
        while(listIterator.hasNext()) {
            Assert.assertEquals(listIterator.print(), DATA[index]);
            index++;
            listIterator.move();
        }
    }
}
