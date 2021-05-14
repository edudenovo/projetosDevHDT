package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import banco.ConFactory;
import model.Consulta;

public abstract class DaoConsulta {
	private String URL;
	private String NOME;
	private String SENHA;
	private String DRIVE;
	private Connection con;
	private Statement comando;

	public abstract Vector<Consulta> Listar(int numero_paciente);

	public void conectar() {
		try {
			con = ConFactory.conexao(URL, NOME, SENHA, DRIVE);
			comando = con.createStatement();
			System.out.println("Conectado!");
		} catch (SQLException e) {
			// JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem", 1);
			imprimeErro("Erro ao conectar", e.getMessage());
		}
	}

	public void fechar() {
		try {
			comando.close();
			con.close();
			System.out.println("Conexão Fechada");
		} catch (SQLException e) {
			imprimeErro("Erro ao fechar conexão", e.getMessage());
		}
	}

	public void imprimeErro(String msg, String msgErro) {
		JOptionPane.showMessageDialog(null, msg, "Erro crítico", 0);
		System.err.println(msg);
		System.out.println(msgErro);
		System.exit(0);
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getNOME() {
		return NOME;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public String getSENHA() {
		return SENHA;
	}

	public void setSENHA(String sENHA) {
		SENHA = sENHA;
	}

	public String getDRIVE() {
		return DRIVE;
	}

	public void setDRIVE(String dRIVE) {
		DRIVE = dRIVE;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getComando() {
		return comando;
	}

	public void setComando(Statement comando) {
		this.comando = comando;
	}
}
