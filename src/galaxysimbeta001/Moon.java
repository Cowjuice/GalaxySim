package galaxysimbeta001;

/*
 * File Name: Moon.java
 *   Created: Apr 28, 2015
 *    Author: 
 */

public class Moon extends SpaceObject
{
  private final double m = (Math.random()*80)+30;
  private final double r = (Math.random()*5)+m/40;
  public Moon(double ixv, double iyv, double ix, double iy)
  {
    super(0, 0, ixv, iyv, ix, iy);
    mass = m;
    radius = r;
  }
  
}
