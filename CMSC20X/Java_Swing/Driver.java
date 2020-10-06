/*
 * Class: CMSC201 
 * Instructor: Dr Gringberg
 * Description: Graphs sine and cosine functions with JavaFX
 * Due: 12/02/2018
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Melvin Molina
*/

import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;
import javax.swing.JPanel;

/**
 * this class decides when to call other class
 * @author melvi
 *
 */
public class Driver extends JFrame {

/**
 * this method creates an instance of drawSine and centers the border layout
 */
public Driver() {
    setLayout(new BorderLayout());
    add(new DrawSine(), BorderLayout.CENTER);
}

/**
 * this is the driver function that calls the Driver method
 * @param args
 */
public static void main(String[] args) {
    Driver frame = new Driver();
    frame.setSize(400, 300);
    frame.setTitle("Project11");
    frame.setVisible(true);

} 

}

/**
 * this method draws the cosine and sine lines
 * and gives each line it's color
 * and sets the x axis
 * converts the polygons into polylines to graph correctly
 * @author Melvin Molina
 */
class DrawSine extends JPanel {

	/**
	 * 
	 * @param x
	 * @return the value of the x value after passing sin
	 */
    double f(double x) {
        return Math.sin(x);
    }

    /**
     * 
     * @param y
     * @return the value of the x value after passing cos
     */
    double gCos(double y) {
        return Math.cos(y);
    }


    /**
     * this class identifies the x axis
     * and also creates the lines for sin and cos
     */
   protected void paintComponent(Graphics k) 
    {
        super.paintComponent(k);

        k.drawLine(10, 100, 380, 100);
        k.drawLine(200, 30, 200, 190);

        
        k.drawLine(380, 100, 370, 90);
        k.drawLine(380, 100, 370, 110);
        k.drawLine(200, 30, 190, 40);
        k.drawLine(200, 30, 210, 40);

        
        k.drawString("X", 360, 80);
        k.drawString("Y", 220, 40);

        Polygon p = new Polygon();
        Polygon p2 = new Polygon();

       for (int x = -170; x <= 170; x++) {
            p.addPoint(x + 200, 100 - 
            	(int) (50 * f((x / 100.0) 
            	* 2 * Math.PI)));

        }

        for (int x = -170; x <= 170; x++) {
            p2.addPoint(x + 200, 100 - (int)
            	(50 * gCos((x / 100.0) * 2 * Math.PI)));
        }

        k.setColor(Color.red);
        k.drawPolyline(p.xpoints, p.ypoints, p.npoints);
        k.drawString("-2\u03c0", 95, 115);
        k.drawString("2\u03c0", 305, 115);
        k.drawString("0", 200, 115);

        k.setColor(Color.blue);
        k.drawPolyline(p2.xpoints, p2.ypoints, p2.npoints);

    }
 }
//javadoc driver.java