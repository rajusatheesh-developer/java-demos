## LMAX
- writing concurrent code is very hard. Locks and semaphores are hard to reason about and hard to test - meaning we are spending more time worrying about satisfying the computer than we are solving the domain problem
- Various concurrency models, such as Actors and Software Transactional Memory, aim to make this easier - but there is still a burden that introduces bugs and complexity.
- all the business logic for their platform: all trades, from all customers, in all markets - on a single thread
- At a top level, the architecture has three parts
  - business logic processor - single thread and 
     - Event sourcing system
         - Diagnostics easily
         - Custom implementations of the java collections that were designed to be cache-friendly and careful with garbage
     - Taking snapshots during night time and rebuilding is fast and easy
     - Multiple instances of Business Logic processor
  - input disruptor - concurrent model
  - output disruptors - concurrent model
  - LMAX team has also found that writing tests first is a very effective discipline for performance tests


# Programming Model
- Slow I/O calls delegate to another concurrent service using events
- com.demos.java.absics.A second feature of the programming model lies in error handling. The traditional model of sessions and database transactions provides a helpful error handling capability. Should anything go wrong, it's easy to throw away everything that happened so far in the interaction. Session data is transient, and can be discarded, at the cost of some irritation to the user if in the middle of something complicated. If an error occurs on the database side you can rollback the transaction.

LMAX's in-memory structures are persistent across input events, so if there is an error it's important to not leave that memory in an inconsistent state. However there's no automated rollback facility. As a consequence the LMAX team puts a lot of attention into ensuring the input events are fully valid before doing any mutation of the in-memory persistent state. They have found that testing is a key tool in flushing out these kinds of problems before going into production.

One of the dominant factors with modern CPUs that affects latency, is how the CPU interacts with memory. These days going to main memory is a very slow operation in CPU-terms. CPUs have multiple levels of cache, each of which of is significantly faster. So to increase speed you want to get your code and data in those caches.

The explanation runs like this: in order to put some data on a queue, you need to write to that queue. Similarly, to take data off the queue, you need to write to the queue to perform the removal. This is write contention - more than one client may need to write to the same data structure. To deal with the write contention a queue often uses locks. But if a lock is used, that can cause a context switch to the kernel. When this happens the processor involved is likely to lose the data in its caches.

An essential part of the path to the LMAX architecture was the use of performance testing.
Testing a low level concurrency component is meaningless unless you take into account the caching behavior of the CPU.

One particular lesson is the importance of writing tests against null components to ensure the performance test is fast enough to really measure what real components are doing. Writing fast test code is no easier than writing fast production code and it's too easy to get false results because the test isn't as fast as the component it's trying to measure.


One characteristic is that this is a connected domain where processing one transaction always has the potential to change how following ones are processed. With transactions that are more independent of each other, there's less need to coordinate, so using separate processors running in parallel becomes more attractive.
