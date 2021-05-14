package dao;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import model.Kanban;
import model.KanbanObs;

public class DaoKanbanLeito extends DaoKanban {
	public DaoKanbanLeito(String URL, String NOME, String SENHA, String DRIVE) {
		this.setURL(URL);
		this.setNOME(NOME);
		this.setSENHA(SENHA);
		this.setDRIVE(DRIVE);
	}

	public Vector<Kanban> ListarLeito() {
		Vector<Kanban> resultados = new Vector<Kanban>();
		conectar();
		ResultSet rs;
		try {
			rs = getComando().executeQuery(" select leito.lto_id " + "     , pac.prontuario "
					+ "     , substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 1), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 2), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 3), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 4), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 5), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 6), 1, 1) nome "
					+ "     , date(now()) - date(dthr_inicio) nr_dias "
					+ "     , extract(day from dthr_inicio) || '/' || extract(month from dthr_inicio) as admissao "
					+ "     , case when extract(year from age(current_date, pac.dt_nascimento)) = 0 and extract(month from age(current_date, pac.dt_nascimento)) = 0 then "
					+ "		 extract(month from age(current_date, pac.dt_nascimento)) || ' d' "
					+ "	    when extract(year from age(current_date, pac.dt_nascimento)) = 0 then"
					+ "		 extract(day from age(current_date, pac.dt_nascimento)) || ' m' " + "	    else "
					+ "		 extract(year from age(current_date, pac.dt_nascimento)) || ' a' " + "       end idade "
					+ "	  , case when extract(year from age(current_date, pac.dt_nascimento)) = 0 or extract(year from age(current_date, pac.dt_nascimento)) <= 18 then "
					+ "	  	 'MENOR' " + "	   else " + "         'ADULTO' " + "       end tipo "
					+ "     , uni.descricao clinica" + "     , esp.nome_especialidade especialidade"
					+ "     , obs.descricao obs " + "  from agh.ain_leitos leito "
					+ "  left join agh.agh_atendimentos atendime " + "    on leito.lto_id = atendime.lto_lto_id "
					+ "   and atendime.ind_pac_atendimento = 'S' " + "  left join agh.aip_pacientes pac "
					+ "    on pac.prontuario = atendime.prontuario " + "  left join agh.agh_unidades_funcionais uni "
					+ "    on uni.seq = leito.unf_seq " + "  left join agh.agh_especialidades esp "
					+ "    on esp.seq = atendime.esp_seq " + "  left join agh.ain_internacoes int "
					+ "    on int.pac_codigo = pac.codigo " + "   and int.ind_paciente_internado = 'S' "
					+ "  left join agh.ain_observacoes_censo obs " + "    on obs.int_seq = int.seq "
					+ " order by leito.lto_id ");
			while (rs.next()) {
				Kanban temp = new Kanban();
				temp.setNome(rs.getString("nome"));
				temp.setLeito(rs.getString("lto_id"));
				temp.setIdade(rs.getString("idade"));
				temp.setProntuario(rs.getInt("prontuario"));
				temp.setClinica(rs.getString("clinica"));
				temp.setEspecialidade(rs.getString("especialidade"));
				temp.setAdmissao(rs.getString("admissao"));
				temp.setDias(rs.getInt("nr_dias"));
				temp.setObs(rs.getString("obs"));
				temp.setTipo(rs.getString("tipo"));
				resultados.add(temp);
			}
			fechar();
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar pessoas", e.getMessage());
			return null;
		}
	}

	public Vector<Kanban> ListarLeito(String tipo) {
		Vector<Kanban> resultados = new Vector<Kanban>();
		conectar();
		ResultSet rs;
		try {
			PreparedStatement stmt = getCon().prepareStatement(" select leito.lto_id " + "     , pac.prontuario "
					+ "     , substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 1), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 2), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 3), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 4), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 5), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 6), 1, 1) nome "
					+ "     , date(now()) - date(dthr_inicio) nr_dias "
					+ "     , extract(day from dthr_inicio) || '/' || extract(month from dthr_inicio) as admissao "
					+ "     , case when extract(year from age(current_date, pac.dt_nascimento)) = 0 and extract(month from age(current_date, pac.dt_nascimento)) = 0 then "
					+ "		 extract(month from age(current_date, pac.dt_nascimento)) || ' d' "
					+ "	    when extract(year from age(current_date, pac.dt_nascimento)) = 0 then"
					+ "		 extract(day from age(current_date, pac.dt_nascimento)) || ' m' " + "	    else "
					+ "		 extract(year from age(current_date, pac.dt_nascimento)) || ' a' " + "       end idade "
					+ "	  , case when extract(year from age(current_date, pac.dt_nascimento)) = 0 or extract(year from age(current_date, pac.dt_nascimento)) <= 18 then "
					+ "	  	 'MENOR' " + "	   else " + "         'ADULTO' " + "       end tipo "
					+ "     , uni.descricao clinica" + "     , esp.nome_especialidade especialidade"
					+ "     , obs.descricao obs " + "  from agh.ain_leitos leito "
					+ "  left join agh.agh_atendimentos atendime " + "    on leito.lto_id = atendime.lto_lto_id "
					+ "   and atendime.ind_pac_atendimento = 'S' " + "  left join agh.aip_pacientes pac "
					+ "    on pac.prontuario = atendime.prontuario " + "  left join agh.agh_unidades_funcionais uni "
					+ "    on uni.seq = leito.unf_seq " + "  left join agh.agh_especialidades esp "
					+ "    on esp.seq = atendime.esp_seq " + "  left join agh.ain_internacoes int "
					+ "    on int.pac_codigo = pac.codigo " + "   and int.ind_paciente_internado = 'S' "
					+ "  left join agh.ain_observacoes_censo obs " + "    on obs.int_seq = int.seq "
					+ " where uni.sigla = ? " + " order by leito.lto_id ");
			stmt.setString(1, tipo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Kanban temp = new Kanban();
				temp.setNome(rs.getString("nome"));
				temp.setLeito(rs.getString("lto_id"));
				temp.setIdade(rs.getString("idade"));
				temp.setProntuario(rs.getInt("prontuario"));
				temp.setEspecialidade(rs.getString("especialidade"));
				temp.setAdmissao(rs.getString("admissao"));
				temp.setDias(rs.getInt("nr_dias"));
				temp.setObs(rs.getString("obs"));
				temp.setTipo(rs.getString("tipo"));
				resultados.add(temp);
			}
			fechar();
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar pessoas", e.getMessage());
			return null;
		}
	}

