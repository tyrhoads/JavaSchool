package edu.odu.cs.cs330.items.creation;


/**
 * Enumerate all possible Item storage events that occur during
 * Inventory creation.
 */
public enum Event
{
    DISCARDED,
    STORED,
    MERGED;

    /**
     * Get the enum variant as a string and grab the first letter
     */
    @SuppressWarnings({
        "PMD.LawOfDemeter"
    })
    public String asLetter()
    {
        return Character.toString(this.name().charAt(0));
    }
}

