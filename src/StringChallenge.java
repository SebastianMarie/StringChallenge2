
import java.util.*;
import java.io.*;

public class StringChallenge {

    // First Challenge : Conditions for array of string numbers
    // Have the function StringChallenge(str) take the str parameter being passed
    // and determine if it is a valid serial number with the following constraints:

    //1. It needs to contain three sets each with three digits (1 through 9) separated by a period.
    //2. The first set of digits must add up to an even number.
    //3. The second set of digits must add up to an odd number.
    //4. The last digit in each set must be larger than the two previous digits in the same set.
    //
    //If all the above constraints are met within the string, the your program should return the string true,
    // otherwise your program should return the string false.

    // For example: if str is "224.315.218" then your program should return "true".
    //Examples
    //Input: "11.124.667"
    //Output: false
    //Input: "114.568.112"
    //Output: true


    static class Main {
        public static boolean StringChallenge(String str) {
            boolean valid = str.matches("\\d{3}[.]\\d{3}[.]\\d{3}");
            if(valid){
                String[] arrOfStr = str.split("\\.");
                String[] firstSetStr = arrOfStr[0].split("(?!^)");
                String[] secondSetStr = arrOfStr[1].split("(?!^)");
                String[] thirdSetStr = arrOfStr[2].split("(?!^)");

                Integer[] firstSetInt = stringSetToIntSet(firstSetStr);
                Integer[] secondSetInt = stringSetToIntSet(secondSetStr);
                Integer[] thirdSetInt = stringSetToIntSet(thirdSetStr);

//            Integer[] firstSetInt = new Integer[3];
//            Integer[] secondSetInt= new Integer[3];
//            Integer[] thirdSetInt= new Integer[3];

//            for(int i = 0;i < firstSetStr.length;i++) {
//                firstSetInt[i] = Integer.parseInt(firstSetStr[i]);
//            }
//            for(int i = 0;i < secondSetStr.length;i++) {
//                secondSetInt[i] = Integer.parseInt(secondSetStr[i]);
//            }
//            for(int i = 0;i < thirdSetStr.length;i++) {
//                thirdSetInt[i] = Integer.parseInt(thirdSetStr[i]);
//            }

                //test if first set sum is even
                Integer firstSetSum = 0;
                for (Integer integer : firstSetInt) {
                    firstSetSum += integer;
                }
                if (firstSetSum % 2 != 0){
                    return false;
                }

                //test if second set sum is odd
                Integer secondSetSum = 0;
                for (Integer integer : secondSetInt) {
                    secondSetSum += integer;
                }
                if (secondSetSum % 2 == 0){
                    return false;
                }

                //test if the last digit in each set is larger than the two previous digits in the same set
                if (!checkIfLastDigitIsGreater(firstSetInt)){
                    return false;
                }
                if (!checkIfLastDigitIsGreater(secondSetInt)){
                    return false;
                }
                if (!checkIfLastDigitIsGreater(thirdSetInt)){
                    return false;
                }

//            if(firstSetInt[2] <= firstSetInt[1] && firstSetInt[2] <= firstSetInt[0]){
//                return false;
//            }
//            if(secondSetInt[2] <= secondSetInt[1] && secondSetInt[2] <= secondSetInt[0]){
//                return false;
//            }
//            if(thirdSetInt[2] <= thirdSetInt[1] && thirdSetInt[2] <= thirdSetInt[0]){
//                return false;
//            }

                return true; // in case no problems were found
            }
            else{
                return false;
            }
        }

        public static boolean checkIfLastDigitIsGreater(Integer[] intSet){
            if(intSet[2] <= intSet[1] && intSet[2] <= intSet[0]){
                return false;
            }
            else return true;
        }

        public static Integer[] stringSetToIntSet(String[] stringSet){
            Integer[] intSet = new Integer[3];
            for(int i = 0;i < stringSet.length;i++) {
                intSet[i] = Integer.parseInt(stringSet[i]);
            }
            return intSet;
        }

        public static void main (String[] args) {
            // keep this function call here
            Scanner s = new Scanner(System.in);
            System.out.print(StringChallenge(s.nextLine()));
        }

    }
}
    class SearchChallenge {
    // Second Challenge : Searching Challenge
    // Have the function SearchingChallenge(str) take the str parameter
    // being passed and return the first word with the greatest number
    // of repeated letters. For example: "Today, is the greatest day ever!"
    // should return greatest because it has 2 e's (and 2 t's)
    // and it comes before ever which also has 2 e's.
    // If there are no words with repeating letters return -1.
    // Words will be separated by spaces.
    // Examples
    //Input: "Hello apple pie"
    //Output: Hello
    //Input: "No words"
    //Output: -1
    //Browse Resources
    //Search for any help or documentation you might need for this problem. For example: array indexing, Ruby hash tables, etc.

    static class Main {
        public static String SearchingChallenge(String str) {
            String[] wordList = splitSentenceIntoWords(str);
            for (int i = 0; i < wordList.length; i++){
                wordList[i] = wordList[i].toLowerCase();
            }

            int[] frequencyList = new int[wordList.length];
            for (int i = 0; i < wordList.length; i++){
                char[] currentWord =wordStringToCharArray(wordList[i]);
                int currentWordFrequency = findCharacterGreatestFrequency(currentWord);
                frequencyList[i] = currentWordFrequency;
            }
            int greatestFrequencyWord = indexOfMax(frequencyList);
            if (findCharacterGreatestFrequency(wordStringToCharArray(wordList[greatestFrequencyWord])) == 1 ){
                return "-1";
            }
            if(greatestFrequencyWord == 0){
                return wordList[greatestFrequencyWord].substring(0, 1).toUpperCase() + wordList[greatestFrequencyWord].substring(1);
            }
            return wordList[greatestFrequencyWord];
        }

        private static String[] splitSentenceIntoWords(String str){
            if(str == null || str.equals(""))
                return new String[0];
            return str.split(" ");
        }

        public static char[] wordStringToCharArray(String word){
            return word.toCharArray();
        }

        static int findCharacterGreatestFrequency(char[] arr)
        {
            HashMap<Character, Integer> hs = new HashMap<>();
            // Iterate through array of words
            for (char s : arr) {
                if (hs.containsKey(s)) {
                    hs.put(s, hs.get(s) + 1);
                }
                else {
                    hs.put(s, 1);
                }
            }

            // set to iterate over HashMap
            Set<Map.Entry<Character, Integer>> set = hs.entrySet();
            Character key = null;
            int value = 0;

            for (Map.Entry<Character, Integer> me : set) {
                if (me.getValue() > value) {
                    value = me.getValue();
                    key = me.getKey();
                }
            }

            return value;
        }

        public static int indexOfMax(int[] arr) {
            if (arr.length == 0) {
                return -1;
            }

            var max = arr[0];
            var maxIndex = 0;

            for (var i = 1; i < arr.length; i++) {
                if (arr[i] > max) {
                    maxIndex = i;
                    max = arr[i];
                }
            }

            return maxIndex;
        }

        public static void main (String[] args) {
            // keep this function call here
            Scanner s = new Scanner(System.in);
            System.out.print(SearchingChallenge(s.nextLine()));
        }

    }

}
