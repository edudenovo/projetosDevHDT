package dao;

import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.Vector;  
import model.Usuario;


public class DaoUsuarioAGHU extends DaoUsuario {

	public DaoUsuarioAGHU(String URL, String NOME, String SENHA, String DRIVE){
		this.setURL(URL);
		this.setNOME(NOME);
		this.setSENHA(SENHA);
		this.setDRIVE(DRIVE);
	}
	
	public Vector<Usuario> ListarUsuarios() {	
		Vector<Usuario> resultados = new Vector<Usuario>();  
		conectar();  
		ResultSet rs;  
		try {  
			rs = getComando().executeQuery("select substring(a.nome, 0, 40) nome"
	         						+ "	 		 , a.dt_nascimento"
	         						+ "  		 , lpad(cast(a.cpf as varchar), 11, '0') as cpf"
	         						+ "  		 , email_particular"
	         						+ "  		 , b.usuario "
	         						+ "	 	  from agh.rap_pessoas_fisicas a "
	         						+ "	  	  join agh.rap_servidores b "
	         						+ "	   		on a.codigo = b.pes_codigo "
	         						+ "		 where b.usuario not in ('AGHU', 'AGHU.HDT')");  
			while (rs.next()) {  
				Usuario temp = new Usuario();          
				temp.setNome(rs.getString("nome"));  
				temp.setDt_nascimento(rs.getDate("dt_nascimento"));  
				temp.setCPF(rs.getString("CPF"));  
				temp.setEmail_particular(rs.getString("email_particular"));
				temp.setUsuario(rs.getString("usuario"));  
				resultados.add(temp);  
			}  
			fechar();			 
			return resultados;  
		} catch (SQLException e) {  
			log("Erro ao buscar pessoas", e.getMessage());  
			return null;  
		}  
	}
}
