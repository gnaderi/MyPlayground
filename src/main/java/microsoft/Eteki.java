package microsoft;

import java.util.Random;

class Eteki
{ 
	public static void main(String[]args)throws java.lang.Exception 
	{
		System.out.println("the string " + replace("hello?"));
	}

public static String replace(String src){
  int length= src.length();
  char a[] = src.toCharArray();
  StringBuffer sb = new StringBuffer();
 // char c = (char)(r.nextInt(26) + 'a');
  for (int i=0 ;i< length; i++) {
      if (a[i] == '?') {
          char c=' ';
          if (i > 0 && i < length - 1) {
              c = generateChar( a[i--], a[i++]);
          } else if (i < 0) {
              c = generateChar( '$', a[i++]);
          } else if (i == length) {
              c = generateChar( a[i--], '$');
          }
          sb.append(c);
      }
  }
  
  
 return sb.toString(); 
}
  
  public static char generateChar(char before , char after) {
      Random r = new Random();
     char c = (char)(r.nextInt(26) + 'a');
     while( c == before || c == after) {
         c = (char)(r.nextInt(26) + 'a');
     }
    return c;
    
  }
}