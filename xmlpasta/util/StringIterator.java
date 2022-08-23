package xmlpasta.util;

import java.util.Iterator;

/*
 * Used to iterate over a string
 * I feel like it is a tiny bit inefficient at times, but it works
 */
public class StringIterator implements Iterator<Character>{
    private String str;
    private int strLen; // string length
    private int cIndex; // index of current character
    private int lineCount;
    private int columCount; // Tabs will be counted as 1. Might cause misalignment
                            // Replace them with four spaces for better counting
    private int pLineCount; // Previous line
    private int pColumCount; // Previous column

    public StringIterator(String str){
        this.str = str;
        this.strLen = str.length();
        this.cIndex = 0;
        this.lineCount = 1;
        this.columCount = 1;
        this.pLineCount = 1;
        this.pColumCount = 1;
    }

    public int line(){
        return lineCount;
    }

    public int column(){
        return columCount;
    }

    public int pLine(){
        return pLineCount;
    }

    public int pColumn(){
        return pColumCount;
    }

    public boolean hasNext(){
        return cIndex < strLen;
    }

    // Returns the next character and moves on to the next
    public Character next(){
        if(!hasNext()) throw new IllegalAccessError("EOF Reached"); // No more text :(
        pLineCount = lineCount;
        pColumCount = columCount;
        char c = str.charAt(cIndex);
        if(c == '\n'){
            lineCount ++; // A new line occured
            columCount = 1;
        }else{
            columCount ++; // Not a new line, so just move the cursor
        }
        return str.charAt(cIndex ++);
    }

    // Skips one character
    public void skip(){
        if(hasNext()){
            // We still have to update all the cursor information
            pLineCount = lineCount;
            pColumCount = columCount;
            char c = str.charAt(cIndex);
            if(c == '\n'){
                lineCount ++;
                columCount = 1;
            }else{
                columCount ++;
            }
            cIndex ++;
        }
    }
    
    // Peek one character, doesn't move on to the next character
    public char peek(){
        if(!hasNext()) throw new IllegalAccessError("EOF Reached"); // No characters? ',:/
        return str.charAt(cIndex);
    }

    // Returns the amount of characters left to read in the string
    public int amountLeft(){
        return strLen - cIndex;
    }

    // Skips multiple characters
    public void skip(int amount){
        // Using 'skip()' because it also updates important cursor data
        for(int i = 0; i < amount && hasNext(); i ++) skip();
    }

    // Get multiple characters
    public String next(int amount){
        StringBuilder bob = new StringBuilder();

        while(amount > 0 && hasNext()){
            bob.append(next());
        }

        return bob.toString();
    }

    // Peek multiple characters
    public String peek(int amount){
        amount = Math.min(Math.max(amount, 0), amountLeft());
        return str.substring(cIndex, cIndex + amount);
    }
}