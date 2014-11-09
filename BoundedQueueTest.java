  
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
 
public class BoundedQueueTest {
 
    BoundedQueue<Integer> bQ;
    BoundedQueue<Integer> bQ1;
 
    @Before
    public void createStack() throws BoundedQueue.SizeException {
      bQ = new BoundedQueue<Integer>(5);
      bQ1 = new BoundedQueue<Integer>(1000);
    }

   @Test(expected=BoundedQueue.SizeException.class)
    public void cantMakeNegativeSizeQueue() throws BoundedQueue.SizeException {
      BoundedQueue<Integer> b = new BoundedQueue<Integer>(-1);
    }

    @Test(expected=BoundedQueue.SizeException.class)
    public void cantMakeZeroSizeQueue() throws BoundedQueue.SizeException {
      BoundedQueue<Integer> b = new BoundedQueue<Integer>(0);
    }
 
    @Test
    public void enqueue() throws BoundedQueue.FullQueueException {
      int i = 0;
      for (i = 1; i < 5; i++) {
        bQ.enqueue(i);
      }
      assertEquals(4, bQ.getSize());
    }

    @Test(expected=BoundedQueue.FullQueueException.class)
    public void cantEnqueueToFullQueue() throws BoundedQueue.FullQueueException {
      for (int i = 0; i < 10002; i++) {
        bQ1.enqueue(i);
      }
    } 

    @Test
    public void dequeue() throws BoundedQueue.FullQueueException, BoundedQueue.EmptyQueueException {
      int i;
      bQ.enqueue(1);
      bQ.enqueue(2);
      bQ.enqueue(3);
      assertEquals(1, bQ.dequeue());
    } 

     @Test
    public void dequeueLots() throws BoundedQueue.FullQueueException, BoundedQueue.EmptyQueueException {
      int i;
      for (i = 1; i < 1000; i++) {
        bQ1.enqueue(i);
      }
      for (i = 9999; i < 0; i--) {
        assertEquals(i, bQ.dequeue());
      } 
    }

    @Test(expected=BoundedQueue.EmptyQueueException.class)
    public void cantDequeueFromEmpty() throws BoundedQueue.EmptyQueueException {
      bQ.dequeue();
    } 

    @Test(expected=BoundedQueue.EmptyQueueException.class)
    public void cantDequeueAfterTooManyDequeues() throws BoundedQueue.FullQueueException, BoundedQueue.EmptyQueueException {
      for (int i = 0; i < 1000; i++) {
        bQ1.enqueue(i);
      }
      for (int i = 0; i < 10001; i++) {
        bQ1.dequeue();
      }
    } 
}
 