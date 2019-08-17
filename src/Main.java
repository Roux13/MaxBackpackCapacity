import java.io.BufferedReader;
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

        for (int j = 1; j < capacity + 1; j++) {
            for (int i = 1; i < n + 1; i++) {
                int currentGoldBar = goldBars.get(i);
                if (j - currentGoldBar >= 0) {
                        weightOfGold[i][j] = Math.max((currentGoldBar + weightOfGold[i - 1][j - currentGoldBar]), weightOfGold[i - 1][j]);
                }
                else weightOfGold[i][j] = weightOfGold[i - 1][j];
            }
        }
        System.out.println(weightOfGold[n][capacity]);
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

}
