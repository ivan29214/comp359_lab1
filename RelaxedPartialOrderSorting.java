import java.util.*;

public class RelaxedPartialOrderSorting {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        // Generating 100, 500 and 1000 random integers between 0 and 1000
        for (int i = 0; i < 100; i++) {
            int ran_num = random.nextInt(1000);  // Generate a random integer between 0 and 999
            numbers.add(ran_num);
        }


        System.out.println("Original random numbers: " + numbers);

        // storing the start time of the algorithm
        long startTime = System.nanoTime();

        // Sorting using a custom Comparator
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                // Ignore the last digit by dividing by 10
                int truncatedNum1 = num1 / 10;  // Remove the last digit
                int truncatedNum2 = num2 / 10;

                // comparing the truncated values
                if (truncatedNum1 < truncatedNum2) {
                    return -1;
                } else if (truncatedNum1 > truncatedNum2) {
                    return 1;
                } else {
                    // If truncated values are the same, compare the original numbers
                    return Integer.compare(num1, num2);
                }
            }
        });


        long endTime = System.nanoTime();


        System.out.println("Sorted with relaxed partial order: " + numbers);

        // Calculating the total runtime of the algorithm in nanoseconds
        long totalTime = (endTime - startTime);
        System.out.println("Total runtime: " + totalTime + " ns");
    }
}
