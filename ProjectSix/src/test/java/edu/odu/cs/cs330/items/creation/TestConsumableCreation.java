package edu.odu.cs.cs330.items.creation;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import edu.odu.cs.cs330.items.Consumable;

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
    "PMD.BeanMembersShouldSerialize",
    "PMD.JUnitAssertionsShouldIncludeMessage",
    "PMD.JUnitTestContainsTooManyAsserts",
    "PMD.LocalVariableCouldBeFinal",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LawOfDemeter"
})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestConsumableCreation
{
    @Test
    public void testFromDefaults()
    {
        Consumable ref = new Consumable();
        assertThat(ConsumableCreation.construct().fromDefaults(), equalTo(ref));
    }

    @Test
    public void testRequiredNumberOfValues()
    {
        assertThat(ConsumableCreation.construct().requiredNumberOfValues(), equalTo(3));
    }

    @Test
    public void testFromTokens()
    {
        String rawStr = "Green-Tea Wake-Up 5";
        String[] tokens = rawStr.split(" ");

        Consumable tea = (Consumable) (ConsumableCreation.construct().fromTokens(tokens));

        assertTrue(tea.isStackable());
        assertThat(tea.getName(), equalTo("Green-Tea"));
        assertThat(tea.getEffect(), equalTo("Wake-Up"));
        assertThat(tea.getNumberOfUses(), is(5));
    }

    @Test
    public void testFromExisting()
    {
        Consumable tea = new Consumable();
        tea.setName("Green Tea");
        tea.setEffect("Wake Up");
        tea.setNumberOfUses(10);

        Consumable moreTea = (Consumable) (ConsumableCreation.construct().fromExisting(tea));

        assertThat(moreTea, is(not(sameInstance(tea))));
        assertThat(moreTea.isStackable(), is(true));
        assertThat(moreTea.getName(), equalTo("Green Tea"));
        assertThat(moreTea.getEffect(), equalTo("Wake Up"));
        assertThat(moreTea.getNumberOfUses(), is(10));

    }
}
