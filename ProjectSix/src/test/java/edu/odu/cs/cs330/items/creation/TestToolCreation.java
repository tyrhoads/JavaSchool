package edu.odu.cs.cs330.items.creation;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import edu.odu.cs.cs330.items.Tool;

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
public class TestToolCreation
{
    @Test
    public void testFromDefaults()
    {
        Tool ref = new Tool();
        assertThat(ToolCreation.construct().fromDefaults(), equalTo(ref));
    }

    @Test
    public void testRequiredNumberOfValues()
    {
        assertThat(ToolCreation.construct().requiredNumberOfValues(), equalTo(6));
    }

    @Test
    public void testFromTokens()
    {
        String rawStr = "Left-Handed-Hammer Titanium 9001 62 WorkAcceleration 999999";
        String[] tokens = rawStr.split(" ");

        Tool hammer = (Tool) (ToolCreation.construct().fromTokens(tokens));

        // Checks
        assertThat(hammer.getName(), equalTo("Left-Handed-Hammer"));
        assertFalse(hammer.isStackable());
        assertThat(hammer.getDurability(), equalTo(9001));
        assertThat(hammer.getSpeed(), equalTo(62));
        assertThat(hammer.getMaterial(), equalTo("Titanium"));
        assertThat(hammer.getModifier(), equalTo("WorkAcceleration"));
        assertThat(hammer.getModifierLevel(), equalTo(999_999));
    }

    @Test
    public void testFromExisting()
    {
        Tool leftHandedHammer = new Tool();

        leftHandedHammer.setName("Left-Handed Hammer");
        leftHandedHammer.setDurability(9001);
        leftHandedHammer.setSpeed(62);
        leftHandedHammer.setMaterial("Titanium");
        leftHandedHammer.setModifier("WorkAcceleration");
        leftHandedHammer.setModifierLevel(999_999);

        Tool copy = (Tool) (ToolCreation.construct().fromExisting(leftHandedHammer));

        assertThat(copy, is(not(sameInstance(leftHandedHammer))));
        assertThat(copy.getName(), equalTo("Left-Handed Hammer"));
        assertFalse(copy.isStackable());
        assertThat(copy.getDurability(), equalTo(9001));
        assertThat(copy.getSpeed(), equalTo(62));
        assertThat(copy.getMaterial(), equalTo("Titanium"));
        assertThat(copy.getModifier(), equalTo("WorkAcceleration"));
        assertThat(copy.getModifierLevel(), equalTo(999_999));

    }
}
