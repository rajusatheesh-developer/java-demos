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
- A second feature of the programming model lies in error handling. The traditional model of sessions and database transactions provides a helpful error handling capability. Should anything go wrong, it's easy to throw away everything that happened so far in the interaction. Session data is transient, and can be discarded, at the cost of some irritation to the user if in the middle of something complicated. If an error occurs on the database side you can rollback the transaction.

LMAX's in-memory structures are persistent across input events, so if there is an error it's important to not leave that memory in an inconsistent state. However there's no automated rollback facility. As a consequence the LMAX team puts a lot of attention into ensuring the input events are fully valid before doing any mutation of the in-memory persistent state. They have found that testing is a key tool in flushing out these kinds of problems before going into production.

One of the dominant factors with modern CPUs that affects latency, is how the CPU interacts with memory. These days going to main memory is a very slow operation in CPU-terms. CPUs have multiple levels of cache, each of which of is significantly faster. So to increase speed you want to get your code and data in those caches.
