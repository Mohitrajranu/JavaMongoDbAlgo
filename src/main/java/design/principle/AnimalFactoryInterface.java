package design.principle;

public abstract class AnimalFactoryInterface {
	public abstract AnimalInterface GetAnimalType(String type) throws Exception;

}

class ConcreteFactory extends AnimalFactoryInterface{

	@Override
	public AnimalInterface GetAnimalType(String type) throws Exception {
		switch(type){
		case "Duck":
			return new Duck();
			 
		case "Tiger":
			return new Tiger();
			
		default:
			
			throw new Exception("Animal type "+ type+
					" cannot be instantiated");
		}
		
	}
	
}