# NO GC Coding Tips

## Singleton objects
## Flyweight Pattern 
## Threads
  - Threads and Hardware
     - Doubling cores can improve performance
     - Doubling hyperthreading cannot improve performance
   - ThreadPoolSize
   - ForkJoinPool
   - Synchronization vs Lock
   - Monitoring Locks
   - Avoiding Synchronization
      - ThreadLocal
      - Immutable Objects
      - Using Locks
## Cahing 
   - Object Reuse
## Strings
- String Concatenation : use String Builder 
- 8-bit byte strings :
  - Compact strings ( similar to Compressed strings but impl is different)
  - In Java 8, all strings are encoded as arrays of 16-bit characters, regardless of the encoding of the string
  - In Java 11, strings are encoded as arrays of 8-bit bytes unless they explicitly need 16-bit characters; these strings are known as compact strings
  - -XX:+CompactStrings flag, which is true by default
- Duplicate Strings and String Interning
  - Heap analysis is required to find the duplicate string objects
  - The duplicate strings can be removed in three ways:
     - Performing automatic deduplication via G1 GC
       ``````
       -XX:+UseStringDeduplication flag (which by default is false)
       -XX:+PrintStringDeduplicationStatistics - Java8 / Xlog:gc+stringdedup*=debug - Java11
       tenured string is eligible for collection is controlled via the -XX:StringDeduplicationAgeThreshold=N flag, which has a default value of 3
       
       ```````
     - Using the intern() method of the String class to create the canonical version of the string
        - The typical way to handle duplicate strings at a programmatic level is to use the intern() method of the String class
        - Interned strings are held in a special hash table that is in native memory (though the strings themselves are in the heap).
        - The size of this table can be set when the JVM starts by using the flag -XX:StringTableSize=N (which defaults to 1,009, 60,013, or 65,536 as previously 
          mentioned). If an application will intern a lot of strings, this number should be increased. The string intern table will operate most efficiently if that           value is a prime number.
     - Using a custom method to create a canonical version of the string
## Buffered I/O
 - Issues around buffered I/O are common because of the default implementation of the simple input and output stream classes.
 - I/O must be properly buffered for files and sockets, as well as for internal operations like compression and string encoding.
## Class Loading
 - The performance of classloading is the bane of anyone attempting to optimize either program startup or deployment of new code in a dynamic system.
 - The best way to speed up classloading is to create a class data sharing archive for the application. Luckily, this requires no programming changes.
 - Class Data Sharing :  A new feature of Java 11 to speed up classloading
    - useful for single JVMs because it can also improve their startup time
    - useful for Multiple VMs because it saves memory
    - Two types 
        - Regular CDS
        - Application classes sharing
    - Steps to create Archive
       - All classes that are to be shared
       - Create archive
         `````````
         -XX:+DumpLoadedClassList=filename
         $ java -Xshare:dump -XX:SharedClassListFile=filename \
         -XX:SharedArchiveFile=myclasses.jsa \
         ... classpath arguments ...
         $ java -Xshare:auto -XX:SharedArchiveFile=myclasses.jsa ... other args ...
         -Xshare:on
         -Xlog:class+load=info
         ```````
## Random Numbers
  - Java’s default Random class is expensive to initialize, but once initialized, it can be reused.
  - In multithreaded code, the ThreadLocalRandom class is preferred.
  - Sometimes, the SecureRandom class will show arbitrary, completely random performance. Performance tests on code using that class must be carefully planned.
  - Issues with the SecureRandom class blocking can be avoided with configuration changes, but it is better to solve them at the OS level by adding entropy to the   
    system.
## JNI
 - JNI is not a solution to performance problems. Java code will almost always run faster than calling into native code.
 - When JNI is used, limit the number of calls from Java to C; crossing the JNI boundary is expensive.
 - JNI code that uses arrays or strings must pin those objects; limit the length of time they are pinned so that the garbage collector is not impacted.
## Exceptions
 - Exceptions are not necessarily expensive to process, though they should be used only when appropriate
 - The deeper the stack, the more expensive to process exceptions.
 - The JVM will optimize away the stack penalty for frequently created system exceptions.
 - Disabling stack traces in exceptions can sometimes help performance, though crucial information is often lost in the process.
## Collections
- Synchronized vs Non-Synchronized
- Collections and Memory Effciency
  - HashMap is faster when key based lookup
  - ArrayList is faster when index based lookup
  - LinkedList is faster for inserting data
- Sizing collections
   Sizing of collections can have a large impact on performance: either slowing down the garbage collector if the collection is too large or causing lots of copying    and resizing if it is too small.
-Primitive Collections : Trave etc   
## Logs ( Guarding Memory allocation)
    - Always check logger is enabled or not
    - Log neccessary information and avoid unnessary information
## Enumerating Constants : Avoid Enum.values() method
## Stream and Filter Performance
  - Streams are performance wise better because of parallelism
  - Streams are Lazy Traversal 
## Object Serialization
  - Key classes are Serializable , Externalizable
  - Transient Fields : Makes serilization less cost
  - Compressed Objects
  - Override default Serialization
    
    
