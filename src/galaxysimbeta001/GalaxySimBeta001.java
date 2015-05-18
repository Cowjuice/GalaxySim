package galaxysimbeta001;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * File Name: GalaxySimBeta001.java
 *   Created: Apr 28, 2015
 *    Author: 
 */


public class GalaxySimBeta001 extends JPanel implements ActionListener, MouseListener
{
  // Declare instance variables here...
  Timer c;
  ArrayList<SpaceObject> s;
  Double G = 0.6670;
  JComboBox ObjectSelect;
  Image lines;
  JButton ClearCanvas;
  
  // Constructor
  public GalaxySimBeta001(int w, int h, JFrame f)
  {
    super.setOpaque(true);
    super.setPreferredSize(new Dimension(w, h));
    super.setBackground(new Color(225, 225, 225));
    c = new Timer(20, this);
    c.addActionListener(this);
    addMouseListener(this);
    s = new ArrayList<SpaceObject>();
    c.start();
    String[] x = {"Select an Object to Create", "Moon", "Planet", "Star", "Black Hole"};
    ObjectSelect = new JComboBox(x);
    super.add(ObjectSelect);
    ObjectSelect.addActionListener(this);
    lines = new BufferedImage(10000,10000, BufferedImage.TYPE_INT_ARGB);
    ClearCanvas = new JButton();
    super.add(ClearCanvas);
    ClearCanvas.setText("Clear Lines");
    ClearCanvas.addActionListener(this);
    //s.add(new Moon(0,0,400,400));
    //s.add(new Moon(0,0,880,320));
  }
  
  public void Gravity()
  {
    int j = 1;
    for (int k = 0; k<s.size();k++)
    {
      for (int m = k+1; m<s.size();m++)
      {
        if(s.get(k).isPhysical() && s.get(m).isPhysical())
        {
          double Force;
          double xDiff = (s.get(k).getXPos()-s.get(m).getXPos());
          double yDiff = (s.get(m).getYPos()-s.get(k).getYPos());
          double r = Math.sqrt((xDiff*xDiff)+(yDiff*yDiff));
          if (r != 0)
          {
            double kMass = s.get(k).getMass();
            double mMass = s.get(m).getMass();
            Force = (G*(kMass*mMass))/(r*r);
            double xForce = Force*(xDiff/r);
            double yForce = Force*(yDiff/r);
            double kxAcc = xForce/kMass;
            double kyAcc = yForce/kMass;
            double mxAcc = xForce/mMass;
            double myAcc = yForce/mMass;
            kxAcc*=-1;
            kyAcc*=-1;
            s.get(k).changeVelocity(kxAcc, kyAcc);
            s.get(m).changeVelocity(mxAcc, myAcc);
            if (s.get(k).isTouching(s.get(m)))
            {
              double nxv = ((kMass*s.get(k).getXVelocity())+(mMass*s.get(m).getXVelocity()))/(kMass+mMass);
              double nyv = ((kMass*s.get(k).getYVelocity())+(mMass*s.get(m).getYVelocity()))/(kMass+mMass);
              if (s.get(k).getMass() >= s.get(m).getMass())
              {
                s.get(k).mass += mMass;
                s.get(k).setVelocity(nxv,nyv);
                s.get(k).radius = Math.pow((Math.pow(s.get(k).getRadius(),3))+(Math.pow(s.get(m).getRadius(),3)), (1.0/3.0));
                s.remove(m);
              }
              else
              {
                s.get(m).mass += kMass;
                s.get(m).setVelocity(nxv, nyv);
                s.get(m).radius = Math.pow((Math.pow(s.get(k).getRadius(),3))+(Math.pow(s.get(m).getRadius(),3)), (1.0/3.0));
                s.remove(k);
              }
            }
          }
        }
      }
      j++;
    }
  }
  
  // Perform any custom painting (if necessary) in this method
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, 10000, 10000);
    g.drawImage(lines, 0, 0, this);
    for(int k = 0; k < s.size(); k++) s.get(k).graph(g);
  }
  
  // Process GUI input in this method
  @Override  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == c)
    {
      for(int k = 0; k<s.size(); k++)
      {
        s.get(k).move();
        s.get(k).checkColor();
        lines.getGraphics().drawLine((int) (s.get(k).getXPos()+s.get(k).getRadius()),(int) (s.get(k).getYPos()+s.get(k).getRadius()),(int) (s.get(k).getXPos()+s.get(k).getRadius()),(int) (s.get(k).getYPos()+s.get(k).getRadius()));
      }
      Gravity();
    }
    if (e.getSource() == ClearCanvas)
    {
      lines.getGraphics().clearRect(0, 0, 10000, 10000);
    }
    super.repaint();
  }
  
  //<editor-fold defaultstate="collapsed" desc="--This method will launch your application--">
  public static void main(String[] args)
  {
    JFrame.setDefaultLookAndFeelDecorated(false);
    JFrame fr = new JFrame("Application: GalaxySimBeta001");
    fr.setContentPane(new GalaxySimBeta001(1280, 720, fr));
    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fr.setLocation(10, 10);
    fr.setResizable(true);
    fr.pack();
    fr.setVisible(true);  
  }
  //</editor-fold>  

  @Override
  public void mouseClicked(MouseEvent e)
  {
    
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    if (ObjectSelect.getSelectedIndex() == 0);
    else if(ObjectSelect.getSelectedIndex() == 1) {s.add(new Moon(0, 0, e.getX(), e.getY())); System.out.println("moon created");}
    else if(ObjectSelect.getSelectedIndex() == 2) {s.add(new Planet(0, 0, e.getX(), e.getY())); System.out.println("planet created");}
    else if(ObjectSelect.getSelectedIndex() == 3) {s.add(new Star(0, 0, e.getX(), e.getY())); System.out.println("star created");}
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    if (ObjectSelect.getSelectedIndex() != 0)
    {
      int index = s.size()-1;
      double xDiff = e.getX()-s.get(index).getXPos();
      double yDiff = e.getY()-s.get(index).getYPos();
      double diff = (xDiff*xDiff)+(yDiff*yDiff);
      s.get(index).setVelocity(xDiff, yDiff);
      s.get(index).phys = true;
    }
  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
    
  }

  @Override
  public void mouseExited(MouseEvent e)
  {
    
  }

}
