package xmlpasta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*  
 *  Represents an XML node in the tree
 *  (Slight warning: The node could have a value and children,
 *   even though that is not permitted in xml, 
 *   if you set any of those yourself)
 */
public class XMLNode {

    public String key;
    public String value;
    private Map<String, String> attribs;
    private ArrayList<XMLNode> children;

    public XMLNode(){ this(""); }

    public XMLNode(String key){ this(key, ""); }

    public XMLNode(String key, String value){
        this.key = key;
        this.value = value;
        attribs = new HashMap<String, String>();
        children = new ArrayList<XMLNode>();
    }

    public void addChild(XMLNode node){
        children.add(node);
    }

    public boolean removeChild(XMLNode node){
        return children.remove(node);
    }

    public ArrayList<XMLNode> getChildren(){
        return children;
    }

    // Adds or sets an attribute of node
    public void setAttrib(String key, String value){
        attribs.put(key, value);
    }

    // Returns the corresponding attribute value, null if it's not set
    public String getAttrib(String key){
        return attribs.get(key);
    }

    public Map<String, String> getAttribs(){
        return attribs;
    }

    // Nicely formatted String containing this node and all the children (recursive)
    @Override
    public String toString(){
        StringBuilder bob = new StringBuilder("<");
        bob.append(key);

        for (Map.Entry<String, String> entry : attribs.entrySet()) {
            bob.append(" " + entry.getKey() + "=\"" + entry.getValue() + "\"");
        }

        if(value.equals("") && children.size() == 0){
            return bob.append("/>").toString();
        }

        bob.append(">");

        if(!value.equals("")){
            return bob.append(value + "</" + key + ">").toString();
        }

        bob.append("\n");

        for (XMLNode node : children) {
            bob.append("\t" + node.toString()
               .replaceAll("\n", "\n\t") + "\n");
        }

        return bob.append("</" + key + ">").toString();
    }

}