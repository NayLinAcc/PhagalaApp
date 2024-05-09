package com.example.phagala;

public class KeyNameMapping {

    // Define a method to retrieve the name based on the key
    public String getNameForKey(String key) {
        // Implement your logic to map keys to names here
        // You can use if-else statements, switch-case, or any other method to map keys to names
        // For this example, we'll use a simple switch-case statement
        switch (key) {
            case "android.pdf":
                return "ဘာဂြိုပ်ဖာဂၠ";
            case "anyibanking.pdf":
                return "သုခဝိဟာရဳ";
            case "ayambanking20.pdf":
                return "ဍုင်ရေဝ်";
            case "ayaibankinguser.pdf":
                return "ပရိယတ္တိ ဓမ္မဒါယာဒ";
            case "bkbz.pdf":
                return "ဂကောံ";
            case "bstory.pdf":
                return "စၟတ်";
            case "c2018_2023.pdf":
                return "၂၀၁၈ - ၂၀၁၉";
            case "chhh.pdf":
                return "၁၃၄၅";
            default:
                return null; // Return null for keys without corresponding names
        }

    }
}
