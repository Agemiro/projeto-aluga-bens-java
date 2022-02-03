package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import icones.Icones;
import labelsEFieldsPadrao.LabelPadrao;
import persistencia.Persistencia;
import pojo.Central;
import pojo.Usuario;


public class TelaHome extends TelaPadrao{
	
	private Usuario usuarioLogado;

	public TelaHome(Usuario usuarioLogado){
		
		this.usuarioLogado = usuarioLogado;
		setTitle("Home");
		
		barraDeMenu();
		labels();
		imagens();
		botoes();
		
		this.setVisible(true);
		
	}
	
	private void barraDeMenu() {
		
		Persistencia p = new Persistencia();
		Central c = p.recuperarDados("dados.xml");
		
		JMenuBar barraDeMenu = new JMenuBar();
		setJMenuBar(barraDeMenu);
		
		JMenu menuOpcoes = new JMenu("Opções");
				
		JMenuItem menuListarBens = new JMenuItem("Lista de Bens");
		
		menuListarBens.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaListaDeTodosBens();
			}
			
		});
		
		menuOpcoes.add(menuListarBens);
		
		JMenuItem menuSair = new JMenuItem("Sair");
				
		menuSair.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				c.setUsuarioLogado("");
				p.salvarDados(c, "dados.xml");

				dispose();
				new TelaInicial();
			}
		});
		
		barraDeMenu.add(menuOpcoes);
		barraDeMenu.add(menuSair);
		
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}


	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}


	private void labels() {

		JLabel bemVindo = new JLabel("Bem-vindo " + usuarioLogado.getNomeCompleto().split(" ")[0]);
		JLabel nome = new LabelPadrao("Nome: " + usuarioLogado.getNomeCompleto(), 260, 250, 300, 28);
		JLabel email = new LabelPadrao("Email: " + usuarioLogado.getEmail(), 260, 280, 400, 28);
		JLabel sexo = new LabelPadrao("Sexo: " + usuarioLogado.getSexo(), 260, 310, 300, 28);
		JLabel nascimento = new LabelPadrao("Data de Nascimento: " + usuarioLogado.getDataDeNascimento(), 260, 340, 300, 28);
		JLabel cargo = new LabelPadrao("Cargo: " + usuarioLogado.getProfissao(), 260, 370, 300, 28);
		
		bemVindo.setBounds(260, 150, 400, 32);
		bemVindo.setFont(new Font("Arial", Font.BOLD, 28));
		bemVindo.setForeground(Color.BLUE);
		
		add(bemVindo);
		add(nome);
		add(email);
		add(sexo);
		add(nascimento);
		add(cargo);
		
	}
	
	private void imagens() {
		
		JLabel imagem = new JLabel(Icones.IMAGEM);
		imagem.setBounds(780, 170, 377, 342);
		add(imagem);
		
	}
	
	private void botoes() {
		
		JButton botaoEditarCadastroUsuario = new JButton("Editar", Icones.EDITAR);
		botaoEditarCadastroUsuario.setBackground(Color.WHITE);
		botaoEditarCadastroUsuario.setBounds(310, 550, 130, 35);
		botaoEditarCadastroUsuario.setToolTipText("Clique para editar seu cadastro");
		
		botaoEditarCadastroUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new TelaCadastroUsuario();
				
			}
		});
		
		add(botaoEditarCadastroUsuario);
	}
}
