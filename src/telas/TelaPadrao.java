package telas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TelaPadrao extends JFrame{
	
	public TelaPadrao() {
		
		setSize(1366,768);
		setResizable(true);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource("icon_image.png")).getImage());

	}

}
