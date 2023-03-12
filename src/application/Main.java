package application;
import entities.cargoDAO;
import entities.FuncionarioDAO;
import entities.VendaDAO;

public class Main {

	public static void main(String[] args) {
		FuncionarioDAO F = new FuncionarioDAO();
		VendaDAO V = new VendaDAO();
		
		System.out.print("\n=-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-=> RELATÒRIO <=-=-=-=-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-==-=-=\n\n");	
		
		//Um método que receba uma lista de funcionários, mês e ano e retorne o valor total pago (salário e benefício) a esses funcionários no mês.
		F.calcularSalarioEBeneficioPago(F.getListaFuncionario(),12, 2023);
		System.out.print("\n----");
		
		//Um método que receba uma lista de funcionários, mês e ano e retorne o total pago somente em salários no mês.
		System.out.print("\n");	
		F.calcularSalario(F.getListaFuncionario(),12, 2023);
		System.out.print("\n----");	
		
		//Um método que receba uma lista somente com os funcionários que recebem benefícios, mês e ano e retorne o total pago em benefícios no mês.
		System.out.print("\n");	
		F.calcularBeneficioPago(F.getListaFuncionario(),12, 2023);
		System.out.print("\n----");
		
		//Um método que receba uma lista de funcionários, mês e ano e retorne o que recebeu o valor mais alto no mês.
		F.calcularMaiorSalarioEFuncionario(F.getListaFuncionario(),12, 2023);
		System.out.print("\n----");
		
		//Um método que receba uma lista somente com os funcionários que recebem benefícios, mês e ano e retorne o nome do funcionário que recebeu o valor mais alto em benefícios no mês.
		F.calcularMaiorBeneficioEFuncionario(F.getListaFuncionario(),12, 2023);
		System.out.print("\n----");
		
		//Um método que receba uma lista de vendedores, mês e ano e retorne o que mais vendeu no mês.
		F.calcularMaiorVendaVendedor(V.getListaVenda(), 04, 2022);
		System.out.print("\n----");
	}

}
