package edu.odu.cs.cs330.items.io;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.StringReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;

import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.Armour;
import edu.odu.cs.cs330.items.Tool;
import edu.odu.cs.cs330.items.Consumable;

import edu.odu.cs.cs330.items.creation.ArmourCreation;
import edu.odu.cs.cs330.items.creation.ConsumableCreation;
import edu.odu.cs.cs330.items.creation.ToolCreation;


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
    "PMD.LawOfDemeter",
    "PMD.ShortVariable"
})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestItemParser
{
    private Consumable foodItem;

    @BeforeEach
    public void setUp()
    {
        foodItem = new Consumable();
        foodItem.setName("Green-Tea");
        foodItem.setEffect("Wake-Up");
        foodItem.setNumberOfUses(5);
    }

    @Test
    public void testStreamItemLineSuccess()
        throws IOException
    {
        String inputStr = "Food Green-Tea Wake-Up 5";
        Item item = ItemParser.streamItems(inputStr).findFirst().orElse(null);

        assertThat(item, equalTo(foodItem));
        assertThat(item.toString(), equalTo(foodItem.toString()));
    }

    @Test
    public void testStreamItemLineFailUnknownItem()
        throws IOException
    {
        String inputStr = "NOTACTUALLY-Food Green-Tea Wake-Up 5";
        Item item = ItemParser.streamItems(inputStr).findFirst().orElse(null);

        assertThat(item, is(nullValue()));
    }

    @Test
    public void testStreamItemLineFailMalformedLine()
        throws IOException
    {
        //Too Many Items
        String inputStr = "Food Green-Tea Wake-Up 5 Too Many Items";
        Item item = ItemParser.streamItems(inputStr).findFirst().orElse(null);
        assertThat(item, is(nullValue()));

        // Too Few Items
        inputStr = "Food Green-Tea Wake-Up";
        item = ItemParser.streamItems(inputStr).findFirst().orElse(null);
        assertThat(item, is(nullValue()));
    }

    @SuppressWarnings({
        "PMD.DataflowAnomalyAnalysis"
    })
    @Test
    public void readItems()
        throws IOException
    {
        // Set up test data
        String src = String.join(
            "\n",
            "Armor Boots Diamond 100 10 FeatherFalling 4 lightning",
            "Food Tomato Hunger-10 2",
            "LOLNOTAVALIDITEM potato 7",
            "Tool Shovel Gold 20 3 Unbreaking 2"
        );

        Armour boots = (Armour) (ArmourCreation.construct().fromTokens(
            "Boots", "Diamond", "100", "10", "FeatherFalling", "4", "lightning"
        ));

        Consumable tomato = (Consumable) (ConsumableCreation.construct().fromTokens(
            "Tomato", "Hunger-10", "2"
        ));

        Tool shovel = (Tool) (ToolCreation.construct().fromTokens(
            "Shovel", "Gold", "20", "3", "Unbreaking", "2"
        ));

        // Run the test
        try (BufferedReader buffer = new BufferedReader(new StringReader(src))) {
            List<Item> items = ItemParser.readItems(buffer);

            assertThat(items.size(), equalTo(3));

            Iterator<Item> it = items.iterator();
            assertThat(it.next(), equalTo(boots));
            assertThat(it.next(), equalTo(tomato));
            assertThat(it.next(), equalTo(shovel));
        }
    }

    @Test
    public void readItemsFromFile()
        throws IOException
    {
        // Set up test data
        String dataFile = "src/test/resources/test-items-01.txt";

        Armour boots = (Armour) (ArmourCreation.construct().fromTokens(
            "Boots", "Diamond", "100", "10", "FeatherFalling", "4", "lightning"
        ));

        Consumable tomato = (Consumable) (ConsumableCreation.construct().fromTokens(
            "Tomato", "Hunger-10", "2"
        ));

        Tool shovel = (Tool) (ToolCreation.construct().fromTokens(
            "Shovel", "Gold", "20", "3", "Unbreaking", "2"
        ));

        // Run the test
        List<Item> items = ItemParser.readItemsFromFile(dataFile);

        assertThat(items.size(), equalTo(3));

        Iterator<Item> it = items.iterator();
        assertThat(it.next(), equalTo(boots));
        assertThat(it.next(), equalTo(tomato));
        assertThat(it.next(), equalTo(shovel));
    }
}

