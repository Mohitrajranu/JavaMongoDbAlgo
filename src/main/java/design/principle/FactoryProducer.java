package design.principle;

public class FactoryProducer {

	public static MovieFactoryInterface getFactory(String type){
		if(type.equalsIgnoreCase("HollyWood")){
			return new HollyWoodMovieFactory();
		}else if(type.equalsIgnoreCase("BollyWood")){
			return new BollyWoodMovieFactory();
		}
		return null;
	}
	
}
