abstract class BloomFilter<T> {

	// конструктор
	// постусловие: создан пустой фильтр Блюма заданного размера
	public BloomFilter(int len) { }
	
	// команды:
	
	// постусловие: добавлено новое значение
	public abstract void add(T value);
	
	// запросы:
	
	public abstract boolean isValue(T value); // проверка, имеется ли значение в фильтре
	
	public abstract int size(); // размер фильтра

}

class BloomFilterImpl<T> extends BloomFilter<T>{
	
	public int filter_len;
	private int bitFilter;
	
	// конструктор
	public BloomFilterImpl(int len) {
		super(len);
		this.filter_len = len;
		this.bitFilter = 0;
	}
	
	// команды:

	// постусловие: добавлено новое значение
	@Override
	public void add(T value) {
		int bitHash1 = hash1(value);
		int bitHash2 = hash2(value);
		bitFilter = (bitFilter | (1 << bitHash1)) | (bitFilter | (1 << bitHash2));
	}

	// запросы:
	
	// проверка, имеется ли значение в фильтре
	@Override
	public boolean isValue(T value) {
		int bitHash1 = hash1(value);
		int bitHash2 = hash2(value);
		return ((bitFilter & (1 << bitHash1)) != 0) && ((bitFilter & (1 << bitHash2)) != 0);
	}

	// размер фильтра
	@Override
	public int size() {
		return this.filter_len;
	}
    
	private int hash1(T value)
	{
		String string = value.toString();
		int hash = 0;
		for(int i = 0; i < string.length(); i ++)
		{
			int code = (int)string.charAt(i);
			hash = hash * 17 + code;
		}
		return hash % filter_len;
	}
	
	private int hash2(T value)
	{
		String string = value.toString();
		int hash = 0;
		for(int i = 0; i < string.length(); i ++)
		{
			int code = (int)string.charAt(i);
			hash = hash * 223 + code;
		}
		return hash % filter_len;
	}
}
