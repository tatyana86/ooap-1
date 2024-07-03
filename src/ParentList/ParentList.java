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
	
	public static final int ADD_TAIL_OK = 1; // первый узел добавлен в список
	public static final int ADD_TAIL_ERR = 2; // список пуст
		
	public static final int REPLACE_OK = 1; // последняя replace() отработала нормально
	public static final int REPLACE_ERR = 2; // список пуст
	
	public static final int FIND_OK = 1; // последняя find() отработала нормально, элемент найден
	public static final int FIND_ERR = 2; // список пуст 
	public static final int FIND_NOT_FOUND = 3; // элемент не найден
	
	public static final int REMOVE_ALL_OK = 1; // последняя remove_all() отработала нормально
	public static final int REMOVE_ALL_ERR = 2; // список пуст
	
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
			
	// постусловие: вставлен новый узел в хвост списка
	public abstract void add_tail(T value);
	
	// предусловие: список не пуст
	// постусловие: заменено значение текущего узла на заданное
	public abstract void replace(T value);
	
	// постусловие: курсор установлен на узел со значением value
	public abstract void find(T value);
	
	// предусловие: список не пуст
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
	public abstract int get_add_tail_status(); // возвращает значение ADD_TAIL_*
	public abstract int get_replace_status(); // возвращает значение REPLACE_*
	public abstract int get_find_status(); // возвращает значение FIND_*
	public abstract int get_remove_all_status(); // возвращает значение REMOVE_ALL_*
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

	// скрытые поля
	private int head_status; // статус команды head()
	private int tail_status; // статус команды tail()
	private int right_status; // статус команды right()
	private int put_right_status; // статус команды put_right()
	private int put_left_status; // статус команды put_left()
	private int remove_status; // статус команды remove()
	private int add_tail_status; // статус команды add_tail()
	private int replace_status; // статус команды replace()
	private int find_status; // статус команды find()
	private int remove_all_status; // статус команды remove_all()
	private int get_status; // статус запроса get()
	
	private int size;
	
	protected Dummy<T> head = new Dummy<>(null);
    private Dummy<T> tail = new Dummy<>(null);
    protected Node<T> current = null;
	
	// конструктор	
	public ParentListImpl() {
		super();
		this.head.setNextNode(this.tail);
		this.tail.setPreviousNode(this.head);
	}

	// команды
	
	// предусловие: список не пуст
	// постусловие: курсор установлен на первый узел в списке
	@Override
	public void head() {
		if(this.current == null) {
			head_status = HEAD_ERR;
		} else {
			this.current = this.head.nextNode();
			head_status = HEAD_OK;
		}
	}

	// предусловие: список не пуст
	// постусловие: курсор установлен на последний узел в списке
	@Override
	public void tail() {
		if(this.current == null) {
			tail_status = TAIL_ERR;
		} else {
			this.current = this.tail.previousNode();
			tail_status = TAIL_OK;
		}		
	}

	// предусловие: список не пуст и курсор не указывает на последний элемент в списке
	// постусловие: курсор сдвинут на один узел вправо
	@Override
	public void right() {
		if(this.current == null || this.current == this.tail.previousNode()) {
			right_status = RIGHT_ERR;
		} else {
			this.current = this.current.nextNode();
			right_status = RIGHT_OK;
		}	
	}

	// предусловие: список не пуст
	// постусловие: вставлен новый узел с заданным значением следом за текущим узлом
	@Override
	public void put_right(T value) {
		if(this.current == null) {
			put_right_status = PUT_RIGHT_ERR;
		} else {
			Node<T> newNode = new Node<>(value);
            newNode.setNextNode(this.current.nextNode());
            newNode.setPreviousNode(this.current);
            this.current.nextNode().setPreviousNode(newNode);
            this.current.setNextNode(newNode);
            this.size ++;
            put_right_status = PUT_RIGHT_OK;
		}
	}

	// предусловие: список не пуст
	// постусловие: вставлен новый узел с заданным значением перед текущим узлом
	@Override
	public void put_left(T value) {
		if(this.current == null) {
            put_left_status = PUT_LEFT_ERR;
        } else {
            Node<T> newNode = new Node<>(value);
            newNode.setNextNode(this.current);
            newNode.setPreviousNode(this.current.previousNode());
            this.current.previousNode().setNextNode(newNode);
            this.current.setPreviousNode(newNode);
            this.size ++;
            put_left_status = PUT_LEFT_OK;
        }
	}

	// предусловие: список не пуст
	// постусловие: удален текущий узел, 
	// (курсор смещается к правому соседу, если он есть, 
    // в противном случае курсор смещается к левому соседу, 
    // если он есть)
	@Override
	public void remove() {
        if(this.current == null) {
            remove_status = REMOVE_ERR;
        } else {
            this.current.previousNode().setNextNode(this.current.nextNode());
            this.current.nextNode().setPreviousNode(this.current.previousNode());
            
            if(this.current.nextNode() != this.tail) {
            	this.current = this.current.nextNode();
            } else if(this.current.previousNode() != this.head) {
            	this.current = this.current.previousNode();
            } else {
            	this.current = null;
            }
            
            this.size --;
            remove_status = REMOVE_OK;
        }
	}

	// постусловие: из списка удаляются все узлы
	@Override
	public void clear() {
		this.current = null;
		this.head.setNextNode(this.tail);
		this.tail.setPreviousNode(this.head);
		this.size = 0;
	}

	// постусловие: вставлен новый узел в хвост списка
	@Override
	public void add_tail(T value) {
		if(this.current == null) {
			add_tail_status = ADD_TAIL_ERR;
		} else {
			Node<T> newNode = new Node<>(value);
			
			this.tail.previousNode().setNextNode(newNode);
            newNode.setPreviousNode(this.tail.previousNode());
            newNode.setNextNode(this.tail);
            this.tail.setPreviousNode(newNode);
			
			this.current = newNode;
			this.size ++;
			add_tail_status = ADD_TAIL_OK;
		}
	}

	// предусловие: список не пуст
	// постусловие: заменено значение текущего узла на заданное
	@Override
	public void replace(T value) {
		if(this.current == null) {
			replace_status = REPLACE_ERR;
		} else {
			this.current.setValue(value);
			replace_status = REPLACE_OK;
		}
	}

	// предусловие: список не пуст
	// постусловие: курсор установлен на узел со значением value
	@Override
	public void find(T value) {
		if(this.current == null) {
			find_status = FIND_ERR;
			return;
		} 
		Node<T> node = this.head.nextNode();
		while(node != this.tail) {
			if(node.value == value) {
				find_status = FIND_OK;
				this.current = node;
				return;
			}
			
			node = node.nextNode();
		}
		find_status = FIND_NOT_FOUND; // узел с таким значением не найден, курсор установлен на последнем узле
	}

	// предусловие: список не пуст
	// постусловие: из списка удаляются все узлы со значением value
	@Override
	public void remove_all(T value) {
		if(this.current == null) {
            remove_all_status = REMOVE_ALL_ERR;
            return;
        }
		Node<T> node = this.head.nextNode();
		while(node != this.tail) {
			if(node.value == value) {
				this.current = node;
				remove();
			}
			
			node = node.nextNode();
		}
		remove_all_status = REMOVE_ALL_OK; // нет гарантии, что узлы с таким значением вообще исходно были
	}
	
	// запросы

	// предусловие: список не пуст
	@Override
	public T get() {
        if(this.current == null) {
            get_status = GET_ERR;
            return null;
        } else {
            get_status = GET_OK;
            return this.current.value;
        }
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean is_head() {
		return this.current == this.head.nextNode();
	}

	@Override
	public boolean is_tail() {
		return this.current == this.tail.previousNode();
	}

	@Override
	public boolean is_value() {
		return this.current != null;
	}

	// дополнительные запросы:
	
	@Override
	public int get_head_status() {
		return head_status;
	}

	@Override
	public int get_tail_status() {
		return tail_status;
	}

	@Override
	public int get_right_status() {
		return right_status;
	}

	@Override
	public int get_put_right_status() {
		return put_right_status;
	}

	@Override
	public int get_put_left_status() {
		return put_left_status;
	}

	@Override
	public int get_remove_status() {
		return remove_status;
	}
	
	@Override
	public int get_add_tail_status() {
		return add_tail_status;
	}

	@Override
	public int get_replace_status() {
		return replace_status;
	}

	@Override
	public int get_find_status() {
		return find_status;
	}
	
	@Override
	public int get_remove_all_status() {
		return remove_all_status;
	}

	@Override
	public int get_get_status() {
		return get_status;
	}
	
}

