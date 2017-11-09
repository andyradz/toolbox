package com.codigo.aplios.toolbox.core.data;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// https://dzone.com/articles/letting-garbage-collector-do-c
public class GarbageCollectionConcurrentMap<K, V> {

	public static void main(String[] args) throws InterruptedException {

		String savePoint = new String("Random"); // a strong object

		ReferenceQueue<String> savepointQ = new ReferenceQueue<>();// the ReferenceQueue
		WeakReference<String> savePointWRefernce = new WeakReference<>(savePoint, savepointQ);

		System.out.println("SavePoint created as a weak ref " + savePointWRefernce);
		Runtime.getRuntime()
			.gc();
		System.out.println("Any weak references in Q ? " + (savepointQ.poll() != null));
		savePoint = null; // the only strong reference has been removed. The heap
		// object is now only weakly reachable

		System.out.println("Now to call gc...");
		Runtime.getRuntime()
			.gc(); // the object will be cleared here - finalize will be called.

		System.out.println("Any weak references in Q ? " + (savepointQ.remove() != null));
		System.out
			.println("Does the weak reference still hold the heap object ? " + (savePointWRefernce.get() != null));
		System.out.println("Is the weak reference added to the ReferenceQ  ? " + (savePointWRefernce.isEnqueued()));

	}

	public static void main1(String[] args) {

		GarbageCollectionConcurrentMap<Long, String> glon = new GarbageCollectionConcurrentMap<>();
		for (long idx = 0; idx < 240000; idx++)
			glon.put(idx, "12123122312", new String("4322312312321423423423"));

	}

	private final static ReferenceQueue<?> referenceQueue = new ReferenceQueue<>();

	static {
		new CleanupThread().start();
	}

	private final ConcurrentMap<K, GarbageReference<K, V>> map = new ConcurrentHashMap<>();

	public void clear() {

		this.map.clear();
	}

	public V get(K key) {

		GarbageReference<K, V> ref = this.map.get(key);
		return ref == null ? null
				: ref.value;
	}

	public Object getGarbageObject(K key) {

		GarbageReference<K, V> ref = this.map.get(key);
		return ref == null ? null
				: ref.get();
	}

	public Collection<K> keySet() {

		return this.map.keySet();
	}

	public void put(K key, V value, Object garbageObject) {

		if ((key == null) || (value == null) || (garbageObject == null))
			throw new NullPointerException();
		if (key == garbageObject)
			throw new IllegalArgumentException("key can't be equal to garbageObject for gc to work");
		if (value == garbageObject)
			throw new IllegalArgumentException("value can't be equal to garbageObject for gc to work");

		GarbageReference<K, V> reference = new GarbageReference(garbageObject, key, value, this.map);
		this.map.put(key, reference);
	}

	static class GarbageReference<K, V> extends WeakReference {

		final K key;
		final V value;
		final ConcurrentMap<K, V> map;

		GarbageReference(Object referent, K key, V value, ConcurrentMap<K, V> map) {

			super(referent, GarbageCollectionConcurrentMap.referenceQueue);
			this.key = key;
			this.value = value;
			this.map = map;
		}
	}

	static class CleanupThread extends Thread {

		CleanupThread() {

			this.setPriority(Thread.MAX_PRIORITY);
			this.setName("GarbageCollectingConcurrentMap-cleanupthread");
			this.setDaemon(true);
		}

		@Override
		public void run() {

			while (true)
				try {
					GarbageReference ref = (GarbageReference) GarbageCollectionConcurrentMap.referenceQueue.remove();
					while (true) {
						System.out.println(ref.key);
						ref.map.remove(ref.key);
						ref = (GarbageReference) GarbageCollectionConcurrentMap.referenceQueue.remove();
					}
				} catch (InterruptedException e) {
					// ignore
				}
		}
	}
}
