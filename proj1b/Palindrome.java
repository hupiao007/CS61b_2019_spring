public class Palindrome {
    /** Put characters of word into deque. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++){
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    /** Return true if the given string is palindrome. Using iteration.
    public boolean isPalindrome(String word) {
        Deque<Character> temp = wordToDeque(word);
        while (temp.size() != 0) {
            if (temp.size() == 1) {
                return true;
            }
            Character temp_f = temp.removeFirst();
            Character temp_l = temp.removeLast();
            if (temp_f != temp_l) {
                return false;
            }
            if (temp.size() == 0) {
                return true;
            }
        }
        return false;
    } */

    private boolean isPalindrome(Deque<Character> temp) {
        if (temp.size() == 0 || temp.size() == 1) {
            return true;
        }
        Character temp_f = temp.removeFirst();
        Character temp_l = temp.removeLast();
        if (temp_f == temp_l) {
            return isPalindrome(temp);
        } else {
            return false;
        }
    }

    /** second method for palindrome. */
    public boolean isPalindrome(String word) {
        Deque<Character> temp = wordToDeque(word);
        return isPalindrome(temp);
    }

    private boolean isOffByOne(Deque<Character> temp, CharacterComparator cc) {
        if (temp.size() == 0 || temp.size() == 1) {
            return true;
        }
        Character temp_f = temp.removeFirst();
        Character temp_l = temp.removeLast();
        if (cc.equalChars(temp_f, temp_l)) {
            return isOffByOne(temp, cc);
        } else {
            return false;
        }
    }
    /** Third public method. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> temp = wordToDeque(word);
        return isOffByOne(temp, cc);
    }
}
