package labelsEFieldsPadrao;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class LabelPadrao extends JLabel{
	
	public LabelPadrao(String texto, int x, int y, int w, int h) {
		super(texto);
		setFont(new Font("Arial", Font.BOLD, 14));
		setBounds(x, y, w, h);
		setForeground(Color.DARK_GRAY);
	}
	
}
