package entities;

public class Funcionario {
		public int id;
		public String nome;
		public Cargo cargo;
		public String mesAnoContratacao;
		
		public Funcionario(int id, String nome, Cargo cargo, String mesAnoContratacao) {
			this.id = id;
			this.nome = nome;
			this.cargo = cargo;
			this.mesAnoContratacao = mesAnoContratacao; 
		}
		
		public int getId(){
			return id;
		}
		
		public String getNomeFuncionario(){
			return nome;
		}
		

		public Cargo getCargo(){
			return cargo;
		}
		
		public String mesAnoContratacao(){
			return mesAnoContratacao;
		}
		
}
