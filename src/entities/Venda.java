package entities;

public class Venda {
	
	public int id;
	public Funcionario funcionario;
	public String mesAnoVenda;
	public float valorVenda;
	
	public Venda(int id, Funcionario funcionario, String mesAnoVenda, float valorVenda) {
		this.id = id;
		this.funcionario = funcionario;
		this.mesAnoVenda = mesAnoVenda;
		this.valorVenda = valorVenda;
		
	}
	
	public int getIdVenda() {
		return id;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public String getMesAnoVenda() {
		return mesAnoVenda;
	}
	
	public float getValorVenda() {
		return valorVenda;
	}

}
