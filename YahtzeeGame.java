/*** Noah Schmidt, noahcschmidt@gmail.com
 *   noahchristopher.schmidt@calbaptist.edu
 *   642383
 *   Yahtzee Project for Data Structures class under Dr. Perkins
 */


import java.util.*;


public class YahtzeeGame {
    private static final int ROUNDS_PLAYED = 3;

    public static void main(String[] args) {
        Set dice = new HashSet<Dice>();

        addTheDice(dice);

        playGame(dice);
    }


    private static void addTheDice(Set<Dice> diceSet) {
        //Adds the five dice to the set
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        Dice dice3 = new Dice();
        Dice dice4 = new Dice();
        Dice dice5 = new Dice();        //Im sure there is a better way to do this

        diceSet.add(dice1);
        diceSet.add(dice2);
        diceSet.add(dice3);
        diceSet.add(dice4);
        diceSet.add(dice5);
    }


    private static void playGame(Set<Dice> diceSet) {
        Map scores = new TreeMap<Integer, Integer>();   //holds score values for numbers rolled

        for (int i = 0; i < ROUNDS_PLAYED; i++) {       //Game loop
            Map rollTotals = new HashMap<Integer, Integer>();     //holds dice possibilities, & number of times rolled
            fillTree(rollTotals);

            rollTheDice(diceSet);
            int best = countTheDice(diceSet, rollTotals, scores);
            int bestScore = (int) rollTotals.get(best) * best;
            scores.put(best, bestScore);
        }
        printScores(scores);
    }

    private static void rollTheDice(Set<Dice> diceSet) {
        for (Dice thisDice : diceSet) {
            thisDice.roll();
        }
        printDice(diceSet);
    }

    private static void printDice(Set<Dice> diceSet) {
        for (Dice thisDice : diceSet) {
            System.out.println(thisDice.getValue());
        }
        System.out.println();
    }

    private static void fillTree(Map<Integer, Integer> tree) {
        //add set all the totals for the rolls to zero
        for (int i = 1; i <= 6; i++) {
            tree.put(i, 0);
        }
    }

    private static int countTheDice(Set<Dice> diceSet, Map<Integer, Integer> totals, Map<Integer, Integer> scores) {
        for (Dice thisDice : diceSet) {
            int num = thisDice.getValue();  //for less confusing code
            int addedVal = totals.get(num) + 1;     //current updated total frequency of dice roll
            totals.put(num, addedVal);
            //print the dice rolls and frequencies

        }
        System.out.println(totals);
        System.out.println();

        int bestNum = 0;
        int frequency = 0;
        for (int currentNum : totals.keySet()) {
            if (totals.get(currentNum) >= frequency) {
                if (!scores.containsKey(currentNum)) {      //if this number is not included yet
                    bestNum = currentNum;
                    frequency = totals.get(currentNum);
                }
            }

        }
        return bestNum;
    }

    private static void printScores(Map<Integer, Integer> scoreMap) {
        int total = 0;
        for(int score : scoreMap.keySet()) {
            System.out.println(score + "'s score: " + scoreMap.get(score));
            total += scoreMap.get(score);
        }

        System.out.println("Total score: " + total);

    }

}
