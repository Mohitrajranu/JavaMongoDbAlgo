package design.principle;

public interface HollyWoodMovieInterface {
	String getMovieName();
}

class HollyWoodActionMovie implements HollyWoodMovieInterface{
	public String getMovieName(){
		return "True Lies is a HollyWood Action Movie";
	}
}
class HollyWoodComedyMovie implements HollyWoodMovieInterface{
	public String getMovieName(){
		return "The Jerk is a HollyWood Comedy Movie";
	}
}