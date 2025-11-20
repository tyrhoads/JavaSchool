package edu.odu.cs.cs330.items.creation;

import edu.odu.cs.cs330.items.Armour;
import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.Consumable;


@SuppressWarnings({
    "PMD.AtLeastOneConstructor"
})
public class ConsumableCreation implements ItemCreationStrategy
{
    /**
     * Convenience wrapper to mirror the Rust new-returns-a-builder pattern.
     * Use "construct" since "new" is a reserved keyword in Java.
     */
    public static ConsumableCreation construct()
    {
        return new ConsumableCreation();
    }

    @Override
    public Item fromDefaults()
    {
        // Maybe call a Default Constructor...
        return new Consumable();
    }

    @Override
    public int requiredNumberOfValues()
    {
        // What is the correct return value?
        return 3;
    }

    @SuppressWarnings({
        "PMD.LawOfDemeter",
        "PMD.LocalVariableCouldBeFinal"
    })
    @Override
    public Item fromTokens(final String... tokens)
    {
        Consumable consumable = new Consumable();


        consumable.setName(tokens[0]);
        consumable.setEffect(tokens[1]);
        consumable.setNumberOfUses(Integer.parseInt(tokens[2]));

        return consumable;
    }

    @Override
    public Item fromExisting(final Item original)
    {
        Consumable src = (Consumable) original;
        Consumable copy = new Consumable();
        copy.setName(src.getName());
        copy.setNumberOfUses(src.getNumberOfUses());
        copy.setEffect(src.getEffect());

        return copy;

    }
}
