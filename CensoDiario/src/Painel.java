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


import dao.DaoCensoAGHU;
import dao.DaoCensoMV;
import model.Censo;
import model.PainelCenso;




@WebServlet("/integracao_censo")
public class Painel extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String so = String.valueOf( System.getProperty("os.name") );		
		Properties config = new Properties();
		String arquivo;		
		
		if (so.equals("Linux")) {
			arquivo = "/etc/CDMA/config.ini";
		} else {
			arquivo = "c:/CDMA/config.ini";
		}
		config.load(new FileInputStream(arquivo));
		
		DaoCensoAGHU daoUsuarioAGHU = new DaoCensoAGHU(config.getProperty("aghu_url"), config.getProperty("aghu_nome"), config.getProperty("aghu_senha"), "org.postgresql.Driver");
		DaoCensoMV daoUsuarioMV = new DaoCensoMV(config.getProperty("mv_url"), config.getProperty("mv_nome"), config.getProperty("mv_senha"), "oracle.jdbc.driver.OracleDriver");		
		Vector<Censo> resultado_aghu = daoUsuarioAGHU.ListarCenso();
		Vector<Censo> resultado_mv = daoUsuarioMV.ListarCenso();
		ArrayList<PainelCenso> painelCenso = new ArrayList<PainelCenso>();
		String cabecalho_painel = "card-panel green lighten-2";

		if (resultado_aghu.size() >=  resultado_mv.size()) {
			for(int i=0; i< resultado_aghu.size(); i++){			
				PainelCenso p = new PainelCenso();
				p.setLeito_aghu(resultado_aghu.get(i).getLeito());
				p.setNome_aghu(resultado_aghu.get(i).getNome());
				p.setProntuario_aghu(resultado_aghu.get(i).getProntuario());
				p.setCss("card-panel red lighten-2");
				for (int j=0; j< resultado_mv.size(); j++) {					
					if (resultado_mv.get(j).getLeito().equals(resultado_aghu.get(i).getLeito())) {
						p.setLeito_mv(resultado_mv.get(j).getLeito());
						p.setNome_mv(resultado_mv.get(j).getNome());
						p.setProntuario_mv(resultado_mv.get(j).getCd_paciente());
						if (resultado_aghu.get(i).getCd_paciente() != resultado_mv.get(j).getCd_paciente()) {
							p.setCss("card-panel red lighten-2");
							cabecalho_painel = "card-panel red lighten-2";
						} else {
							p.setCss("card-panel");							
						}
						break;
					}
				}
				painelCenso.add(p);
		    }
		} else {
			for(int i=0; i< resultado_mv.size(); i++){			
				PainelCenso p = new PainelCenso();
				p.setLeito_mv(resultado_mv.get(i).getLeito());
				p.setNome_mv(resultado_mv.get(i).getNome());
				p.setProntuario_mv(resultado_mv.get(i).getCd_paciente());
				p.setCss("card-panel red lighten-2");
				for (int j=0; j< resultado_aghu.size(); j++) {					
					if (resultado_mv.get(i).getLeito().equals(resultado_aghu.get(j).getLeito())) {
						p.setLeito_aghu(resultado_aghu.get(j).getLeito());
						p.setNome_aghu(resultado_aghu.get(j).getNome());
						p.setProntuario_aghu(resultado_aghu.get(j).getProntuario());
						if (resultado_aghu.get(j).getCd_paciente() != resultado_mv.get(i).getCd_paciente()) {
							p.setCss("card-panel red lighten-2");
							cabecalho_painel = "card-panel red lighten-2";
						} else {
							p.setCss("card-panel");
						}
						break;
					}
				}
				painelCenso.add(p);
		    }
		}
		
		if (resultado_aghu.size() !=  resultado_mv.size()) {
			cabecalho_painel = "card-panel red lighten-2";
		}
		       
		response.setContentType("text/html");
		response.addHeader("Refresh", config.getProperty("tempo_refresh"));				
		request.setAttribute("painelCenso", painelCenso);
		request.setAttribute("cabecalho_painel", cabecalho_painel);
		request.setAttribute("rodape", config.getProperty("roda_pe"));
		request.setAttribute("so", so);
		request.getRequestDispatcher("painelcenso.jsp").forward(request, response);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	
}