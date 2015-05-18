package galaxysimbeta001;

/*
 * File Name: Planet.java
 *   Created: May 12, 2015
 *    Author: 
 */

public class Planet extends SpaceObject
{
  private final double m = (Math.random()*25000)+10000;
  private final double r = (Math.random()*5)+(m/5000);
  public Planet(double ixv, double iyv, double ix, double iy)
  {
    super(0, 0, ixv, iyv, ix, iy);
    mass = m;
    radius = r;
  }
  
}
