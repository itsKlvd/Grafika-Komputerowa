import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class transform2d extends JPanel {

	private class Display extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.translate(300,300);  // Moves (0,0) to the center of the display.
			int whichTransform = transformSelect.getSelectedIndex();	

			 switch(whichTransform)
			{
			    case 0: break;
			    case 1:
			        g2.scale(0.5,0.5);
			        break;

			    case 2: 
			    	
			    	g2.rotate(Math.toRadians(45));
			        break;

			    case 3:
			        g2.rotate(Math.toRadians(180));
			        g2.scale(0.5,0.8);
			        break;

			    case 4:
			        g2.shear(0.40,0);
			        break;

			    case 5:
			        g2.scale(1,0.3);
			        g2.translate(0,-800);
			        break;

			    case 6:
			        g2.shear(0,-0.4);
			        g2.rotate(Math.toRadians(90));
			        break;

			    case 7:
			    	g2.rotate(Math.toRadians(180));
			    	g2.scale(0.6, 1);
					break;
			        

			    case 8:   	
			    	g2.scale(1,0.3);	        
			        g2.translate(-100,600);
			        g2.rotate(Math.toRadians(40));   
			        break;

			    case 9:
			    	g2.translate(150, 0);
					g2.shear(0, 0.6);
					g2.rotate(Math.toRadians(180));
					break;

			}

			    Polygon p = new Polygon();
		        for (int i = 0; i < 12; i++)
		            p.addPoint((int) (150 * Math.cos(i * 2 * Math.PI / 12)),
		            (int) (150 * Math.sin(i * 2 * Math.PI / 12)));        
		        g2.drawPolygon(p);
		        g2.setColor(Color.blue);
		        g2.fillPolygon(p);
		}
		
	}

	private Display display;
	private BufferedImage pic;
	private JComboBox<String> transformSelect;

	public transform2d() throws IOException {
		display = new Display();
		display.setBackground(Color.black);
		display.setPreferredSize(new Dimension(600,600));
		transformSelect = new JComboBox<String>();
		transformSelect.addItem("None");
		for (int i = 1; i < 10; i++) {
			transformSelect.addItem("No. " + i);
		}
		transformSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.repaint();
			}
		});
		setLayout(new BorderLayout(3,3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.add(new JLabel("Transform: "));
		top.add(transformSelect);
		add(display,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
	}


	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new transform2d());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}

}