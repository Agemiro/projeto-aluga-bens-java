package pojo;

import java.util.ArrayList;

/**
 * Classe para onde vai os locadores, locatario, bens, e o usario logado em determinada ocasião
 * 
 * @author agemiro
 *
 */

public class Central {
	
	private ArrayList<Locador> locadoresCadastrados = new ArrayList<Locador>();
	private ArrayList<Bem> bensCadastrados = new ArrayList<Bem> ();
	private Locatario locatario = new Locatario();
	private String usuarioLogado;
	
	public void adicionarLocador(Locador usuario) {
		locadoresCadastrados.add(usuario);
	}
	
	public void adicionarNovoBem(Bem bem) {
		bensCadastrados.add(bem);
	}

	public ArrayList<Locador> getLocadoresCadastrados() {
		return locadoresCadastrados;
	}

	public void setLocadoresCadastrados(ArrayList<Locador> locadoresCadastrados) {
		this.locadoresCadastrados = locadoresCadastrados;
	}

	public ArrayList<Bem> getBensCadastrados() {
		return bensCadastrados;
	}

	public void setBensCadastrados(ArrayList<Bem> bensCadastrados) {
		this.bensCadastrados = bensCadastrados;
	}

	public String getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

}
