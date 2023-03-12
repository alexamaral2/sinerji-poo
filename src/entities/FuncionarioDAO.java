package entities;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
	public static float valorTotalVendedor;
	public static float valorTotalVendedorBeneficio;
	public static List<Funcionario> listaFuncionario = new ArrayList<>();
	public static cargoDAO CargoDao = new cargoDAO();
	
	static {
		
		listaFuncionario.add(new Funcionario(1, "Jorge Carvalho", cargoDAO.consultar_cargo_id(1),"01/2018"));
		listaFuncionario.add(new Funcionario(2, "Maria Souza", cargoDAO.consultar_cargo_id(1),"12/2015"));
		listaFuncionario.add(new Funcionario(3, "Ana Silva", cargoDAO.consultar_cargo_id(2),"12/2021"));
		listaFuncionario.add(new Funcionario(4, "João Mendes", cargoDAO.consultar_cargo_id(2),"12/2021"));
		listaFuncionario.add(new Funcionario(5, "Juliana Alves", cargoDAO.consultar_cargo_id(3),"07/2017"));
		listaFuncionario.add(new Funcionario(6, "Bento Albino", cargoDAO.consultar_cargo_id(3),"03/2014"));
	}
	
	public static List<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}
	
	public void calcularMaiorVendaVendedor(List<Venda> vendas, int mes, int ano) {
		float maiorVenda = 0;
		String nomeFuncionario = "";
		
		for (Venda venda : vendas) {
			String cargo = venda.getFuncionario().getCargo().getNomeCargo();
			if (cargo.equals("Vendedor")) {
				int posicaoBarraVenda = venda.getMesAnoVenda().indexOf("/");
				int mesVenda = Integer.parseInt(venda.getMesAnoVenda().substring(0, posicaoBarraVenda));
				int anoVenda = Integer.parseInt(venda.getMesAnoVenda().substring(posicaoBarraVenda + 1));
				
				if (ano == anoVenda && mes == mesVenda) {
					float valorVenda = venda.getValorVenda();
					if (valorVenda > maiorVenda) {
						maiorVenda = valorVenda;
						nomeFuncionario = venda.getFuncionario().getNomeFuncionario();
					}
				}
			}
		}
		
		System.out.printf("\nA maior venda realizada por um vendedor foi de R$%.2f, realizada por %s.\n", maiorVenda, nomeFuncionario);
	}
	
	public void calcularMaiorBeneficioEFuncionario(List<Funcionario> funcionarios, int mes, int ano) {
		float maiorBeneficio = 0;
		String nomeFuncionario = "";

		for (Funcionario funcionario : funcionarios) {
		    int posicaoBarra = funcionario.mesAnoContratacao().indexOf("/");
		    int mesInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(0, posicaoBarra));
		    int anoInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(posicaoBarra + 1));

		    if (ano < anoInicio || (ano == anoInicio && mes < mesInicio)) {
		        continue; // o funcionário não trabalhou nesse período
		    }

		    float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * (ano - anoInicio);
		    float salarioBase = funcionario.getCargo().getSalario() + adicionalAnoServico;

		    float porcentagemBeneficio = funcionario.getCargo().getPorcentagemBeneficio();

		    if (funcionario.cargo.nomeCargo.equals("Vendedor")) {
		        for (Venda venda : VendaDAO.getListaVenda()) {
		            int posicaoBarraVenda = venda.getMesAnoVenda().indexOf("/");
		            int mesVenda = Integer.parseInt(venda.getMesAnoVenda().substring(0, posicaoBarraVenda));
		            int anoVenda = Integer.parseInt(venda.getMesAnoVenda().substring(posicaoBarraVenda + 1));

		            if (mesVenda == mes && anoVenda == ano && venda.getFuncionario().nome.equals(funcionario.nome)) {
		                float valorVenda = venda.getValorVenda();
		                float beneficio = valorVenda * porcentagemBeneficio / 100;

		                if (beneficio > maiorBeneficio) {
		                    maiorBeneficio= beneficio;
		                    nomeFuncionario = funcionario.getNomeFuncionario();
		                }
		            }
		        }
		    } else {
		        float beneficio = salarioBase * porcentagemBeneficio / 100;

		        if (beneficio > maiorBeneficio) {
		            maiorBeneficio = beneficio;
		            nomeFuncionario = funcionario.getNomeFuncionario();
		        }
		    }
		}

		System.out.printf("\nO funcionário com maior beneficio é %s, com um ganho de beneficio de R$ %.2f\n", nomeFuncionario, maiorBeneficio);
	}
	
	
	public void calcularMaiorSalarioEFuncionario(List<Funcionario> funcionarios,int mes, int ano) {
		float maiorSalario = 0;
		String nomeFuncionario = "";

		for (Funcionario funcionario : funcionarios) {
		    int posicaoBarra = funcionario.mesAnoContratacao().indexOf("/");
		    int mesInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(0, posicaoBarra));
		    int anoInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(posicaoBarra + 1));

		    if (ano < anoInicio || (ano == anoInicio && mes < mesInicio)) {
		        continue; // o funcionário não trabalhou nesse período
		    }

		    float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * (ano - anoInicio);
		    float salarioBase = funcionario.getCargo().getSalario() + adicionalAnoServico;

		    float porcentagemBeneficio = funcionario.getCargo().getPorcentagemBeneficio();

		    if (funcionario.cargo.nomeCargo.equals("Vendedor")) {
		        for (Venda venda : VendaDAO.getListaVenda()) {
		            int posicaoBarraVenda = venda.getMesAnoVenda().indexOf("/");
		            int mesVenda = Integer.parseInt(venda.getMesAnoVenda().substring(0, posicaoBarraVenda));
		            int anoVenda = Integer.parseInt(venda.getMesAnoVenda().substring(posicaoBarraVenda + 1));

		            if (mesVenda == mes && anoVenda == ano && venda.getFuncionario().nome.equals(funcionario.nome)) {
		                float valorVenda = venda.getValorVenda();
		                float beneficio = valorVenda * porcentagemBeneficio / 100;
		                float salarioLiquido = salarioBase + beneficio;

		                if (salarioLiquido > maiorSalario) {
		                    maiorSalario = salarioLiquido;
		                    nomeFuncionario = funcionario.getNomeFuncionario();
		                }
		            }
		        }
		    } else {
		        float beneficio = salarioBase * porcentagemBeneficio / 100;
		        float salarioLiquido = salarioBase + beneficio;

		        if (salarioLiquido > maiorSalario) {
		            maiorSalario = salarioLiquido;
		            nomeFuncionario = funcionario.getNomeFuncionario();
		        }
		    }
		}

		System.out.printf("\nO funcionário com maior salário é %s, com um salário líquido de R$ %.2f\n", nomeFuncionario, maiorSalario);
	}
	
	public void calcularBeneficioPago(List<Funcionario> funcionarios, int mes, int ano) {
		float valorTotal = 0;
	    float valorBeneficio = 0;
	    float valorTotalVendedor = 0;
	    float valorTotalVendedorBeneficio = 0;

	    System.out.println("Pagamento no Mês:" + mes + " Ano:" + ano);
	    System.out.print("Funcionários com Direito a receber o Beneficio: \n");
	    
	    for (Funcionario funcionario : funcionarios) {
	        String cargo = (String) funcionario.getCargo().nomeCargo;
	    	int posicaoBarra = funcionario.mesAnoContratacao().indexOf("/");
	        int mesInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(0, posicaoBarra));
	        int anoInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(posicaoBarra + 1));
	        
	        if (cargo == "Vendedor" || cargo == "Secretario") {
	        
		        if (ano < anoInicio || (ano == anoInicio && mes < mesInicio)) {
		            continue; // o funcionário não trabalhou nesse período
		        }
		        
		        float porcentagemBeneficio = funcionario.getCargo().getPorcentagemBeneficio();
	
		        if (funcionario.cargo.nomeCargo.equals("Vendedor")) {
		            for (Venda venda : VendaDAO.getListaVenda()) {
		                int posicaoBarraVenda = venda.getMesAnoVenda().indexOf("/");
		                int mesVenda = Integer.parseInt(venda.getMesAnoVenda().substring(0, posicaoBarraVenda));
		                int anoVenda = Integer.parseInt(venda.getMesAnoVenda().substring(posicaoBarraVenda + 1));
	
		                if (mesVenda == mes && anoVenda == ano && venda.getFuncionario().nome.equals(funcionario.nome)) {
		                    float valorVenda = venda.getValorVenda();
		                    float beneficio = valorVenda * porcentagemBeneficio / 100;
		                    System.out.print(funcionario.nome+ "\n");
		                    valorTotalVendedorBeneficio += beneficio;
		                }
		            }
		        } else {
		        	float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * (ano - anoInicio);
		            float salarioBase = funcionario.getCargo().getSalario() + adicionalAnoServico;
		        	float beneficio = salarioBase * porcentagemBeneficio / 100;
		        	System.out.print(funcionario.nome+ "\n");
		            valorBeneficio += beneficio;
		        }
		    }
	    }
	    System.out.printf("Total de benefícios a serem pagos: R$ %.2f\n", valorBeneficio + valorTotalVendedorBeneficio);
	}

	
	public static Funcionario consultar_funcionario_id(int valor) {
        for (Funcionario funcionario : listaFuncionario) {
            if (funcionario.getId() == valor) {
                return funcionario;
            }
        }
        return null;
    }
	
	public static Funcionario consultar_funcionario() {
        for (Funcionario funcionario : listaFuncionario) {
           System.out.print(funcionario.id + "\n");
        }
        return null;
    }
	
	public void calcularSalario(List<Funcionario> funcionarios ,int mes, int ano) {
		System.out.print("Pagamento no Mês:" + mes + " Ano:" +ano + "\n");
		float valorTotal = 0;
		for (Funcionario funcionario : funcionarios) {
			int posicaoBarra = funcionario.mesAnoContratacao().indexOf("/");
            int mesInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(0, posicaoBarra));
            int anoInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(posicaoBarra + 1));
            
            if (ano == anoInicio){
            	if (mes >= mesInicio){
            		int anosTrabalhados = ano - anoInicio;
	        		float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * anosTrabalhados;
	        		float salarioLiquido = funcionario.getCargo().getSalario() + adicionalAnoServico;
	        		
	        		System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio + " | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano  + " | " +  "Salario Liquido: R$ " + salarioLiquido+"\n");
	        		valorTotal += salarioLiquido;
            	}
            	
            }
            
            else if (ano > anoInicio && anoInicio < ano){
	            
	        	if (mes >= mesInicio){
	        		int anosTrabalhados = ano - anoInicio;
	        		float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * anosTrabalhados;
	        		float salarioLiquido = funcionario.getCargo().getSalario() + adicionalAnoServico;
	        		
	        		System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio + " | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano  + " | " +  "Salario Liquido: R$ " + salarioLiquido+"\n");
	        		valorTotal += salarioLiquido;
	        		
	        	}
	        	else {
	        		int anosTrabalhados = ano - anoInicio;
	        		float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * (anosTrabalhados - 1);
	        		float salarioLiquido = funcionario.getCargo().getSalario() + adicionalAnoServico;
	        		
	        		System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio + " | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano  + " | " +  "Salario Liquido: R$" + salarioLiquido+"\n");
	        		valorTotal += salarioLiquido;
	        	}
            }
		}
		System.out.print("\nValor Total gasto com Salário Mensal: R$ " + valorTotal);
	}
	
	public void calcularSalarioEBeneficioPagoVendedor(int mes, int ano) {
		valorTotalVendedor = 0;
		boolean verifcaVenda = false;
		for (Venda venda : VendaDAO.getListaVenda()){
				String vendedor = (String) venda.getFuncionario().nome;
				int posicaoBarraVenda = venda.getMesAnoVenda().indexOf("/");
	            int mesVenda = Integer.parseInt(venda.getMesAnoVenda().substring(0, posicaoBarraVenda));
	            int anoVenda = Integer.parseInt(venda.getMesAnoVenda().substring(posicaoBarraVenda + 1));
	            
	            if (mesVenda == mes && anoVenda == ano){
	            	for (Funcionario funcionario : listaFuncionario) {
	            		if (vendedor == funcionario.nome){
		            		int posicaoBarra = funcionario.mesAnoContratacao().indexOf("/");
		                    int mesInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(0, posicaoBarra));
		                    int anoInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(posicaoBarra + 1));
		                    
		                    if (ano >= anoInicio){
		        	            
		    		        	if (mes >= mesInicio){
		    		    			
		    		        		int anosTrabalhados = ano - anoInicio;
		    		        		float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * anosTrabalhados;
		    		        		float salarioComAnoAdicional = funcionario.getCargo().getSalario() + adicionalAnoServico;
		    		        		float porcentagemSalario =  venda.getValorVenda() / 100 * funcionario.getCargo().getPorcentagemBeneficio();
		    		        		float salarioLiquido = porcentagemSalario + salarioComAnoAdicional;
		    		        		System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio + " | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano  + "| Beneficio: R$"+ porcentagemSalario + " | Salario Liquido: R$" + salarioLiquido+"\n");
		    		        		valorTotalVendedor += salarioLiquido;
		    		        		valorTotalVendedorBeneficio += porcentagemSalario;
		    		        		verifcaVenda = true;
		    		        	}
		    		
		    		        	else {
		    		        		int anosTrabalhados = ano - anoInicio;
		    		        		float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * (anosTrabalhados - 1);
		    		        		float salarioComAnoAdicional = adicionalAnoServico + funcionario.getCargo().getSalario();
		    		        		float porcentagemSalario = venda.getValorVenda() * funcionario.getCargo().getPorcentagemBeneficio() /100;
		    		        		float salarioLiquido = porcentagemSalario + salarioComAnoAdicional;
		    		        		System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio +" | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano +  "| Beneficio: R$"+ porcentagemSalario +"  | Salário Liquido: R$" + salarioLiquido+"\n");
		    		        		valorTotalVendedor += salarioLiquido;
		    		        		valorTotalVendedorBeneficio += porcentagemSalario;
		    		        		verifcaVenda = true;
		    		        	}
		                    }
	            		}
	            	}
	            }
		}
		
		if (verifcaVenda == false) {
			
			for (Funcionario funcionario : listaFuncionario) {
	        	String cargo = (String) funcionario.cargo.nomeCargo;
	        	int posicaoBarra = funcionario.mesAnoContratacao().indexOf("/");
	            int mesInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(0, posicaoBarra));
	            int anoInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(posicaoBarra + 1));
	            if (cargo == "Vendedor"){
		           if (ano == anoInicio) {
		        	   if (mes >= mesInicio){
		        		   int anosTrabalhados = ano - anoInicio;
		        		   float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * anosTrabalhados;
		        		   float salarioComAnoAdicional = funcionario.getCargo().getSalario() + adicionalAnoServico;
		        		   float porcentagemSalario =  0;
		        		   float salarioLiquido = porcentagemSalario + salarioComAnoAdicional;
		        		   System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio + " | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano + " | Beneficio: R$"+ porcentagemSalario +" | Salário Liquido: R$" + salarioLiquido+"\n");
		        		   valorTotalVendedor += salarioLiquido;
		        		   valorTotalVendedorBeneficio += porcentagemSalario;
		        	   }
		           }
		           else if (ano > anoInicio && anoInicio < ano){	
			        	if (mes >= mesInicio){
			        		
			        		int anosTrabalhados = ano - anoInicio;
    		        		float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * anosTrabalhados;
    		        		float salarioComAnoAdicional = funcionario.getCargo().getSalario() + adicionalAnoServico;
    		        		float porcentagemSalario =  0;
    		        		float salarioLiquido = porcentagemSalario + salarioComAnoAdicional;
			        		System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio + " | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano + " | Beneficio: R$"+ porcentagemSalario +" | Salário Liquido: R$" + salarioLiquido+"\n");
			        		valorTotalVendedor += salarioLiquido;
			        		valorTotalVendedorBeneficio += porcentagemSalario;
			        	}
			
			        	else {
			        		int anosTrabalhados = ano - anoInicio;
    		        		float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * (anosTrabalhados - 1);
    		        		float salarioComAnoAdicional = funcionario.getCargo().getSalario() + adicionalAnoServico;
    		        		float porcentagemSalario =  0;
    		        		float salarioLiquido = porcentagemSalario + salarioComAnoAdicional;
			        		System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio +" | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano +  " | Beneficio: R$"+ porcentagemSalario + " | Salário Liquido: R$" + salarioLiquido+"\n");
			        		valorTotalVendedor += salarioLiquido;
			        		valorTotalVendedorBeneficio += porcentagemSalario;
			        	}
		            }
	            }
			}
		}
	}
		                    
	    	
		        	
	public void calcularSalarioEBeneficioPago(List<Funcionario> funcionarios,int mes, int ano) {
      
		float valorTotal = 0;
		float valorBeneficio = 0;
		
		System.out.print("Pagamento no Mês:" + mes + " Ano:" +ano + "\n");
		for (Funcionario funcionario : listaFuncionario) {
        	String cargo = (String) funcionario.cargo.nomeCargo;
        	int posicaoBarra = funcionario.mesAnoContratacao().indexOf("/");
            int mesInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(0, posicaoBarra));
            int anoInicio = Integer.parseInt(funcionario.mesAnoContratacao().substring(posicaoBarra + 1));
            if (cargo != "Vendedor"){
            	if (ano == anoInicio && anoInicio == ano) {
		        	   if (mes >= mesInicio){
		        		    int anosTrabalhados = ano - anoInicio;
			        		float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * anosTrabalhados;
			        		float salarioComAnoAdicional = adicionalAnoServico + funcionario.getCargo().getSalario();
			        		float porcentagemSalario = salarioComAnoAdicional * funcionario.getCargo().getPorcentagemBeneficio() /100;
			        		float salarioLiquido = porcentagemSalario + salarioComAnoAdicional;
			        		System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio + " | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano + " | Beneficio: R$"+ porcentagemSalario +" | Salário Liquido: R$" + salarioLiquido+"\n");
			        		valorTotal += salarioLiquido;
			        		valorBeneficio += porcentagemSalario;
		        	   }
            	}
            	else if (ano > anoInicio && anoInicio <ano){	
		        	if (mes >= mesInicio){
		    			
		        		int anosTrabalhados = ano - anoInicio;
		        		float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * anosTrabalhados;
		        		float salarioComAnoAdicional = adicionalAnoServico + funcionario.getCargo().getSalario();
		        		float porcentagemSalario = salarioComAnoAdicional * funcionario.getCargo().getPorcentagemBeneficio() /100;
		        		float salarioLiquido = porcentagemSalario + salarioComAnoAdicional;
		        		System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio + " | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano + " | Beneficio: R$"+ porcentagemSalario +" | Salário Liquido: R$" + salarioLiquido+"\n");
		        		valorTotal += salarioLiquido;
		        		valorBeneficio += porcentagemSalario;
		        	}
		
		        	else {
		        		int anosTrabalhados = ano - anoInicio;
		        		float adicionalAnoServico = funcionario.getCargo().getAdicionalAnoServico() * (anosTrabalhados - 1);
		        		float salarioComAnoAdicional = adicionalAnoServico + funcionario.getCargo().getSalario();
		        		float porcentagemSalario = salarioComAnoAdicional * funcionario.getCargo().getPorcentagemBeneficio() /100;
		        		float salarioLiquido = porcentagemSalario + salarioComAnoAdicional;
		        		
		        		System.out.print(funcionario.getNomeFuncionario()  + " | Mês Contratação: " + mesInicio +" | " + "Ano Contratação: "+ anoInicio + " | Ano Salário: " + ano +  " | Beneficio: R$"+ porcentagemSalario + " | Salário Liquido: R$" + salarioLiquido+"\n");
		        		valorTotal += salarioLiquido;
		        		valorBeneficio += porcentagemSalario;
		        	}
	            }
            }
            
        }
		calcularSalarioEBeneficioPagoVendedor(mes, ano);
		float gastoSalario = valorTotalVendedor + valorTotal;
		float gastoBeneficio = valorTotalVendedorBeneficio + valorBeneficio;
        System.out.print("\nValor Total gasto com Salário Mensal(Salário com Beneficio): R$ " + gastoSalario);
        
	}
}