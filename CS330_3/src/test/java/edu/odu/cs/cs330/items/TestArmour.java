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
public class TestArmour
{
    private Armour fancyArmour;

    @BeforeEach
    public void setUp()
    {
        fancyArmour = new Armour();

        fancyArmour.setName("Fancy");
        fancyArmour.setDurability(9001);
        fancyArmour.setDefense(62);
        fancyArmour.setMaterial("Vibranium");
        fancyArmour.setModifier("ProcrastinationReduction");
        fancyArmour.setModifierLevel(999_999);
        fancyArmour.setElement("H20");
    }

    @Test
    public void testDefaultConstructor()
    {
        Armour genericArmour = new Armour();
        Item  genericRef = (Item) genericArmour;

        assertFalse(genericArmour.isStackable());
        assertFalse(genericRef.isStackable());

        // I should really complete this unit test with calls to each of the
        // accessors. However, I will forgo the remaining checks for this test

        // I should really check toString here. However, I
        // will do that in a separate `testToString` function
    }

    @Test
    public void testCopyConstructor()
    {
        Armour copy = new Armour(fancyArmour);

        // Checks
        assertThat(copy.getName(), equalTo("Fancy"));
        assertFalse(copy.isStackable());
        assertThat(copy.getDurability(), equalTo(9001));
        assertThat(copy.getDefense(), equalTo(62));
        assertThat(copy.getMaterial(), equalTo("Vibranium"));
        assertThat(copy.getModifier(), equalTo("ProcrastinationReduction"));
        assertThat(copy.getModifierLevel(), equalTo(999999));
        assertThat(copy.getElement(), equalTo("H20"));

        // I should really check toString here. However, I will
        // do that in a separate `testToString` function
    }

    @Test
    public void testClone()
    {
        Armour copy = (Armour) fancyArmour.clone();

        assertThat(copy, is(not(sameInstance(fancyArmour))));
        assertThat(copy.getName(), equalTo("Fancy"));
        assertFalse(copy.isStackable());
        assertThat(copy.getDurability(), equalTo(9001));
        assertThat(copy.getDefense(), equalTo(62));
        assertThat(copy.getMaterial(), equalTo("Vibranium"));
        assertThat(copy.getModifier(), equalTo("ProcrastinationReduction"));
        assertThat(copy.getModifierLevel(), equalTo(999_999));
        assertThat(copy.getElement(), equalTo("H20"));

        // I should really check toString here. However, I will
        // do that in a separate `testToString` function
    }

    @Test
    public void testToString()
    {
        String expected = String.join(
            System.lineSeparator(),
            "  Nme: Fancy",
            "  Dur: 9001",
            "  Def: 62",
            "  Mtl: Vibranium",
            "  Mdr: ProcrastinationReduction (Lvl 999999)",
            "  Emt: H20",
            ""
        );

        assertThat(fancyArmour.toString(), equalTo(expected));
    }

    @Test
    public void testRead()
    {
        Armour fancyArmour = new Armour();

        String inputStr = "Fancy Vibranium 9001 62 ProcrastinationReduction 999999 H20";
        Scanner ins = new Scanner(new StringReader(inputStr));

        fancyArmour.read(ins);

        // Checks
        assertThat(fancyArmour.getName(), equalTo("Fancy"));
        assertFalse(fancyArmour.isStackable());
        assertThat(fancyArmour.getDurability(), equalTo(9001));
        assertThat(fancyArmour.getDefense(), equalTo(62));
        assertThat(fancyArmour.getMaterial(), equalTo("Vibranium"));
        assertThat(fancyArmour.getModifier(), equalTo("ProcrastinationReduction"));
        assertThat(fancyArmour.getModifierLevel(), equalTo(999_999));
        assertThat(fancyArmour.getElement(), equalTo("H20"));
    }

    @Test
    public void testInterfaceNotChanged()
    {
        Class<?> clazz = Armour.class;

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        assertThat(constructors.length, is(equalTo(2)));

        Method[] methods = clazz.getDeclaredMethods();
        assertThat(methods.length, is(equalTo(7)));

        /*
        for (Method method : methods) {
            System.err.println(method);
        }
        */
    }
}

