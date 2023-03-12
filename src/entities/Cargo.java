package entities;

public class Cargo {
	public int id;
	public String nomeCargo;
	public float salario;
	public int adicionalAnoServico;
	public int porcentagemBeneficio;
	
	public Cargo(int id, String nomeCargo, float salario, int adicionalAnoServico, int porcentagemBeneficio) {
		this.id = id;
		this.nomeCargo = nomeCargo;
		this.salario = salario;
		this.adicionalAnoServico = adicionalAnoServico; 
		this.porcentagemBeneficio = porcentagemBeneficio;
	}

	public int getId() {
		return id;
		
	}
	
	public String getNomeCargo() {
		return nomeCargo;
		
	}
	
	public float getSalario() {
		return salario;
		
	}
	
	public int getAdicionalAnoServico() {
		return adicionalAnoServico;
		
	}
	
	public int getPorcentagemBeneficio() {
		return porcentagemBeneficio;
		
	}

}
