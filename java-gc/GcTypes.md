## GC Types

# Tuning
 - Tenuring threshold
    - Tenuring Survivor Spaces 
        - a limit to the number of GC cycles during which an object can remain in the survivor spaces
         ````````````````
         -XX:InitialSurvivorRatio=N
         survivor_space_size = new_size / (initial_survivor_ratio + 2)
         -XX:MinSurvivorRatio=N
         maximum_survivor_space_size = new_size / (min_survivor_ratio + 2)
         UseAdaptiveSizePolicy
         -XX:TargetSurvivorRatio=N flag
         
         -XX:InitialTenuringThreshold=N ( CMS 6 , Throughput and G1 GC 7 )
         -XX:MaxTenuringThreshold=N ( CMS 6 , Throughput and G1 GC 15 )
         
         -XX:+AlwaysTenure (by default, false) is same as MaxTenuringThreshold to 0
         -XX:+NeverTenure (also false by default)
         
         Tenuring logs 
         -XX:+PrintTenuringDistribution (which is false by default) 
         In JDK 11, it is added by including age*=debug or age*=trace to the Xlog argument.
         `````````````````
        - Default space is 8 and it occupies 10% of yound generation
    
- TLAB and Its Sizing
   - By default, the size of a TLAB is based on three factors: the number of threads in the application, the size of eden, and the allocation rate of threads.
   - By default, TLABs are enabled; they can be disabled by specifying 
     ```````````
     -XX:-UseTLAB
     -XX:+PrintTLAB
     -XX:TLABSize=N
     -XX:-ResizeTLAB (the default for that flag is true)
     `````````
- G1 GC region sizes
- G1 GC Homongous objects    

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
