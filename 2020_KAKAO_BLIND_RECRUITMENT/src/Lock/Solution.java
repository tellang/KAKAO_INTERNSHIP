package Lock;

public class Solution {
    int keySize, lockSize, lockOffset, keyMoveSize;
    int[][] key, lock;
    public boolean solution(int[][] key, int[][] lock) {
        this.key = key;
        this.lock = lock;
        this.keySize = key.length;
        this.lockSize = lock.length;
        keyMoveSize = key.length + lock.length - 1;
        lockOffset = key.length - 1;
        int[][] extendMatrix = makeExtendMatrix();
        for (int y = 0; y < keyMoveSize; y++) {
            for (int x = 0; x < keyMoveSize; x++) {
                if(addKeyMatrix(x, y, deepCopy(extendMatrix)))
                    return true;
            }
        }
        return false;
    }
    private int[][] deepCopy(int[][] origin){
        int[][] result = new int[origin.length][origin.length];
        for (int i = 0; i < origin.length; i++)
            System.arraycopy(origin[i], 0, result[i], 0, origin[i].length);
        return result;
    }

    private int[][] makeExtendMatrix (){
        int extendSize = 2*keySize+lockSize-2;
        int[][] extendLock = new int[extendSize][extendSize];
        for (int y = 0; y < lockSize; y++) {
            System.arraycopy(lock[y], 0, extendLock[keySize - 1 + y], keySize - 1, lockSize);
        }
        return extendLock;
    }
    private boolean addKeyMatrix (int xOffset, int yOffset, int[][] extendLock){
        int[][] extendLock0Pie = deepCopy(extendLock) , extendLockHalfPie = deepCopy(extendLock),
                extendLockPie = deepCopy(extendLock), extendLock3HalfPie = deepCopy(extendLock);
        for (int y = 0; y < keySize; y++) {
            for (int x = 0; x < keySize; x++) {
                extendLock0Pie[y + yOffset][x + xOffset] += key[y][x];
                extendLockHalfPie[y + yOffset][x + xOffset] += key[x][keySize - 1 - y];
                extendLockPie[y + yOffset][x + xOffset] += key[keySize - 1 - y][keySize - 1 - x];
                extendLock3HalfPie[y + yOffset][x + xOffset] += key[keySize - 1 - x][y];
            }
        }
        return checkPattern(extendLock0Pie) || checkPattern(extendLockHalfPie) ||
                checkPattern(extendLockPie) || checkPattern(extendLock3HalfPie);
    }
    public boolean checkPattern (int[][] extendLock){
        for (int y = 0; y < lockSize; y++) {
            for (int x = 0; x < lockSize; x++) {
                if(extendLock[y + lockOffset][x + lockOffset] != 1)
                    return false;
            }
        }
        return true;
    }
    public static void main (String[] args){
        Solution s = new Solution();
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(s.solution(key, lock));
    }
}
