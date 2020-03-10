package design.principle;

 class Service implements DependencyInversion{

	public void write(String message){
		System.out.println("Hello World "+message);
	}
}
public class Client{
	private DependencyInversion di;

	/**
	 * @param di
	 */
	public Client(DependencyInversion di) {
		this.di = di;
	}
	public void doSomething(){
		di.write("My DI implementation");
	}
	public static void main(String[] args) {
		
		DependencyInversion service = new Service();
		Client client = new Client(service);
		client.doSomething();
	}
	
}