public class GuessAPI {
    public int count = 0;
    public int random = (int) (Math.random() * 100);
    public int bestScore = 100;

    public GuessAPI() {
    }

    public int guessNumber(String inputValue) {
        count++;
        int value = Integer.parseInt(inputValue);
        if (value > 100 || value < 1) {
            return 2;
        }
        if (value < random) {
            return -1;
        }
        if (value > random) {
            return 1;
        }

        return 0;
    }
}
