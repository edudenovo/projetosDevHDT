package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import banco.ConFactory;
import model.Agenda;


public class DaoAgenda {
	private String URL;  
	private String NOME;
	private String SENHA;
	private String DRIVE;
	private Connection con;  
	private Statement comando;
	
	public DaoAgenda(String URL, String NOME, String SENHA, String DRIVE){
		this.setURL(URL);
		this.setNOME(NOME);
		this.setSENHA(SENHA);
		this.setDRIVE(DRIVE);
	}

	public Vector<Agenda> ListarAgenda() {	
		Vector<Agenda> resultados = new Vector<Agenda>();  
		conectar();  
		ResultSet rs;  
		try {  
			rs = getComando().executeQuery(" select agenda_id" + 
											"     , telefone" + 
											"     , nome" +
											"  from agenda");  
			while (rs.next()) {  
				Agenda temp = new Agenda();          
				temp.setAgenda_id(rs.getInt("agenda_id")); 
				temp.setTelefone(rs.getString("telefone"));  
				temp.setNome(rs.getString("nome"));				  
				resultados.add(temp);  
			}  
			fechar();
			return resultados;  
		} catch (SQLException e) {  
			imprimeErro("Erro ao buscar pessoas", e.getMessage());  
			return null;  
		}  
	};
	
	public Vector<Agenda> ListarAgenda(String telefone, String nome) {	
		Vector<Agenda> resultados = new Vector<Agenda>();  
		conectar();  
		ResultSet rs;  
		try {  
			PreparedStatement stmt = getCon().prepareStatement(" select agenda_id" + 
															   "     , telefone" + 
															   "     , nome" +
															   "  from agenda" +
															   " where ((telefone like ? and ? <> '') or (? = '' and 1=1))" +
															   "   and ((UPPER(TRANSLATE(nome,'¡«…Õ”⁄¿»Ã“Ÿ¬ Œ‘€√’À‹·ÁÈÌÛ˙‡ËÏÚ˘‚ÍÓÙ˚„ıÎ¸', 'ACEIOUAEIOUAEIOUAOEUaceiouaeiouaeiouaoeu')) like ? and ? <> '')or (? = '' and 1=1))" +
															   " order by 2, 3");  

			stmt.setString(1, '%' + telefone + '%');
			stmt.setString(2, telefone);
			stmt.setString(3, telefone);
			stmt.setString(4, '%' + nome + '%');
			stmt.setString(5, nome);
			stmt.setString(6, nome);
			rs = stmt.executeQuery();
			while (rs.next()) {  
				Agenda temp = new Agenda();          
				temp.setAgenda_id(rs.getInt("agenda_id")); 
				temp.setTelefone(rs.getString("telefone"));  
				temp.setNome(rs.getString("nome"));				  
				resultados.add(temp);  
			}  
			fechar();
			return resultados;  
		} catch (SQLException e) {  
			imprimeErro("Erro ao buscar pessoas", e.getMessage());  
			return null;  
		}  
	};
	
	public int insereAgenda(String telefone, String nome){  
		conectar();  
		try { 
			ResultSet rs;  
			int resultado;			
			PreparedStatement stmt = getCon().prepareStatement(" select telefone from telefones where telefone = ?");  
			stmt.setString(1, telefone);			
			rs = stmt.executeQuery();
			if(!rs.next()) {  
				stmt = getCon().prepareStatement("insert into telefones(telefone) values (?)");
				stmt.setString(1, telefone);
				stmt.execute(); 
			} 
			
			stmt = getCon().prepareStatement(" select nome from nomes where nome = ?");  
			stmt.setString(1, nome);			
			rs = stmt.executeQuery();
			if(!rs.next()) { 
				stmt = getCon().prepareStatement("insert into nomes(nome) values (?)");
				stmt.setString(1, nome);
				stmt.execute();
			}
			
			stmt = getCon().prepareStatement(" select agenda_id from agenda where nome = ? and telefone = ?");  
			stmt.setString(1, nome);
			stmt.setString(2, telefone);
			rs = stmt.executeQuery();
			if(!rs.next()) { 
				stmt = getCon().prepareStatement("insert into agenda(telefone, nome) values (?, ?)");
				stmt.setString(1, telefone);
				stmt.setString(2, nome);
				stmt.execute();
			} else {
				fechar();
				return 0;
			}
			rs = getComando().executeQuery("select max(agenda_id) qtd from agenda");
			resultado = 0;
			if(rs.next()) { 
				resultado = rs.getInt("qtd");
			}
			fechar();
			return resultado;
			 
		} catch (SQLException e) {  
			return 0;
		} finally {  
			fechar();  
		}  
	} 
	
	public void delete(int agenda_id){  
		conectar();  
		try {					
			PreparedStatement stmt = getCon().prepareStatement(" delete from agenda where agenda_id = ?");  
			stmt.setInt(1, agenda_id);			
			stmt.execute();			
			 
		} catch (SQLException e) {  
			
		} finally {  
			fechar();  
		}  
	}
	
	public int update(int agenda_id, String nome, String telefone){  
		conectar();  
		try {	
			ResultSet rs;						
			PreparedStatement stmt = getCon().prepareStatement(" select telefone from telefones where telefone = ?");  
			stmt.setString(1, telefone);			
			rs = stmt.executeQuery();
			if(!rs.next()) {  
				stmt = getCon().prepareStatement("insert into telefones(telefone) values (?)");
				stmt.setString(1, telefone);
				stmt.execute(); 
			} 
			
			stmt = getCon().prepareStatement(" select nome from nomes where nome = ?");  
			stmt.setString(1, nome);			
			rs = stmt.executeQuery();
			if(!rs.next()) { 
				stmt = getCon().prepareStatement("insert into nomes(nome) values (?)");
				stmt.setString(1, nome);
				stmt.execute();
			}
			
			stmt = getCon().prepareStatement(" select agenda_id from agenda where nome = ? and telefone = ?");  
			stmt.setString(1, nome);
			stmt.setString(2, telefone);
			rs = stmt.executeQuery();
			if(!rs.next()) { 
				stmt = getCon().prepareStatement(" update agenda set nome = ?, telefone = ? where agenda_id = ?");  
				stmt.setString(1, nome);
				stmt.setString(2, telefone);
				stmt.setInt(3, agenda_id);
				stmt.execute();	
				fechar();
				return 1;
			} else {
				fechar();
				return 0;
			}		
			 
		} catch (SQLException e) {  
			return 0;
		} finally {  
			fechar();  
		}  
	}
	
	public void conectar() {  
		try {			
			con = ConFactory.conexao(URL, NOME, SENHA, DRIVE);  
			comando = con.createStatement();			
			System.out.println("Conectado!");  
		} catch (SQLException e) {  
			//JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem", 1);
			imprimeErro("Erro ao conectar", e.getMessage());  
		}  
	}  

	public void fechar() {  
		try {  
			comando.close();  
			con.close();  
			System.out.println("Conex„o Fechada");  
		} catch (SQLException e) {  
			imprimeErro("Erro ao fechar conex„o", e.getMessage());  
		}  
	} 
	
	public void imprimeErro(String msg, String msgErro) {  
		JOptionPane.showMessageDialog(null, msg, "Erro crÌtico", 0);  
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