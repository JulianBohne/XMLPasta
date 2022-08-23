import java.util.Scanner;

import xmlpasta.XMLNode;
import xmlpasta.XMLParser;

import java.io.File;
import java.io.FileNotFoundException;

/*
 * Basically a small Demo for the xml parser
 */
class Main{
    public static void main(String[] args) throws FileNotFoundException{
        // Scanner that stops at the end of file (EOF = \Z)
        Scanner sc = new Scanner(new File("Test.xml")).useDelimiter("\\Z");
        String content = sc.next(); // Read whole File into content
        sc.close(); 

        // Create XMLParser
        XMLParser parser = new XMLParser();
        // Parse Test.xml
        XMLNode root = parser.parse(content);

        // Print xml tree
        System.out.println(root);
    }
}