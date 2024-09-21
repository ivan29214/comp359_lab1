import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Partial_C {

    //Creating comparator to compare the integers
    public static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer num1, Integer num2) {
            return Integer.compare(num1, num2);  // Standard integer comparison
        }
    }

    // Partial sort function using a heap (similar to C++ std::partial_sort)
    public static void partialSortHeap(Integer[] arr, int k, Comparator<Integer> comparator) {
        // Ensure k is within the array bounds
        k = Math.min(k, arr.length);

        // Min-heap
        PriorityQueue<Integer> heap = new PriorityQueue<>(comparator);

        // Add the first k elements to the heap
        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }

        // Process the remaining elements
        for (int i = k; i < arr.length; i++) {
            if (comparator.compare(arr[i], heap.peek()) < 0) {
                heap.poll();  // Remove the root of the heap
                heap.add(arr[i]);  // Add the new element to the heap
            }
        }

        //Extracting the k elements that need to be sorted
        for (int i = 0; i < k; i++) {
            arr[i] = heap.poll();
        }
    }

    public static void main(String[] args) {
        //Array of integers to partially sort
        Integer[] numbers = {153, 42, 327, 91, 85, 130, 211};


        int k = 4;

        //time used as nano seconds for more precise comparison
        long startTime = System.nanoTime();

       //performing partial sort using IntComparator
        partialSortHeap(numbers, k, new IntComparator());


        long endTime = System.nanoTime();


        System.out.println("Partially Sorted: " + Arrays.toString(numbers));

        //Processing the actual time taken for the algorithm
        long duration = endTime - startTime;
        System.out.println("Runtime in nanoseconds: " + duration);
    }
}
