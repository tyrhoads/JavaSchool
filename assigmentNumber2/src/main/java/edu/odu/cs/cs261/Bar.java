package edu.odu.cs.cs261;
/**
 * 
 */

/**
 * A balanced bar that provides the structure of a mobile.
 * Bars have the following properties:
 *   1) Some component, either a decorative object or another bar, will
 *        hand from its extreme left and right ends.
 *   2) A bar has a negligible weight of its own, but its total weight
 *        is the sum of the weights of all components hanging from it.
 *   3) They have a positive, non-zero width.
 *  
 * @author zeil
 *
 */
public class Bar extends MobileComponent {
    
    private double width;
    MobileComponent left;
    MobileComponent right;
    
    public Bar(int ID, double wide, 
            MobileComponent hangingFromLeft, MobileComponent hangingFromRight)
    {
        super(ID);
        width = wide;
        left = hangingFromLeft;
        right = hangingFromRight;
    }

}
