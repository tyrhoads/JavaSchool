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
public  class Decoration extends MobileComponent {

    private int idNum;
    private double w;


    public Decoration(int IDNum, double weight) {
        super(IDNum);
        w = weight;
    }
    @Override
    public double getWeight() {
        return 0;
    }

    public double getWidth(){
        return 0.0;
    }


}

