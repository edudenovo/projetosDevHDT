package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Censo;

public class DaoCensoMV extends DaoCenso {
	public DaoCensoMV(String URL, String NOME, String SENHA, String DRIVE){
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
			rs = getComando().executeQuery(" select distinct leito.ds_resumo" + 
											"     , leito.tp_ocupacao" + 
											"     , paciente.cd_paciente" + 
											"     , paciente.nm_paciente" + 
											"  from leito" + 
											"  left join mov_int" + 
											"    on leito.cd_leito = mov_int.cd_leito" + 
											"   and mov_int.hr_lib_mov is null" + 
											"   and mov_int.dt_lib_mov is null" + 
											"  left join atendime" + 
											"    on atendime.cd_atendimento = mov_int.cd_atendimento" + 
											"	and atendime.dt_alta is null" +
											"  left join paciente" + 
											"    on paciente.cd_paciente = atendime.cd_paciente" + 
											" where leito.tp_situacao = 'A'" + 
											"   and leito.cd_leito not in (234,235,236,237,238,239)" + 
											" order by leito.ds_resumo" + 
											"     , leito.tp_ocupacao ");  
			while (rs.next()) {  
				Censo temp = new Censo();          
				temp.setNome(rs.getString("nm_paciente"));  
				temp.setLeito(rs.getString("ds_resumo"));  
				temp.setCd_paciente(rs.getInt("cd_paciente"));				
				temp.setSituacao(rs.getString("tp_ocupacao"));  
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
