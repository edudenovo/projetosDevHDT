package dao;

import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.Vector;  
import model.Censo;

public class DaoCensoAGHU extends DaoCenso {
	public DaoCensoAGHU(String URL, String NOME, String SENHA, String DRIVE){
		this.setURL(URL);
		this.setNOME(NOME);
		this.setSENHA(SENHA);
		this.setDRIVE(DRIVE);
	}
	
	public Vector<Censo> ListarCenso() {	
		Vector<Censo> resultados = new Vector<Censo>();  
		conectar();  
		ResultSet rs;  
		try {  
			rs = getComando().executeQuery(" select leito.lto_id" + 
											"     , case when atendime.ind_pac_atendimento = 'S' then 'O' else 'V' end situacao" + 
											"     , pac.codigo" + 
											"     , pac.prontuario" + 
											"     , pac.nome" + 
											"  from agh.ain_leitos leito" + 
											"  left join agh.agh_atendimentos atendime" + 
											"    on leito.lto_id = atendime.lto_lto_id" + 
											"   and atendime.ind_pac_atendimento = 'S'" + 
											"  left join agh.aip_pacientes pac" + 
											"    on pac.prontuario = atendime.prontuario" + 
											" where ind_situacao = 'A'" + 
											" order by leito.lto_id" + 
											"     , situacao");  
			while (rs.next()) {  
				Censo temp = new Censo();          
				temp.setNome(rs.getString("nome"));  
				temp.setLeito(rs.getString("lto_id"));  
				temp.setCd_paciente(rs.getInt("codigo"));  
				temp.setProntuario(rs.getInt("prontuario"));
				temp.setSituacao(rs.getString("situacao"));  
				resultados.add(temp);  
			}  
			fechar();
			return resultados;  
		} catch (SQLException e) {  
			imprimeErro("Erro ao buscar pessoas", e.getMessage());  
			return null;  
		}  
	}
}
