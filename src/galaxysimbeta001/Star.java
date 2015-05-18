package galaxysimbeta001;

/*
 * File Name: Star.java
 *   Created: May 14, 2015
 *    Author: 
 */

public class Star extends SpaceObject
{
  private final double m = (Math.random()*2500000)+500000;
  private final double r = (Math.random()*5)+(m/50000);
  public Star(double ixv, double iyv, double ix, double iy)
  {
    super(0, 0, ixv, iyv, ix, iy);
    mass = m;
    radius = r;
  }
  
}
