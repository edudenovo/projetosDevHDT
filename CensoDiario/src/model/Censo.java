package model;

public class Censo {
	private String leito;
	private int prontuario;
	private int cd_paciente;
	private String situacao;
	private String nome;
	
	public String getLeito() {
		return leito;
	}
	public void setLeito(String leito) {
		this.leito = leito;
	}
	public int getProntuario() {
		return prontuario;
	}
	public void setProntuario(int prontuario) {
		this.prontuario = prontuario;
	}
	public int getCd_paciente() {
		return cd_paciente;
	}
	public void setCd_paciente(int cd_paciente) {
		this.cd_paciente = cd_paciente;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
