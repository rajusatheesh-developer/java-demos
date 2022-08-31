#GC

- App Performance
  - Tuning GC
  - Short of rewriting code

- GC Types
  - It varies depends on Java Implementations
     eg: OpenJDK, AzulVM , OpenJ9
  - OpenJDK has following GC's
     - Three production ready collectors
     - Deprecated in Java11 but still widely used in Java8
     - Experimental Collectors ( ideally production ready ) in future releases

- GC Basics
  - In Java Objects created on demand and Frees unused objects by JVM
  - Tuning garbage collectors is far easier and less time consuming
  - Some Object references tracked by counting - LinkedList
  - Some Object references cannot be tracked by counting,
     so JVM periodically search the heap for unused objects.
     It search by using GC Roots (includes thread stacks and system classes) are objects accessible outside of Heap
     Live Objects which are accessible via GC Roots
     Garbage Objects which are not accessible via GC Roots
     GC algorithm scans all objects that are reachable via one of the root objects
  - Memory Compaction : memory must be compacted to prevent memory fragmentation after
    removing unused objects from memory

  - GC Operations
    - Mark - Sweep - Compact
    - Different GC Algorithms will take different approaches for above ops
    - Some will compact if needed , some will compact entire heap at a time, some will compact heap by
      relocating small amount of memory
  - GC is easy if no app threads are running but Java Apps are multithreaded
    - This requires two logical groups of threads
      1) Mutator threads- Performing app logic while app running
      2) GC Threads
    - While GC threads are moving objects around memory it is mandatory
      that app threads should not use Objects
    - Due to this app threads are stopped so its called Pauses and it impacts the app performance
    - Tuning GC means minimizing GC Pauses

 Generational Garbage Collectors
 - Heap divided into regions
    Young region
      Eden + Two survivor spaces
      minor GC/ young GC
      App threads paused/stopped short period of time - trade-off app threads are stopped frequently
      Common GC algorithms have stop-the-world pauses during collection of the young generation.
    Old Region
 - FullGC
   - stop all application threads, find the unused objects, free their memory, and then compact the heap
   - long pauses
 Concurrent Collectors
   - they scan for unused objects can occur without stopping application threads,
     these algorithms are called concurrent collectors
   - low-pause collectors
   - Trade-off is More CPU
   - Java11 - G1 GC is concurrent collector
   - Tuning it is difficult compared to Java11 than earlier releases

 Tuning GC depends on overall performance goals
 Examples:
   REST Server - measuring the response time of individual requests
    The individual requests will be impacted by pause times—and more importantly by long pause times for full GCs. If minimizing the effect of pauses on response times is the goal, a concurrent collector may be more appropriate.
    If the average response time is more important than the outliers (i.e., the 90th%) response time), a nonconcurrent collector may yield better results.
    The benefit of avoiding long pause times with a concurrent collector comes at the expense of extra CPU usage. If your machine lacks the spare CPU cycles needed by a concurrent collector, a nonconcurrent collector may be the better choice.
  Batch application
   Similarly, the choice of garbage collector in a batch application is guided by the following trade-off:
   If enough CPU is available, using the concurrent collector to avoid full GC pauses will allow the job to finish faster.
   If CPU is limited, the extra CPU consumption of the concurrent collector
   will cause the batch job to take more time.

Serial GC:
------------
- Default if app runs on client-class machine (32 bit machine)/Single processor machine

DisableExplicitGC:
------------------------
System.gc() -Bad and Triggers Full GC
it is usually a good idea to force a full GC before taking the heap dump. Most techniques to obtain a heap dump will perform a full GC anyway, but you also can force a full GC in other ways: you can execute jcmd <process id> GC.run,
or you can connect to the JVM using jconsole and click the Perform GC button in the Memory panel.

Remote Method Invocation (RMI), which calls System.gc() every hour as part of its distributed garbage collector
Dsun.rmi.dgc.server.gcInterval=N and -Dsun.rmi.dgc.client.gcInterval=N

If you end up running third-party code that incorrectly calls the System.gc() method, those GCs can be prevented by including -XX:+DisableExplicitGC in the JVM arguments; by default, that flag is false. Applications like Java EE servers often include
this argument to prevent the RMI GC calls from interfering with their operations.

Choose GC:
----------------
The choice of a GC algorithm depends
in part on the hardware available,
in part on what the application looks like,
and in part on the performance goals for the application.
In JDK 11, G1 GC is often the better choice; in JDK 8, the choice will depend on your application.


Tuning GC :
1) Heap size
   Cannot be larger than Physical memory due to swapping/paging issue
   Must DisableExplicitGC includes third party jars
   Parameters
     XX:Xms -Min Size
     XX:Xmx -Max Size
