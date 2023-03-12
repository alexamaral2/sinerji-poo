package entities;
import java.util.ArrayList;
import java.util.List;

public class cargoDAO {

	public static List<Cargo> listaCargo= new ArrayList<>();
	
	static {
		listaCargo.add(new Cargo(1,"Secretario",7000,1000,20));
		listaCargo.add(new Cargo(2,"Vendedor",12000,1800,30));
		listaCargo.add(new Cargo(3,"Gerente",20000,3000,0));
	}	
	
	public static List<Cargo> getListaCargo() {
        return listaCargo;
    }
	
	 public static Cargo consultar_cargo_id(int valor) {
	        for (Cargo cargo : cargoDAO.getListaCargo()) {
	            if (cargo.getId() == valor) {
	                return cargo;
	            }
	        }
	        return null;
	    }

}


