
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.ShapeGraphicAttribute;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static  org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import javax.swing.JColorChooser;
public class SwingPaint {

    JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn, circleBtn, lineBtn,starBtn,reactBtn,ellipseBtn, chooserBtn, fillBtn;
    DrawArea drawArea;
    ActionListener actionListener = new ActionListener() {


        ///FOR CLICK ACTION
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                drawArea.clear();

            } else if (e.getSource() == blueBtn) {
                drawArea.blue();
            }
            else if (e.getSource() == greenBtn) {
                drawArea.green();
            }
            else if (e.getSource() == redBtn) {
                drawArea.red();
            }
            else if (e.getSource() == circleBtn) {
                drawArea.circle();
            }
            else if (e.getSource() == lineBtn) {
                drawArea.line();
            }
            else if (e.getSource() == starBtn) {
                drawArea.star();
            }
            else if (e.getSource() == reactBtn) {
                drawArea.react();
            }


            else if (e.getSource() == fillBtn) {
                drawArea.fill();
            }
            else if (e.getSource() == chooserBtn) {
                drawArea.chooser();
            }


        }
    };
    //FOR FIRST SCREEN
    public static void main(String[] args) {
        new SwingPaint().show();
    }

    @Test
    public void  Testclass()
    {
        ShapeDimensions sh=new ShapeDimensions();
        Assert.assertSame(sh.getH(),sh.getH());

    }

    public void show() {

        JFrame frame = new JFrame("Swing Paint");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        drawArea = new DrawArea();
        content.add(drawArea, BorderLayout.CENTER);
        JPanel controls = new JPanel();

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);
        greenBtn = new JButton("Green");
        greenBtn.addActionListener(actionListener);
        blueBtn = new JButton("Blue");
        blueBtn.addActionListener(actionListener);
        redBtn = new JButton("Red");
        redBtn.addActionListener(actionListener);

        circleBtn = new JButton("Circle");
        circleBtn.addActionListener(actionListener);

        reactBtn = new JButton("Reactangle");
        reactBtn.addActionListener(actionListener);

        lineBtn = new JButton("Line");
        lineBtn.addActionListener(actionListener);

        starBtn = new JButton("Star");
        starBtn.addActionListener(actionListener);

        chooserBtn = new JButton("ColorChooser");
        chooserBtn.addActionListener(actionListener);

        fillBtn = new JButton("Fill");
        fillBtn.addActionListener(actionListener);

        // add to panel
        controls.add(greenBtn);
        controls.add(blueBtn);
        controls.add(redBtn);
        controls.add(circleBtn);
        controls.add(lineBtn);
        controls.add(reactBtn);
        controls.add(starBtn);
        controls.add(chooserBtn);
        controls.add(fillBtn);
        controls.add(clearBtn);

        JMenu menu, submenu;
        JMenuItem i1, i2, i3, i4, i5;


        JMenuBar mb=new JMenuBar();
        menu=new JMenu("Menu");

        i1=new JMenuItem("Open");


        i1.addActionListener(actionListener);
        i1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                drawArea.open();

            }
        });

        i2=new JMenuItem("Save");
        i2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                drawArea.save();

            }
        });

        i3=new JMenuItem("Item 3");
        i4=new JMenuItem("Item s4");
        i5=new JMenuItem("Item 5");
        menu.add(i1); menu.add(i2);

        mb.add(menu);
        frame.setJMenuBar(mb);

        // add to content pane
        content.add(controls, BorderLayout.NORTH);

        frame.setSize(1200, 800);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);


    }

}