class Node<T>
{
    public T value;
    public Node next;
    public Node prev;

    public Node(T _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
    
    public void setValue(T value){
        this.value = value;
    }
    public Node nextNode(){
        return next;
    }

    public Node previousNode(){
        return prev;
    }
    public void setNextNode(Node nextNode){
        this.next = nextNode;
    }

    public void setPreviousNode(Node previousNode){
        this.next = previousNode;
    }
}

class Dummy<T> extends Node<T> {
    public Dummy(T value) {
        super(value);
    }
}

class LinkedListImpl<T> extends ParentListImpl<T> {
    public LinkedListImpl(){
        super();
    }
}

class TwoWayListImpl<T> extends ParentListImpl<T> {
    private final int LEFT_OK = 1; // последняя left() отработала нормально
    private final int LEFT_ERR = 2; // список пуст или курсор указывает на первый элемент
    
    // скрытые поля
    private int left_status; // статус команды right()

    // конструктор
    protected TwoWayListImpl() {
        super();
    }
    
    // команды:
    
	// предусловие: список не пуст и курсор не указывает на первый элемент в списке
	// постусловие: курсор сдвинут на один узел влево
    public void left() {
        if(this.current == null || this.current == this.head.nextNode()) {
            left_status = LEFT_ERR;
        } else {
            this.current = this.current.previousNode();
            left_status = LEFT_OK;
        }
    }

    // дополнительные запросы:
    
    public int get_left_status() {
        return left_status;
    }
}