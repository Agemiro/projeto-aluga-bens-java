package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import labelsEFieldsPadrao.LabelPadrao;
import labelsEFieldsPadrao.TextFieldPadrao;
import persistencia.Persistencia;
import pojo.Bem;
import pojo.Central;
import pojo.Locador;

public class TelaListaDeTodosBens extends TelaPadrao {
	
	private DefaultTableModel modelo;
	private JTable tabela;
	private JTextField pesquisar;
	private JButton botaoFiltrar;
	
	public TelaListaDeTodosBens() {
				
		setTitle("Lista de Bens Cadastrados");
        
		barraMenu();
        tabela();
        filtrar();
        
        this.setVisible(true);
        
	}
	
	private void barraMenu() {
		
		Persistencia p = new Persistencia();
		Central c = p.recuperarDados("dados.xml");

		JMenuBar barraDeMenu = new JMenuBar();
		setJMenuBar(barraDeMenu);
		
		JMenu menuOpcoes = new JMenu("Opções");
				
		JMenuItem menuVoltar = new JMenuItem("Voltar");
		
		if(c.getUsuarioLogado().equals("")) {
			JMenuItem menuVoltarDeslogado = new JMenuItem("Voltar");
		
			menuVoltarDeslogado.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					dispose();
					new TelaInicial();
				}
			});
		
			barraDeMenu.add(menuVoltarDeslogado);
		
		}else if(c.getUsuarioLogado().equals(c.getLocatario().getEmail())){
			menuVoltar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					dispose();
					new TelaHome(c.getLocatario());
				}
			});
			
			JMenuItem menuCadastroBem = new JMenuItem("Cadastrar novo bem");
			
			menuCadastroBem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
						dispose();
						new TelaCadastroBens();
				}
				
			});
			
			JMenuItem menuEditarCadastroBem = new JMenuItem("Editar cadastro de bem");
			
			menuEditarCadastroBem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
			JMenuItem menuExcluirBem = new JMenuItem("Excluir bem");
			
			menuExcluirBem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {			
			
					int linhaSelecionada = tabela.getSelectedRow();
					
					if (linhaSelecionada == -1) {
						JOptionPane.showMessageDialog(null, "Selecione uma linha");
					} else {							
							Bem b = c.getBensCadastrados().get(linhaSelecionada);
							c.getBensCadastrados().remove(b);
							
							modelo.removeRow(linhaSelecionada);
							p.salvarDados(c, "dados.xml");
					}
				}
			});
			menuOpcoes.add(menuCadastroBem);
			menuOpcoes.add(menuEditarCadastroBem);
			menuOpcoes.add(menuExcluirBem);
			menuOpcoes.add(menuVoltar);
			
			barraDeMenu.add(menuOpcoes);
			
		}else {
			for (Locador locador : c.getLocadoresCadastrados()) {
				if(c.getUsuarioLogado().equals(locador.getEmail())) {
					menuVoltar.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							dispose();
							new TelaHome(locador);
						}
					});
					menuOpcoes.add(menuVoltar);
						
					JMenuItem reservarBem = new JMenuItem("Reservar Bem");
					
					reservarBem.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							int linhaSelecionada = tabela.getSelectedRow();
							
							if (linhaSelecionada == -1) {
								JOptionPane.showMessageDialog(null, "Selecione uma linha");
							} else {							
									Bem bem = c.getBensCadastrados().get(linhaSelecionada);
									bem.setQuantidadeDeBensSendoCadastrados(0);
									float preco = bem.getPrecoAluguel();
									String nomeBem = bem.getNome();
									String pagador = c.getUsuarioLogado();
									new TelaReservaBem(nomeBem, preco, pagador);
									p.salvarDados(c, "dados.xml");
							}
						}
					});
					menuOpcoes.add(reservarBem);
					
					JMenuItem devolverBem = new JMenuItem("Devolver Bem");
					
					devolverBem.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
					
						}
					});
					menuOpcoes.add(devolverBem);
				}
			}
			barraDeMenu.add(menuOpcoes);
		}	    
	}
	
	public void tabela() {
		
		Persistencia p = new Persistencia();
		Central c = p.recuperarDados("dados.xml");
		
		modelo = new DefaultTableModel();
		tabela = new JTable(modelo);
		
		modelo.addColumn("Nome");
		modelo.addColumn("Quantidade");
		modelo.addColumn("Data de Validade");
		modelo.addColumn("Tipo de Aluguel");
		modelo.addColumn("Preço");
		modelo.addColumn("Estado");
		modelo.addColumn("Status");
		
		for(Bem bem: c.getBensCadastrados()) {
			
			Object[] linha = new Object[modelo.getColumnCount()];
			
			linha[0] = bem.getNome();
			linha[1] = bem.getQuantidade();
			linha[2] = bem.getValidade();
			linha[3] = bem.getTipoAluguel();
			linha[4] = bem.getPrecoAluguel();
			linha[5] = bem.getEstado();

			if(bem.getQuantidade() > 0) {
				linha[6] = "Disponível";
			}else {
				linha[6] = "Indisponível";
			}
			
			modelo.addRow(linha);
			
		}
		
		JScrollPane painel = new JScrollPane(tabela);
		painel.setBounds(0, 100, 1366, 668);
		
		add(painel);
		
		p.salvarDados(c, "dados.xml");

	}
	
	private void filtrar() {

		JLabel label = new LabelPadrao("Filtro ", 905, 40, 200, 28);
		
	    pesquisar = new TextFieldPadrao(950, 40, 200, 28);
	    botaoFiltrar = new JButton("OK");
	    botaoFiltrar.setBounds(1160, 35, 80, 35);
	    botaoFiltrar.setBackground(Color.GREEN);
	    
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
	    tabela.setRowSorter(sorter);
	    
	    botaoFiltrar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String texto = pesquisar.getText();
	    		if (texto.length() == 0) {
	    			sorter.setRowFilter(null);
	    		} else {
	    			sorter.setRowFilter(RowFilter.regexFilter(texto));
	    		}
			      
	    	};
	    });
	    
	    add(botaoFiltrar);
		add(label);
		add(pesquisar);
			
	}
	
}
