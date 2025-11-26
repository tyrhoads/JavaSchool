package edu.odu.cs.cs330.items;

/**
 * This is the common (intermediary) base for all equipable items (i.e., Armour
 * and Tool).
 */
@SuppressWarnings({
    "PMD.AbstractClassWithoutAbstractMethod",
    "PMD.BeanMembersShouldSerialize",
    "PMD.DataClass",
    "PMD.ClassNamingConventions",
    "PMD.AbstractNaming",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LocalVariableCouldBeFinal"
})
public abstract class Equippable implements Item
{
    /**
     * The name...
     */
    protected final String name;

    /**
     * Durability decreases each time armour is used.
     */
    private final int durability;

    /**
     * Base material out of which the armour is constructed.
     */
    private final String material;

    /**
     * Type of enchantment afforded (e.g. protection, feather_falling, or
     * unbreaking)
     */
    private final String modifier;

    /**
     * Enchantment level applied.
     */
    private final int modifierLevel;

    /**
     * Associated element--e.g., ice, fire, and lightning.
     */
    private final String element;

    /**
     * Default to a an equippable with an empty name, zero durability,
     * blank material, no modifier a zero modifier level, and a blank
     * element.
     */
    public Equippable()
    {
        this.name = "[Placeholder]";

        this.durability    = 0;
        this.material      = "";
        this.modifier      = "";
        this.modifierLevel = 1;
        this.element       = "";
    }

    public Equippable(String nme, int dur, String mtl, String mdr, int lvl, String emt)
    {
        this.name = nme;
        this.durability = dur;
        this.material = mtl;
        this.modifier = mdr;
        this.modifierLevel = lvl;
        this.element = emt;
    }

    /**
     * Retrieve name.
     *
     * @return current name
     */
    @Override
    public String getName()
    {
        return this.name;
    }

    /**
     * Retrieve armour durability.
     *
     * @return remaining durability
     */
    public int getDurability()
    {
        return this.durability;
    }

    /**
     * Retrieve armour material.
     *
     * @return base material
     */
    public String getMaterial()
    {
        return this.material;
    }

    /**
     * Retrieve armour modifier.
     *
     * @return buff/debuff provided
     */
    public String getModifier()
    {
        return this.modifier;
    }

    /**
     * Retrieve armour modifier level.
     *
     * @return buff/debuff level
     */
    public int getModifierLevel()
    {
        return this.modifierLevel;
    }

    /**
     * Retrieve armour element.
     *
     * @return element
     */
    public String getElement()
    {
        return this.element;
    }
}




