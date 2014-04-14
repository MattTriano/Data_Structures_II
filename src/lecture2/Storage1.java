package lecture2;


class Item1 {
	String key;
	String value;
	Item1(String key, String value) {
		this.key = key;
		this.value = value;
	}
}

public class Storage1 {
	private Item1[] items;
	private int nitems;
	Storage1(int N) {
		this.items = new Item1[N];
		this.nitems = 0;
	}
	
	void put(Item1 item) {
		Item1 tmp;
		items[nitems] = item;
		for(int k = nitems; k>0;k--) {
			if(items[k-1].key.compareTo(items[k].key)>0) {
				tmp = items[k-1];
				items[k-1] = items[k];
				items[k] = tmp;
			} else break;
		}
		nitems++;
	}
	
	int search(String key) {
		int a=0, b=nitems;
		int k, comp;
		while(b>a) {
			k = (a+b)/2;
			comp = items[k].key.compareTo(key);
			if(comp == 0) {
				return k;
			} else if(comp > 0) {
				b = k;
			} else if(comp < 0) {
				a = k+1;
			}
		}
		return -1;
	}
	
	boolean has(String key) {
		return search(key) >= 0;
	}
	
	String get(String key) {
		int k = search(key);
		if(k>=0) 
			return this.items[k].value;
		return null;
	}
	
	boolean delete(String key) {
		int k = search(key);
		if(k>= 0) {
			for (int i=k+1; i<nitems; i++) 
				items[k-1] = items[k];
			items[nitems-1] = null;
			nitems--;
		}
		return (k>=0);
	}
	
	int size() {
		return this.nitems;
	}
	
	public static void main(String[] args) {
		Item item1 = new Item("Max", "313-867-5309");
		Item item2 = new Item("Tim", "111-111-1111");
		Item item3 = new Item("Jim", "222-222-2222");
		Storage storage = new Storage(1000);
		storage.put(item1);
		storage.put(item2);
		storage.put(item3);
		System.out.println(storage.size());
		System.out.println(storage.get("Tim"));
		storage.delete("Tim");
		System.out.println(storage.get("Tim"));
		System.out.println(storage.get("Alex"));
		System.out.println(storage.has("Tim"));
		
		/*int sum = 0;
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNextInt()) {
			sum += sc.nextInt();
			System.out.println(sum);
		}
		*/
	}
}
