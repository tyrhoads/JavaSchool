package edu.odu.cs.cs330.items;

import java.util.Scanner;

/**
 * This class represents one piece of armour--as found in most video games.
 * This includes boots and helmets.
 *
 * Armour may not be stacked.
 */
@SuppressWarnings({
    "PMD.BeanMembersShouldSerialize",
    "PMD.CloneMethodReturnTypeMustMatchClassName",
    "PMD.CloneThrowsCloneNotSupportedException",
    "PMD.LawOfDemeter",
    "PMD.OnlyOneReturn",
    "PMD.ProperCloneImplementation",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LocalVariableCouldBeFinal"
})
public class Armour extends Equippable {
    /**
     * The amount of damage that can be negated.
     */
    protected int defense;

    /**
     * Default to a armour with an empty name, zero durability, zero defense,
     * blank material, no modifier a zero modifier level, and a blank element.
     */
    public Armour()
    {
        super();

        this.defense = 0;
        this.durability=0;
        this.name="";
        this.material="";
        this.modifier="";
        this.modifierLevel=0;
        this.element="";

    }

    /**
     * Duplicate a piece of armour.
     *
     * @param src armour to duplicate
     */
    public Armour(Armour src)
    {
        super(src.name);

        this.durability = src.durability;
        this.defense = src.defense;
        this.name=src.name;
        this.material=src.material;
        this.modifier=src.modifier;
        this.modifierLevel=src.modifierLevel;
        this.element=src.element;

    }

    /**
     * Retrieve armour defense.
     *
     * @return total defense provided
     */
    public int getDefense()
    {
        return this.defense;
    }

    /**
     * Update defense.
     *
     * @param def replacement defense
     */
    public void setDefense(int def)
    {
        this.defense = def;
    }

    /**
     * Read Armour attributes.
     */
    @Override
    public void read(Scanner snr)
    {
        super.name = snr.next();
        super.material=snr.next();
        super.durability = snr.nextInt();
        this.defense=snr.nextInt();
        this.modifier=snr.next();
        super.modifierLevel=snr.nextInt();
        super.element=snr.next();

        // Use snr.next() and snr.nextInt() to read in values remaining fields

    }

    /**
     * Clone--i.e., copy--this Armour.
     */
    @Override
    public Item clone()
    {
        // Replace the return
        return new Armour();
    }

    /**
     * *Print* one Armour.
     */
    @Override
    public String toString()
    {
        // Complete the String.join sequence
        return String.join(
            System.lineSeparator(),
            String.format("  Nme: %s", super.getName()),
            ""
        );
    }
}