2) Sizing Generations
   - Young generation
     Parameters
      1) XX:NewRatio
      2) XX:NewSize
      3) XX:MaxNewSize
      4) XX:Xmn - Setting NewSize and MaxNewSize
    Initial Young Gen Size = Initial Heap Size / (1 + NewRatio) - Default


   - Old Generation
        Will take leftover space
3) Adaptive Sizing
    Size of Heap,Generations and Survivor Spaces vary during JVM execution to optimize
    as per JVM Performance and Tuning Policies
    It allows JVM to autotune as per GC Algorithm Goals
    Adaptive sizing occurs during GC causes small GC pause time
    Adaptive size can be disabled when we set the Heap min and max sizes to same and NewSize and MaxNewSize to same

    Parameters
     XX:UseAdaptiveSizePolicy=true(default)
     XX:PrintAdaptiveSizePolicy

4) Metaspace
   1) PermGen in older versions
   2) Contains class metadata but Class and Method objects are still loaded in Heap
   3) Tuning Metaspace size is very rare
   4) Sizes : JVM , Min, Max(Unlimited)
      32-bit Client Machine  12
      32-bit Server Machine  16
      64-bit Machine         20.75

    Parameters:
      XX:MetaspaceSize=N
      XX:MaxMetaspaceSize

   5) OuOfmemoryError if metaspace is full so need to find the reason
   6) Resizing Metaspace requires fullGC and is Expensive operation so better to set initial size
      to high value
   7) Java classes also needs to be in full GC,when we deploy apps metaspace
      will discard old class metadata and load new one
   8) Need to set size limit to guard against Classloader leak which occurs by IDE/
      Server creates new classloaders keeping old references so
      leads OutOfMemoryError
      Use Heap dumps to diagnose Classloader leaks
      Use jmap -clstats

5) Controlling Parallelism
   1) Except Serial Collector all other will use multiple threads
      XX:XX:ParallelGCThreads=N

   2) Collection of the young generation when using -XX:+UseParallelGC
      Collection of the old generation when using -XX:+UseParallelGC
      Collection of the young generation when using -XX:+UseG1GC
      Stop-the-world phases of G1 GC (though not full GCs)

     ParallelGCThreads = 8 + ((N - 8) * 5 / 8)


#GC Tools
1) GC LOgs

   JDK8
   XX:PrintGC - Simple Log - disabled default
   verbose:gc - Simple log - disabled default
   XX:PrintGCDetails - Detailed log - disabled default

   XX:PrintGCTimestamps
   XX:PrintGCDateStamps
   XX:loggc: filename - enables Simple Log
   XX:GCFileLogRotation
   XX:NumberOfGCLogFiles
   XX:GCLogFileSize

   Example:
   -Xloggc:gc.log -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation
   -XX:NumberOfGCLogFile=8 -XX:GCLogFileSize=8m


     In JDK 8, the logs will be written this way:
     Start logging to gc.log.0.current.
     When full, rename that to gc.log.0 and start logging to gc.log.1.current.
     When full, rename that to gc.log.1 and start logging to gc.log.2.current.
     When full, rename that to gc.log.2, remove gc.log.0, and start logging to a new gc.log.0.current.



  JDK11
  -Xlog:gc*:file=gc.log:time:filecount=7,filesize=8M

   In JDK 11, the logs will be written this way:
   Start logging to gc.log.
   When that is full, rename it to gc.log.0 and start a new gc.log.
   When that is full, rename it to gc.log.1 and start a new gc.log.
   When that is full, rename it to gc.log.2 and start a new gc.log.
   When that is full, rename it to gc.log.0, removing the old gc.log.0, and start a new gc.log.

  GCeasy and jClarity - Log parsing
  Heap tools - jconsole, jvisulavm,% jstat -gcutil 23461 1000

ALWAYS ENABLE GC ONALL JVMS - Best Practice


Java Memory Issues :
1) Memory Leak
   Analyze heap over a time
   If Memory is not reclaimed in FullGC then increase Heap Size then test against
      If issue not resolved then its memory leak
      Xms =Xmx
  Collect heap data
     GC logs: Excessive GC , Long Pauses GC
     Heap Dumps :
       jcmd,jmap,JConsole,Java MissionControl,-XX:HeapDumpOnOutOfmemoryError
       -XX:GCTimeLimit : 98% - decrease
       -XX:GCHeapFreeLimit : 2% of heap - increase

     Heap Histograms
       Objects of heap
       jcmd
       jmap
       Java Mission Control - Diagnostic commands

  Tools to analyze heap data
   GC LOgs : gceasy,GCViewer
   Heap Dumps : Eclipse MAT
      Leak suspects
      Duplicate classes
      Path to GC Roots
      OQL
      Histograms

  PermSpace/Metaspace:  OutOfmemoryError
     +XX:CpmpressedClassSpace
     GC Logs :

       XX:TraceClassLoading/UnLoading
       jcmd permstat
       jmap -clstats

    HeapDumps
    Makesure that class get unloaded

 Finalize() : OutOfMemoryError
    jconsole
    jmap finalizeinfo
    Heap Dumps

 CodeCache is full. Compiler has been disabled
   CodeCache : memory pool to store compiled code by JIT compiler
   CodeCacheSize maximum to ReservedCodeCacheSize

 Native OutOfmemoryError
   In JVM(Native memory tracker) or Outside JVM(pmap)


