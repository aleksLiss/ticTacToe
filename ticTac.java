import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ticTac {
    public static void main(String[] args) {

        Game g = new Game();
        g.rulesOfGame();
        while (g.answer) {
            g.setWinsNumbers();
            g.drawField();
            while (g.gameCycle) {
                g.playerGuessOfNumberField();
                g.whoWin();
                g.botGuessOfNumberField();
                g.drawField();
                g.whoWin();
            }
            g.invitePlayer();
        }
    }
}


class Game {

    String StringField = "123456789";
    int move = 0;
    ArrayList<ArrayList<Integer>> listWin = new ArrayList<>();
    boolean gameCycle = true;

    boolean answer = true;

    public void setStringField() {
        StringField = "123456789";
    }

    public void invitePlayer() {
        System.out.println("Are you want to play again?" + "\n" + "Input Y(Yes) or N(No)...");
        while (true) {
            Scanner num = new Scanner(System.in);
            String playerAnswer = num.next();
            if (playerAnswer.contains("Y")) {
                answer = true;
                gameCycle = true;
                move = 0;
                setStringField();
                return;
            } else if (playerAnswer.contains("N")) {
                System.out.println("Good Bye!");
                return;
            } else {
                System.out.println("Please input correct answer...");
            }
        }
    }


    public void whoWin() {

        for (ArrayList i : listWin) {
            int ind1 = (int) i.get(0);
            int ind2 = (int) i.get(1);
            int ind3 = (int) i.get(2);
            if (StringField.charAt(ind1) == 'X' && StringField.charAt(ind2) == 'X' && StringField.charAt(ind3) == 'X') {
                System.out.println("Player win!");
                gameCycle = false;
                answer = false;
                return;
            } else if (StringField.charAt(ind1) == '0' && StringField.charAt(ind2) == '0' && StringField.charAt(ind3) == '0') {
                System.out.println("Bot win!");
                gameCycle = false;
                answer = false;
                return;
            } else if (move >= 9) {
                if (StringField.charAt(ind1) == '0' && StringField.charAt(ind2) == '0' && StringField.charAt(ind3) == '0') {
                    System.out.println("Bot win!");
                    gameCycle = false;
                    answer = false;
                    return;
                } else if (StringField.charAt(ind1) == 'X' && StringField.charAt(ind2) == 'X' && StringField.charAt(ind3) == 'X') {
                    System.out.println("Player win!");
                    gameCycle = false;
                    answer = false;
                    return;
                }

            }
        }
        if (move >= 9) {
            System.out.println("The draw!");
            gameCycle = false;
            answer = false;
            return;
        }
    }


    public void botGuessOfNumberField() {
        if (gameCycle && move < 9) {
            Random index = new Random();
            while (true) {
                int res = index.nextInt(1, 9);
                if (StringField.contains(String.valueOf(res))) {
                    StringField = StringField.replace(StringField.charAt(res - 1), '0');
                    move++;
                    return;
                }
            }
        }
    }


    public void playerGuessOfNumberField() {
        if (gameCycle && move < 9) {
            System.out.println("Guess number of field...\n");
            while (true) {
                Scanner num = new Scanner(System.in);
                String numberOfField = num.next();
                if (StringField.contains(numberOfField)) {
                    int ind = Integer.parseInt(numberOfField);
                    StringField = StringField.replace(StringField.charAt(ind - 1), 'X');
                    move++;
                    break;
                } else {
                    System.out.println("Please input correct number...");
                }
            }
        }
    }


    public void drawField() {

        String field = "_______________________________\n" +
                "|         |         |         |\n" +
                "|    1    |    2    |    3    |\n" +
                "|_________|_________|_________|\n" +
                "|         |         |         |\n" +
                "|    4    |    5    |    6    |\n" +
                "|_________|_________|_________|\n" +
                "|         |         |         |\n" +
                "|    7    |    8    |    9    |\n" +
                "|_________|_________|_________|\n";

        for (int i = 0; i < StringField.length(); i++) {
            field = field.replace(i + 1 + "", StringField.charAt(i) + "");
        }
        System.out.println(field);
    }


    public void rulesOfGame() {
        System.out.println("Hi! This is tic-tac-toe game.\n" + "1. Player goes first.\n" +
                "2. Player goes X\n" + "3. Bot goes 0\n" + "4. Good luck!");

    }

    public void setWinsNumbers() {
        int counter = 1;
        while (counter != 5) {
            if (counter == 1) {
                for (int i = 1; i < 8; i += 3) {
                    ArrayList<Integer> numbers = new ArrayList<>();
                    numbers.add(i - 1);
                    numbers.add(i);
                    numbers.add(i + 1);
                    listWin.add(numbers);
                }
            } else if (counter == 2) {
                for (int i = 1; i < 4; i++) {
                    ArrayList<Integer> numbers = new ArrayList<>();
                    numbers.add(i - 1);
                    numbers.add(i + 2);
                    numbers.add(i + 5);
                    listWin.add(numbers);
                }
            } else if (counter == 3) {
                ArrayList<Integer> numbers = new ArrayList<>();
                numbers.add(0);
                numbers.add(4);
                numbers.add(8);
                listWin.add(numbers);

            } else {
                ArrayList<Integer> numbers = new ArrayList<>();
                numbers.add(2);
                numbers.add(4);
                numbers.add(6);
                listWin.add(numbers);
            }
            counter++;
        }
    }
}