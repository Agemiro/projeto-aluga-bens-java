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
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import icones.Icones;
import labelsEFieldsPadrao.LabelPadrao;
import labelsEFieldsPadrao.TextFieldPadrao;
import persistencia.Persistencia;
import pojo.Bem;
import pojo.Central;
import pojo.Locatario;

public class TelaCadastroBens extends TelaPadrao{

	private JTextField nome;
	private JTextField quantidade;
	private JFormattedTextField validade;
	private JTextArea descricao;
	private JComboBox<String> tipoAluguel;
	private JTextField preco;
	private JRadioButton novo, usado;

	public TelaCadastroBens() {
		
		setTitle("Cadastro de Bens");
		
		imagens();
		titulos();
		campo();
		comboBox();
		radioButtons();
		botoes();
		
		setVisible(true);
		
	}
	
	public void imagens() {
		
		JLabel imagem = new JLabel(Icones.IMAGEM);
		imagem.setBounds(780, 170, 377, 342);
		
		add(imagem);

	}
	
	public void titulos() {
		
		JLabel nome = new LabelPadrao("Nome", 200, 150, 50, 28);
		JLabel quantidade = new LabelPadrao("Quantidade", 200, 190, 100, 28);
		JLabel estado = new LabelPadrao("Estado", 200, 230, 70, 28);
		JLabel validade = new LabelPadrao("Validade", 200, 270, 70, 28);
		JLabel descricao = new LabelPadrao("Descrição", 200, 310, 70, 28);
		JLabel tipoAluguel = new LabelPadrao("Tipo de Aluguel", 200, 390, 120, 28);
		JLabel valorAluguel = new LabelPadrao("Valor do Aluguel", 200, 435, 120, 28);
		
		add(nome);
		add(quantidade);
		add(estado);
		add(validade);
		add(descricao);
		add(tipoAluguel);
		add(valorAluguel);
	}
	
	public void campo() {
		
		nome = new TextFieldPadrao(250, 150, 220, 28);
		quantidade = new TextFieldPadrao(290, 190, 50, 28);
		
		descricao = new JTextArea();
		descricao.setLineWrap(true);
		descricao.setWrapStyleWord(true);
		descricao.setBounds(280, 310, 300, 70);
		
		nome.setToolTipText("Digite aqui o nome do bem");
		quantidade.setToolTipText("Digite aqui a quantidade");
		
		try {
			
			MaskFormatter mascaraDaData = new MaskFormatter("##/##/####");
		
			mascaraDaData.setValidCharacters("0123456789");
	
			validade = new JFormattedTextField(mascaraDaData);
			validade.setFont(new Font("Arial", Font.ITALIC, 15));
			validade.setBounds(270, 270, 80, 28);
			validade.setToolTipText("Digite aqui a data de validade");
			
		}catch(ParseException e) {}
		
		preco = new TextFieldPadrao(325, 435, 70, 28);
		
		add(nome);
		add(quantidade);
		add(descricao);
		add(validade);
		add(preco);
	}
	
	public void radioButtons() {
		novo = new JRadioButton("Novo", false);
		usado = new JRadioButton("Usado", false);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(novo);
		grupo.add(usado);
		
		novo.setBounds(260, 230, 60, 28);
		usado.setBounds(320, 230, 70, 28);
		
		add(novo);
		add(usado);
	}
	
	private void comboBox() {

		String[] tiposAlugueis = {"Diario", "Mensal"};
	
		tipoAluguel = new JComboBox<String>(tiposAlugueis);
		tipoAluguel.setBounds(320, 390, 70, 28);

		add(tipoAluguel);
	}
	
	public void botoes() {
		
		JButton botaoCadastro = new JButton("Cadastrar", Icones.OK);
		botaoCadastro.setBackground(Color.WHITE);
		botaoCadastro.setBounds(160, 550, 130, 35);
		botaoCadastro.setToolTipText("Clique para cadastrar o bem");
		
		
		JButton botaoReiniciar = new JButton("Reiniciar", Icones.REINICIAR);
		botaoReiniciar.setBackground(Color.WHITE);
		botaoReiniciar.setBounds(320, 550, 130, 35);
		botaoReiniciar.setToolTipText("Clique pra reiniciar o cadastro do bem");
		
		JButton botaoVoltar = new JButton("Voltar", Icones.VOLTAR);
		botaoVoltar.setBackground(Color.WHITE);
		botaoVoltar.setBounds(480, 550, 130, 35);
		botaoVoltar.setToolTipText("Clique pra voltar para o Home");
		
		OuvinteCadastroBens ouvinteBem = new OuvinteCadastroBens(this);

		botaoCadastro.addActionListener(ouvinteBem);
		botaoReiniciar.addActionListener(ouvinteBem);
		botaoVoltar.addActionListener(ouvinteBem);
		
		add(botaoCadastro);
		add(botaoReiniciar);
		add(botaoVoltar);

	}
	public class OuvinteCadastroBens implements ActionListener{

