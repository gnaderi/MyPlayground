package optum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Reader;
import java.util.List;
import java.util.NoSuchElementException;

class SolutionIter implements Iterable<Integer>
{
    private Reader inp;
    public SolutionIter(Reader inp)
    {
        this.inp = inp;
    }

    public Iterator<Integer> iterator()
    {
        return new NumberIterator();
    }

    // Inner class
    private class NumberIterator implements Iterator<Integer>
    {
        private List<Integer> list = new ArrayList<Integer>();
        private Reader input;
        private int pos=-1;

        public NumberIterator()
        {
            this.input = SolutionIter.this.inp;
            list = read(input);
            list.get(0);
        }

        public boolean hasNext()
        {
            try
            {
                int a = list.get(pos+1);
                return true;
            }
            catch(Exception ex)
            {
                return false;
            }

        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next()
        {
            try
            {
                pos++;
                return list.get(pos);

            }
            catch(Exception ex)
            {
                throw new NoSuchElementException();
            }
        }

        private List<Integer> read(Reader input)
        {
            char c;
            List<Integer> integers = new ArrayList<Integer>();
            String iputString = "";
            try
            {
                int data = input.read();
                while (-1 != data)
                {
                    char dataChar = (char) data;
                    iputString += dataChar;
                    data = input.read();
                }
            }
            catch(IOException ex)
            {
                //Do Nothing
            }

            String[] lines = iputString.split("\\r?\\n");
            for(String st : lines)
            {
                st= st.trim();
                try
                {
                    int a = Integer.parseInt(st);
                    if(a >=-1000000000 && a<=1000000000)
                    {
                        integers.add(a);
                    }
                }
                catch(Exception ex)
                {
                    // Do Nothing
                }

            }

            return  integers;
        }
    }

}