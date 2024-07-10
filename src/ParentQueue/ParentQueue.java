package ParentQueue;

import java.util.ArrayList;
import java.util.List;

abstract class ParentQueue<T> {
	
	public static final int REMOVE_FRONT_OK = 1; // последняя remove_front() вернула корректное значение
	public static final int REMOVE_FRONT_ERR = 2; // очередь пуста
	
	public static final int GET_FRONT_OK = 1; // последняя get_front() отработала нормально
	public static final int GET_FRONT_ERR = 2; // очередь пуста

	// конструктор
	// постусловие создана пустая двухсторонняя очередь
	public ParentQueue() { }
	
	// команды:
	
	// постусловие: добавлен элемент в хвост очереди
	public abstract void add_tail(T value);
	
	// предусловие: очередь не пуста
	// постусловие: удален элемент из головы очереди
	public abstract void remove_front();
	
	// запросы:
	
	// предусловие: очередь не пуста
	public abstract T get_front();
	
	public abstract int size(); // размер очереди
	
	// дополнительные запросы:
	public abstract int get_remove_front_status(); // возвращает значение REMOVE_FRONT_*
	public abstract int get_get_front_status(); // возвращает значение GET_FRONT_*
	
}

abstract class Queue<T> extends ParentQueue<T> {
	// конструктор
	public Queue() {
		super();
	} 
}

abstract class Deque<T> extends ParentQueue<T> {
	
	public static final int REMOVE_TAIL_OK = 1; // последняя remove_tail() вернула корректное значение
	public static final int REMOVE_TAIL_ERR = 2; // очередь пуста
	
	public static final int GET_TAIL_OK = 1; // последняя get_tail() отработала нормально
	public static final int GET_TAIL_ERR = 2; // очередь пуста

	// конструктор
	public Deque() {
		super();
	} 
		
	// команды:
	
	// постусловие: добавлен элемент в голову очереди
	public abstract void add_front(T value); 

	// предусловие: очередь не пуста
	// постусловие: удален элемент из хвоста очереди
	public abstract void remove_tail(); 

	// запросы:
	
	// предусловие: очередь не пуста
	public abstract T get_tail();

	//public abstract int size(); // размер очереди

	// дополнительные запросы:
	public abstract int get_remove_tail_status(); // возвращает значение REMOVE_TAIL_*
	public abstract int get_get_tail_status(); // возвращает значение GET_TAIL_*

}

class ParentQueueImpl<T> extends ParentQueue<T> {

	private List<T> queue; // основное хранилище очередиы
	private int remove_front_status; // статус команды remove_front()
	private int get_front_status; // статус команды get_front()
	
	// конструктор
	public ParentQueueImpl() {
		super();
		this.queue = new ArrayList<>();
	}

	// команды:
	
	// постусловие: добавлен элемент в хвост очереди
	@Override
	public void add_tail(T value) {
		queue.add(value);	
	}

	// предусловие: очередь не пуста
	// постусловие: удален элемент из головы очереди
	@Override
	public void remove_front() {
		if(size() == 0) {
			remove_front_status = REMOVE_FRONT_ERR;
			return;
		}
		queue.remove(0);
		remove_front_status = REMOVE_FRONT_OK;
	}
	
	// запросы:

	// предусловие: очередь не пуста
	@Override
	public T get_front() {
		if(size() == 0) {
			get_front_status = GET_FRONT_ERR;
			return null;
		}
		get_front_status = GET_FRONT_OK;
		return queue.get(0);
	}

	@Override
	public int size() {
		return queue.size();
	}
	
	// дополнительные запросы:

	@Override
	public int get_remove_front_status() {
		return remove_front_status;
	}

	@Override
	public int get_get_front_status() {
		return get_front_status;
	}
	
}

class QueueImpl<T> extends ParentQueueImpl<T> {
	public QueueImpl() {
		super();
	}
}

class DequeImpl<T> extends ParentQueueImpl<T> {
	private final int REMOVE_TAIL_OK = 1; // последняя remove_tail() вернула корректное значение
	private final int REMOVE_TAIL_ERR = 2; // очередь пуста
	
	private final int GET_TAIL_OK = 1; // последняя get_tail() отработала нормально
	private final int GET_TAIL_ERR = 2; // очередь пуста
	
	// скрытые поля
	private int remove_tail_status; // статус команды remove_tail()
	private int get_tail_status; // статус команды get_tail()
	private List<T> deque; // основное хранилище очереди
	
	// конструктор
	public DequeImpl() {
		super();
		this.deque = new ArrayList<>();
	}
	
	// команды:
	
	// постусловие: добавлен элемент в голову очереди
	public  void add_front(T value) {
		deque.add(0, value);
	}
	
	// предусловие: очередь не пуста
	// постусловие: удален элемент из хвоста очереди
	public void remove_tail() {
		if(size() == 0) {
			remove_tail_status = REMOVE_TAIL_ERR;
			return;
		}
		deque.remove(size() - 1);
		remove_tail_status = REMOVE_TAIL_OK;
	}

	// запросы:
	
	// предусловие: очередь не пуста
	public T get_tail() {
		if(size() == 0) {
			get_tail_status = GET_TAIL_ERR;
			return null;
		}
		get_tail_status = GET_TAIL_OK;
		return deque.get(size() - 1);
	}

	@Override
	public int size() {
		return deque.size();
	}

	// дополнительные запросы:

	public int get_remove_tail_status() {
		return remove_tail_status;
	}

	public int get_get_tail_status() {
		return get_tail_status;
	}

}

