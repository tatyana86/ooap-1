

import java.lang.reflect.Array;

abstract class DynArray<T> {

	public static final int INSERT_OK = 1; // последняя insert() отработала нормально
	public static final int INSERT_ERR = 2; // индекс в недопустимых границах
	
	public static final int REMOVE_OK = 1; // последняя remove() вернула корректное значение
	public static final int REMOVE_ERR = 2; // индекс в недопустимых границах
	
	public static final int GET_OK = 1; // последняя get() отработала нормально
	public static final int GET_ERR = 2; // индекс в недопустимых границах
	
	// конструктор
	// постусловие: создан пустой динамический массив
	public DynArray() {}
	
	// команды:
	
	// предусловие: индекс в допустимых границах
	// постусловие: добавлен элемент на позицию i
	public abstract void insert(int i, T value);
	
	// постусловие: добавлен элемент в конец
	public abstract void append(T itm);
	
	// предусловие: индекс в допустимых границах
	// постусловие: удален элемент на позиции i
	public abstract void remove(int i);
	
	// запросы:
	
	// предусловие: индекс в допустимых границах
	public abstract T get(int i);
	
	public abstract int size();

	// дополнительные запросы:
	public abstract int get_insert_status(); // возвращает значение INSERT_*
	public abstract int get_remove_status(); // возвращает значение REMOVE_*
	public abstract int get_get_status(); // возвращает значение GET_*
}

class DynArryaImpl<T> extends DynArray<T> {

	private static final int MIN_CAPACITY = 16; // минимальный размер массива по умолчанию
	private static final double MIN_FULLNESS = 0.5; // минимальная доля заполненности массива
	private static final double REDUCTION_FACTOR = 1.5; // оэффициент уменьшения размера массива
	private int insert_status; // статус запроса insert()
	private int remove_status; // статус команды remove()
	private int get_status; // статус команды get()
	
	public T [] array;
	public int count;
	public int capacity;
	Class clazz;
    
	// конструктор с размером стека по умолчанию
	public DynArryaImpl(Class clz) {
		this.clazz = clz;
		this.count = 0;
		makeArray(MIN_CAPACITY);
	}
	
	private void makeArray(int new_capacity)
	{
	    if(new_capacity < MIN_CAPACITY) {
	        new_capacity = MIN_CAPACITY;
	    }
	
	    T [] tempArray = (T[]) Array.newInstance(this.clazz, new_capacity);
	
	    if(this.count > 0) {
	        System.arraycopy(array, 0, tempArray, 0, this.count);
	    }
	
	    this.array = tempArray;
	    this.capacity = new_capacity;
	}
	
	// команды:
	
	// предусловие: индекс в допустимых границах
	// постусловие: добавлен элемент на позицию i	
	@Override
	public void insert(int i, T value) {
		if(i < 0 || i > count) {
			insert_status = INSERT_ERR;
			return;
		}
		
		if(this.count == this.capacity) {
	        makeArray(2 * this.capacity);
	    }
		
		System.arraycopy(this.array, i, this.array, i + 1, this.count - i);
	    this.array[i] = value;
	    this.count ++;
		
	    insert_status = INSERT_OK;
	}

	// постусловие: добавлен элемент в конец
	@Override
	public void append(T itm) {
		if(this.count == this.capacity) {
	        makeArray(2 * this.capacity);
	    }
	    array[this.count] = itm;
	    this.count ++;
	}

	// предусловие: индекс в допустимых границах
	// постусловие: удален элемент на позиции i
	@Override
	public void remove(int i) {
		if(i < 0 || i >= count) {
			remove_status = REMOVE_ERR;
			return;
		}
		
		System.arraycopy(this.array, i + 1, this.array, i, this.count - i - 1);
		this.array[count - 1] = null;
		this.count --;
		
		if(this.count < (this.capacity * MIN_FULLNESS)) {
		    makeArray((int) (this.capacity / REDUCTION_FACTOR));
		}
	}

	// запросы:
	
	// предусловие: индекс в допустимых границах
	@Override
	public T get(int i) {
		if(i < 0 || i >= count) {
			get_status = GET_ERR;
			return null;
		}
		get_status = GET_OK;
		return array[i];
	}

	@Override
	public int size() {
		return this.count;
	}

	@Override
	public int get_insert_status() {
		return insert_status;
	}

	@Override
	public int get_remove_status() {
		return remove_status;
	}

	@Override
	public int get_get_status() {
		return get_status;
	}
	
}