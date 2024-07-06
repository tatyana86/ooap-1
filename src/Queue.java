import java.util.ArrayList;
import java.util.List;

abstract class Queue<T> {

	public static final int DEQUEUE_OK = 1; // последняя dequeue() вернула корректное значение
	public static final int DEQUEUE_ERR = 2; // очередь пуста
	
	public static final int GET_OK = 1; // последняя get() отработала нормально
	public static final int GET_ERR = 2; // очередь пуста
		
	// конструктор
	// постусловие создана пустая очередь
	public Queue() { }
	
	// команды:
	
	// постусловие: добавлен элемент в хвост очереди
	public abstract void enqueue(T value);
	
	// предусловие: очередь не пуста
	// постусловие: удален элемент из головы очереди
	public abstract void dequeue();
	
	// запросы:
	
	// предусловие: очередь не пуста
	public abstract T get();
	
	public abstract int size(); // размер очереди
	
	// дополнительные запросы:
	public abstract int get_dequeue_status(); // возвращает значение DEQUEUE_*
	public abstract int get_get_status(); // возвращает значение GET_*
}

class QueueImpl<T> extends Queue<T> {

	private List<T> queue; // основное хранилище очередиы
	private int dequeue_status; // статус команды dequeue()
	private int get_status; // статус команды get()
	
	// конструктор
	public QueueImpl() {
		super();
		this.queue = new ArrayList<>();
	}

	// команды:
	
	// постусловие: добавлен элемент в хвост очереди
	@Override
	public void enqueue(T value) {
		queue.add(value);		
	}

	// предусловие: очередь не пуста
	// постусловие: удален элемент из головы очереди
	@Override
	public void dequeue() {
		if(size() == 0) {
			dequeue_status = DEQUEUE_ERR;
			return;
		}
		queue.remove(0);
		dequeue_status = DEQUEUE_OK;
	}
	
	// запросы:

	// предусловие: очередь не пуста
	@Override
	public T get() {
		if(size() == 0) {
			get_status = GET_ERR;
			return null;
		}
		get_status = GET_OK;
		return queue.get(0);
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public int get_dequeue_status() {
		return dequeue_status;
	}

	@Override
	public int get_get_status() {
		return get_status;
	}
}