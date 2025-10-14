package edu.odu.cs.cs330.items;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.hamcrest.core.IsNull;

import java.io.StringReader;
import java.util.Scanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 */
@SuppressWarnings({
    "PMD.AtLeastOneConstructor",
    "PMD.AvoidDuplicateLiterals",
    "PMD.BeanMembersShouldSerialize",
    "PMD.JUnitAssertionsShouldIncludeMessage",
    "PMD.JUnitTestContainsTooManyAsserts",
    "PMD.LocalVariableCouldBeFinal",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LawOfDemeter"
})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestConsumable
{
    private Consumable tea;

    @BeforeEach
    public void setUp()
    {
        tea = new Consumable();
        tea.setName("Green Tea");
        tea.setEffect("Wake Up");
        tea.setNumberOfUses(10);
    }

    @Test
    public void testDefaultConstructor()
    {
        Consumable imagination = new Consumable();

        Item  genericRef = (Item) imagination;

        assertTrue(imagination.isStackable());
        assertTrue(genericRef.isStackable());

        // I should really complete this unit test with calls to each of the
        // accessors. However, I will forgo the remaining checks for this test

        // I should really check toString here. However, I will
        // do that in a separate `testToString` function
    }

    @Test
    public void testCopyConstructor()
    {
        Consumable moreTea = new Consumable(tea);

        assertThat(moreTea.isStackable(), is(true));
        assertThat(moreTea.getName(), equalTo("Green Tea"));
        assertThat(moreTea.getEffect(), equalTo("Wake Up"));
        assertThat(moreTea.getNumberOfUses(), is(10));

        // I should really complete this unit test with calls to each of the
        // accessors. However, I will forgo the remaining checks for this test

        // I should really check toString here. However, I
        // will do that in a separate `testToString` function
    }

    @Test
    public void testClone()
    {
        Consumable moreTea = (Consumable) tea.clone();

        assertThat(moreTea, is(not(sameInstance(tea))));

        assertThat(moreTea.isStackable(), is(true));
        assertThat(moreTea.getName(), equalTo("Green Tea"));
        assertThat(moreTea.getEffect(), equalTo("Wake Up"));
        assertThat(moreTea.getNumberOfUses(), is(10));

        // I should really complete this unit test with calls to each of the
        // accessors. However, I will forgo the remaining checks for this test

        // I should really check toString here. However, I
        // will do that in a separate `testToString` function
    }

    @Test
    public void testToString()
    {
        String expected = String.join(
            System.lineSeparator(),
            "  Nme: Green Tea",
            "  Eft: Wake Up",
            "  Use: 10",
            ""
        );

        assertThat(tea.toString(), equalTo(expected));
    }

    @Test
    public void testRead()
    {
        Consumable tea = new Consumable();

        String inputStr = "Green-Tea Wake-Up 5";
        Scanner ins = new Scanner(new StringReader(inputStr));

        tea.read(ins);

        assertTrue(tea.isStackable());
        assertThat(tea.getName(), equalTo("Green-Tea"));
        assertThat(tea.getEffect(), equalTo("Wake-Up"));
        assertThat(tea.getNumberOfUses(), is(5));
    }

    @Test
    public void testInterfaceNotChanged()
    {
        Class<?> clazz = Consumable.class;

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        assertThat(constructors.length, is(equalTo(2)));

        Method[] methods = clazz.getDeclaredMethods();
        assertThat(methods.length, is(equalTo(9)));

        /*
        for (Method method : methods) {
            System.err.println(method);
        }
        */
    }
}
