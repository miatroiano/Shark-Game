import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class App extends JFrame {

	public App() {
		super("Game");
		this.setLayout(new BorderLayout());
		
		DrawingPanel drawingPanel = new DrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
		ControlPanel controlPanel = new ControlPanel(drawingPanel);
		this.add(controlPanel, BorderLayout.SOUTH);
		drawingPanel.addListener(controlPanel);
		
		BufferedImage icon16 = null;
		BufferedImage icon24 = null;
		BufferedImage icon32 = null;
		BufferedImage icon64 = null;

		ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>();
		try {
			icon16 = ImageIO.read(new File("./images/game-controller-icon-16.png"));
			icon24 = ImageIO.read(new File("./images/game-controller-icon-24.png"));
			icon32 = ImageIO.read(new File("./images/game-controller-icon-32.png"));
			icon64 = ImageIO.read(new File("./images/game-controller-icon-64.png"));

			icons.add(icon16);
			icons.add(icon24);
			icons.add(icon32);
			icons.add(icon64);
			this.setIconImages(icons);
		}
		catch (IOException e) {
			System.out.println("Unable to load icon images! Using defaults.");
		}
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new App();

	}

}
