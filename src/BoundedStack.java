import java.util.ArrayList;
import java.util.List;

abstract class BoundedStack<T> {
	
	public static final int POP_NIL = 0; // push() ещё не вызывалась
	public static final int POP_OK = 1; // последняя pop() отработала нормально
	public static final int POP_ERR = 2; // стек пуст
	
	public static final int PEEK_NIL = 0; // push() ещё не вызывалась
	public static final int PEEK_OK = 1; // последняя peek() вернула корректное значение
	public static final int PEEK_ERR = 2; // стек пуст
	
	public static final int PUSH_NIL = 0; // push() ещё не вызывалась
	public static final int PUSH_OK = 1; // последняя push() отработала нормально
	public static final int PUSH_ERR = 2; // стек заполнен
	
	// конструктор
	// постусловие: создан новый пустой стек, кол-во элементов которого ограничено maxSize
	public BoundedStack(int maxSize) {} 
	
	// команды:
	
	// предусловие: размер стека менее maxSize элементов;
	// постусловие: в стек добавлено новое значение
	public abstract void push(T value);
	
	// предусловие: стек не пустой;
	// постусловие: из стека удалён верхний элемент
	public abstract void pop();
	
	// постусловие: из стека удалятся все значения
	public abstract void clear();
	
	// запросы:
	
	// предусловие: стек не пустой
	public abstract T peek();
	
	public abstract int size();
	
	// дополнительные запросы:
	public abstract int get_pop_status(); // возвращает значение POP_*
	public abstract int get_peek_status(); // возвращает значение PEEK_*
	public abstract int get_push_status(); // возвращает значение PUSH_*
}

class BoundedStackImpl<T> extends BoundedStack<T> {

	private static final int MAX_SIZE = 32; // максимальный размер стека по умолчанию
	
	// скрытые поля
	private List<T> stack; // основное хранилище стека
	private int peek_status; // статус запроса peek()
	private int pop_status; // статус команды pop()
	private int push_status; // статус команды pop()
	private static int maxSize = MAX_SIZE; // максимальный размер стека
	
	// конструктор с заданным размером стека
	public BoundedStackImpl(int maxSize) {
		super(maxSize);
		this.maxSize = maxSize;
	}
	
	// конструктор с размером стека по умолчанию
	public BoundedStackImpl() {
		super(MAX_SIZE);
	}

	// команды:
	
	// предусловие: размер стека менее maxSize элементов;
	// постусловие: в стек добавлено новое значение
	@Override
	public void push(T value) {
		if(size() < maxSize) {
			stack.add(value);
			push_status = PUSH_OK;
		} else {
			push_status = PUSH_ERR;
		}
		
	}

	// предусловие: стек не пустой;
	// постусловие: из стека удалён верхний элемент
	@Override
	public void pop() {
		if(size() > 0) {
			stack.remove(size() -1);
			pop_status = POP_OK;
		} else {
			pop_status = POP_ERR;
		}
		
	}

	// постусловие: из стека удалятся все значения
	@Override
	public void clear() {
		stack = new ArrayList<>(maxSize); // пустой список/стек
		
		// начальные статусы для предусловий push(), peek() и pop()
		push_status = PUSH_NIL;
		peek_status = PEEK_NIL;
		pop_status = POP_NIL;
	}

	// запросы:
	
	// предусловие: стек не пустой
	@Override
	public T peek() {
		T result;
		
		if(size() > 0) {
			result = stack.get(size() - 1);
			peek_status = PEEK_OK;
		} else {
			result = null;
			peek_status = PEEK_ERR;
		}
		
		return result;
	}

	@Override
	public int size() {
		return stack.size();
	}

	// дополнительные запросы:
	
	@Override
	public int get_pop_status() {
		return pop_status;
	}

	@Override
	public int get_peek_status() {
		return peek_status;
	}

	@Override
	public int get_push_status() {
		return push_status;
	}
	
}