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
     * Default to a armour with a defense of zero.
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
        // Copt the remaining fields (data members)
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

    @Override
    public boolean isStackable()
    {
        return false;
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
        Armour copy = new Armour();
        copy.setName(super.name);
        copy.setMaterial(super.material);
        copy.setDurability(super.durability);
        copy.setDefense(this.defense);
        copy.setMaterial(this.material);
        copy.setModifier(this.modifier);
        copy.setElement(super.element);
        copy.setModifierLevel(this.modifierLevel);

        // Replace the return
        return copy;
    }

    /**
     * Check for logical equivalence--based on name, material, modifier, and
     * element.
     *
     * @param rhs object for which a comparison is desired
     */
    @Override
    public boolean equals(Object rhs)
    {
        if (!(rhs instanceof Armour)) {
            return false;
        }

        Armour rhsItem = (Armour) rhs;

                return this.name.equals(rhsItem.name)
                && this.material.equals(rhsItem.material)
                && this.modifier.equals(rhsItem.modifier)
                && this.element.equals(rhsItem.element);


    }

    /**
     * Generate a hash code by adding the name, material, modifier, and element
     * hash codes.
     */
    @Override
    public int hashCode()
    {

        return -1;
    }

    /**
     * *Print* one Armour.
     */
    @Override
    public String toString()
    {
        return String.join(
                System.lineSeparator(),
                String.format("  Nme: %s", super.getName()),
                String.format("  Dur: %d", super.getDurability()),
                String.format("  Def: %d", this.getDefense()),
                String.format("  Mtl: %s", super.getMaterial()),
                String.format("  Mdr: %s (Lvl %d)", this.getModifier(), super.getModifierLevel()),
                String.format("  Emt: %s", super.getElement()),
                ""
        );
    }
}




