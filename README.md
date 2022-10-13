# XMLPasta
Simple XML (1.0) Parser written in Java

## Usage
1. Include the xmlpasta folder in your project
2. Import xmlpasta package
3. Create an XMLParser object (for different varieties look at the comments next to the constructers)
4. Call 'parse' on it -> Returns the root XMLNode of the tree

(An example of all this is given in the Main.java file)

## Nice Things
- When an error occurs while parsing, the exception contains a nicely formatted string about what happened where
- When you set the DEBUG boolean in the XMLParser to true, parsing will log information about all the steps and errors in the console
- Convert a node tree to a string by calling 'toString' on the root node
- Not dependent on anything

## Limitations
- Only supports xml 1.0 spec
- Only accepts a String of the entire xml file (no streams or similar)
