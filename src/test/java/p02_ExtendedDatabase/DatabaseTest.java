package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private Database database;
    private final static Person[] PEOPLE = {new Person(1, "Nikola")
            , new Person(2, "Valio")
            , new Person(3, "Gabi")};

    @Before
    public void setUpDatabase() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }

    //1. конструкторът
    //1.1 създава правилен обект
    @Test
    public void testConstructorCreatesValidObject() {
        Person[] elements = database.getElements();

        Assert.assertArrayEquals("Constructor not working properly -> Arrays are not the same", elements, PEOPLE);
    }

    //1.2 елементите > 16
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenElementsAreMoreThanSixteen() throws OperationNotSupportedException {
        //създаваме масив с размер 17
        Person[] elements = new Person[17];
        new Database(elements);
    }

    //1.3 елементите < 1
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWhenElementsAreLessThanOne() throws OperationNotSupportedException {
        Person[] elements = new Person[0];
        new Database(elements);
    }

    //1.4 подаване на null Обект
    @Test(expected = NullPointerException.class)
    public void testConstructorThrowsWhenNullElement() throws OperationNotSupportedException {
        new Database(null);
    }

    //2. add
    //2.1 успешно добавяне на елемент
    @Test
    public void testSuccessfulAdd() throws OperationNotSupportedException {
        database.add(new Person(4, "Person 4"));

        Person[] people = database.getElements();
        Assert.assertEquals("Invalid add operation", people.length, PEOPLE.length + 1);
        Assert.assertEquals("Invalid add operation", people[people.length - 1].getId(), 4);
        Assert.assertEquals("Invalid add operation", people[people.length - 1].getUsername(), "Person 4");
    }

    //2.2 добавяне на Null
    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowsWhenNullElement() throws OperationNotSupportedException {
        database.add(null);
    }

    //TODO NOT IMPLEMENTED METHODS
    /*
    //2.3 добавяне на id < 0
    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowsWhenNegativeId() throws OperationNotSupportedException {
        Person person = new Person(-1, "Negative Person");
        database.add(person);
    }

    //2.4 id вече съществува
    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowsWhenIdExists() throws OperationNotSupportedException {
        Person person = new Person(2, "Same ID Person");
        database.add(person);
    }
     */

    //3. remove
    //3.1 Успешно премахване
    @Test
    public void testSuccessfulRemovingElement() throws OperationNotSupportedException {
        database.remove();
        Person[] elements = database.getElements();

        Assert.assertEquals("Invalid remove operation",elements.length, PEOPLE.length - 1);
        Assert.assertEquals("Invalid remove operation", elements[elements.length - 1].getId(), 2);
        Assert.assertEquals("Invalid remove operation", elements[elements.length - 1].getUsername(), "Valio");
    }

    //3.2 премахване на Null
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowFromEmptyDatabase() throws OperationNotSupportedException {
        database = new Database();
        database.remove();
    }

    //4. findByUsername
    //4.1 подаваме Null
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsWhenNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    //4.2 ако нямаме хора в базата данни
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsWhenEmptyDatabase() throws OperationNotSupportedException {
        database = new Database();
        database.findByUsername("Empty database");
    }

    //4.3 ако подадаем валиден Username
    @Test
    public void testFindByUsername() throws OperationNotSupportedException {
        Person person = database.findByUsername("Nikola");

        Assert.assertEquals("Nikola", person.getUsername());
        Assert.assertEquals(1, person.getId());
    }

    //4.4 ако намерим повече от 1 човек с този Username
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsMoreThanOnePerson() throws OperationNotSupportedException {
        database.add(new Person(4, "Nikola"));

        database.findByUsername("Nikola");
    }

    //4.5 ако нямаме човек с този Username
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsInvalidName() throws OperationNotSupportedException {
        database.findByUsername("Pesho");
    }

    //5. findById
    //5.1 подаваме валидно id
    @Test
    public void testFindById() throws OperationNotSupportedException {
        Person person = database.findById(1);

        Assert.assertEquals(person.getId(), 1);
        Assert.assertEquals(person.getUsername(), "Nikola");
    }

    //5.2 празна база базата данни
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdThrowsEmptyDatabase() throws OperationNotSupportedException {
        database = new Database();
        database.findById(1);
    }

    //5.3 повече от 1 човек с това id
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdThrowsMoreThanOneWithSameId() throws OperationNotSupportedException {
        database.add(new Person(2, "Pesho"));

        database.findById(2);
    }
}
