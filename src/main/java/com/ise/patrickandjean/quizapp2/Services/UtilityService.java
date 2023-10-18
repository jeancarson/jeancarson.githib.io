package com.ise.patrickandjean.quizapp2.Services;

public class UtilityService {

    /**
     * @param Num The number to clamp.
     * @param Min The minimum value of the number after clamping.
     * @param Max The maximum value of the number after clamping.
     * @return The clamped number.
     */
    public static int clamp(int Num, int Min, int Max)
    {
        return Math.max(Min, Math.min(Num, Max));
    }

    /**
     * @param Num The number to clamp.
     * @param Min The minimum value of the number after clamping.
     * @return The clamped number.
     */
    public static int clamp(int Num, int Min)
    {
        return Math.max(Num, Min);
    }

    /**
     * @param Array The array to convert into a string.
     * @param Indent The start indentation level (5 is recommended)
     * @return The array in formatted string form.
     */
    private static String arrayToDisplayString(Object[] Array, int Indent)
    {
        /// Variables
        StringBuilder Result = new StringBuilder();
        Result.append("{\n");

        /// Go!
        for (int Idx = 0; Idx < Array.length; Idx++)
        {
            /// Vars
            Object Value = Array[Idx];
            Result.append((" ").repeat(Indent)).append(Idx).append(" : ");

            /// Handle recursion, otherwise append regular
            if (Value instanceof Object[]) {
                Result.append(arrayToDisplayString((Object[]) Value, Indent + 5));
            } else {
                Result.append(Value.toString());
            }

            Result.append("\n");
        }

        /// Finish off
        Result.append( (" ").repeat(clamp(Indent - 5, 0))).append("}");
        return Result.toString();
    }

    final private static String ANSI_RESET = "\u001b[0m";
    final private static String ANSI_FILECOLOR = "\u001b[38;2;32;102;198m";
    final private static String ANSI_FUNCCOLOR = "\u001b[38;2;207;68;73m";
    final private static String ANSI_TEXTCOLOR = "\u001b[38;2;29;155;86m";

    /**
     * System.out.println wrapper - with colours and array unwrapping.
     * @param Values Anything you want! :D
     */
    public static void print(Object... Values)
    {
        /// Vars
        StringBuilder FinalOutput = new StringBuilder();
        StackTraceElement Caller = Thread.currentThread().getStackTrace()[2];
        String CallerFileName = Caller.getFileName() != null ? Caller.getFileName() : "Unknown.java";

        /// Iterate, adding everything to the final output :)
        for (Object field: Values) {
            if (field == null)
            {
                field = "[null]";
            }

            if (field.getClass().isArray())
            {
                FinalOutput.append(arrayToDisplayString((Object[]) field, 5)).append(" ");
                continue;
            }

            FinalOutput.append(field).append(" ");
        }

        /// Output :>
        System.out.println(ANSI_FILECOLOR + "[" + CallerFileName.substring(0, CallerFileName.length() - 5) + "] " + ANSI_FUNCCOLOR + "[" + Caller.getMethodName() + ":" + Caller.getLineNumber() + "] " + ANSI_TEXTCOLOR + FinalOutput + ANSI_RESET);
    }
}