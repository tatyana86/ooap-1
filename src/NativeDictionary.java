import java.lang.reflect.Array;

abstract class NativeDictionary<T> {

	public static final int REMOVE_OK = 1; // последняя remove() отработала нормально
	public static final int REMOVE_ERR = 2; // ключа нет в массиве
	
	public static final int GET_OK = 1; // последняя get() отработала нормально
	public static final int GET_ERR = 2; // ключа нет в массиве
	
	// конструктор
	// постусловие: создан пустой ассоциативный массив
	public NativeDictionary() { }
	
	// команды:
	
	// постусловие: в массив добавлена новая пара ключ-значение
	public abstract void put(String key, T value);
	
	// предусловие: ключ есть в массиве
	// постусловие: ключ и значение удалены
	public abstract void remove(String key);
	
	// запросы:
	
	// проверка, что в массиве есть ключ
	public abstract boolean isContain(String key);
		
	// предусловие: ключ есть в массиве
	public abstract T get(String key);
	
	public abstract int size();
	
	// дополнительные запросы:
	
	public abstract int get_remove_status(); // возвращает значение REMOVE_*
	public abstract int get_get_status(); // возвращает значение GET_*	

}

class NativeDictionaryImpl<T> extends NativeDictionary<T> {

	private int remove_status; // статус команды remove()
	private int get_status; // статус команды get()
	
	private final int size;
	private final String [] slots;
	private final T [] values;
	
	// конструктор
	public NativeDictionaryImpl(int sz, Class clazz) {
		this.size = sz;
		this.slots = new String[size];
		this.values = (T[]) Array.newInstance(clazz, this.size);
	}

	// команды:
	
	// постусловие: в массив добавлена новая пара ключ-значение
	@Override
	public void put(String key, T value) {
		int slot = hashFun(key);
		this.slots[slot] = key;
		this.values[slot] = value;
	}

	// предусловие: ключ есть в массиве
	// постусловие: ключ и значение удалены
	@Override
	public void remove(String key) {
		int slot = hashFun(key);
		if(this.slots[slot].equals(key)) {
			remove_status = REMOVE_OK;
			this.slots[slot] = null;
			this.values[slot] = null;
		} 
		
		remove_status = REMOVE_ERR;
	}

	// запросы:
	
	// предусловие: в таблице есть ключ
	@Override
	public boolean isContain(String key) {
		int slot = hashFun(key);
		return this.slots[slot].equals(key);
	}

	// предусловие: ключ есть в массиве
	@Override
	public T get(String key) {
		if(!isContain(key)) {
			get_status = GET_ERR;
			return null;
		}
		
		get_status = GET_OK;
		return values[hashFun(key)];
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int get_remove_status() {
		return remove_status;
	}

	@Override
	public int get_get_status() {
		return get_status;
	}
	
    private int hashFun(String key)
    {
        int slot = Math.abs(key.hashCode());
        return slot % this.size;
    }
}
