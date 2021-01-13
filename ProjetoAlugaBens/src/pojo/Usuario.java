package pojo;

public class Usuario {
	
	private String nomeCompleto;
	private char sexo;
	private String dataDeNascimento;
	private int idade;
	private String endereco;
	private String profissao;
	private String email;
	private String senha;
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String dataDeNascimento2) {
		this.dataDeNascimento = dataDeNascimento2;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/*public Usuario(String nomeCompleto, char sexo, Date dataDeNascimento, int idade, String endereco, String profissao,
			String email, String senha) {
		this.nomeCompleto = nomeCompleto;
		this.sexo = sexo;
		this.dataDeNascimento = dataDeNascimento;
		this.idade = idade;
		this.endereco = endereco;
		this.profissao = profissao;
		this.email = email;
		this.senha = senha;
	}*/
	
}
