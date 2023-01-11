package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class DummyTests {

    private static final int ATTACK = 10;
    private static final int HEALTH = 15;
    private static final int EXPERIENCE = 10;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void arrangeInstances() {
        this.dummy = new Dummy(HEALTH, EXPERIENCE);
    }

    @Test
    public void testIfDummyLosesHealthOnAttack() {
        //Arrange

        //Act
        dummy.takeAttack(ATTACK);

        //Assert
        int expected = 5;
        int actual = dummy.getHealth();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void deadDummyCanNotBeAttacked() {
        //Arrange

        //Act
        dummy.takeAttack(ATTACK);
        dummy.takeAttack(ATTACK);
        dummy.takeAttack(ATTACK);

        //Assert
    }

    @Test
    public void deadDummyGivesXP() {
        //Arrange

        //Act
        dummy.takeAttack(ATTACK);
        dummy.takeAttack(ATTACK);

        //Assert
        int expected = EXPERIENCE;
        int actual = dummy.giveExperience();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void aliveDummyDoesNotGiveXP() {
        //Arrange

        //Act
        dummy.takeAttack(ATTACK);
        dummy.giveExperience();

        //Assert
    }
}
