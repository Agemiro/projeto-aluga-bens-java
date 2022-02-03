package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import icones.Icones;
import labelsEFieldsPadrao.JPassWordFieldCaixa;
import labelsEFieldsPadrao.LabelPadrao;
import labelsEFieldsPadrao.TextFieldPadrao;
import persistencia.Persistencia;
import pojo.Central;
import pojo.Locador;
import pojo.Locatario;

public class TelaInicial extends TelaPadrao{
	
	private JTextField email;
	private JPasswordField campoDeSenha;
	private JButton botaoCadastro, botaoLogin;
	private JCheckBox esqueceuSenha;
	
	public TelaInicial() {
		
		setTitle("Aluga Bens - Início");
        
		labels();
		campoDeDigito();
		imagens();
		barraDeMenu();
		recuperarSenha();
		botoes();
		
		this.setVisible(true);
		
	}
	
	public void labels() {		
		
		JLabel cadastro = new LabelPadrao("Cadastrar um novo usuário", 70, 150, 180, 28);
	    cadastro.setFont(new Font("Arial", Font.BOLD, 12));
		JLabel email = new LabelPadrao("Email", 370, 295, 100, 28);
		JLabel senha = new LabelPadrao("Senha", 370, 345, 100, 28);
				
		add(cadastro);
		add(email);
		add(senha);
		
	}
	
	public void campoDeDigito() {
		
		email = new TextFieldPadrao(370,320,220,28);	
		email.setToolTipText("Digite seu email");
		campoDeSenha = new JPassWordFieldCaixa(370,370,220,28);
		
		add(email);
		add(campoDeSenha);
		
	}
	
	public void imagens () {
		
		JLabel esquina = new JLabel(Icones.IMAGEM_FUNDO2);
		JLabel imagem = new JLabel(Icones.IMAGEM);
		JLabel login = new JLabel(Icones.LOGIN);
		JLabel seta = new JLabel(Icones.SETA);
		JLabel cadeadoFechado = new JLabel(Icones.CADEADO_FECHADO);		
		
		cadeadoFechado.setToolTipText("Confidencial");
		
		imagem.setBounds(780, 170, 377, 342);
		login.setBounds(420, 150, 140, 140);
		seta.setBounds(135, 95, 200, 200);
		cadeadoFechado.setBounds(595, 365, 32, 32);
	    esquina.setBounds(800, 0, 624, 266);
		
		add(imagem);
		add(login);
		add(seta);
		add(cadeadoFechado);
		add(esquina);
		
	}

	private void barraDeMenu() {
		
		JMenuBar barraDeMenu = new JMenuBar();
		setJMenuBar(barraDeMenu);
		
		JMenuItem menuListarBens = new JMenuItem("Bens cadastrados");
				
		menuListarBens.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaListaDeTodosBens();
			}
		});
		
		barraDeMenu.add(menuListarBens);
		
	}
	
	public void recuperarSenha() {
		
		esqueceuSenha = new JCheckBox("Esqueci minha senha", false);
		esqueceuSenha.setBounds(400, 495, 150, 30);
		esqueceuSenha.setToolTipText("Marque a opção, caso tenha esquecido sua senha");
		
		esqueceuSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esqueceuSenha.isSelected()) {
					new TelaRecuperarSenha();
				}
			}
		});	
		
		add(esqueceuSenha);
		
	}
	
	public void botoes() {
		
		botaoCadastro = new JButton("Cadastrar", Icones.CADASTRO);
		botaoCadastro.setBounds(80, 200, 130, 35);
		botaoCadastro.setBackground(Color.WHITE);
		botaoCadastro.setToolTipText("Clique para cadastrar um novo usúario");
		
		botaoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadastroUsuario();
			}
		}
		);
		
		add(botaoCadastro);
		
		botaoLogin = new JButton("Login", Icones.LOGIN2);
		botaoLogin.setBounds(410, 430, 130, 35);
		botaoLogin.setBackground(Color.WHITE);
		botaoLogin.setToolTipText("Clique para fazer Login");
		
		OuvinteBotaoLogin ouvinteLogin = new OuvinteBotaoLogin(this);

		botaoLogin.addActionListener(ouvinteLogin);		
		
		add(botaoLogin);
		
	}
	
	private class OuvinteBotaoLogin implements ActionListener{
		
		private JFrame tela;
		
		public OuvinteBotaoLogin(JFrame tela) {
			this.tela = tela;
		}
		
		public void actionPerformed(ActionEvent evento) {
			
			String acessoEmail = email.getText();
			String acessoSenha = new String(campoDeSenha.getPassword());
						
			Persistencia p = new Persistencia();
			Central c = p.recuperarDados("dados.xml");
						
			if(acessoEmail.equals("") && acessoSenha.equals("")) {
				
				JOptionPane.showMessageDialog(tela, "Campos em branco!", "Falha", JOptionPane.ERROR_MESSAGE);
				
			}else {	
				
				if(acessoEmail.equals(c.getLocatario().getEmail()) && acessoSenha.equals(c.getLocatario().getSenha())) {
					
					c.setUsuarioLogado(acessoEmail);
					p.salvarDados(c, "dados.xml");
					
					Locatario locatario = c.getLocatario();
				
					new TelaHome(locatario).setLocationRelativeTo(tela);
					dispose();
					
				}else {
					for (Locador locador : c.getLocadoresCadastrados()) {
						if (acessoEmail.equals(locador.getEmail()) && acessoSenha.equals(locador.getSenha())) {

							c.setUsuarioLogado(acessoEmail);
							p.salvarDados(c, "dados.xml");
						
							new TelaHome(locador).setLocationRelativeTo(tela);
							dispose();
						}
					}
				}
			}
		}			
	}
	
	public JTextField getEmail() {
		return email;
	}

	public void setEmail(JTextField email) {
		this.email = email;
	}

	public JPasswordField getCampoDeSenha() {
		return campoDeSenha;
	}

	public void setCampoDeSenha(JPasswordField campoDeSenha) {
		this.campoDeSenha = campoDeSenha;
	}

	public JButton getBotaoCadastro() {
		return botaoCadastro;
	}

	public void setBotaoCadastro(JButton botaoCadastro) {
		this.botaoCadastro = botaoCadastro;
	}

	public JButton getBotaoLogin() {
		return botaoLogin;
	}

	public void setBotaoLogin(JButton botaoLogin) {
		this.botaoLogin = botaoLogin;
	}

	public JCheckBox getEsqueceuSenha() {
		return esqueceuSenha;
	}

	public void setEsqueceuSenha(JCheckBox esqueceuSenha) {
		this.esqueceuSenha = esqueceuSenha;
	}
	
}
