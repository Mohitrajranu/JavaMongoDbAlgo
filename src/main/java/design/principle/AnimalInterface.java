package design.principle;

public interface AnimalInterface {
void speak();
}

class Duck implements AnimalInterface{
	public void speak(){
		System.out.println("quack quach");
	}
}
class Tiger implements AnimalInterface{
	public void speak(){
		System.out.println("Growl grr");
	}
}