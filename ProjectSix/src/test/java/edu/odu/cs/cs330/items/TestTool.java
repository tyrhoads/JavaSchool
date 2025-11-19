package edu.odu.cs.cs330.items;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
public class TestTool
{
    private Tool leftHandedHammer;

    @BeforeEach
    public void setUp()
    {
        leftHandedHammer = new Tool();

        leftHandedHammer.setName("Left-Handed Hammer");
        leftHandedHammer.setDurability(9001);
        leftHandedHammer.setSpeed(62);
        leftHandedHammer.setMaterial("Titanium");
        leftHandedHammer.setModifier("WorkAcceleration");
        leftHandedHammer.setModifierLevel(999_999);
    }

    @Test
    public void testDefaultConstructor()
    {
        Tool genericTool = new Tool();
        Item  genericRef = (Item) genericTool;

        assertFalse(genericTool.isStackable());
        assertFalse(genericRef.isStackable());

        // I should really complete this unit test with calls to each of the
        // accessors. However, I will forgo the remaining checks for this test
    }

    @Test
    public void testClone()
    {
        Tool copy = (Tool) leftHandedHammer.clone();

        assertThat(copy, is(not(sameInstance(leftHandedHammer))));

        assertThat(copy.getName(), equalTo("Left-Handed Hammer"));
        assertFalse(copy.isStackable());
        assertThat(copy.getDurability(), equalTo(9001));
        assertThat(copy.getSpeed(), equalTo(62));
        assertThat(copy.getMaterial(), equalTo("Titanium"));
        assertThat(copy.getModifier(), equalTo("WorkAcceleration"));
        assertThat(copy.getModifierLevel(), equalTo(999_999));

        // I should really check toString here. However, I will
        // do that in a separate `testToString` function
    }

    @Test
    public void testToString()
    {
        String expected = String.join(
            System.lineSeparator(),
            "  Nme: Left-Handed Hammer",
            "  Dur: 9001",
            "  Spd: 62",
            "  Mtl: Titanium",
            "  Mdr: WorkAcceleration (Lvl 999999)",
            ""
        );

        assertThat(leftHandedHammer.toString(), equalTo(expected));
    }

    @Test
    public void testEquals()
    {
        Tool generic = new Tool();

        assertThat(leftHandedHammer, not(equalTo(generic)));

        Tool imitation = (Tool) leftHandedHammer.clone();

        imitation.setDurability(12);
        assertThat(leftHandedHammer, is(equalTo(imitation)));

        imitation = (Tool) leftHandedHammer.clone();
        imitation.setSpeed(1234);
        assertThat(leftHandedHammer, is(not(equalTo(imitation))));

        imitation = (Tool) leftHandedHammer.clone();
        imitation.setModifierLevel(8888);
        assertThat(leftHandedHammer, is(not(equalTo(imitation))));

        imitation = (Tool) leftHandedHammer.clone();
        imitation.setName("More Left-Handed Hammer!");
        assertThat(leftHandedHammer, is(not(equalTo(imitation))));

        imitation = (Tool) leftHandedHammer.clone();
        imitation.setMaterial("Potato");
        assertThat(leftHandedHammer, is(not(equalTo(imitation))));

        imitation = (Tool) leftHandedHammer.clone();
        imitation.setModifier("Hydration");
        assertThat(leftHandedHammer, is(not(equalTo(imitation))));

        // Test Tool vs non-Tool object
        assertThat(imitation, not(equalTo("")));
    }

    @Test
    public void testHashCode()
    {
        Tool generic = new Tool();

        assertThat(leftHandedHammer.hashCode(), not(equalTo(generic.hashCode())));

        Tool imitation = (Tool) leftHandedHammer.clone();

        imitation.setDurability(12);
        assertThat(leftHandedHammer.hashCode(), equalTo(imitation.hashCode()));

        imitation = (Tool) leftHandedHammer.clone();
        imitation.setSpeed(1234);
        assertThat(leftHandedHammer.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = (Tool) leftHandedHammer.clone();
        imitation.setModifierLevel(8888);
        assertThat(leftHandedHammer.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = (Tool) leftHandedHammer.clone();
        imitation.setName("Right-Handed Hammer!");
        assertThat(leftHandedHammer.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = (Tool) leftHandedHammer.clone();
        imitation.setMaterial("Potato");
        assertThat(leftHandedHammer.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = (Tool) leftHandedHammer.clone();
        imitation.setModifier("Hydration");
        assertThat(leftHandedHammer.hashCode(), not(equalTo(imitation.hashCode())));
    }

    @Test
    public void testCopyConstructorDoesNotExist()
    {
        Class<?> clazz = Tool.class;

        assertThrows(
            NoSuchMethodException.class,
            () -> {
                Constructor<?> constructor = clazz.getDeclaredConstructor(clazz);
            }
        );
    }

    @Test
    public void testInterfaceNotChanged()
    {
        Class<?> clazz = Tool.class;

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        assertThat(constructors.length, is(equalTo(1)));

        Method[] methods = clazz.getDeclaredMethods();
        assertThat(methods.length, is(equalTo(8)));

        /*
        for (Method method : methods) {
            System.err.println(method);
        }
        */
    }

}

