public class OffByN implements CharacterComparator {

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
