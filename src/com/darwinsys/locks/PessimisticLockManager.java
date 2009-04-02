package locks;

/** PessimisticLockManager keeps track of Locks and
* their id's (which would normally be the (compound?)
* primary key of the row being locked).
* There's no expiry notification (indeed, the thread that
* got the lock might not be active when expiry happens),
* so it's crucial that you check isReleased()
* before proceding with the operation that was locked.
* Example:
* PessimisticLockManager<Integer> mgr =
* new PessimisticLockManagerImpl();
* Lock l = mgr.tryLock(123);
* ...
* if (l.isReleased()) {
*   message("Sorry, you took too long");
*   return;
*   }
* ... OK, commit the order... 
* l.release();
* @author Ian Darwin, based on a design by Stephen Neal
* */
public interface PessimisticLockManager<T> {
	
	/** Try to get the lock for the given ID */
	Lock tryLock(T id);
	
	/** Release the given lock. */
	boolean releaseLock(Lock lock);
}