package model;
import java.util.Date;

public class Usuario {
	private String nome;
	private Date dt_nascimento;
	private String CPF;
	private String email_particular;
	private String usuario;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDt_nascimento() {
		return dt_nascimento;
	}
	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEmail_particular() {
		return email_particular;
	}
	public void setEmail_particular(String email_particular) {
		this.email_particular = email_particular;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
