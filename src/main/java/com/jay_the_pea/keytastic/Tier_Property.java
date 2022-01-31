package com.jay_the_pea.keytastic;

public class Tier_Property {
    private static String[][] tier_chances =
            {       {"heroic","1"},
                    {"ultra_rare","2"},
                    {"rare","3"},
                    {"uncommon","4"},
                    {"common","5"}
            };
    private static int total_range = calc_total_range();

    private static int calc_total_range(){
        int foo = 0;
        for(String[] tier : tier_chances){
            foo+= Integer.parseInt(tier[1]);
        }
        return foo;
    }

    public static String generate_Tier(){
        int randomNumber = (int) Math.floor(Math.random()*total_range);
        int upper_evaluation_cap = 0;
        System.out.println("generated Number: " + randomNumber);
        System.out.println("calulated Total Range:" + total_range);
        for(String[] tier : tier_chances){
            upper_evaluation_cap+= Integer.parseInt(tier[1]);
            if(randomNumber <= upper_evaluation_cap){
                System.out.println("returning Tier: " + tier[0]);
                return tier[0];
            }
        }
        String[] returnbuffer = tier_chances[tier_chances.length-1];
        return returnbuffer[0];
    }

}
