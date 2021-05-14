package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.Vector;
import model.Usuario; 

public class DaoUsuarioMV extends DaoUsuario {
	
	public DaoUsuarioMV(String URL, String NOME, String SENHA, String DRIVE){
		this.setURL(URL);
		this.setNOME(NOME);
		this.setSENHA(SENHA);
		this.setDRIVE(DRIVE);
	}

	public Vector<Usuario> ListarUsuarios() {  
		try {			
			conectar();			
			Vector<Usuario> resultados = new Vector<Usuario>();  
			ResultSet rs;
			
			rs = getComando().executeQuery("select nm_usuario, cd_usuario, cpf, dt_nascimento, ds_email from dbasgu.usuarios");  
			
			while (rs.next()) {  
				Usuario temp = new Usuario();
				temp.setNome(rs.getString("nm_usuario"));  
				temp.setDt_nascimento(rs.getDate("dt_nascimento"));  
				temp.setCPF(rs.getString("cpf"));  
				temp.setEmail_particular(rs.getString("ds_email"));
				temp.setUsuario(rs.getString("cd_usuario"));  
				resultados.add(temp);  
			}  
			fechar();
			return resultados;  
		} catch (SQLException e) {  
			log("Erro ao buscar pessoas", e.getMessage());
			fechar();
			return null;  
		}  
	} 
		  
	public void insereUsuario(Usuario usuario){  
		conectar();  
		try {  
			PreparedStatement stmt = getCon().prepareStatement("INSERT INTO DBASGU.USUARIOS(SN_CERTIFICADO_DIGITAL" + 
															   "     , CD_USUARIO " + 
													           "     , NM_USUARIO " + 
													           "     , TP_PRIVILEGIO  " + 
													           "     , CD_SENHA " + 
													           "     , TP_STATUS " + 
													           "     , SN_ATIVO " + 
													           "     , SN_SENHA_PLOGIN " + 
													           "     , SN_ABRE_FECHA_CONTA " + 
													           "     , CPF  " + 
													           "     , SN_RECEBE_MSG_EXPIRA_CHAVE  " + 
													           "     , SN_ALTERA_AUDITORIA_IN_LOCO " + 
													           "     , SN_CADASTRA_PACIENTE " + 
													           "     , SN_ALTERA_CADASTRO_PACIENTE " + 
													           "     , DT_NASCIMENTO " + 
													           "     , DS_EMAIL)" +
													           "VALUES ('N'" + 
													           "     , ? " + 
													           "     , ?" + 
													           "     , 'U'" + 
													           "     , 'HI\\J]Q^cVle\\bcvMWWZbxz|~'" + 
													           "     , 'N'" + 
													           "     , 'N'" + 
													           "     , 'S'" + 
													           "     , 'N'" + 
													           "     , ?" + 
													           "     , 'N'" + 
													           "     , 'N'" + 
													           "     , 'N'" + 
													           "     , 'N'" + 
													           "     , ?" + 
													           "     , ?)" );
			
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getCPF());
			stmt.setDate(4, (Date) usuario.getDt_nascimento());
			stmt.setString(5, usuario.getEmail_particular());
			stmt.execute();			    	
			  
		} catch (SQLException e) {  
			log("Erro ao inserir Usuário", e.getMessage());  
		} finally {  
			fechar();  
		}  
	} 
		   
	public void associaUsuarioPapel(Usuario usuario){  
		conectar();  
		try {  
			PreparedStatement stmt = getCon().prepareStatement("INSERT INTO DBASGU.PAPEL_USUARIOS (CD_USUARIO, CD_PAPEL, TP_PAPEL) VALUES(?, 56, 'P')" );
			stmt.setString(1, usuario.getUsuario());
			stmt.execute();		    	
			  
		} catch (SQLException e) {  
			log("Erro ao associar Papel de Usuário", e.getMessage());  
		} finally {  
			fechar();  
		}  
	} 
		   
	public void associaUsuarioEmpresa(Usuario usuario){  
		conectar();  
		try {  
			PreparedStatement stmt = getCon().prepareStatement("INSERT INTO USUARIO_MULTI_EMPRESA(CD_MULTI_EMPRESA, CD_ID_USUARIO) VALUES(1, ?)" );
			stmt.setString(1, usuario.getUsuario());
			stmt.execute();
			  
		} catch (SQLException e) {  
			log("Erro ao associar Usuário a Empresa", e.getMessage());  
		} finally {  
			fechar();  
		}  
	} 
		   
	public void associaUsuarioEstoque(Usuario usuario){  
		conectar();  
		try {  
			PreparedStatement stmt = getCon().prepareStatement("INSERT INTO USU_ESTOQUE (CD_ESTOQUE" + 
															   "     , CD_ID_DO_USUARIO" + 
													    	   "     , SN_AUTORIZA_EXCL_SOLICITACAO" + 
													    	   "     , SN_AUTORIZA_ALTE_SOLICITACAO" + 
													    	   "     , TP_USUARIO" + 
													    	   "     , SN_PERMITE_ALT_ORD_COMPRAS" + 
													    	   "     , SN_ALT_VL_UNIT_OC" + 
													    	   "     , VL_PERC_VAR_VL_UNIT" + 
													    	   "     , SN_TRANS_QUANT_COTA" + 
													    	   "     , SN_AUTORIZA_ALTE_MOVIMENTACAO" + 
													    	   "     , SN_AUTORIZA_EXCL_MOVIMENTACAO)" + 
													    	   "VALUES (1" + 
													    	   "     , ?" + 
													    	   "     , 'N'" + 
													    	   "     , 'N'" + 
													    	   "     , 'S'" + 
													    	   "     , 'N'" + 
													    	   "     , 'N'" + 
													    	   "     , 0" + 
													    	   "     , 'N'" + 
													    	   "     , 'N'" + 
													    	   "     , 'N')" );
			stmt.setString(1, usuario.getUsuario());
			stmt.execute();			    	
			 
		} catch (SQLException e) {  
			log("Erro ao associar Usuário ao Estoque", e.getMessage());  
		} finally {  
			fechar();  
		}  
	}
}


