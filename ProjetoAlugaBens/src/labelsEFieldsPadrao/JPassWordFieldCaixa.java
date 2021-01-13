package labelsEFieldsPadrao;

import java.awt.Font;
import javax.swing.JPasswordField;

public class JPassWordFieldCaixa extends JPasswordField{
	
	public JPassWordFieldCaixa(int x, int y, int w, int h) {
		setFont(new Font("Arial", Font.BOLD, 14));
		setBounds(x, y, w, h);
		setToolTipText("Digite sua senha");
	}
}
