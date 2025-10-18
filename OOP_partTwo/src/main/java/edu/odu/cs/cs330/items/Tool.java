package edu.odu.cs.cs330.items;

import java.util.Scanner;

/**
 * This class represents one tool--as found in most video games. This includes
 * pickaxes and shovels.
 *
 * Tools may not be stacked. All Constructors must initialize Item::stackable
 * to false.
 */
@SuppressWarnings({
    "PMD.BeanMembersShouldSerialize",
    "PMD.CloneMethodReturnTypeMustMatchClassName",
    "PMD.CloneThrowsCloneNotSupportedException",
    "PMD.LawOfDemeter",
    "PMD.OnlyOneReturn",
    "PMD.ProperCloneImplementation",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LocalVariableCouldBeFinal",
    "PMD.ShortClassName",
})
public class Tool extends Equippable {
    /**
     * Base operation (e.g., harvest/mine) speed.
     */
    protected int speed;

    /**
     * Default to an unstackable tool with an empty name, zero durability, zero
     * speed, an empty material name, no modifier, and a modifier level of 1.
     */
    public Tool()
    {
        super();

        this.speed = 0;
    }

    /**
     * Duplicate a Tool.
     *
     * @param src Tool to duplicate
     */
    public Tool(Tool src)
    {
        super();

        this.setName(src.name);

        this.durability    = src.durability;
        this.speed         = src.speed;
        this.material      = src.material;
        this.modifier      = src.modifier;
        this.modifierLevel = src.modifierLevel;
    }

    /**
     * Retrieve tool speed.
     *
     * @return how quickly a tool operates
     */
    public int getSpeed()
    {
        return this.speed;
    }

    /**
     * Set tool speed.
     *
     * @param spd new speed
     */
    public void setSpeed(int spd)
    {
        this.speed = spd;
    }

    @Override
    public boolean isStackable()
    {
        return false;
    }

    /**
     * Read tool attributes.
     */
    @Override
    public void read(Scanner snr)
    {
        // Complete this method
    }

    /**
     * Clone--i.e., copy--this Tool.
     */
    @Override
    public Item clone()
    {
        Tool cpy = new Tool();

        cpy.setName(this.name);

        cpy.setDurability(this.getDurability());
        cpy.setSpeed(this.speed);
        cpy.setMaterial(this.getMaterial());
        cpy.setModifier(this.getModifier());
        cpy.setModifierLevel(this.getModifierLevel());

        return cpy;
    }

    /**
     * Check for logical equivalence--based on name, material, and modifier.
     *
     * @param rhs object for which a comparison is desired
     */
    @Override
    public boolean equals(Object rhs)
    {
        if (!(rhs instanceof Tool)) {
            return false;
        }

        Tool rhsItem = (Tool) rhs;

        // Replace the return
        return false;
    }

    /**
     * Generate a hash code by adding the name, material, and modifier hash
     * codes.
     */
    @Override
    public int hashCode()
    {
        // Replace the return
        return -1;
    }

    /**
     * *Print* a Tool.
     */
    @Override
    public String toString()
    {
        return String.join(
            System.lineSeparator(),
            String.format("  Refer to..."),
            String.format("  ...solution for the..."),
            String.format("  ...previous assignment"),
            ""
        );
    }
}
