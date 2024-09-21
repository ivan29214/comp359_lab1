import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Random;

public class Partial_C {

    //Creating comparator to compare the integers
    public static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return Integer.compare(a, b); //using compare function which belongs to interface Comparator
        }
    }

    //Partial sort function using a heap type data structure priority queue(similar to C++ std::partial_sort)
    public static void partialSortHeap(Integer[] arr, int k, Comparator<Integer> comparator) {
        //Ensure k is within the array bounds
        k = Math.min(k, arr.length);

        PriorityQueue<Integer> heap = new PriorityQueue<>(comparator);

        //Add the first k elements to the heap
        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (comparator.compare(arr[i], heap.peek()) < 0) {
                heap.poll();  //remove the root of the heap
                heap.add(arr[i]);  //add the new element to the heap
            }
        }

        //The top k elements to be sorted
        for (int i = 0; i < k; i++) {
            arr[i] = heap.poll();
        }
    }

    public static void main(String[] args) {
        //creating an array of 100 random integers between 0-999
        Integer[] numbers = new Integer[100];
        Random random = new Random();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(1000);
        }

        
        int k = 10;//specifying k (number of elements to partially sort)

       
        System.out.println("Original Array: " + Arrays.toString(numbers));

        //Record algorithm run-time in nanoseconds for better comparison
        long startTime = System.nanoTime();

        // Perform partial sort using IntComparator
        partialSortHeap(numbers, k, new IntComparator());
        
        long endTime = System.nanoTime();

        
        System.out.println("Partially Sorted Array: " + Arrays.toString(numbers));

        long duration = endTime - startTime;
        System.out.println("Runtime in nanoseconds: " + duration);
    }
}