	public Vector<Kanban> CarregarLeito(String leito) {
		Vector<Kanban> resultados = new Vector<Kanban>();
		conectar();
		ResultSet rs;
		try {
			PreparedStatement stmt = getCon().prepareStatement(" select leito.lto_id " + "     , pac.prontuario "
					+ "	  , pac.nome nome_completo"
					+ "     , substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 1), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 2), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 3), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 4), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 5), 1, 1) || '.' || "
					+ "       substring(SPLIT_PART(replace(replace(pac.nome, ' DA ', ' '), ' DE ', ' '), ' ', 6), 1, 1) nome "
					+ "     , date(now()) - date(dthr_inicio) nr_dias "
					+ "     , extract(day from dthr_inicio) || '/' || extract(month from dthr_inicio) as admissao "
					+ "     , case when extract(year from age(current_date, pac.dt_nascimento)) = 0 and extract(month from age(current_date, pac.dt_nascimento)) = 0 then "
					+ "		 extract(month from age(current_date, pac.dt_nascimento)) || ' d' "
					+ "	    when extract(year from age(current_date, pac.dt_nascimento)) = 0 then"
					+ "		 extract(day from age(current_date, pac.dt_nascimento)) || ' m' " + "	    else "
					+ "		 extract(year from age(current_date, pac.dt_nascimento)) || ' a' " + "       end idade "
					+ "	  , case when extract(year from age(current_date, pac.dt_nascimento)) = 0 or extract(year from age(current_date, pac.dt_nascimento)) <= 18 then "
					+ "	  	 'MENOR' " + "	   else " + "         'ADULTO' " + "       end tipo "
					+ "     , uni.descricao clinica" + "     , esp.nome_especialidade especialidade"
					+ "     , obs.descricao obs " + "  from agh.ain_leitos leito "
					+ "  left join agh.agh_atendimentos atendime " + "    on leito.lto_id = atendime.lto_lto_id "
					+ "   and atendime.ind_pac_atendimento = 'S' " + "  left join agh.aip_pacientes pac "
					+ "    on pac.prontuario = atendime.prontuario " + "  left join agh.agh_unidades_funcionais uni "
					+ "    on uni.seq = leito.unf_seq " + "  left join agh.agh_especialidades esp "
					+ "    on esp.seq = atendime.esp_seq " + "  left join agh.ain_internacoes int "
					+ "    on int.pac_codigo = pac.codigo " + "   and int.ind_paciente_internado = 'S' "
					+ "  left join agh.ain_observacoes_censo obs " + "    on obs.int_seq = int.seq "
					+ " where leito.lto_id = ? ");
			stmt.setString(1, leito);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Kanban temp = new Kanban();
				temp.setNomecompleto(rs.getString("nome_completo"));
				temp.setNome(rs.getString("nome"));
				temp.setLeito(rs.getString("lto_id"));
				temp.setIdade(rs.getString("idade"));
				temp.setProntuario(rs.getInt("prontuario"));
				temp.setEspecialidade(rs.getString("especialidade"));
				temp.setAdmissao(rs.getString("admissao"));
				temp.setDias(rs.getInt("nr_dias"));
				temp.setObs(rs.getString("obs"));
				temp.setTipo(rs.getString("tipo"));
				resultados.add(temp);
			}
			fechar();
			return resultados;
		} catch (SQLException e) {
			imprimeErro("Erro ao buscar pessoas", e.getMessage());
			return null;
		}
	}

	public void incluirObs(String leito, int prontuario, String observacao, int fugulin) {
		conectar();
		try {		

			PreparedStatement stmt = getCon().prepareStatement(
					" update kanban set dt_exclusao = now(), dt_alteracao = now() where prontuario = ? and dt_exclusao is null");
			stmt.setInt(1, prontuario);
			stmt.execute();
			System.out.println(observacao);
			stmt = getCon().prepareStatement(
					" insert into kanban(prontuario, leito, observacao, dt_inclusao, fugulin) values(?,?,?,now(),?)");
			stmt.setInt(1, prontuario);
			stmt.setString(2, leito);
			stmt.setString(3, observacao);
			stmt.setInt(4, fugulin);
			stmt.execute();

			fechar();

		} catch (SQLException e) {

		} finally {
			fechar();
		}
	}

	public Vector<KanbanObs> CarregarObs() {
		Vector<KanbanObs> resultados = new Vector<KanbanObs>();
		conectar();
		ResultSet rs;
		try {
			PreparedStatement stmt = getCon().prepareStatement(
					" select prontuario, leito, observacao, fugulin from kanban where dt_exclusao is null");
			rs = stmt.executeQuery();
			while (rs.next()) {
				KanbanObs temp = new KanbanObs();
				temp.setProntuario(rs.getInt("prontuario"));
				temp.setLeito(rs.getString("leito"));
				temp.setObservacao(rs.getString("observacao"));
				temp.setFugulin(rs.getInt("fugulin"));
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
