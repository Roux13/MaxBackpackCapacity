import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class Main {

    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] nAndW = reader.readLine().split(" ");
        int capacity = Integer.parseInt(nAndW[0]);
        int n = Integer.parseInt(nAndW[1]);

        TreeMap<Integer, Integer> goldBars = new TreeMap<>();
        String[] lineOfBars = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            goldBars.put(i + 1, Integer.parseInt(lineOfBars[i]));
        }

        int[][] weightOfGold = new int[n + 1][capacity + 1];
        int[] maxWeightForBar = new int [n];
        maxWeightForBar[0] = goldBars.get(0 + 1);
        for (int iMax = 1; iMax < n; iMax++) {
            maxWeightForBar[iMax] = goldBars.get(iMax - 0) + goldBars.get(iMax + 1);
        }

        for (int j = 1; j < capacity + 1; j++) {
            for (int i = 1; i < n + 1; i++) {
                int currentGoldBar = goldBars.get(i);
                if (weightOfGold[i][j - 1] >= maxWeightForBar[i - 1]) {
                    weightOfGold[i][j] = weightOfGold[i][j - 1];
                }
                else if (currentGoldBar > j) {
                    weightOfGold[i][j] = weightOfGold[i - 1][j];
                }
                else if (currentGoldBar == j) {
                    weightOfGold[i][j] = j;
                }
                else {
                    if (weightOfGold[i - 1][j - currentGoldBar] + currentGoldBar <= j){
                        weightOfGold[i][j] =
                                weightOfGold[i - 1][j - currentGoldBar] + currentGoldBar;
                    }
                    else {
                        weightOfGold[i][j] = weightOfGold[i][j - 1];
                    }
                }
            }
        }
        System.out.println(weightOfGold[n][capacity]);
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

}
