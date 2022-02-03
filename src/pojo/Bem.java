package pojo;

public class Bem {
	
	private String nome;
	private String descricao;
	private String estado;
	private int quantidade;
	private float precoAluguel;
	private String validade;
	private String tipoAluguel;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidadeDeBensSendoCadastrados(int quantidade) {
		this.quantidade = quantidade;
	}
	public float getPrecoAluguel() {
		return precoAluguel;
	}
	public void setPrecoAluguel(float precoAluguel) {
		this.precoAluguel = precoAluguel;
	}
	public String getTipoAluguel() {
		return tipoAluguel;
	}
	public void setTipoAluguel(String tipoAluguel) {
		this.tipoAluguel = tipoAluguel;
	}
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}

}
