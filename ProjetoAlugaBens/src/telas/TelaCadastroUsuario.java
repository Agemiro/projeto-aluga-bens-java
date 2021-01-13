package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import icones.Icones;
import labelsEFieldsPadrao.JPassWordFieldCaixa;
import labelsEFieldsPadrao.LabelPadrao;
import labelsEFieldsPadrao.TextFieldPadrao;
import persistencia.Persistencia;
import pojo.Central;
import pojo.Locador;
import pojo.Locatario;

public class TelaCadastroUsuario extends TelaPadrao{
	
	private JTextField nome;
	private JTextField email;
	private JPasswordField senha;
	private JTextField idade;
	
	private JFormattedTextField dataDeNascimento;
	private JTextField endereco;
	private JComboBox<String> cargo;
	private JRadioButton masculino, feminino;

	public TelaCadastroUsuario() {
		
		setTitle("Cadastro de Usuário");
		
		imagens();
		titulos();
		campo();
		comboBox();
		botoes();

		this.setVisible(true);
		
	}
	
	public void imagens () {
		
		JLabel imagem = new JLabel(Icones.IMAGEM);
		JLabel cadeadoFechado = new JLabel(Icones.CADEADO_FECHADO);
		
		cadeadoFechado.setToolTipText("Confidencial");
		
		imagem.setBounds(780, 170, 377, 342);
		cadeadoFechado.setBounds(475, 225, 32, 32);
		
		add(imagem);
		add(cadeadoFechado);
		
	}
	
	public void titulos() {
		
		JLabel nome = new LabelPadrao("Nome", 200, 150, 50, 28); 
		JLabel email = new LabelPadrao("Email", 200, 190, 50, 28);
		JLabel senha = new LabelPadrao("Senha", 200, 230, 50, 28);
		JLabel idade = new LabelPadrao("Idade", 520, 150, 50, 28);
		JLabel sexo = new LabelPadrao("Sexo", 520, 190, 50, 28);
		JLabel dataDeNascimento = new LabelPadrao("Data de Nascimento", 200, 270, 160, 28);
		JLabel endereco = new LabelPadrao("Endereço", 200, 310, 100, 28);
		JLabel situacao = new LabelPadrao("Cargo", 200, 350, 100, 28);

		add(nome);
		add(email);
		add(senha);
		add(idade);
		add(sexo);
		add(dataDeNascimento);
		add(endereco);
		add(situacao);
		
	}
	
	public void campo() {
		
		nome = new TextFieldPadrao(250, 150, 220, 28);
		email = new TextFieldPadrao(250, 190, 220, 28);
		senha = new JPassWordFieldCaixa(250, 230, 220, 28);
		idade = new TextFieldPadrao(570, 150, 30, 28);
		endereco = new TextFieldPadrao(280, 310, 210, 28);

		add(nome);
		add(email);
		add(senha);
		add(idade);
		add(endereco);
		
		try {
			
			MaskFormatter mascaraDaData = new MaskFormatter("##/##/####");
			dataDeNascimento = new JFormattedTextField(mascaraDaData);
			dataDeNascimento.setFont(new Font("Arial", Font.ITALIC, 14));
			dataDeNascimento.setBounds(350, 270, 80, 28);

			add(dataDeNascimento);
			
		}catch(ParseException e) {}
		
	}
	
	private void comboBox() {
		
		Persistencia p = new Persistencia();
		Central c = p.recuperarDados("dados.xml");
		
		String[] cargos = {"Locatario", "Locador"};

		cargo = new JComboBox<String>(cargos);
		cargo.setBounds(280, 350, 80, 28);
		
		if(c.getLocatario().getIdade() == (0)) {
			add(cargo);
		}else {
			cargo.removeItemAt(0);
			add(cargo);
		}
			
	}
	
	public void botoes() {
		
		JButton botaoCadastro = new JButton("Cadastrar", Icones.OK);
		botaoCadastro.setBounds(220, 550, 130, 35);
		botaoCadastro.setBackground(Color.WHITE);
		botaoCadastro.setToolTipText("Clique para cadastrar o usuário");
		
		JButton botaoCancelar = new JButton("Cancelar", Icones.CANCELAR);
		botaoCancelar.setBounds(500, 550, 130, 35);
		botaoCancelar.setBackground(Color.WHITE);
		botaoCancelar.setToolTipText("Clique pra cancelar o cadastro");
		
		OuvinteInternoCadastroUsuario ouvinteUsuario = new OuvinteInternoCadastroUsuario(this);
		
		botaoCadastro.addActionListener(ouvinteUsuario);
		botaoCancelar.addActionListener(ouvinteUsuario);
		
		add(botaoCadastro);
		add(botaoCancelar);
		
		masculino = new JRadioButton("M", false);
		masculino.setBounds(570, 190, 50, 28);
		
		feminino = new JRadioButton("F", false);
		feminino.setBounds(620, 190, 50, 28);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(masculino);
		grupo.add(feminino);
		
		add(masculino);
		add(feminino);
		
	}
	
	public class OuvinteInternoCadastroUsuario implements ActionListener{
		
		private TelaCadastroUsuario telaCadastroUsuario;
		
		public OuvinteInternoCadastroUsuario(TelaCadastroUsuario telaCadastroUsuario) {
			this.telaCadastroUsuario = telaCadastroUsuario;
		}
		
