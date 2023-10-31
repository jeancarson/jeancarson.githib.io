package com.ise.patrickandjean.quizapp2.Services;

import java.text.DecimalFormat;
import java.util.Arrays;

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

    public static double calculateMedian(int[] scores) {
        /// Sort the scores array
        Arrays.sort(scores);

        /// Calculate median
        double median;
        if (scores.length % 2 == 0) {
            median = ((double) scores[scores.length / 2] + (double) scores[scores.length / 2 - 1]) / 2;
        } else {
            median = scores[scores.length / 2];
        }

        ///
        return median;
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

