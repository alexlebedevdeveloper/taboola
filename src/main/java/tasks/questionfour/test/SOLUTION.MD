Generic local cache solution.
Features : 
1) On server start is empty
2) Once up - lazy loading all keys possible (if usage may occur any moment and loading is heavy)
otherwise - persists empty unless asked for a key
3) Once a key asked - loading from external resource ( DB / Context/ external API/ etc)

Options can be set on setup
1) Lazy load enabled/ disabled on server start
2) If key not ready, return dummy value in order not to make user wait and continue loading in backend
3) LRU on how many keys we want to keep
4) TTL on keys reload / expiration
  
public class LRUCache<K, V> implements Cache<K, V> {
    private int size;
    private final Map<K, LinkedListNode<CacheElement<K,V>>> linkedListNodeMap;
    private final DoublyLinkedList<CacheElement<K,V>> doublyLinkedList;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUCache(int size) {
        this.size = size;
        this.linkedListNodeMap = new ConcurrentHashMap<>(size);
        this.doublyLinkedList = new DoublyLinkedList<>();
    }
// ...
}

public boolean put(K key, V value) {
  this.lock.writeLock().lock();
   try {
         CacheElement<K, V> item = new CacheElement<K, V>(key, value);
         LinkedListNode<CacheElement<K, V>> newNode;
         if (this.linkedListNodeMap.containsKey(key)) {
             LinkedListNode<CacheElement<K, V>> node = this.linkedListNodeMap.get(key);
             newNode = doublyLinkedList.updateAndMoveToFront(node, item);
         } else {
             if (this.size() >= this.size) {
                 this.evictElement();
             }
             newNode = this.doublyLinkedList.add(item);
         }
         if(newNode.isEmpty()) {
             return false;
         }
         this.linkedListNodeMap.put(key, newNode);
         return true;
   } finally {
       this.lock.writeLock().unlock();
   }
}

public V removeElement(K key){
 this.lock.writeLock().lock();
    try {
    V v=null;
    if(this.linkedListNodeMap.contains(key)){
    v=this.linkedListNodeMap.remove(key);
        this.doublyLinkedList.remove(key);
    }

    return v;
 } finally {
       this.lock.writeLock().unlock();
   }
}


private boolean evictElement() {
    this.lock.writeLock().lock();
    try {
        //...
    } finally {
        this.lock.writeLock().unlock();
    }
}

public Optional<V> get(K key) {
    this.lock.readLock().lock();
    try {
       LinkedListNode<CacheElement<K, V>> linkedListNode = this.linkedListNodeMap.get(key);
       if(linkedListNode != null && !linkedListNode.isEmpty()) {
           linkedListNodeMap.put(key, this.doublyLinkedList.moveToFront(linkedListNode));
           return Optional.of(linkedListNode.getElement().getValue());
       }
      return Optional.empty();
    } finally {
        this.lock.readLock().unlock();
    }
}