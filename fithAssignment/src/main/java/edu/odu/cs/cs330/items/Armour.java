package edu.odu.cs.cs330.items;

import java.util.Objects;

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
     * Format used to generate a printable representation.
     */
    public static final String FMT_STR = String.join(
        "",
        "  Nme: %s%n",
        "  Dur: %s%n",
        "  Def: %d%n",
        "  Mtl: %s%n",
        "  Mdr: %s (Lvl %d)%n",
        "  Emt: %s%n"
    );

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
    public int requiredNumberOfValues()
    {
        // What is the correct return value?
        return -1;
    }

    @Override
    public void fromTokens(String[] tokens)
    {
        this.setName(tokens[0]);

        // Add the missing lines

    }

    /**
     * Clone--i.e., copy--this Armour.
     */
    @Override
    public Item clone()
    {
        Armour copy = new Armour();
        copy.setName(super.name);
        copy.setMaterial(super.getMaterial());
        copy.setDurability(super.getDurability());
        copy.setDefense(this.getDefense());
        copy.setModifier(super.getModifier());
        copy.setElement(super.getElement());
        copy.setModifierLevel(super.getModifierLevel());

        // Replace the return
        return copy;
    }

    /**
     * Check for logical equivalence--based on name, material, modifier,
     * modifierLevel, element, and defense.
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
                && super.getMaterial().equals(rhsItem.getMaterial())
                && super.getModifier().equals(rhsItem.getModifier())
                && super.getElement().equals(rhsItem.getElement())
                && super.getModifierLevel() == rhsItem.getModifierLevel()
                && this.getDefense() == rhsItem.getDefense();

    }

    /**
     * Compute hashCode based on name, material, modifier, modifierLevel,
     * element, and defense.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(
                name,
                getMaterial(),
                getModifier(),
                getElement(),
                getModifierLevel(),
                getDefense()
        );
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




