package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import banco.ConFactory;
import model.Usuario;
import java.util.Date;
import java.text.SimpleDateFormat;

public abstract class DaoUsuario {
	private String URL;  
	private String NOME;
	private String SENHA;
	private String DRIVE;
	private Connection con;  
	private Statement comando;	

	public abstract Vector<Usuario> ListarUsuarios();
	
	public void conectar() {  
		try {			
			con = ConFactory.conexao(URL, NOME, SENHA, DRIVE);  
			comando = con.createStatement();  
			log(URL, "Conectado!");  
		} catch (SQLException e) {			
			log("Erro ao conectar", e.getMessage());  
		}  
	}  

	public void fechar() {  
		try {  
			comando.close();  
			con.close();  
			log("Conexão Fechada", "");  
		} catch (SQLException e) {  
			log("Erro ao fechar conexão", e.getMessage());  
		}  
	} 
	
	public void log(String msg, String msgErro) {	
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd.MM.yyyy");		
		File log = new File ("log_" + formatador.format( data ) + ".txt");
		//
		try
		{
			if (!log.exists()) {
				log.createNewFile ();				
			}
			FileWriter fileW = new FileWriter (log, true);
			BufferedWriter buffW = new BufferedWriter (fileW);
			//buffW.newLine ();	
			buffW.write (msg + " " + msgErro + "\n");
				    
		    buffW.close ();
		    //System.out.println(msg + " " + msgErro);
		    
		} catch (IOException io){
			System.out.println(io.getMessage());			 
		}
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
