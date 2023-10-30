package com.ise.patrickandjean.quizapp2.Services;

import java.text.DecimalFormat;

public class StatisticService {
    public static double calculateMean(int[] scores) {
        float total = 0;
        for (int score : scores) {
            total += score;
        }
        //show score to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(total / scores.length));
    }

    public static int calculateMode(int[] scores) {
        int max = 0;
        int mode = 0;
        for (int score : scores) {
            int count = 0;
            for (int occurence : scores) {
                if (occurence == score) {
                    count++;
                }
            }
            if (count >= max) {
                max = count;
                mode = score;
            }
        }
        return mode;
    }

    /// https://stackoverflow.com/questions/58142956/computing-variance-standard-deviation-java
    public static double calculateStandardDeviation(int[] scores) {
        /// Vars
        double sum = 0;
        double sq_sum = 0;

        /// Calc Sums
        for (double val : scores) {
            sum += val;
            sq_sum += val * val;
        }

        ///
        double mean = sum / scores.length;
        double variance = sq_sum / scores.length - mean * mean;
        return Math.sqrt(variance);
    }
}

