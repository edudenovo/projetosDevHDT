import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoKanbanLeito;
import model.Kanban;
import model.KanbanObs;

@WebServlet("/kanban_pediatria")
public class PainelPediatria extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String so = String.valueOf(System.getProperty("os.name"));
		Properties config = new Properties();
		String arquivo;

		if (so.equals("Linux")) {
			arquivo = "/etc/KANBAN/config.ini";
		} else {
			arquivo = "c:/KANBAN/config.ini";
		}
		config.load(new FileInputStream(arquivo));

		DaoKanbanLeito daoKanbanLeito = new DaoKanbanLeito(config.getProperty("aghu_url"),
				config.getProperty("aghu_nome"), config.getProperty("aghu_senha"), "org.postgresql.Driver");

		Vector<Kanban> resultado = daoKanbanLeito.ListarLeito("CLPED");
		String cabecalho_painel = "card-panel green lighten-2";

		daoKanbanLeito = new DaoKanbanLeito(config.getProperty("kanban_url"), config.getProperty("kanban_nome"),
				config.getProperty("kanban_senha"), "org.postgresql.Driver");

		Vector<KanbanObs> resultado_obs = daoKanbanLeito.CarregarObs();

		for (int i = 0; i < resultado.size(); i++) {
			for (int j = 0; j < resultado_obs.size(); j++) {
				if (resultado.get(i).getProntuario() == resultado_obs.get(j).getProntuario()) {
					resultado.get(i).setObservacao(resultado_obs.get(j).getObservacao());
					if (resultado_obs.get(j).getFugulin() > 31) {
						resultado.get(i).setFugulin("INTENSIVO");
					}
					if (resultado_obs.get(j).getFugulin() >= 27 && resultado_obs.get(j).getFugulin() <= 31) {
						resultado.get(i).setFugulin("SEMI-INTENSIVO");
					}
					if (resultado_obs.get(j).getFugulin() >= 21 && resultado_obs.get(j).getFugulin() <= 26) {
						resultado.get(i).setFugulin("ALTA DEPENDÊNCIA");
					}
					if (resultado_obs.get(j).getFugulin() >= 15 && resultado_obs.get(j).getFugulin() <= 20) {
						resultado.get(i).setFugulin("INTERMEDIÁRIO");
					}
					if (resultado_obs.get(j).getFugulin() >= 9 && resultado_obs.get(j).getFugulin() <= 14) {
						resultado.get(i).setFugulin("MÍNIMO");
					}
				}
			}
			if (resultado.get(i).getNome() != null) {
				if (resultado.get(i).getTipo().equals("ADULTO") && resultado.get(i).getDias() >= 0
						&& resultado.get(i).getDias() <= 12) {
					resultado.get(i).setKanban("card-panelKO green lighten-2");
				} else if (resultado.get(i).getTipo().equals("ADULTO")
						&& (resultado.get(i).getDias() >= 13 && resultado.get(i).getDias() <= 15)) {
					resultado.get(i).setKanban("card-panelKO yellow lighten-2");
				} else if (resultado.get(i).getTipo().equals("ADULTO") && resultado.get(i).getDias() >= 16) {
					resultado.get(i).setKanban("card-panelKO red lighten-2");
				} else if (resultado.get(i).getTipo().equals("MENOR") && resultado.get(i).getDias() >= 0
						&& resultado.get(i).getDias() <= 9) {
					resultado.get(i).setKanban("card-panelKO green lighten-2");
				} else if (resultado.get(i).getTipo().equals("MENOR")
						&& (resultado.get(i).getDias() >= 10 && resultado.get(i).getDias() <= 12)) {
					resultado.get(i).setKanban("card-panelKO yellow lighten-2");
				} else if (resultado.get(i).getTipo().equals("MENOR") && resultado.get(i).getDias() >= 13) {
					resultado.get(i).setKanban("card-panelKO red lighten-2");
				}
			}
		}

		response.setContentType("text/html");
		response.addHeader("Refresh", config.getProperty("tempo_refresh"));
		request.setAttribute("resultado", resultado);
		request.setAttribute("cabecalho_painel", cabecalho_painel);
		request.setAttribute("rodape", config.getProperty("roda_pe"));
		request.setAttribute("so", so);
		request.getRequestDispatcher("painel.jsp").forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

}
