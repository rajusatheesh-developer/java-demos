Java ->multi-threaded programming,memory management, profiling
(REST, SOAP), XML
Spring Framework, Spring Boot, Hibernate, JMS
Oracle Database, SQL, PLSQL, transactions
Event Driven Architecture

Profilers
-----------------------
Java VisualVM
- JProfiler 
  - provides interfaces for viewing 
   - system performance,
   - memory usage, 
   - potential memory leaks, 
   - thread profiling.
  - we can use this tool for both local and remote applications.
  - JProfiler also provides advanced profiling for both SQL and NoSQL databases
     - JDBC probing interface with a list of current connections
     - call tree of interactions with our database
     - call tree of interactions with our database
     - Live Memory - allows us to see current memory usage by our application. 
        We can view memory usage for object declarations and instances or for the full call tree.
       In the case of the allocation call tree, we can choose to view the call tree of live objects, 
       garbage-collected objects, or both. We can also decide if this allocation tree should be for a 
       particular class or package or all classes.

- CPU Profiler
 - In Intellij Java Profilers section we can enable
 - Flame Graph 
   - Every rectangle in flame graph is method call
      blue rectangles - Native calls
      yellow rectangles - Java calls
   - It doesnot show time series information
   - It represents call stack and how long that executed or snapshot total time on CPU
   - It lets you find total CPU time spent on your app 
       eg: 29% on methodA() not means it took more time for execution it also mean many times called methodA()
        so optimize it by calling that methods less time
  - Call trees : shows flame graphs in text form
      percentage of time that method took CPU Time
      We can use 'search' option to search for a particular method
      We can see callees and backtraces 
  - Method List : list of methods executed 
  
  
- JFR :
 - When we profile out application with JFR we can see Events
   - JVM Events : Class Loading , OS Events , Garbage Collection 
- We can also attach to process - 'Attach profiler to process'  
- Export profile data from Intellij to file


Memory Leaks :
--------------
  - GC Logging
  - Heap Dumps
  - Heap Histogram
  - GC Viewer
  - Leads fullGC and outofmemory error at some point
 
questions :
  - do i have leak ? with GC Logs viewer : fullGC no memory reclamed
  - what is leaking ? jmap -hist:live processid
     we will take multiple heap histagrams and we will compare the instance count
    if count is more than memory leak because of fullGC objects should reclaimed
  - what is keeping objects alive ?
    take heap dump jmap -dump:live , file=filepath processid
    Eclispe MAT - analayse heap dump
       shallow heap , retained heap
  - where is it leaking ? where objects created / allocated ?
    visualVM - profiler - memory settings ( profile allocations)
       - generations column to be sorted
  Note : Thread dump : jstack PID filepath  ,
          jvisualvm 
            - till jdk8 - /bin folder
            - cpu , memeory , classes , threads
            - heap dump , thread dump , perform GC
            - heapdump (hprof) load in heaphero website
    

ExecutorService :
----------------
- Task types
  - CPU Intensive : CPU Core Count=NoOfThreads
  - IO Intensive Task:  More Threads
- Queue Types
   - Blocking queue
   - Synchronous queue - can hold one task 
- Executors Types 
  - Fixed Thread Pool : Blocking Queue
  - Cached Thread Pool : Synchronous Queue
  - Scheduled Thread Pool : DelayedWorkQueue    
