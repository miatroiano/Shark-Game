import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements DrawingPanelListener {
	private JButton startGameButton;
	private JButton pauseGameButton;

	public ControlPanel(DrawingPanel drawingPanel) {
		setLayout(new FlowLayout());
		setBackground(Color.green);
		
		startGameButton = new JButton();
		startGameButton.setText("Start");
		startGameButton.setFocusable(false);
		startGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingPanel.startGame();
				startGameButton.setEnabled(false);
				pauseGameButton.setEnabled(true);
			}
		});
		this.add(startGameButton);
		
		pauseGameButton = new JButton();
		pauseGameButton.setText("Pause");
		pauseGameButton.setFocusable(false);
		pauseGameButton.setEnabled(false);
		pauseGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingPanel.togglePause();
				if (pauseGameButton.getText().equals("Pause")) {
					pauseGameButton.setText("Unpause");
				}
				else {
					pauseGameButton.setText("Pause");
				}
				
			}
		});
		this.add(pauseGameButton);
	}

	@Override
	public void onGameOver() {
		pauseGameButton.setEnabled(false);
		startGameButton.setEnabled(true);
	}

}