Java Performance Tips:
1. Don’t optimize before you know it’s necessary
  First of all, you need to define how fast your application code has to be, e.g., by specifying a maximum response time for
  all API calls or the number of records that you want to import within a specified time frame.
2. Use a profiler to find the real bottleneck
3. Work on the biggest bottleneck first
4. Use StringBuilder to concatenate Strings programmatically
6. Use + to concatenate Strings in in one statement
    eg: Query q = em.createQuery(“SELECT a.id, a.firstName, a.lastName “
        + “FROM Author a “
        + “WHERE a.id = :id”);
7. Use primitives where possible
  primitives won't save in Heap
8. Try to avoid BigInteger and BigDecimal
   BigInteger and BigDecimal require
   much more memory than a simple long or double and slow down all calculations dramatically.
9. Check the current log level first
    // don’t do this
    log.debug(“User [” + userName + “] called method X with [” + i + “]”);

    // do this
    if (log.isDebugEnabled()) {
    log.debug(“User [” + userName + “] called method X with [” + i + “]”);
    }
10.Use Apache Commons StringUtils.replace instead of String.replace in Java<9
11. Cache expensive resources, like database connections
     You can also find other examples in the Java language itself.
     The valueOf method of the Integer class, for example, caches the values between -128 and 127.
2. Avoid regular expressions
   at least cache the Pattern reference instead of compiling it afresh all the time
13. Do not use iterator()
     int size = strings.size();
     for (int i = 0; i < size; i++) {
         String value : strings.get(i);
         // Do something useful here
     }
14.Use primitives and the stack
6. Avoid recursion
7. Use entrySet() for Map
8. Use EnumSet or EnumMap
9. Optimise your hashCode() and equals() methods



1. Healthy saw-tooth pattern
2. Heavy caching pattern
3. Acute memory leak pattern -leads OutOfMemoryError
4. Consecutive Full GC pattern
6. Metaspace Memory problem Pattern
     a. Metaspace region size is under allocated
     b. Memory leak in the Metaspace region.

1. Maximum GC Pause time
2. Avoid setting young gen size
3. Remove old arguments
4. Eliminating String duplicates

G1 GC argument	Description
-XX:MaxGCPauseMillis=200
Sets a maximum pause time value. The default value is 200 milliseconds.
-XX:G1HeapRegionSize=n	Sets the size of a G1 region. The value has to be power of two i.e. 256, 512, 1024,…. It can range from 1MB to 32MB.
-XX:GCTimeRatio=12	Sets the total target time that should be spent on GC vs total time to be spent on processing customer transactions. The actual formula for determining the target GC time is [1 / (1 + GCTimeRatio)]. Default value 12 indicates target GC time to be [1 / (1 + 12)] i.e. 7.69%. It means JVM can spend 7.69% of its time in GC activity and remaining 92.3% should be spent in processing customer activity.
-XX:ParallelGCThreads=n	Sets the number of the Stop-the-world worker threads.
If there are less than or equal to 8 logical processors then sets the value of n to the number of logical processors. Say if your server 5 logical processors then sets n to 5.
If there are more than eight logical processors, set the value of n to approximately 5/8 of the logical processors. This works in most cases except for larger SPARC systems where the value of n can be approximately 5/16 of the logical processors.
-XX:ConcGCThreads=n	Sets the number of parallel marking threads. Sets n to approximately 1/4 of the number of parallel garbage collection threads (ParallelGCThreads).
-XX:InitiatingHeapOccupancyPercent=45	GC marking cycles are triggered when heap’s usage goes beyond this percentage. The default value is 45%.
-XX:G1NewSizePercent=5	Sets the percentage of the heap to use as the minimum for the young generation size. The default value is 5 percent of your Java heap.
-XX:G1MaxNewSizePercent=60	Sets the percentage of the heap size to use as the maximum for young generation size. The default value is 60 percent of your Java heap.
-XX:G1OldCSetRegionThresholdPercent=10
Sets an upper limit on the number of old regions to be collected during a mixed garbage collection cycle. The default is 10 percent of the Java heap.
-XX:G1ReservePercent=10	Sets the percentage of reserve memory to keep free. The default is 10 percent. G1 Garbage collectors will try to keep 10% of the heap to be free always.