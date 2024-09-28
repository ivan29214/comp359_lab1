import java.util.*;

public class partialSortHeap {

        // Generate an array of random integers
        public static Integer[] generateRandomArray(int size, int bound) {
                Random random = new Random();
                Integer[] array = new Integer[size];
                for (int i = 0; i < size; i++) {
                        array[i] = random.nextInt(bound);
                }
                return array;
        }

        // Partial heap sort for finding top 'k' elements
        public static Integer[] partialHeapSort(Integer[] array, int k) {
                PriorityQueue<Integer> heap = new PriorityQueue<>(k, (x, y) -> {
                        //Custom comparison logic: ignore the last digit
                        int truncatedX = x / 10;
                        int truncatedY = y / 10;
                        return Integer.compare(truncatedY, truncatedX);//reversed order for max-heap
                });

                // Add the first k elements to the heap
                for (int i = 0; i < k; i++) {
                        heap.offer(array[i]);
                }

                //For each remaining element, compare with the root of the heap
                for (int i = k; i < array.length; i++) {
                        if (heap.comparator().compare(array[i], heap.peek()) < 0) {
                                heap.poll();  //Remove the largest element in the heap
                                heap.offer(array[i]);  //Insert the new element
                        }
                }

                //Extracting elements from the heap to form a sorted array
                Integer[] result = new Integer[k];
                for (int i = k - 1; i >= 0; i--) {
                        result[i] = heap.poll();
                }
                return result;
        }

        public static void main(String[] args) {

                int size = 1000;  // size of array
                int bound = 1000000;  // max value for integers
                int k = 1000;  // Number of elements to partially sort


                Integer[] array = generateRandomArray(size, bound);

                // measuring time elapsed by the algorithm
                long startTime = System.nanoTime();
                Integer[] partiallySorted = partialHeapSort(array, k);
                long endTime = System.nanoTime();


                System.out.println("Top " + k + " elements (partially sorted, ignoring rightmost digit): " + Arrays.toString(partiallySorted));
                System.out.println("Time for partial heap sort: " + (endTime - startTime) + " nanoseconds");
        }
}
