import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {

    private class Node {
        private int numWords;
        private boolean word;
        private Map<Character, Node> children;

        private Node() {
            word = false;
            numWords = 0;
            children = new HashMap<>();
        }
    }

    private Node root;

    public MyTrieSet() {
        root = new Node();
    }

    @Override
    public void clear() {
        root = new Node();
    }

    @Override
    public boolean contains(String key) {
        Node current = root;
        for (int i = 0; i < key.length(); i++) {
            if (current.children.containsKey(key.charAt(i))) {
                current = current.children.get(key.charAt(i));
            } else {return false;}
        }
        if (current.word == true) return true;
        return false;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {return;}
        Node current = root;
        for (int i = 0; i < key.length(); i++) {
            if (!current.children.containsKey(key.charAt(i))) {
                Node n = new Node();
                current.children.put(key.charAt(i), n);
            }
            current = current.children.get(key.charAt(i));
            current.numWords++;
        }
        current.word = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> ans = new ArrayList<>();
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!current.children.containsKey(prefix.charAt(i))) {
                throw new IllegalArgumentException("No string with such prefix");
            }
            current = current.children.get(prefix.charAt(i));
        }
        return keysHepler(current.children, prefix, ans);
    }

    private List<String> keysHepler(Map<Character, Node> m, String prefix, List<String> l) {
        if (m.isEmpty()) {return l;}
        for (Character c : m.keySet()) {
            if (m.get(c).word == true) {
                l.add(prefix + c.toString());
            }
            l = keysHepler(m.get(c).children, prefix + c.toString(), l);
        }
        return l;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException("Not supported");
    }
}
