# NO GC Coding Tips

- Singleton objects
- Flyweight Pattern 
- Primitive Collections : Trave etc
## Strings
- String Concatenation : use String Builder 
- 8-bit byte strings :
  - Compact strings ( similar to Compressed strings but impl is different)
  - In Java 8, all strings are encoded as arrays of 16-bit characters, regardless of the encoding of the string
  - In Java 11, strings are encoded as arrays of 8-bit bytes unless they explicitly need 16-bit characters; these strings are known as compact strings
  - -XX:+CompactStrings flag, which is true by default
## Collections
- Presizing collections
- Enumerating Constants : Avoid Enum.values() method
- Logs ( Guarding Memory allocation)
    - Always check logger is enabled or not
    - Log neccessary information and avoid unnessary information
