package design.principle;

public class FactoryAbstract {

	public static void main(String[] args) {
		
	
	MovieFactoryInterface hollyWoodMovieFactory = FactoryProducer.getFactory("HollyWood");
	MovieFactoryInterface bollyWoodMovieFactory = FactoryProducer.getFactory("BollyWood");
	HollyWoodMovieInterface haction = hollyWoodMovieFactory.GetHollyWoodMovie("Action");
	BollyWoodMovieInterface baction = bollyWoodMovieFactory.GetBollyWoodMovie("Comedy");
	
	System.out.println(haction.getMovieName());
	System.out.println(baction.getMovieName());
}
}
