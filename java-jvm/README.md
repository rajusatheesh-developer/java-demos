#JVM

1) Class loader
2) Memory Area
3) Execution Engine

# Class Loader
- Loading
  - Bootstrap Class Loader
     - JDK/JRE/lib/rt.jar
     - Impl in Native languages
  - Extension Class Loader
     - Extends Bootstrap class loader
     - Impl in Java
     - ExtensionClassLoader
     - JDK/JRE/lib/ext/*.jar
  - Application Class Loader
       - Extends Extension class loader
       - Impl in Java
       - ApplicationClassLoader
       - Environment variable CLASSPATH
- Linking
   - Verify - BytecodeVerifier
   - Prepare - static variables , blocks
   - Resolve - Symbol resolve
- Initialization



Java SE Tips:
---------------------
1) Strings : CompactStrings , DuplicateStrings and Concatenate
2) Class Loading : Class Data Sharing Between JVMs
3) Random Numbers : Random,ThreadLocal.Random,SecuredRandom
4) Java Native Interfaces : JNI is very expensive such as calls from Java to JNI
5) Logging : Log necessary data and check isLoggEnabled() every time
6) Exceptions : Not expensive but is expensive when stack traces are deeper so better to
         handle exceptions if required
7) Buffered IO : Use bufferes for Java IO
8) Serialization : Transient Fields ,
     Overide default serialization - writeObject(),readObject(), Track DuplicateObjects
     Externalizable vs Serializable
      The practical difference between these two interfaces is in the way they handle nontransient fields.
      The Serializable interface writes out nontransient fields when the writeObject() method calls the defaultWriteObject() method. The Externalizable interface has no such method. An Externalizable class must explicitly write out all fields,
      transient or not, that it is interested in transmitting.
9) JSON Parsing : Parser - faster( Jackson Parser) / POJO's not efficient leads GC when objects are heavy
10) Stream and Filter Performance
11) Lambdas and Anonymous Classes
12) Collections : Sizing , Memory & Efficiency , Synchronized vs Unsynchronized

