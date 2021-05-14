package Dao;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import model.Consulta;


public class DaoConsultaPaciente extends DaoConsulta {
	public DaoConsultaPaciente(String URL, String NOME, String SENHA, String DRIVE) {
		this.setURL(URL);
		this.setNOME(NOME);
		this.setSENHA(SENHA);
		this.setDRIVE(DRIVE);
	}

	public Vector<Consulta> Listar(int numero_paciente) {
		Vector<Consulta> resultados = new Vector<Consulta>();
		conectar();
		ResultSet rs;
		try {
			PreparedStatement stmt = getCon().prepareStatement( "select i.cd_paciente " + 
																"     , i.dt_agendado data " +	
																"     , to_char(m.dt_marcacao, 'DD/MM/YYYY') dt_agendado " + 
																"     , to_char(m.hr_inicial, 'HH24') ||':00' hora" + 
																"     , nm_prestador " + 
																"from marcacao m " + 
																"join it_marcacao i " + 
																"on i.cd_marcacao = m.cd_marcacao " + 
																"join prestador p " + 
																"on p.cd_prestador = m.cd_prestador " + 
																"where i.cd_paciente = ? " + 
																"order by 2 desc ");
			stmt.setInt(1, numero_paciente);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Consulta temp = new Consulta();
				temp.setNumero_paciente(rs.getInt("cd_paciente"));
				temp.setData_consulta(rs.getString("dt_agendado"));
				temp.setHora(rs.getString("hora"));
				temp.setProfissional(rs.getString("nm_prestador"));
	
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
