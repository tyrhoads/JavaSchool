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
public abstract class Equippable
{
    /**
     * The name...
     */
    protected String name;

    /**
     * Durability decreases each time armour is used.
     */
    private int durability;

    /**
     * Base material out of which the armour is constructed.
     */
    private String material;

    /**
     * Type of enchantment afforded (e.g. protection, feather_falling, or
     * unbreaking)
     */
    private String modifier;

    /**
     * Enchantment level applied.
     */
    private int modifierLevel;

    /**
     * Associated element--e.g., ice, fire, and lightning.
     */
    private String element;

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

    /**
     * Retrieve name.
     *
     * @return current name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Update name.
     *
     * @param nme replacement name
     */
    public void setName(final String nme)
    {
        this.name = nme;
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
     * Set armour durability.
     *
     * @param dur new durability value
     */
    public void setDurability(int dur)
    {
        this.durability = dur;
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
     * Set armour Material.
     *
     * @param mat replacement material type
     */
    public void setMaterial(String mat)
    {
        this.material = mat;
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
     * Set armour modifier.
     *
     * @param mod updated modifier
     */
    public void setModifier(String mod)
    {
        this.modifier = mod;
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
     * Set armour modifier level.
     *
     * @param level replacement modifier level
     */
    public void setModifierLevel(int level)
    {
        this.modifierLevel = level;
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

    /**
     * Set armour element.
     *
     * @param ele new element
     */
    public void setElement(String ele)
    {
        this.element = ele;
    }
}




