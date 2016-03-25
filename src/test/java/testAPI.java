import org.junit.Assert;
import org.junit.Test;

public class testAPI {

    @Test
    public void testHigherGuessNumber() {
        GuessAPI guessAPI = new GuessAPI();
        guessAPI.random = 50;
        String higherNumber = "51";

        Assert.assertNotNull(guessAPI.guessNumber(higherNumber));
        Assert.assertEquals(guessAPI.guessNumber(higherNumber), 1);
    }

    @Test
    public void testCorrectNumber(){
        GuessAPI guessAPI = new GuessAPI();
        guessAPI.random = 50;
        String correctNumber = "50";

        Assert.assertNotNull(guessAPI.guessNumber(correctNumber));
        Assert.assertEquals(guessAPI.guessNumber(correctNumber), 0);
    }

    @Test
    public void testLowerNumber() {
        GuessAPI guessAPI = new GuessAPI();
        guessAPI.random = 50;
        String lowerNumber = "49";

        Assert.assertNotNull(guessAPI.guessNumber(lowerNumber));
        Assert.assertEquals(guessAPI.guessNumber(lowerNumber), -1);
    }

    @Test
    public void inputValueNotInRange(){
        GuessAPI guessAPI = new GuessAPI();
        String lowerBound = "0";
        String higherBound = "101";

        Assert.assertNotNull(guessAPI.guessNumber(lowerBound));
        Assert.assertNotNull(guessAPI.guessNumber(higherBound));
        Assert.assertEquals(guessAPI.guessNumber(lowerBound), 2);
        Assert.assertEquals(guessAPI.guessNumber(higherBound), 2);
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidFormatInput(){
        GuessAPI guessAPI = new GuessAPI();
        guessAPI.guessNumber("Wrong format");
    }
}
