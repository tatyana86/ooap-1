package ParentList;

abstract class ParentList<T> {

	public static final int HEAD_OK = 1; // последняя head() отработала нормально
	public static final int HEAD_ERR = 2; // список пуст
	
	public static final int TAIL_OK = 1; // последняя tail() отработала нормально
	public static final int TAIL_ERR = 2; // список пуст
	
	public static final int RIGHT_OK = 1; // последняя right() отработала нормально
	public static final int RIGHT_ERR = 2; // список пуст или курсор указывает на последний элемент

	public static final int PUT_RIGHT_OK = 1; // последняя put_right() отработала нормально
	public static final int PUT_RIGHT_ERR = 2; // список пуст
	
	public static final int PUT_LEFT_OK = 1; // последняя put_left() отработала нормально
	public static final int PUT_LEFT_ERR = 2; // список пуст

	public static final int REMOVE_OK = 1; // последняя remove() отработала нормально
	public static final int REMOVE_ERR = 2; // список пуст
	
	public static final int REPLACE_OK = 1; // последняя replace() отработала нормально
	public static final int REPLACE_ERR = 2; // список пуст
	
	public static final int FIND_OK = 1; // последняя find() отработала нормально
	public static final int FIND_ERR = 2; // список пуст или элемент не найден
	
	public static final int GET_OK = 1; // последняя get() отработала нормально
	public static final int GET_ERR = 2; // список пуст
	
	// конструктор
	// постусловие: создан пустой список
	public ParentList() { }
			
	// команды
	
	// предусловие: список не пуст
	// постусловие: курсор установлен на первый узел в списке
	public abstract void head();
	
	// предусловие: список не пуст
	// постусловие: курсор установлен на последний узел в списке
	public abstract void tail();
	
	// предусловие: список не пуст и курсор не указывает на последний элемент в списке
	// постусловие: курсор сдвинут на один узел вправо
	public abstract void right();
	
	// предусловие: список не пуст
	// постусловие: вставлен новый узел с заданным значением следом за текущим узлом
	public abstract void put_right(T value);
	
	// предусловие: список не пуст
	// постусловие: вставлен новый узел с заданным значением перед текущим узлом
	public abstract void put_left(T value);
	
	// предусловие: список не пуст
	// постусловие: удален текущий узел, 
	// (курсор смещается к правому соседу, если он есть, 
    // в противном случае курсор смещается к левому соседу, 
    // если он есть)
	public abstract void remove();
	
	// постусловие: из списка удаляются все узлы
	public abstract void clear();
	
	// постусловие: вставлен новый узел с заданным значением
	public abstract void add_to_empty(T value);
		
	// постусловие: вставлен новый узел в хвост списка
	public abstract void add_tail(T value);
	
	// предусловие: список не пуст
	// постусловие: заменено значение текущего узла на заданное
	public abstract void replace(T value);
	
	// постусловие: курсор установлен на узел со значением value
	public abstract void find(T value);
	
	// постусловие: из списка удаляются все узлы со значением value
	public abstract void remove_all(T value);
	
	// запросы
	
	// предусловие: список не пуст
	public abstract T get();
	
	public abstract int size();
	
	public abstract boolean is_head();
	
	public abstract boolean is_tail();
	
	public abstract boolean is_value();
	
	// дополнительные запросы:
	public abstract int get_head_status(); // возвращает значение HEAD_*
	public abstract int get_tail_status(); // возвращает значение TAIL_*
	public abstract int get_right_status(); // возвращает значение RIGHT_*
	public abstract int get_put_right_status(); // возвращает значение PUT_RIGHT_*
	public abstract int get_put_left_status(); // возвращает значение PUT_LEFT_*
	public abstract int get_remove_status(); // возвращает значение REMOVE_*
	public abstract int get_replace_status(); // возвращает значение REPLACE_*
	public abstract int get_find_status(); // возвращает значение FIND_*
	public abstract int get_get_status(); // возвращает значение GET_*		
}

abstract class LinkedList<T> extends ParentList<T> {
	
}

abstract class TwoWayList<T> extends ParentList<T> {
	
	public static final int LEFT_OK = 1; // последняя left() отработала нормально
	public static final int LEFT_ERR = 2; // список пуст или курсор указывает на первый элемент
	
	// предусловие: список не пуст и курсор не указывает на первый элемент в списке
	// постусловие: курсор сдвинут на один узел влево
	public abstract void left();
	
	// дополнительные запросы:
	public abstract int get_left_status(); // возвращает значение LEFT_*
	
}

class ParentListImpl<T> extends ParentList<T> {

	
	
	
	@Override
	public void head() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put_right(T value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put_left(T value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add_to_empty(T value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add_tail(T value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(T value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(T value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove_all(T value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean is_head() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean is_tail() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean is_value() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int get_head_status() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_tail_status() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_right_status() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_put_right_status() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_put_left_status() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_remove_status() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_replace_status() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_find_status() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_get_status() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

class Node
{
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}








