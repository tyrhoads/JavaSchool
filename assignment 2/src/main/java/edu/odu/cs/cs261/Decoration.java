package edu.odu.cs.cs261;

/**
 * A decorative object that hangs from the "terminal"
 * points of the mobile. DecorativeWeights have the following
 * properties:
 *   1) They have a positive (non-zero) weight
 *   2) They hang from a bar, but nothing hangs from them.
 *   3) Their width is negligible compared to the bars, and so can
 *         be treated as zero. 
 *  
 * @author zeil
 *
 */
public class Decoration extends MobileComponent {

    private int idNum;
    private double weight;


    public Decoration(int IDNum, double weight) {
        super(IDNum);
    }
    @Override
    public double getWeight() {
        return 0;
    }

    // Implementing the abstract method from MobileComponent


    @Override
    public double getWidth() {
        return 0;
    }
}

