package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import icones.Icones;
import labelsEFieldsPadrao.LabelPadrao;
import labelsEFieldsPadrao.TextFieldPadrao;

public class TelaReservaBem extends JFrame{
	
	private JTextField campoQuantidade;
	private JTextField campoTempo;
	private JButton gerarPDF;
	private String nomeBem;
	private float preco;
	private String pagador;
	
	public TelaReservaBem(String nomeBem, float preco, String pagador) {
		
		this.nomeBem = nomeBem;
		this.preco = preco;
		this.pagador = pagador;
		setTitle("Reserva de bem");
		setSize(500,250);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource("icon_image.png")).getImage());
        
		labels();
		botaoGerarBoleto();
		
		this.setVisible(true);
	}
	
	private void labels() {
		
		JLabel quantidade = new LabelPadrao("Quantidade: ", 170, 50, 100, 28);
		JLabel tempo = new LabelPadrao("Tempo (dias): ", 170, 90, 100, 28);
		
		campoQuantidade = new TextFieldPadrao(260, 50, 50, 28);
		campoTempo = new TextFieldPadrao(270, 90, 50, 28);
		
		add(quantidade);
		add(campoQuantidade);
		add(tempo);
		add(campoTempo);
	}
	
	private void botaoGerarBoleto() {
		
		gerarPDF = new JButton("Gerar Boleto", Icones.PDF_IMAGE);
		gerarPDF.setBounds(180, 150, 150, 35);
		gerarPDF.setBackground(Color.WHITE);
		gerarPDF.setToolTipText("Clique para gerar o boleto");
		
		OuvinteBotaoGerarPDF ouvinte = new OuvinteBotaoGerarPDF();

		gerarPDF.addActionListener(ouvinte);	
		
		add(gerarPDF);
		
	}
	
	private class OuvinteBotaoGerarPDF implements ActionListener{

		public void actionPerformed(ActionEvent evento) {

			
			 float acessaQuantidade = Integer.parseInt(campoQuantidade.getText());
			 String precoAPagar = Float.toString(acessaQuantidade * preco);
			 			 			
		     Document doc = new Document(PageSize.A4);
		     
	            try {
	            	OutputStream os = new FileOutputStream("boleto.pdf");
	                PdfWriter.getInstance(doc, os);
	  
	                doc.open();
	  
	                Paragraph p1 = new Paragraph("Boleto Aluga Bens");
	                p1.setAlignment(Element.ALIGN_CENTER);
	                doc.add(p1);
	                
	                PdfPTable tabela1 = new PdfPTable(2);
	                
	                PdfPCell cabecalho1 = new PdfPCell(new Paragraph("ALUGA BENS | 000-0 |  000000.000000 000000.000000 000000.000000 0 00000000000000"));
	                
	                cabecalho1.setColspan(2);
	                cabecalho1.setBackgroundColor(BaseColor.RED);
	                
	                tabela1.addCell(cabecalho1);
	                tabela1.addCell("Local de pagamento: PAGÁVEL EM NENHUM BANCO");
	                tabela1.addCell("Vencimento");
	                tabela1.addCell("Beneficiário: ALUGA BENS 0 - CPNJ: 00.000.000/0000-00");
	                tabela1.addCell("Agência/Cód.Cedente: 0000/000000");
	                tabela1.addCell("Número do documento: 000000000");
	                tabela1.addCell("(=) Valor do documento R$ " + precoAPagar);
	                tabela1.addCell("Objeto: " + nomeBem);
	                tabela1.addCell("Pagador: " + pagador);
	                
	                doc.add(tabela1);
	                
	                Image imagem = Image.getInstance("codigo_barras.png");
	                imagem.setAlignment(Element.ALIGN_CENTER);
	                doc.add(imagem);
	                
	                doc.close();
	  
	            } catch(FileNotFoundException e) {
	            		e.printStackTrace();
	            } catch(DocumentException e) {
	            	e.printStackTrace();
	            } catch(IOException e) {
	            	e.printStackTrace();
	            }
		}
		
	}

}
