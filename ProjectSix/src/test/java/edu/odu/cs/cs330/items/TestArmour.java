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
    public void testEquals()
    {
        Armour generic = new Armour();

        assertThat(fancyArmour, not(equalTo(generic)));

        Armour imitation = (Armour) fancyArmour.clone();

        imitation.setDurability(12);
        assertThat(fancyArmour, is(equalTo(imitation)));

        imitation = (Armour) fancyArmour.clone();
        imitation.setDefense(1234);
        assertThat(fancyArmour, is(not(equalTo(imitation))));

        imitation = (Armour) fancyArmour.clone();
        imitation.setModifierLevel(8888);
        assertThat(fancyArmour, is(not(equalTo(imitation))));

        imitation = (Armour) fancyArmour.clone();
        imitation.setName("More Fancy!");
        assertThat(fancyArmour, is(not(equalTo(imitation))));

        imitation = (Armour) fancyArmour.clone();
        imitation.setMaterial("Nacho Cheese Doritos");
        assertThat(fancyArmour, is(not(equalTo(imitation))));

        imitation = (Armour) fancyArmour.clone();
        imitation.setModifier("Eat more green vegetables");
        assertThat(fancyArmour, is(not(equalTo(imitation))));

        imitation = (Armour) fancyArmour.clone();
        imitation.setElement("Aluminum");
        assertThat(fancyArmour, is(not(equalTo(imitation))));

        // Test Armour vs non-Armour object
        assertThat(imitation, not(equalTo("")));
    }

    @Test
    public void testHashCode()
    {
        Armour generic = new Armour();

        assertThat(fancyArmour.hashCode(), not(equalTo(generic.hashCode())));

        Armour imitation = (Armour) fancyArmour.clone();

        imitation.setDurability(12);
        assertThat(fancyArmour.hashCode(), equalTo(imitation.hashCode()));

        imitation = (Armour) fancyArmour.clone();
        imitation.setModifierLevel(8888);
        assertThat(fancyArmour.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = (Armour) fancyArmour.clone();
        imitation.setDefense(1234);
        assertThat(fancyArmour.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = (Armour) fancyArmour.clone();
        imitation.setName("More Fancy!");
        assertThat(fancyArmour.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = (Armour) fancyArmour.clone();
        imitation.setMaterial("Nacho Cheese Doritos");
        assertThat(fancyArmour.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = (Armour) fancyArmour.clone();
        imitation.setModifier("Eat more green vegetables");
        assertThat(fancyArmour.hashCode(), not(equalTo(imitation.hashCode())));

        imitation = (Armour) fancyArmour.clone();
        imitation.setElement("Aluminum");
        assertThat(fancyArmour.hashCode(), not(equalTo(imitation.hashCode())));
    }

    @Test
    public void testCopyConstructorDoesNotExist()
    {
        Class<?> clazz = Armour.class;

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
        Class<?> clazz = Armour.class;

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

