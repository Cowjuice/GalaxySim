package galaxysimbeta001;

import java.awt.Color;
import java.awt.Graphics;

/*
 * File Name: Planet.java
 *   Created: Apr 28, 2015
 *    Author: 
 */

public class SpaceObject extends Object
{
  public double mass, radius, xVelocity, yVelocity, xPos, yPos;
  public Color c;
  public boolean phys;
  
  
  public SpaceObject(double m, double r, double ixv, double iyv, double ix, double iy)
  {
    mass = m;
    radius = r;
    xVelocity = ixv;
    yVelocity = iyv;
    xPos = ix+r;
    yPos = iy+r;
    phys = false;
  }
   
  public void graph(Graphics g)
  {
    g.setColor(c);
    g.fillOval((int) (xPos),(int) (yPos),(int) radius*2,(int) radius*2);
    g.setColor(Color.GRAY);
    g.drawString(mass+"",(int) xPos,(int) yPos);
  }
  
  public boolean isTouching(SpaceObject that)
  {
    double iR = this.getRadius();
    double iX = this.getXPos()+iR;
    double iY = this.getYPos()+iR;
    double aR = that.getRadius();
    double aX = that.getXPos()+aR;
    double aY = that.getYPos()+aR;
    double dist = Math.sqrt((aY-iY)*(aY-iY)+(aX-iX)*(aX-iX));
    if (dist <= iR+aR && phys) return true;
    else return false;
  }
  
  public void changeVelocity(double xa, double ya)
  {
    xVelocity = xVelocity+xa;
    yVelocity = yVelocity-ya;
  }
  
  public void setVelocity(double xv, double yv)
  {
    xVelocity = xv;
    yVelocity = yv;
  }
  
  public double getXVelocity()
  {
    return xVelocity;
  }
  
  public double getYVelocity()
  {
    return yVelocity;
  }
  
  public double getXPos()
  {
    return xPos;
  }
  
  public double getYPos()
  {
    return yPos;
  }
  
  public double getMass()
  {
    return mass;
  }
  
  public double getRadius()
  {
    return radius;
  }
  
  public boolean isPhysical()
  {
    return phys;
  }
  
  public void move()
  {
    xPos += xVelocity/50;
    yPos += yVelocity/50;
  }
  
  public void checkColor()
  {
    if (mass <= 50) c = Color.LIGHT_GRAY;
    else if (mass > 50 && mass <= 150) c = Color.GRAY;
    else if (mass > 150 && mass <= 5000) c = Color.DARK_GRAY;
    else if (mass > 5000 && mass <= 30000) c = Color.ORANGE.darker().darker().darker();
    else if (mass > 30000 && mass <= 100000) c = Color.GREEN.darker().darker();
    else if (mass > 100000 && mass <= 900000) c = Color.CYAN;
    else if (mass > 900000 && mass <= 3000000) c = Color.BLUE;
    else if (mass > 3000000 && mass <= 5000000) c = Color.ORANGE;
    else if (mass > 5000000 && mass <= 10000000) c = Color.RED;
    else if (mass > 10000000) c = Color.BLACK;
  }
  
}
