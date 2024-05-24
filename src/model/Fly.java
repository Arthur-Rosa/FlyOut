package model;

public class Fly {
	// ID
	// Identity
	private int id;

	private String nome;
	private String inicioFly;
	private String fimFly;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInicioFly() {
		return inicioFly;
	}

	public void setInicioFly(String inicioFly) {
		this.inicioFly = inicioFly;
	}

	public String getFimFly() {
		return fimFly;
	}

	public void setFimFly(String fimFly) {
		this.fimFly = fimFly;
	}

}
