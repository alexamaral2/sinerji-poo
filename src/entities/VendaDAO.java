package entities;

import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
		
		public static List<Venda> listaVenda = new ArrayList<>();
		public static FuncionarioDAO funcionarioDao = new FuncionarioDAO();
		
		static {

			listaVenda.add(new Venda(1,funcionarioDao.consultar_funcionario_id(3),"12/2021",5200));
			listaVenda.add(new Venda(2,funcionarioDao.consultar_funcionario_id(4),"12/2021",3400));
			listaVenda.add(new Venda(3,funcionarioDao.consultar_funcionario_id(3),"01/2022",4000));
			listaVenda.add(new Venda(4,funcionarioDao.consultar_funcionario_id(4),"01/2022",7700));
			listaVenda.add(new Venda(5,funcionarioDao.consultar_funcionario_id(3),"02/2022",4200));
			listaVenda.add(new Venda(6,funcionarioDao.consultar_funcionario_id(4),"02/2022",5000));
			listaVenda.add(new Venda(7,funcionarioDao.consultar_funcionario_id(3),"03/2022",5850));
			listaVenda.add(new Venda(8,funcionarioDao.consultar_funcionario_id(4),"03/2022",5900));
			listaVenda.add(new Venda(9,funcionarioDao.consultar_funcionario_id(3),"04/2022",7000));
			listaVenda.add(new Venda(10,funcionarioDao.consultar_funcionario_id(4),"04/2022",6500));
			
		}
		
		public static List<Venda> getListaVenda(){
			return listaVenda;
		}
		
		
}