		Persistencia p = new Persistencia();
		Central c = p.recuperarDados("dados.xml");
		
		public void actionPerformed(ActionEvent evento) {
			
			String label = evento.getActionCommand();
			
			switch (label) {
			
				case "Cadastrar":
					
					boolean confere = true;

					String pegaNomeCompleto = nome.getText();
								
					if (pegaNomeCompleto.equals(""))
						confere = false;
					
					String pegaEmail = email.getText();
					
					if(pegaEmail.equals("")) {
						confere = false;
					}
					
					String acessaData = dataDeNascimento.getText();
					SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/mm/yyyy");
					Date data = null;
					String data2 = null;
					try {
						
						data = dataFormatada.parse(acessaData);
						data2 = dataFormatada.format(data);
						
					} catch (ParseException e1) {
						confere = false;
					}
					
					String[] quebra = data2.split("/");
					
					int dia = Integer.parseInt(quebra[0]);
					int mes = Integer.parseInt(quebra[1]);
					int ano = Integer.parseInt(quebra[2]);
					
					String data3 = null;

					if((dia >= 1 && dia <= 31) && (mes >= 1 && mes <=12) && (ano >= 1910 && ano <= 2018)) {
						data3 = data2;
					}else {
						JOptionPane.showMessageDialog(null, "Digite uma data válida", "Falha", JOptionPane.ERROR_MESSAGE);
						confere = false;break;
					}
					
					int pegaIdade = 0;
					
					if(Integer.parseInt(idade.getText()) >= 18) {
						pegaIdade = Integer.parseInt(idade.getText());
					}else {
						JOptionPane.showMessageDialog(null, "Idade deve ser maior ou igual a 18", "Falha", JOptionPane.ERROR_MESSAGE);
						confere = false;break;
					}
					
					char pegaSexo = ' ';
					
					if (masculino.isSelected()) {
						pegaSexo = 'M';
					} else {
						pegaSexo = 'F';
					}
					
					String pegaSenha = senha.getText();
					
					if (pegaSenha.equals("")) {
						confere = false;
					}
					
					String pegaEndereco = endereco.getText();
					
					if(pegaEndereco.equals("")) {
						confere = false;
					}
					
					String pegaCargo = (String) telaCadastroUsuario.getCargo().getSelectedItem();
					
					if (confere == true) {
						
						Locador locador = new Locador();
						Locatario locatario = new Locatario();
						
						switch(pegaCargo) {
						
							case("Locador"):
								locador.setNomeCompleto(pegaNomeCompleto);
								locador.setEmail(pegaEmail);
								locador.setSenha(pegaSenha);
								locador.setSexo(pegaSexo);
								locador.setDataDeNascimento(data3);
								locador.setIdade(pegaIdade);
								locador.setProfissao(pegaCargo);
								locador.setEndereco(pegaEndereco);
											
								c.adicionarLocador(locador);
								break;
								
							case("Locatario"):
								locatario.setNomeCompleto(pegaNomeCompleto);
								locatario.setEmail(pegaEmail);
								locatario.setSenha(pegaSenha);
								locatario.setSexo(pegaSexo);
								locatario.setDataDeNascimento(data3);
								locatario.setIdade(pegaIdade);
								locatario.setProfissao(pegaCargo);
								locatario.setEndereco(pegaEndereco);
										
								c.setLocatario(locatario);	
								break;
						}
						p.salvarDados(c, "dados.xml");
						JOptionPane.showMessageDialog(telaCadastroUsuario, "Usuário cadastrado com sucesso!", "Cadastrado", JOptionPane.INFORMATION_MESSAGE);
						new TelaInicial().setLocationRelativeTo(telaCadastroUsuario); 
						telaCadastroUsuario.dispose();
						
					} else {
						JOptionPane.showMessageDialog(telaCadastroUsuario, "Todos os campos devem estar preenchidos corretamente!", "Falha", JOptionPane.ERROR_MESSAGE);
					}
					break;

				case "Cancelar":
					telaCadastroUsuario.dispose();
					new TelaInicial();
					break;
					
			}	
		}
	}
	
	public JTextField getNome() {
		return nome;
	}

	public void setNome(JTextField nome) {
		this.nome = nome;
	}

	public JTextField getEmail() {
		return email;
	}

	public void setEmail(JTextField email) {
		this.email = email;
	}

	public JPasswordField getSenha() {
		return senha;
	}

	public void setSenha(JPasswordField senha) {
		this.senha = senha;
	}

	public JTextField getIdade() {
		return idade;
	}

	public void setIdade(JTextField idade) {
		this.idade = idade;
	}

	public JFormattedTextField getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(JFormattedTextField dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public JTextField getEndereco() {
		return endereco;
	}

	public void setEndereco(JTextField endereco) {
		this.endereco = endereco;
	}

	public JComboBox<String> getCargo() {
		return cargo;
	}

	public void setCargo(JComboBox<String> cargo) {
		this.cargo = cargo;
	}
	
	public JRadioButton getMasculino() {
		return masculino;
	}

	public void setMasculino(JRadioButton masculino) {
		this.masculino = masculino;
	}

	public JRadioButton getFeminino() {
		return feminino;
	}

	public void setFeminino(JRadioButton feminino) {
		this.feminino = feminino;
	}
	
}
