#1.What will happen?
 ``````````````
  package com.other;
  public class com.demos.java.absics.A
  {
  
  }
  
  package com.others;
  class B
  {
  
  }
 
 - Compilation error because top lvele class should have one package statement.  
 ``````````````

#2.What will happen?
 ``````````````
  package com.other;
  public class com.demos.java.absics.A
  {
  
  }
  
  public class B
  {
  
  }
  
 - Compilation error because top level class should have one public type.  
 ``````````````

#3.What do you think about X?
 ``````````````
  package com.other;
  public interface com.demos.java.absics.A
  {
    int x=99;
  }

 - x is public final and static
 ``````````````
#4.What happens?
 ``````````````
  package com.other;
  public class final abstract com.demos.java.absics.A
  {
      void doThings()
      {
        sout("test"); 
      }
  }

 - Compilation error because abstract clases cannot be final
   and abstract class should have atleast one abstract  methods
 ``````````````
#5.What happens?
 ``````````````
  package com.other;
  public class  com.demos.java.absics.A
  {
      static { sout("test"): }
       { sout("test"): }
      static class B { }
      class C { }
      static enum {  }
      static interface {  }
  }

 - Compilation success
   Note : Inner enum and interfaces are implicitly static 
 ``````````````
