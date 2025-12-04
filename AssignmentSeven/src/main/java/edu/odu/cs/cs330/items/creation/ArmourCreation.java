package edu.odu.cs.cs330.items.creation;

import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.Armour;


@SuppressWarnings({
    "PMD.AtLeastOneConstructor"
})
public class ArmourCreation implements ItemCreationStrategy
{
    /**
     * Convenience wrapper to mirror the Rust new-returns-a-builder pattern.
     * Use "construct" since "new" is a reserved keyword in Java.
     */
    public static ArmourCreation construct()
    {
        return new ArmourCreation();
    }

    @Override
    public Item fromDefaults()
    {
        // Maybe call a Default Constructor...
        return new Armour();
    }

    @Override
    public int requiredNumberOfValues()
    {
        // What is the correct return value?
        return 7;
    }

    @SuppressWarnings({
        "PMD.LawOfDemeter",
        "PMD.LocalVariableCouldBeFinal"
    })
    @Override
    public Item fromTokens(final String... tokens)
    {
        String nme = tokens[0];
        String mtl = tokens[1];
        int dur = Integer.parseInt(tokens[2]);
        int def = Integer.parseInt(tokens[3]);
        String mdr = tokens[4];
        int lvl = Integer.parseInt(tokens[5]);
        String emt = tokens[6];
        return new Armour(nme, dur, def, mtl, mdr, lvl, emt);
    }

    @SuppressWarnings({
        "PMD.LawOfDemeter",
        "PMD.LocalVariableCouldBeFinal",
        "PMD.OnlyOneReturn"
    })
    @Override
    public Item fromExisting(final Item original)
    {
        if (!(original instanceof Armour)) {
            return null;
        }

        Armour theOriginal = (Armour) original;

        // Maybe call a Constructor that accepts multiple arguments...
        return new Armour(theOriginal.getName(),theOriginal.getDurability(),theOriginal.getDefense(),
                          theOriginal.getMaterial(),theOriginal.getModifier(),theOriginal.getModifierLevel()
                , theOriginal.getElement());
    }
}
