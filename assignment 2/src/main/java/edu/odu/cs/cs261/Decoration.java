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
 public Decoration(int idNum, double weight) {
  System.out.println(idNum);
  System.out.println(weight);
 }

 public int getIDNumber(){

  return 1;
 };
 public double getWeight(){
  double weight = 1.2;
  return weight;
 }

 @Override
 public double getWidth() {
  return 0;

 }

 ;


}
