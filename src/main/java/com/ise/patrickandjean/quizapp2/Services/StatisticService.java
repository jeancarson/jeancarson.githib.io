package com.ise.patrickandjean.quizapp2.Services;

//Used to give the mean as a decimal
import java.text.DecimalFormat;
import java.util.Arrays;

public class StatisticService {
    /**
     * @param scores The array of scores, for a particular game mode, either from jsut a particular user or all users
     * @return the mean of all the scores passed in
     */
    public static double calculateMean(int[] scores) {
        float total = 0;
        for (int score : scores) {
            total += score;
        }
        //show score to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(total / scores.length));
    }


    /**
     * @param scores An array of the user scores
     * @return the median
     */
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
    ///This function is an altered version of one I found online at the link above, I shortened it by using the calculate mean
    ///function I already made.
    public static double calculateStandardDeviation(int[] scores) {
        /// Vars
        double sum = 0;
        double sq_sum = 0;

        /// Calc Sums
        for (double val : scores) {
            sum += val;
            sq_sum += val * val;
        }

        ///Using mean method already defined
        double mean = calculateMean(scores);
        double variance = sq_sum / scores.length - mean * mean;
        return Math.sqrt(variance);
    }
}

