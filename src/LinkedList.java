// 2.1

abstract class LinkedList<T> {

	public static final int HEAD_NIL = 0; // head() ещё не вызывалась
	public static final int HEAD_OK = 1; // последняя head() отработала нормально
	public static final int HEAD_ERR = 2; // список пуст
	
	public static final int TAIL_NIL = 0; // tail() ещё не вызывалась
	public static final int TAIL_OK = 1; // последняя tail() отработала нормально
	public static final int TAIL_ERR = 2; // список пуст
	
	public static final int RIGHT_NIL = 0; // right() ещё не вызывалась
	public static final int RIGHT_OK = 1; // последняя right() отработала нормально
	public static final int RIGHT_ERR = 2; // список пуст или курсор указывает на последний элемент
	
	public static final int PUT_RIGHT_NIL = 0; // put_right() ещё не вызывалась
	public static final int PUT_RIGHT_OK = 1; // последняя put_right() отработала нормально
	public static final int PUT_RIGHT_ERR = 2; // список пуст
	
	public static final int PUT_LEFT_NIL = 0; // put_left() ещё не вызывалась
	public static final int PUT_LEFT_OK = 1; // последняя put_left() отработала нормально
	public static final int PUT_LEFT_ERR = 2; // список пуст
	
	public static final int REMOVE_NIL = 0; // remove() ещё не вызывалась
	public static final int REMOVE_OK = 1; // последняя remove() отработала нормально
	public static final int REMOVE_ERR = 2; // список пуст
	
	public static final int ADD_TO_EMPTY_OK = 1; // последняя add_to_empty() отработала нормально
	public static final int ADD_TO_EMPTY_ERR = 2; // список не пуст
	
	public static final int REPLACE_NIL = 0; // replace() ещё не вызывалась
	public static final int REPLACE_OK = 1; // последняя replace() отработала нормально
	public static final int REPLACE_ERR = 2; // список пуст
	
	public static final int FIND_NIL = 0; // find() ещё не вызывалась
	public static final int FIND_OK = 1; // последняя find() отработала нормально
	public static final int FIND_ERR = 2; // список пуст или элемент не найден
	
	public static final int GET_NIL = 0; // get() ещё не вызывалась
	public static final int GET_OK = 1; // последняя get() отработала нормально
	public static final int GET_ERR = 2; // список пуст
	
	// конструктор
	// постусловие: создан новый пустой связный список
	public LinkedList() {	}
		
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
	
	// предусловие: список пуст
	// постусловие: вставлен новый узел с заданным значением
	public abstract void add_to_empty(T value);
	
	// постусловие: вставлен новый узел в хвост списка
	public abstract void add_tail(T value);
	
	// предусловие: список не пуст
	// постусловие: заменено значение текущего узла на заданное
	public abstract void replace(T value);
	
	// предусловие: значение текущего узла равно value
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
	public abstract int get_add_to_empty_status(); // возвращает значение ADD_TO_EMPTY_*
	public abstract int get_replace_status(); // возвращает значение REPLACE_*
	public abstract int get_find_status(); // возвращает значение FIND_*
	public abstract int get_get_status(); // возвращает значение GET_*	
}

/* 

2.2 Почему операция tail не сводима к другим операциям (если исходить из эффективной реализации)?

В данной реализации операция будет выполнена за O(1). 
Если же использовать операцию right() для достижения установки курсора на последний элемент,
то придется пройтись по всем элементам => сложность O(N).

2.3 Операция поиска всех узлов с заданным значением, выдающая список таких узлов, уже не нужна. Почему?

Операция поиска всех узлов с заданным значением, выдающая список таких узлов, 
сводится к циклу по всем элементам с использованием операций find() и get().

*/

