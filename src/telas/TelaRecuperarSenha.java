package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import icones.Icones;
import labelsEFieldsPadrao.LabelPadrao;
import labelsEFieldsPadrao.TextFieldPadrao;
import persistencia.Persistencia;
import pojo.Central;
import pojo.JavaMail;
import pojo.Locador;

public class TelaRecuperarSenha extends JFrame{
	
		private JTextField emailDestinatario;
		private JButton botaoEnviar;
		private int novaSenha = (int) 10000 +(int)(Math.random() * 90.001);
	
		public TelaRecuperarSenha() {
			
			setTitle("Recuperar senha");
			setSize(500,250);
			setLocationRelativeTo(null);
			setLayout(null);
			setResizable(false);
	        this.setIconImage(new ImageIcon(getClass().getResource("icon_image.png")).getImage());
	        
	        campoEmail();
	        botao();
	        
			this.setVisible(true);
			
		}
		
		public void campoEmail() {
			
			JLabel label = new LabelPadrao("Digite aqui seu email: ", 120, 40, 170, 28);
			emailDestinatario = new TextFieldPadrao(120, 70, 250, 28);
			
			add(label);
			add(emailDestinatario);
		}
		
		public void botao() {
			
			botaoEnviar = new JButton("Enviar", Icones.ENVIAR_EMAIL);
			botaoEnviar.setBackground(Color.WHITE);
			botaoEnviar.setBounds(180, 120, 130, 35);
			
			OuvinteInternoRecuperarSenha ouvinte = new OuvinteInternoRecuperarSenha();
			botaoEnviar.addActionListener(ouvinte);
			
			add(botaoEnviar);
			
		}

		private class OuvinteInternoRecuperarSenha implements ActionListener{
		    
			public void actionPerformed(ActionEvent evento) {
				
				String acessaEmailDestinatario = emailDestinatario.getText();
				String assunto = "Solicitação de redefinição de senha do Aluga Bens";
				String senhaStr = Integer.toString(novaSenha);
				String mensagem = "Sua nova senha é: " + senhaStr;
				
				Persistencia p = new Persistencia();
				Central c = p.recuperarDados("dados.xml");
				
				if(acessaEmailDestinatario.equals(c.getLocatario().getEmail())) {
					
					c.getLocatario().setSenha(senhaStr);
					p.salvarDados(c, "dados.xml");
					new JavaMail().enviarEmail(acessaEmailDestinatario, assunto, mensagem);
					
				}else {
					for (Locador locador : c.getLocadoresCadastrados()) {
						
						if (acessaEmailDestinatario.equals(locador.getEmail())) {

							locador.setSenha(senhaStr);
							p.salvarDados(c, "dados.xml");
							new JavaMail().enviarEmail(acessaEmailDestinatario, assunto, mensagem);
						}
					}
					JOptionPane.showMessageDialog(null, "Não existe ninguém cadastrado com esse email!", "Falha", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		public JTextField getEmailDestinatario() {
			return emailDestinatario;
		}

		public void setEmailDestinatario(JTextField emailDestinatario) {
			this.emailDestinatario = emailDestinatario;
		}

		public JButton getBotaoEnviar() {
			return botaoEnviar;
		}

		public void setBotaoEnviar(JButton botaoEnviar) {
			this.botaoEnviar = botaoEnviar;
		}
			
}

	

