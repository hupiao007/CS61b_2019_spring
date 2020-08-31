/** This interface defines a method for determining equality of characters. */
public interface CharacterComparator {
    /** Returns true if characters are equal by the rules of the implementing class. */
    boolean equalChars(char x, char y);
}

class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int dif = x - y;
        return dif == 1 || dif == -1;
    }
}

class OffByN implements CharacterComparator {
    //constructor
    private int count;
    public OffByN(int N) {
        count = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int dif = x - y;
        return dif == count || dif == -count;
    }
}