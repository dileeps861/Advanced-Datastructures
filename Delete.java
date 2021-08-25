import java.util.Arrays;

/**
*   Allocate space with given number of spaces, or empty space bloack starting from the given idex.
*   The below solution is not working correctly.
*
**/
public class Delete {
    public static void main(String[] args) {
        int[] memory = new int[]{0,1,0,0,0,1,1,0,0,0,1,0,0};
        int[][] queries = new int[][]{
                {0,2},
                {0,1},
                {0,1},
                {1,0},
                {1,1},
                {1,3},
                {0,4}

        };
        del(memory, queries);
    }

    private static void del(int[] memory, int[][] queries){
        for (int i = 0; i < queries.length; i++) {
            System.out.println("i: "+i +", "+(del(memory, queries[i][0], queries[i][1])));
            System.out.println(Arrays.toString(memory));
        }

    }

    private static int del(int[] memory, int op, int idx){
        if(op == 0){
            int i = 0;
            int j = 0;
            while (i < memory.length || j < memory.length){

                if((j - i) == idx){
                    System.out.println(j-i);
                    int k = i;
                    while (k <= j){
                        memory[k] = 1;
                        k++;
                    }
                    return i;
                }
                if(memory[j] == 1){
                    i = j+1;
                    j = i;
                }
                else{
                    j++;
                }
            }

        }
        else{

            int i = idx;
            if(memory[idx] == 0) return -1;
            while (i < memory.length){
                if(memory[i] == 0) return 1;
                memory[i] = 0;
                i++;
            }
            return (i -idx);
        }
        return  -1;
    }
}
