package tp7;

public class Philosophe extends Thread {
	int _id;
	Tablev3 _table;
	
	Philosophe (int i, Tablev3 t) {
		_id = i;
		_table = t;
	}
	
	public void run() {
		try {
			while(true) {
				System.out.println("Je desire manger "+_id);
				_table.prendreBaguettes(_id);
				System.out.println("J'ai fini de manger "+_id);
				System.out.println("Je pense "+_id);
				sleep(5000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
