package observer;

public class ManagerObserver {

	private IObserver observer;
	
	public ManagerObserver(IObserver observer) {
		this.observer = observer;
	}
	
	public void prueba(String string) {
		observer.prueba(string);
	}
	
	public void deleteConnection() {
		observer.deleteConnection();
	}
}
