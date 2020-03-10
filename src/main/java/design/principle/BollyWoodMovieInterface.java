package design.principle;

public interface BollyWoodMovieInterface {
	String getMovieName();
}
class BollyWoodActionMovie implements BollyWoodMovieInterface{
	public String getMovieName(){
		return "bang bang is a BollyWood Action Movie";
	}
}
class BollyWoodComedyMovie implements BollyWoodMovieInterface{
	public String getMovieName(){
		return "phir hera pheri is a BollyWood Comedy Movie";
	}
}