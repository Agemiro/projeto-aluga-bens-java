package labelsEFieldsPadrao;

import java.awt.Font;
import javax.swing.JTextField;

public class TextFieldPadrao extends JTextField{
	
	public TextFieldPadrao(int x, int y, int w, int h) {
		setFont(new Font("Arial", Font.ITALIC, 15));
		setBounds(x, y, w, h);
		setEnabled(true);
	}
}
