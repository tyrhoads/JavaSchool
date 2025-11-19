package edu.odu.cs.cs330.items.creation;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import edu.odu.cs.cs330.items.Armour;

/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 *
 * This is technically an Integration Test.
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
public class TestArmourCreation
{
    @Test
    public void testFromDefaults()
    {
        Armour ref = new Armour();
        assertThat(ArmourCreation.construct().fromDefaults(), equalTo(ref));
    }

    @Test
    public void testRequiredNumberOfValues()
    {
        assertThat(ArmourCreation.construct().requiredNumberOfValues(), equalTo(7));
    }

    @Test
    public void testFromTokens()
    {
        String rawStr = "Fancy Vibranium 9001 62 ProcrastinationReduction 999999 H20";
        String[] tokens = rawStr.split(" ");

        Armour fancyArmour = (Armour) (ArmourCreation.construct().fromTokens(tokens));

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
    public void testFromExisting()
    {
        Armour fancyArmour = new Armour();
        fancyArmour.setName("Fancy");
        fancyArmour.setDurability(9001);
        fancyArmour.setDefense(62);
        fancyArmour.setMaterial("Vibranium");
        fancyArmour.setModifier("ProcrastinationReduction");
        fancyArmour.setModifierLevel(999_999);
        fancyArmour.setElement("H20");

        Armour copy = (Armour) (ArmourCreation.construct().fromExisting(fancyArmour));

        // Checks
        assertThat(copy, is(not(sameInstance(fancyArmour))));
        assertThat(copy.getName(), equalTo("Fancy"));
        assertFalse(copy.isStackable());
        assertThat(copy.getDurability(), equalTo(9001));
        assertThat(copy.getDefense(), equalTo(62));
        assertThat(copy.getMaterial(), equalTo("Vibranium"));
        assertThat(copy.getModifier(), equalTo("ProcrastinationReduction"));
        assertThat(copy.getModifierLevel(), equalTo(999_999));
        assertThat(copy.getElement(), equalTo("H20"));
    }
}
