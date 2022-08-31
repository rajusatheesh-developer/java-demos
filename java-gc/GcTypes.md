## GC Types

# Epsilon GC : No Collection
 - In Java11 SDK
 - When you use this collector, objects are never freed from the heap, and when the heap fills up, you will get an out-of-memory error.
 - It is in JDK for internal JDK testing
 - It is usefull in following ways
     - Very short-lived programs
     - Programs carefully written to reuse memory and never perform new allocations
 - To use this collector
    ``````````
    -XX:+UnlockExperimentalVMOptions
    -XX:+UseEpsilonGC
    ``````````
 - Use cases : Embedded environment programs   
