package design.principle;

public interface MovieFactoryInterface {

	BollyWoodMovieInterface GetBollyWoodMovie(String type);
	HollyWoodMovieInterface GetHollyWoodMovie(String type);
}
class HollyWoodMovieFactory implements MovieFactoryInterface{

	@Override
	public BollyWoodMovieInterface GetBollyWoodMovie(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HollyWoodMovieInterface GetHollyWoodMovie(String type) {
		
		if(type.equalsIgnoreCase("Action")){
			return new HollyWoodActionMovie();
		}else if(type.equalsIgnoreCase("Comedy")){
			return new HollyWoodComedyMovie();
		}
		return null;
	}
	
}
class BollyWoodMovieFactory implements MovieFactoryInterface{

	@Override
	public BollyWoodMovieInterface GetBollyWoodMovie(String type) {
		if(type.equalsIgnoreCase("Action")){
			return new BollyWoodActionMovie();
		}else if(type.equalsIgnoreCase("Comedy")){
			return new BollyWoodComedyMovie();
		}
		return null;
	
	}

	@Override
	public HollyWoodMovieInterface GetHollyWoodMovie(String type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
