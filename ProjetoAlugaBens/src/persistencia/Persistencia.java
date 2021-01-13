package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import pojo.Central;

public class Persistencia {
	
	private XStream xstream = new XStream(new DomDriver());

	public void salvarDados(Central obj, String nomeXML) {

		try {
			String xml = xstream.toXML(obj);
			File arquivo = new File(nomeXML);
			arquivo.createNewFile();
			PrintWriter salvar = new PrintWriter(arquivo);
			salvar.print(xml);
			salvar.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Central recuperarDados(String nomeXML) {
		File arquivo = new File(nomeXML);
		try {
			if(arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return(Central) xstream.fromXML(arq);
			}			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Central();
	}	
}
