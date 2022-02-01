package com.jay_the_pea.keytastic;

public class Tier_Property {
    private static int calc_total_range(String[][] tiers){
        int foo = 0;
        for(String[] tier : tiers){
            foo+= Integer.parseInt(tier[1]);
        }
        return foo;
    }

    public static String generate_Tier(String[][] tiers){
        int total_range = calc_total_range(tiers);
        int randomNumber = (int) Math.floor(Math.random()*total_range);
        int upper_evaluation_cap = 0;
        System.out.println("generated Number: " + randomNumber);
        System.out.println("calulated Total Range:" + total_range);
        for(String[] tier : tiers){
            upper_evaluation_cap+= Integer.parseInt(tier[1]);
            if(randomNumber <= upper_evaluation_cap){
                System.out.println("returning Tier: " + tier[0]);
                return tier[0];
            }
        }
        String[] returnbuffer = tiers[tiers.length-1];
        return returnbuffer[0];
    }

}
