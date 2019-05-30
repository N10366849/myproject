import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JComponent;


public class DrawArea extends JComponent {

    // Image in which we're going to draw
    private Image image;
    // Graphics2D object ==> used to draw on
    private Graphics2D g2;
    // Mouse coordinates
    private int currentX, currentY, oldX, oldY;

    //for last size memory
    int lastshapeX;
    int lastshapeY;
    int lastshapeW;
    int lastshapeH;
    int shape; ///1 for circle 2 for react 3 for

    //for maximum size
    int basicshapew=600;
    int basicshapeh=600;
    ShapeDimensions sh,cir,ln,st;
    //active shape
    int activeshape=0; //0 for line  // 1 for circle // 2 for react  //3 for star
    //ARRAY list for memorization
    ArrayList<ShapeDimensions> di = new ArrayList<ShapeDimensions>();

    //poiunt info
    int x = 0;

    int y = 0;

    int w = getSize().width/2;

    int h = getSize().height/2;
    //constructior
    public DrawArea() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // coord x,y when drag mouse
                currentX = e.getX();
                currentY = e.getY();
                x=e.getX();
                y=e.getY();

                if (g2 != null) {


                    g2.drawLine(oldX, oldY,currentX , currentY);
                    // refresh draw area to repaint
                    repaint();
                    // store current coords x,y as olds x,y
                    oldX = currentX;
                    oldY = currentY;

                }
            }
        });
    }
    //paint method
    protected void paintComponent(Graphics g) {
        if (image == null) {
            // image to draw null ==> we create
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // clear draw area
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    // now we create exposed methods

    //action methods
    public void red() {
        // apply red color on g2 context
        g2.setPaint(Color.red);
    }


    public void green() {
        g2.setPaint(Color.green);
    }

    public void blue() {
        g2.setPaint(Color.blue);
    }


    //FOR PRINTING CIRCLE
    public void circle() {

        g2.drawOval(x, y, basicshapew, basicshapeh);

        lastshapeX=x;
        lastshapeY=y;
        lastshapeW=basicshapew;
        lastshapeH=basicshapeh;
        shape=1;
        sh=new ShapeDimensions("circle",lastshapeX,lastshapeY,lastshapeW,lastshapeH);
        //di.add(sh);


    }

    //FOR PRINTING LINE
    public void line() {

        activeshape=0;
        g2.drawLine(oldX, oldY,basicshapew , basicshapeh);
        lastshapeX=oldX;
        lastshapeY=oldY;
        lastshapeW=basicshapew;
        lastshapeH=basicshapeh;
        shape=3;
        ln=new ShapeDimensions("line",lastshapeX,lastshapeY,lastshapeW,lastshapeH);


    }

    //FOR PRINTING STAR
    public void star() {

        activeshape=3;

        int[] x  = {32,42,62,42,50,30,5,18,3,22,32};
        int[] y = {28,52,58,70,95,75,92,65,48,50,28};
        AffineTransform scaleMatrix = new AffineTransform();
        AffineTransform oldAT = g2.getTransform();
        scaleMatrix.scale(14.5, 6.5);
        g2.setTransform(scaleMatrix);
        g2.drawPolygon(x, y, y.length);


        //revert back the scalling
        g2.setTransform(oldAT);
        //scaleMatrix.scale(1.1, 1.1);
        //st=new ShapeDimensions("star",lastshapeX,lastshapeY,lastshapeW,lastshapeH);


    }
    public void react() {

        g2.drawRect(oldX, oldY, basicshapew, basicshapeh);
        lastshapeX=oldX;
        lastshapeY=oldY;
        lastshapeW=basicshapew;
        lastshapeH=basicshapeh;
        shape=2;
        cir=new ShapeDimensions("react",lastshapeX,lastshapeY,lastshapeW,lastshapeH);

    }
    public void fill() {
        if (shape==1)
        {
            g2.fillOval(lastshapeX, lastshapeY, lastshapeW,lastshapeH);
            String hex = String.format("#%02x%02x%02x", g2.getColor().getRed(), g2.getColor().getGreen(), g2.getColor().getBlue());
            sh.setColor(hex);
            // di.add(sh);


        }
        if (shape==2)
        {
            g2.fillRect(lastshapeX, lastshapeY, lastshapeW,lastshapeH);
            String hex = String.format("#%02x%02x%02x", g2.getColor().getRed(), g2.getColor().getGreen(), g2.getColor().getBlue());
            cir.setColor(hex);
        }
        if (shape==3)
        {
            int[] x  = {32,42,62,42,50,30,5,18,3,22,32};
            int[] y = {28,52,58,70,95,75,92,65,48,50,28};
            AffineTransform scaleMatrix = new AffineTransform();
            AffineTransform oldAT = g2.getTransform();
            scaleMatrix.scale(14.5, 6.5);
            g2.setTransform(scaleMatrix);
            g2.fillPolygon(x, y, y.length);
            //revert back the scalling
            g2.setTransform(oldAT);

        }


    }
    public void chooser() {
        Color c = JColorChooser.showDialog(null, "Choose a Color",  null);
        if (c != null) {
            g2.setPaint(c);
        }

    }
    public void clear() {
        g2.setPaint(Color.white);

        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }

    public void save()
    {

        presave();
        try {
            PrintWriter writer = new PrintWriter("file.vec");
            for (int i = 0; i < di.size(); i++) {

                writer.println(di.get(i).name+","+di.get(i).x+","+di.get(i).y+","+di.get(i).h+","+di.get(i).w);
                if (di.get(i).color !="" )

                {
                    writer.println(di.get(i).color);
                }
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }

    }
    public void open()
    {
        ///decoding the file
        try {
            BufferedReader in = new BufferedReader(new FileReader("file.vec"));
            String str;
            int i=0;
            int j=0;
            int k=0;
            int lx,ly,lw,lh;
            int shapetype=0;
            sh=new ShapeDimensions();

            while ((str = in.readLine())!= null) {
                String[] values = str.split(",");
                for (String str2 : values) {

                    if (i==0)
                    {


                        sh.setName(str2);
                        i=1;
                        if (str2.equals("circle"))
                        {
                            shapetype=1;
                        }
                        else if (str2.equals("react"))
                        {
                            shapetype=2;
                        }

                        else if (str2.equals("line"))
                        {
                            shapetype=3;
                        }


                    }
                    else if (i==1)
                    {
                        sh.setX(Integer.parseInt(str2));
                        i=2;

                    }

                    else if (i==2)
                    {
                        sh.setY(Integer.parseInt(str2));

                        i=3;
                    }

                    else if (i==3)
                    {
                        sh.setW(Integer.parseInt(str2));

                        i=4;
                    }

                    else if (i==4)
                    {
                        sh.setH(Integer.parseInt(str2));

                        i=5;

                    }

                    else if (i==5)
                    {
                        if (str2.equals("null"))

                        {
                            sh.setColor("#000000");
                        }

                        else
                        {
                            sh.setColor(str2);
                        }
                        i=0;
                        k=1;
                        j=1;
                    }

                    if (shapetype==1 && j==1)
                    {

                        g2.drawOval(sh.getX(), sh.getY(), sh.getW(), sh.getH());
                        repaint();
                        j=0;
                    }

                    else if (shapetype==2 && j==1)
                    {

                        g2.drawRect(sh.getX(), sh.getY(), sh.getW(), sh.getH());
                        repaint();
                        j=0;
                    }

                    else if (shapetype==3 && j==1)
                    {


                        g2.drawLine(sh.getX(), sh.getY(), sh.getW(), sh.getH());
                        repaint();
                        j=0;
                    }

                    else if (shapetype==4 && j==1)
                    {

                        int[] x  = {32,42,62,42,50,30,5,18,3,22,32};
                        int[] y = {28,52,58,70,95,75,92,65,48,50,28};
                        AffineTransform scaleMatrix = new AffineTransform();

                        //scaleMatrix.scale(14.5, 6.5);
                        // g2.setTransform(scaleMatrix);
                        g2.drawPolygon(x, y, y.length);
                        j=0;
                    }


                    else if (shapetype==1 && k==1)
                    {
                        // g2.drawOval(sh.getX(), sh.getY(), sh.getW(), sh.getH());
                        g2.setColor(Color.decode(sh.getColor()));
                        //repaint();
                        g2.fillOval(sh.getX(), sh.getY(), sh.getW(), sh.getH());
                        repaint();

                    }

                    else if (shapetype==2 && k==1)
                    {
                        g2.setColor(Color.decode(sh.getColor()));
                        repaint();
                        g2.fillRect(lastshapeX, lastshapeY, lastshapeW,lastshapeH);


                    }




                }
            }


            in.close();


        } catch (IOException e) {
            System.out.println("File Read Error");
        }
    }

    public void presave()
    {
        if (sh!=(null))
        {
            di.add(sh);

        }

        if (cir !=null)
        {
            di.add(cir);

        }
        if (ln !=null)
        {
            di.add(ln);

        }
    }


}