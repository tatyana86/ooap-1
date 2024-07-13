abstract class PowerSet<T> extends HashTable<T> {
	// конструктор
	// постусловие: создано пусто множество заданного размера
	public PowerSet(int sz) {
		super(sz);
	}
	
	// запросы:
	
	// возвращает пересечение исходного и set множеств
	public abstract PowerSet<T> intersection(PowerSet<T> set);
	
	// возвращает объединение исходного и set множеств
	public abstract PowerSet<T> union(PowerSet<T> set);
	
	// возвращает разницу исходного и set множеств
	public abstract PowerSet<T> difference(PowerSet<T> set);

	// проверка, является ли set подмножеством исходного множества
	public abstract boolean isSubset(PowerSet<T> set);
}

class PowerSetImpl<T> extends HashTableImpl<T> {

	// конструктор
	public PowerSetImpl(int size) {
		super(size);
	}
	
	// запросы:
	
	// возвращает пересечение исходного и set множеств
	public PowerSetImpl<T> intersection(PowerSetImpl<T> set) {
		PowerSetImpl<T> newSet = new PowerSetImpl<>(this.capacity);
		for(T slot : this.slots) {
			if (slot != null && set.isContain(slot))
				newSet.put(slot);
		}
		return newSet;
	}

	// возвращает объединение исходного и set множеств
	public PowerSetImpl<T> union(PowerSetImpl<T> set) {
		PowerSetImpl<T> newSet = new PowerSetImpl<>(this.capacity + set.capacity);
		for(T slot : this.slots) {
			if (slot != null && !set.isContain(slot))
				newSet.put(slot);
		}
		for(T slot : set.slots) {
			if (slot != null && !this.isContain(slot))
				newSet.put(slot);
		}
		return newSet;
	}

	// возвращает разницу исходного и set множеств
	public PowerSetImpl<T> difference(PowerSetImpl<T> set){
		PowerSetImpl<T> newSet = new PowerSetImpl<>(this.capacity);
		for (T slot : this.slots) {
			if(slot != null && !set.isContain(slot))
				newSet.put(slot);
		}
		return newSet;
	}

	// проверка, является ли set подмножеством исходного множества
	public boolean isSubset(PowerSetImpl<T> set) {
		for(T slot : set.slots) {
			if(slot != null && !this.isContain(slot)) {
				return false;
			}
		}
		
		return true;
	}
}