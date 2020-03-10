package design.principle;

public class FactoryDesign {

	public static void main(String[] args) throws Exception {
		
		AnimalFactoryInterface animalFactory = new ConcreteFactory();
		AnimalInterface duckType = animalFactory.GetAnimalType("Duck");
		duckType.speak();
	}
}
