package model;

public class Consulta {
		private int numero_paciente;
		private String data_consulta;
		private String hora;
		private String Profissional;
		
		public int getNumero_paciente() {
			return numero_paciente;
		}
		public void setNumero_paciente(int numero_paciente) {
			this.numero_paciente = numero_paciente;
		}
		public String getData_consulta() {
			return data_consulta;
		}
		public void setData_consulta(String data_consulta) {
			this.data_consulta = data_consulta;
		}
		public String getHora() {
			return hora;
		}
		public void setHora(String hora) {
			this.hora = hora;
		}
		public String getProfissional() {
			return Profissional;
		}
		public void setProfissional(String profissional) {
			Profissional = profissional;
		}
		
}
