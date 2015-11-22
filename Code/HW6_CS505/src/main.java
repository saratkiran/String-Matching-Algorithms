/**
 * Created by saratkiran on 4/24/14.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.*;
import java.io.*;
import java.lang.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException  {
        for(int l =0;l<15;l++){ // Number of trails
            //String text= "ABABABCABABABCABABABC";
            //String pattern = "ABABAC";
            //System.out.println(text.length());
            //System.out.println(pattern.length());
            String text = new Scanner(new File("data.txt")).useDelimiter("\\Z").next();
            //System.out.println(content);
            int substring_start = 0 + (int)Math.random( ) * 15;
            int substring_size = (int)Math.pow(2,15);
            String pattern = text.substring(substring_start,substring_start+substring_size);
            long startTime = System.nanoTime();    //Start the timer
            /*int text_len = (int)Math.pow(2,20);
            int pattern_len = (int)Math.pow(2,19);


            ------------------------------Uncomment to see Artificial string generator -------------------------------
            String text = string_generator(text_len);
            String pattern = string_generator(pattern_len);*/


            //------------------------------Uncomment to see Naive algorithm --------------------------------------
            //List<Integer> naive_answer = naive_search(text,pattern);
            //System.out.println(naive_answer +  "    " + naive_answer.size());
            //-------------------------------------------------------------------------------------------------------

            //------------------------------Uncomment to see Naive algorithm --------------------------------------
            //List<Integer> kmp_answer = kmp_search(text, pattern);
            //System.out.println(kmp_answer + "    " + kmp_answer.size());
            //-------------------------------------------------------------------------------------------------------

            //------------------------------Uncomment to see Naive algorithm --------------------------------------
            List<Integer> bm_answer = bm_answer(text,pattern);
            //System.out.println(bm_answer +  "    "+ bm_answer.size());
            //--------------------------------------------------------------------------------------------------------

            long stopTime = System.nanoTime();
            float elapsedTime = stopTime - startTime;
            System.out.println(elapsedTime/1000000);    // Calculate the time taken.

        }
    }
    //Function to generate the artificial strings
    private static String string_generator(int str_len) {
        String s;
        double min= 0.0;
        double max = 1.0;
        double diff = max - min;
        double thres =  min + Math.random( ) * diff;  // random thresould
        double prob;
        int[] value = new int[2];
        value[0] = 0;       // values 0,1 in array
        value[1] = 1;
        int other = 0;
       // System.out.println(thres);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i <str_len; i++){
            prob = min + Math.random( ) * diff;
            //System.out.println(prob);
            if(prob > thres){           // check if it is more than the thresould value
                stringBuilder.append(Integer.toString(value[other]));
            }
            else{                       // if not change the character
                if(other == 0)
                    other =1;
                else
                other = 0;
                stringBuilder.append(value[other]);
            }
        }
        s = stringBuilder.toString();   // change it into string

        return s;       //return the string

    }
    //function to find string matching using BM algorithm
    private static List<Integer> bm_answer(String text, String pattern) {

        List<Integer> answer = new ArrayList<Integer>();
        int text_len = text.length(); // finding the length of text
        int pattern_len = pattern.length(); // finding the length of pattern

        int[] result = precomputebm(pattern);
        //System.out.println(Arrays.toString(result));

        int shift = 0;      // shift to transfer

        while(shift <= text_len - pattern_len){
            int pattern_index = pattern_len -1;

            while(pattern_index >=0 && pattern.charAt(pattern_index) == text.charAt(shift+pattern_index) ){ // if character match
               pattern_index--;
            }
            if (pattern_index < 0){ // if pattern length is zero match is found
                answer.add(shift);
               if(shift+pattern_len < text_len){
                    shift = shift + (pattern_len - result[text.charAt(shift+pattern_len)]); // shift to correct character
               }
               else{
                    shift = shift + 1;
               }
            }
            else{
               int check = (pattern_index-result[text.charAt(shift+pattern_index)]);  // if above courses work
               if( check > 1){
                   shift = shift + check;
               }
               else{
                    shift = shift + 1;
               }
            }
        }

        return answer;      // return the array that contains the index of matched things

    }
    // Function to check the last occurence of the characters
    private static int[] precomputebm(String pattern) {
        int[] last_occurance = new int[256];

        Arrays.fill(last_occurance,-1);

        for(int i=0;i < pattern.length();i++){
            last_occurance[pattern.charAt(i)] = i;      // saivng the last occurence
        }
        return last_occurance;
    }
    // Function to compute the KMP value
    private static List<Integer> kmp_search(String text, String pattern) {
        List<Integer> answer = new ArrayList<Integer>();    // list for holding the values
        int text_len = text.length(); // finding the length of text
        int pattern_len = pattern.length(); // finding the length of pattern
        int[] result = precomputekmp(pattern);
        //System.out.println(text_len);
        //System.out.println(pattern_len);
        //System.out.println(Arrays.toString(result));
        int text_index=0, pattern_index =0;
        while(text_index < text_len){
            if(pattern.charAt(pattern_index) == text.charAt(text_index)){ // if the same character are same
                pattern_index++;
                text_index++;
            }

            if(pattern_index == pattern_len){       // if the total pattern matches
                answer.add(text_index-pattern_index);
                //System.out.println("found it");
               pattern_index = result[pattern_index-1];
            }
            else if (text_index <text_len){         // if not check in the pre computed array to find the repeated pattern
                if(pattern.charAt(pattern_index)!= text.charAt(text_index)){
                if( pattern_index !=0){
                    pattern_index = result[pattern_index-1];
                }
                else
                    text_index++;
            }
            }
        }

        return answer;  // return the array that contains the index of matched things
    }

    private static List<Integer> naive_search(String text, String pattern) {
        List<Integer> answer = new ArrayList<Integer>();
        int text_len = text.length(); // finding the length of text
        int pattern_len = pattern.length(); // finding the length of pattern

        for(int i =0; i <= text_len - pattern_len; i++){        // two for loops checking for every character in text with pattern
            for(int j = 0; j < pattern_len;j++){
                if(text.charAt(i+j) != pattern.charAt(j)){
                    break;
                }
                if (j ==  pattern_len-1){

                   answer.add(i);   // adding them in to answer value
                }
            }
        }
        return answer;      // reutrn the index value
    }
    // Pre processing the kmp pattern using the following function
    public static int[] precomputekmp(String pattern){
        int pattern_len = pattern.length();     // pettern length
        int[] precom_array = new int[pattern_len];  // array to save precomputed values
        precom_array[0] = 0;
        int index = 1;
        int long_prefix = 0;  // length of longest prefix
        while(index < pattern_len){
            if(pattern.charAt(index)== pattern.charAt(long_prefix)){    // check if its same character

                long_prefix++;                                          // check for other values
                precom_array[index]= long_prefix;
                index++;            // increase the index
            }
            else{
                if(long_prefix!=0){
                    long_prefix = precom_array[long_prefix-1];  // go back and check for similar character by changing the long_prefix
                }
                else{
                    precom_array[index] = 0;
                    index++;
                }

            }
        }

        return precom_array;            // return the computed array
    }


}