		private TelaCadastroBens telaCadastroBens;
		
		public OuvinteCadastroBens(TelaCadastroBens telaCadastroBens) {
			this.telaCadastroBens = telaCadastroBens;
		}
		
		Persistencia p = new Persistencia();
		Central c = p.recuperarDados("dados.xml");
		
		public void actionPerformed(ActionEvent e) {
			
			String label = e.getActionCommand();
			
			switch (label) {
			
				case "Cadastrar":
					String acessaNome = nome.getText();
				
					boolean confere = true;
				
					if (acessaNome.equals(""))
						confere = false;
				
					String acessaDescricao = descricao.getText();
				
					if (acessaDescricao.equals(""))
						confere = false;
				
					String acessaEstado = "";
					
					if (novo.isSelected()) {
						acessaEstado = "Novo";
					} else {
						acessaEstado = "Usado";			
					}
					
					int acessaQuantidade = Integer.parseInt(quantidade.getText());
					
					if(acessaQuantidade <= 0) {
						JOptionPane.showMessageDialog(null, "Deve conter no minímo um!", "Falha", JOptionPane.ERROR_MESSAGE);
						confere = false;break;
					}
					
					float acessaPreco = Integer.parseInt(preco.getText());
					
					if(acessaPreco < 0) {
						confere = false;
					}
					
					String acessaTipoAluguel = (String) telaCadastroBens.getTipoAluguel().getSelectedItem();
					
					if(acessaTipoAluguel == null) {
						confere = false;
					}
					
					String acessaData = validade.getText();
					SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/mm/yyyy");
					Date acessaValidade = null;
					String data2 = null;
					try {
						acessaValidade = dataFormatada.parse(acessaData);
						data2 = dataFormatada.format(acessaValidade);
					} catch (ParseException e1) {
						confere = false;
					}
					
					String[] quebra = data2.split("/");
					
					int dia = Integer.parseInt(quebra[0]);
					int mes = Integer.parseInt(quebra[1]);
					int ano = Integer.parseInt(quebra[2]);
					
					String data3 = null;

					if((dia >= 1 && dia <= 31) && (mes >= 1 && mes <=12) && (ano >= 2018 && ano <= 2050)) {
						data3 = data2;
					}else {
						JOptionPane.showMessageDialog(null, "Digite uma data válida", "Falha", JOptionPane.ERROR_MESSAGE);
						confere = false;break;
					}
					
					if (confere == true) {
						
						Bem bem = new Bem();
												
						bem.setNome(acessaNome);
						bem.setDescricao(acessaDescricao);
						bem.setEstado(acessaEstado);
						bem.setQuantidadeDeBensSendoCadastrados(acessaQuantidade);
						bem.setValidade(data3);
						bem.setTipoAluguel(acessaTipoAluguel);
						bem.setPrecoAluguel(acessaPreco);
											
						c.adicionarNovoBem(bem);
						
						p.salvarDados(c, "dados.xml");
						JOptionPane.showMessageDialog(null, "Bem cadastrado com sucesso!", "Cadastrado", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos corretamente!", "Falha", JOptionPane.ERROR_MESSAGE);
					}

				case "Reiniciar":
					telaCadastroBens.dispose();
					new TelaCadastroBens();
					break;
					
				case "Voltar":
					telaCadastroBens.dispose();
					new TelaHome(c.getLocatario());
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

	public JTextField getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(JTextField quantidade) {
		this.quantidade = quantidade;
	}

	public JFormattedTextField getValidade() {
		return validade;
	}

	public void setValidade(JFormattedTextField validade) {
		this.validade = validade;
	}

	public JTextArea getDescricao() {
		return descricao;
	}

	public void setDescricao(JTextArea descricao) {
		this.descricao = descricao;
	}

	public JComboBox<String> getTipoAluguel() {
		return tipoAluguel;
	}

	public void setTipoAluguel(JComboBox<String> tipoAluguel) {
		this.tipoAluguel = tipoAluguel;
	}

	public JTextField getPreco() {
		return preco;
	}

	public void setPreco(JTextField preco) {
		this.preco = preco;
	}

	public JRadioButton getNovo() {
		return novo;
	}

	public void setNovo(JRadioButton novo) {
		this.novo = novo;
	}

	public JRadioButton getUsado() {
		return usado;
	}

	public void setUsado(JRadioButton usado) {
		this.usado = usado;
	}
	
}
