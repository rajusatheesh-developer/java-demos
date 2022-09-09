# Interview questions
## Java 
 - OOPS
    Abstraction, Encapsulation,Aggregation,Composition
    Diamond Problem
    Inheritance - Multiple, Multilevel
 - Collections
 
## JMS 
 - Parts 
    Session,Connection,ConnectionFactory,Producer,Consumer
## Hibernete 
- Improve performance of hibernete application
   - Enable Hibernete statistics
   - Optimize slow queries - Analyze execution plans , use Native queries
   - N+1 Problem - use fetch join
   - Let database handle heavy operations - functions , stored procedure
   - Use caches for data which reading multiple times
   - Use Bulk updates and inserts , deletes 
   - Use query specific fetching 
   - persist() ,merge() , save(), update()
     managed - find,get,load,all queries
     transient (persist(),merge())-> managed ^(Detached) -> flush->db
   - implement optimistic locks ? use version , Entity graphs
   - Caching 
      first level - session
      second level - optional , ENCache
      query level cache - query result cache
     
# Event Driven Arch
 - Scalable, resilient, message driven , responsive , maintainable,extensible
 - DataLake,KafkaStreams,EventBackbone, EventSourcing,EventSnapshot

## Database
 - DML,DDL
 - Primary key vs Unique Key
 - Joins Vs Union
 - Views , Materialized Views
 - Types of Joins
 - Too many Indexs affect performance - too much space and query optimzer takes more time
 - Isolation level : Read Committed , Snapshot,Serializable , Read Uncommitted
 - Innre join vs Outer Join
 - Self Join
 - WITH Clause
 - Bitmap Index - bitmap or bit array
 - Subqueries  ; Single Row,MultiRow,Single column,Multiple coulmn, Nested , Correlated
 - IN vs Exists - subquery larger than use EXISTS
 - Partitioning : Range,List,Composite , Hash

# Database Performance
 - Optimize queries
 - Allocate more memory
 - Defragment data
 - Index optimization
 - Disk Types : SSD
 