package com.epam.week6.pages;

public class Palindrome {

    public static boolean findPalindrome(String inputString){

        String reverse = "";
        char[] a = inputString.toCharArray();
        for(int i=inputString.length()-1; i>=0;i--){
            reverse = reverse+a[i];
        }

        if(reverse.equalsIgnoreCase(inputString)){
            System.out.println("String is Palindrome");
            return true;
        }
        else{
            System.out.println("String is not Palindrome");
            return false;
        }

    }


    public static void main(String[] args){

        findPalindrome("aabaa");
    }
}
