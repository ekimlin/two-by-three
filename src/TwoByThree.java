import java.util.ArrayList;

/**
 * Created by dewdmcmann on 6/20/16.
 */
public class TwoByThree {

    static ArrayList<Character> abcs = new ArrayList<Character>();
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    /* combos is a binary representation of the possible combinations of 2 letters where each letter
     * appears three times. For examples, the combination of xxxyyy or aaabbb is represented in 
     * binary by 000111. 
    */
    static String[] combos = {"000111", "001011", "001101", "001110", "010011",
            "010101", "010110", "011001", "011010", "011100",
            "100011", "100101", "100110", "101001", "101010",
            "101100", "110001", "110010", "110100", "111000"};
    static String globalInputString;

    public static void main(String[] args) {

        //get input from command line:
        String puzzle = args[0];
        globalInputString = puzzle;
        //fill abcs with any letter that is not in the user puzzle:
        prepareAlphabet(puzzle);
        //iterate through remaining alphabet letter combinations
        //plug in combinations of letters
        iterateLetterCombos();
    }

    static void iterateLetterCombos() {

        for (int i = 0; i < abcs.size(); i++) { 
            for (int j = 0; j < abcs.size(); j++) { 
                if (i != j) {
                    tryCombinations(abcs.get(i), abcs.get(j));
                }
            }
        }
    }
    /* 
     * Precondition: s is the puzzle the user provided as input, including underscores. 
     * Precondition: abcs is empty 
     * Postcondition: abcs is filled with all letters of the alphabet that do not appear
     *                  in the user puzzle. 
     */
    static void prepareAlphabet(String s) {
        //Fill abcs with every letter of the alphabet:
        for (char c : alphabet.toCharArray()) {
            abcs.add(c);
        }
        //Remove from abcs any letter that is present in the puzzle:
        System.out.println("Preparing the alphabet...");
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '_') {
                int remove = abcs.indexOf(s.charAt(i));
                System.out.println("trying to remove: ..." + s.charAt(i) + "...");
                abcs.remove(remove);
                System.out.println("Removed: " + s.charAt(i));
            }
        }
    }
    /*
     * Try all of the possible combinations of a and b where a and b appear 3 times each. 
     * For each combination, plug into the puzzle by replacing the underscores with a 
     * letter. For example, if current combo is 'aaabbb' and the puzzle is "x_x_x_x_x_x_", 
     * the result after replaceUnderscores is "xaxaxaxbxbxb"
    */
    static void tryCombinations(char a, char b) {
        for (int i = 0; i < combos.length; i++) {
            char[] currentCombo = globalInputString.toCharArray();
            replaceUnderscores(a, b, currentCombo, combos[i]);

        }
    }
    /*
     * Precondition: the number of underscores = the length of currentBinary = 6
    */
    static void replaceUnderscores(char a, char b, char[] array, String currentBinary) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '_') {
                if (currentBinary.charAt(counter) == '0') {
                    array[i] = a;
                } else if (currentBinary.charAt(counter) == '1') {
                    array[i] = b;
                }
                counter++;
            }
        }
        System.out.println(String.valueOf(array)); //print the possible solution to the puzzle. 
    }
}
