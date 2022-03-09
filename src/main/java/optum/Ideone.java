package optum;/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		String s = "137\n-104\n2 58\n  +0\n++3\n+1\n 23.9\n2000000000\n-0\nfive\n -1";
		Reader reader = new StringReader(s);
		for (Integer x : new SolutionIter(reader))
		{
         	System.out.println(x);
        }
	}
}

