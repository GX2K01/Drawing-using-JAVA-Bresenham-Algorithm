import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Frame extends JFrame {

    private JPanel contentPane;
    private JTextField txtx1;
    private JTextField txty1;
    private JTextField txtx2;
    private JTextField txty2;
    Ellipse2D.Double ellipse;
    Rectangle square;
    JPanel drawpanel = new JPanel();
    JRadioButton radioligne;
    JRadioButton radiocercle;
    JRadioButton radiorectangle;
    JRadioButton radioellipse;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame frame = new Frame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Frame() {
        setFont(new Font("Agency FB", Font.PLAIN, 32));
        ImageIcon img = new ImageIcon("C:/Users/GX2K01/Desktop/java/icon.jpg");
        this.setIconImage(img.getImage());
 //       setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\GX2K01\\Desktop\\java\\favicon.ico"));
        setTitle("Mini projet : Dessin des formes 2D - By GX2K01");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 852, 753);
        contentPane = new JPanel();
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        drawpanel.setBorder(new TitledBorder(null, "Dessin", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        drawpanel.setBounds(10, 286, 818, 391);
        contentPane.add(drawpanel);

        radioligne = new JRadioButton("Ligne -AlgoBresenham");
        buttonGroup.add(radioligne);
        radioligne.setBounds(85, 220, 208, 23);
        contentPane.add(radioligne);

        radiocercle = new JRadioButton("Cercle -AlgoBresenham");
        buttonGroup.add(radiocercle);
        radiocercle.setBounds(85, 246, 208, 23);
        contentPane.add(radiocercle);

        radiorectangle = new JRadioButton("Rectangle");
        buttonGroup.add(radiorectangle);
        radiorectangle.setBounds(323, 246, 111, 23);
        contentPane.add(radiorectangle);

        radioellipse = new JRadioButton("Ellipse");
        buttonGroup.add(radioellipse);
        radioellipse.setBounds(323, 220, 111, 23);
        contentPane.add(radioellipse);

        JLabel l_x1 = new JLabel("Cordonné X1");
        l_x1.setFont(new Font("Tahoma", Font.BOLD, 11));
        l_x1.setBounds(111, 34, 111, 14);
        contentPane.add(l_x1);

        txtx1 = new JTextField();
        txtx1.setBounds(111, 59, 96, 20);
        contentPane.add(txtx1);
        txtx1.setColumns(10);


        JLabel l_y1 = new JLabel("Cordonné Y1");
        l_y1.setFont(new Font("Tahoma", Font.BOLD, 11));
        l_y1.setVerticalAlignment(SwingConstants.BOTTOM);
        l_y1.setBounds(294, 34, 96, 14);
        contentPane.add(l_y1);

        txty1 = new JTextField();
        txty1.setColumns(10);
        txty1.setBounds(294, 59, 96, 20);
        contentPane.add(txty1);

        JLabel l_x2 = new JLabel("Cordonné X2");
        l_x2.setFont(new Font("Tahoma", Font.BOLD, 11));
        l_x2.setBounds(111, 123, 111, 14);
        contentPane.add(l_x2);


        txtx2 = new JTextField();
        txtx2.setColumns(10);
        txtx2.setBounds(111, 147, 96, 20);
        contentPane.add(txtx2);

        JLabel l_y2 = new JLabel("Cordonné Y2");
        l_y2.setFont(new Font("Tahoma", Font.BOLD, 11));
        l_y2.setBounds(294, 122, 96, 14);
        contentPane.add(l_y2);

        txty2 = new JTextField();
        txty2.setColumns(10);
        txty2.setBounds(294, 147, 96, 20);
        contentPane.add(txty2);


        JButton btnNewButton = new JButton("DRAW!");
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int x1 = Integer.parseInt(txtx1.getText());
                int x2 = Integer.parseInt(txtx2.getText());
                int y1 = Integer.parseInt(txty1.getText());
                int y2 = Integer.parseInt(txty2.getText());

                Graphics g = drawpanel.getGraphics();
                if(radioligne.isSelected()) {
                    BresenhamLigne(x1,y1,x2,y2, g);
                }
                else if(radiocercle.isSelected()) {
                    BresenhamCercle(x1,y1,x2, g);
                }
                else if(radioellipse.isSelected()) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setPaint(Color.RED);
                    ellipse = new Ellipse2D.Double(x1, y1, x2, y2);//(x point, y point, width, height)
                    g2.draw(ellipse);
                }
                else if(radiorectangle.isSelected()) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setPaint(Color.WHITE);
                    square = new Rectangle(x1,y1,x2,y2);
                    g2.draw(square);
                }
            }
            private void BresenhamCercle(int radius, int centerX, int centerY, Graphics g) {
                int y = radius;
                int x = 0;

                int delta = calculateStartDelta(radius);
                while (y >= x) {
                    drawPixelAndReflect(centerX, centerY, x, y, g);

                    if (delta < 0) {
                        delta = calculateDeltaForHorizontalPixel(delta, x);
                    } else {
                        delta = calculateDeltaForDiagonalPixel(delta, x, y);
                        y--;
                    }
                    x++;
                }
            }

            private int calculateStartDelta(int radius) {
                return 3 - 2 * radius;
            }

            private int calculateDeltaForHorizontalPixel(int oldDelta, int x) {
                return oldDelta + 4 * x + 6;
            }

            private int calculateDeltaForDiagonalPixel(int oldDelta, int x, int y) {
                return oldDelta + 4 * (x - y) + 10;
            }

            private void drawPixelAndReflect(int centerX, int centerY, int x, int y, Graphics g) {
                g.drawLine(centerX + x, centerY + y, centerX + x, centerY + y);
                g.drawLine(centerX + x, centerY - y, centerX + x, centerY - y);
                g.drawLine(centerX - x, centerY + y, centerX - x, centerY + y);
                g.drawLine(centerX - x, centerY - y, centerX - x, centerY - y);
                g.drawLine(centerX - y, centerY + x, centerX - y, centerY + x);
                g.drawLine(centerX - y, centerY - x, centerX - y, centerY - x);
                g.drawLine(centerX + y, centerY + x, centerX + y, centerY + x);
                g.drawLine(centerX + y, centerY - x, centerX + y, centerY - x);
                Graphics2D g0 = (Graphics2D) g;
                g0.setPaint(Color.YELLOW);
            }
            private void BresenhamLigne(int x1, int y1, int x2, int y2, Graphics g) {
                int dx,dy,xEnd,p,x,y;
                dx=Math.abs(x1-x2);
                dy=Math.abs(y1-y2);
                p=2*dy-dx;
                if(x1>x2) {
                    x=x2;
                    y=y2;
                    xEnd=x1;
                }
                else {
                    x=x1;
                    y=y1;
                    xEnd=x2; /* Error was here*/
                }
                g.drawString(".", x, y);
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(Color.BLACK);
                while(x<xEnd) {
                    x++;
                    if(p<0) {
                        p+=2*dy;
                    }
                    else {
                        y++;
                        p+=2*(dy-dx);
                    }
                    g.drawString(".", x, y);
                }
            }
        });
        btnNewButton.setBounds(532, 93, 142, 74);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Encadré par : Ismail Elbatteoui");
        lblNewLabel.setBounds(10, 691, 229, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Made with ❤️ by GX2K01 ");
        lblNewLabel_1.setBounds(686, 689, 256, 14);
        contentPane.add(lblNewLabel_1);
    }
}
