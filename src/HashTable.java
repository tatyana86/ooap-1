import java.util.LinkedList;

abstract class HashTable<T> {
		
	public static final int PUT_OK = 1; // последняя put() отработала нормально
	public static final int PUT_ERR = 2; // неразрешимая коллизия
	
	public static final int REMOVE_OK = 1; // последняя remove() отработала нормально
	public static final int REMOVE_ERR = 2; // элемент не найден или таблица пуста
	
	public int sz;
	
	// конструктор
	// постусловие: создана пустая хеш-таблица
	public HashTable(int sz) { }
	
	// команды:
	
	// предусловие: в таблице есть свободный слот
	// постусловие: элемент добавлен в таблицу
	public abstract void put(T value);
	
	// предусловие: в таблице есть значение value
	// постусловие: элемент удален из таблицы
	public abstract void remove(T value);
	
	// запросы:
	
	// предусловие: в таблице есть значение value
	public abstract boolean isContain(T value);
	
	public abstract int size();
	
	// дополнительные запросы:
	
	public abstract int get_put_status(); // возвращает значение PUT_*	
	public abstract int get_remove_status(); // возвращает значение REMOVE_*
	
}

class HashTableImpl<T> extends HashTable<T> {
	
	private int put_status; // статус команды put()
	private int remove_status; // статус команды remove()
	
	private LinkedList<T>[] slots;
	private int capacity;
	private int sub_capacity;
	private int freeSlots = capacity;
	private int size;

		
	// конструктор
	public HashTableImpl(int capacity) {
		super(capacity);
		this.capacity = capacity;
		this.sub_capacity = capacity;
		this.size = 0;
		this.slots = init(capacity);
	}
	
	// команды:
	
	// предусловие: в таблице есть свободный слот
	// постусловие: элемент добавлен в таблицу
	@Override
	public void put(T value) {
		int index = seekSlot(value);
		if(this.slots[index] == null) {
			this.slots[index] = new LinkedList<>();
			freeSlots --;
		}
		
		boolean contains = this.slots[index].contains(value);
		
		if(contains) {
			put_status = PUT_OK;
			this.size ++;
			return;
		} if(this.slots[index].size() == sub_capacity) {
			put_status = PUT_ERR;
		} else {
			this.slots[index].add(value);
			put_status = PUT_OK;
			this.size ++;
		} if((float) freeSlots / capacity <= 0.3) {
			reallocation();
		}
	}

	// предусловие: в таблице есть значение value
	// постусловие: элемент удален из таблицы
	@Override
	public void remove(T value) {
		int index = seekSlot(value);
		if(this.slots[index] == null) {
			remove_status = REMOVE_ERR;
			return;
		}
		
		boolean result = this.slots[index].contains(value);
		
		if(result) {
			this.slots[index].remove(value);
			remove_status = REMOVE_OK;
			this.size --;
		} else {
			remove_status = REMOVE_ERR;
		}
		
		if(this.slots[index].isEmpty())
		    this.freeSlots++;	
	}

	// запросы:
	
	// предусловие: в таблице есть значение value
	@Override
	public boolean isContain(T value) {
		int index = seekSlot(value);
		
		if(this.slots[index] == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int get_put_status() {
		return put_status;
	}

	@Override
	public int get_remove_status() {
		return remove_status;
	}
	
	private LinkedList<T>[] init(int capacity) {
		return new LinkedList[capacity];
	}
	
	private int seekSlot(T value) {
		int hashFun = value.toString().chars().sum();
		return hashFun % this.capacity;
	}
	
	private void reallocation() {
		this.capacity *= 2;
		LinkedList<T>[] newSlots = init(this.capacity);
		
		for(LinkedList<T> ts : slots) {
			if(ts == null) {
				continue;
			}
			for(T slot : ts) {
				int index = seekSlot(slot);
				if(newSlots[index] == null) {
				    newSlots[index] = new LinkedList<>();
				}
				newSlots[index].add(slot);
			}
		}
		
		this.slots = newSlots;
	 }
}