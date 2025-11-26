package edu.odu.cs.cs330.items;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        leftHandedHammer = new Tool(
            "Left-Handed Hammer",
            9001,
            62,
            "Titanium",
            "WorkAcceleration",
            999_999
        );
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
    public void testEqualsAndHashCode()
    {
        Tool generic = new Tool();

        assertThat(leftHandedHammer, not(equalTo(generic)));

        Tool imitation = new Tool(
            "Left-Handed Hammer",
            12,
            62,
            "Titanium",
            "WorkAcceleration",
            999_999
        );

        assertThat(leftHandedHammer, is(equalTo(imitation)));
        assertThat(leftHandedHammer.hashCode(), equalTo(imitation.hashCode()));

        imitation = new Tool(
            "Left-Handed Hammer",
            9001,
            1234,
            "Titanium",
            "WorkAcceleration",
            999_999
        );
        assertThat(leftHandedHammer, is(not(equalTo(imitation))));
        assertThat(leftHandedHammer.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = new Tool(
            "Left-Handed Hammer",
            9001,
            62,
            "Titanium",
            "WorkAcceleration",
            9999
        );
        assertThat(leftHandedHammer, is(not(equalTo(imitation))));
        assertThat(leftHandedHammer.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = new Tool(
            "More Left-Handed Hammeri!",
            9001,
            62,
            "Titanium",
            "WorkAcceleration",
            999_999
        );
        assertThat(leftHandedHammer, is(not(equalTo(imitation))));
        assertThat(leftHandedHammer.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = new Tool(
            "Left-Handed Hammer",
            9001,
            62,
            "Potato",
            "WorkAcceleration",
            999_999
        );
        assertThat(leftHandedHammer, is(not(equalTo(imitation))));
        assertThat(leftHandedHammer.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = new Tool(
            "Left-Handed Hammer",
            9001,
            62,
            "Titanium",
            "Hydration",
            999_999
        );
        assertThat(leftHandedHammer, is(not(equalTo(imitation))));
        assertThat(leftHandedHammer.hashCode(), not(equalTo(imitation.hashCode())));

        // Test Tool vs non-Tool object
        assertThat(imitation, not(equalTo("")));
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

    @ParameterizedTest(name = "test ''{0}'' does not exist")
    @ValueSource(strings = {"clone", "fromTokens"})
    public void testMethodDoesNotExist(String methodName)
    {
        Class<?> clazz = Tool.class;

        assertThrows(
            NoSuchMethodException.class,
            () -> {
                Method cloneMethod = clazz.getDeclaredMethod(methodName);
            }
        );
    }

    @Test
    public void testInterfaceNotChanged()
    {
        Class<?> clazz = Tool.class;

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        assertThat(constructors.length, is(equalTo(2)));

        Method[] methods = clazz.getDeclaredMethods();
        assertThat(methods.length, is(equalTo(5)));

        /*
        for (Method method : methods) {
            System.err.println(method);
        }
        */
    }

}

