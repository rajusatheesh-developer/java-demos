# DB Tips
`````````````````````````````
Spend time evaluating the best JDBC driver for the application.

The best driver will often vary depending on the specific deployment.
The same application may be better with one JDBC driver in one deployment and a different JDBC driver in a different deployment.

If you have a choice, avoid ODBC and type 1 JDBC drivers.
``````````````````````````

`````````````````````
JDBC Connection Pools :

all JDBC connections come from the server’s connection pool. In a Java SE environment with JPA, most JPA providers will use a connection pool transparently, and you can configure the connection pool within the persistence.xml file. In a standalone Java SE environment, the connections must be managed by the application. To deal with that last case, you can use one of several connection pool libraries that are available from many sources. 
Often, though, it is easier to create a connection and store it in a thread-local variable for each thread in a standalone application.
```````````````

```````````````````
prepared statement caches
Database performance can be adversely affected if the application server has too many open connections.
The general rule of thumb for connection pools is to have one connection for every thread in the application

Java applications will typically execute the same SQL statement repeatedly. In those cases, reusing prepared statements will offer a significant performance boost.

Prepared statements must be pooled on a per connection basis. Most JDBC drivers and data frameworks can do this automatically.

Prepared statements can consume a significant amount of heap. The size of the statement pool must be carefully tuned to prevent GC issues from pooling too many very large objects.

Transactions affect the speed of applications in two ways: transactions are expensive to commit, and the locking associated with transactions can prevent database scaling.

Those two effects are antagonistic: waiting too long to commit a transaction increases the amount of time that locks associated with the transaction are held. Especially for transactions using stricter semantics, the balance should be toward committing more frequently rather than holding the locks longer.

For fine-grained control of transactions in JDBC, use a default TRANSACTION_READ_UNCOMMITTED level and explicitly lock data as needed.

Applications that process large amounts of data from a query should consider changing the fetch size of the data.

A trade-off exists between loading too much data in the application (putting pressure on the garbage collector) and making frequent database calls to retrieve a set of data


``````````````````
````````````````````
JPA :

JPA can perform several optimizations to limit (or increase) the amount of data read in a single operation.

Large fields (e.g., BLOBs) that are not frequently used should be loaded lazily in a JPA entity.

When a relationship exists between JPA entities, the data for the related items can be loaded eagerly or lazily. The choice depends on the needs of the application.

When eagerly loading relationships, named queries can be used to issue a single SQL statement using a JOIN statement. Be aware that this affects the JPA cache; it is not always the best idea (as the next section discusses).

Reading data via named queries will often be faster than a regular query, since it is easier for the JPA implementation to use a PreparedStatement for named queries.



The JPA L2 cache will automatically cache entities for an application.

The L2 cache does not cache entities retrieved via queries. This means that in the long run it can be beneficial to avoid queries altogether.

Unless query caching is supported by the JPA implementation in use, using a JOIN query turns out to frequently have a negative performance effect, since it bypasses the L2 cache.

Batch reads and writes as much as possible by configuring the JDBC or JPA configuration appropriately.

Optimize the SQL the application issues. For JDBC applications, this is a question of basic, standard SQL commands. For JPA applications, be sure to consider the involvement of the L2 cache.

Minimize locking where possible. Use optimistic locking when data is unlikely to be contended, and use pessimistic locking when data is contended.

Make sure to use a prepared statement pool.

Make sure to use an appropriately sized connection pool.

Set an appropriate transaction scope: it should be as large as possible without negatively affecting the scalability of the application because of the locks held during the transaction.

```````````

