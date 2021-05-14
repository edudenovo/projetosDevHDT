package model;

public class KanbanObs {
	public String getLeito() {
		return leito;
	}

	public void setLeito(String leito) {
		this.leito = leito;
	}

	public int getProntuario() {
		return prontuario;
	}

	public void setProntuario(int prontuario) {
		this.prontuario = prontuario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getFugulin() {
		return fugulin;
	}

	public void setFugulin(int fugulin) {
		this.fugulin = fugulin;
	}

	private String leito;
	private int prontuario;
	private String observacao;
	private int fugulin;
}
