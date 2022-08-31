NIO

- IO Drawbacks
    - Synchronous
    - No rich charset support
    - all read/write ops are singlee byte/char wise
    - buffering in heap-memory (within JVM)
    - Readers for reading data and Writers for writing data

- NIO Advantages
    - ASynchronous
    - Rich charset support
    - all read/write ops are bulk byte/char wise
    - buffering in off-heap memory (outside of JVM)

- NIO Main Concepts
  - Channels
  - Buffers
  - Selectors

- NIO : Channels
  - FileChannel
    - Reading from file using FileChannel
    - It has Cursor
    - Multiple Read Writes
    - Thread-Safe
  - SocketChannel : DatagramChannel
    - Access from socket using DatagramChannel
    - Multicast - UDP
    - NonConcurrent multiple read/writes
  - SocketChannel : Socket/ServerSocketChannel
    - Access from socket using Socket/ServerSocketChannel
    - Supports async ops
    - NonConcurrent multiple read/writes
  - In-Memory FileChannel
    - Mapping FileChannel to In-Memory array for direct access
    - Three Modes - READ, READ_WRITE, PRIVATE
    - Multiple Read Writes
    - Thread-Safe

  - FileChannel,Socket/ServerSocketChannel are abstract classes
  - Impls are hidden
  - We can use factory methods to create channel

- NIO : Buffers
 - InMemory / Off-heap
 - Props
    capacity , limit, cursor
 - Marking buffer
 - Ops : rewind, reset,flip
 - clear


 - Reading and Writing to Multiple Buffers
    - Scattering Read and Write
    - Scattering reads from sngle channel to array of buffers
    - Scattering write to  array of buffers from sngle channel
    - ScatteringByteChannel
    - Useful for handling messages with fixed length
 - MappedByteBuffers
   - Maps file to memory
   - Useful for apps that read file many times
   - Modes : READ, READ_WRITE, PRIVATE

 - ByteBuffer to CharBuffer
   - Charsets : encode(), decode()
   - Encoding and Decoding only way to convert ByteBuffer to CharBuffer and ViceVersa
 - Convert NIO to IO
   - Utility class having factory methods

# Async Operations with NIO
 - synchronous : read() method on reader in IO and Channel i NIO
 - async : read from multiple sources
    we can use single thread which will read from multiple sources
    instead of creating separate thread for each data source
 - Selector :
     entry object for async op setup
       1) create channel
       2) configure it to be async
       3) Register with selector and it returns reg key
       4) Channel fire events : READ, WRITE , CONNECT , ACCEPT(SocketChannel)
       5) SELECT()- blocking call

    Sockets :
     ServerSocketChannel : special type of channel listening on incoming requests in sockets