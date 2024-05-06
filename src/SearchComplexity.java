// SearchComplexity.java
// Starter code for final programming assignment in CIT-63 Java Programming Spring 2024

import java.util.Arrays;
import java.util.Scanner;

public class SearchComplexity {

    // Linear Search Method
    public static int linearSearch(int[] array, int target) {
        long startTime = System.currentTimeMillis(); //Starts timer
        int iterations = 0;
        for (int i = 0; i < array.length; i++) {
            iterations++;
            if (array[i] == target) {
                System.out.println("Linear search iterations: " + iterations);
                long endTime = System.currentTimeMillis(); //ends timer
                System.out.println(" " + (endTime - startTime) + "ms"); // total time
                return i;  // Returns index of found element
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Linear search iterations: " + iterations); // end timer
        System.out.println(" " + (endTime - startTime) + "ms"); // total time
        return -1;  // Target not found
    }

    // Binary Search Method
    public static int binarySearch(int[] array, int target) {
        long startTime = System.currentTimeMillis(); //start timer
        int left = 0;
        int right = array.length - 1;
        int iterations = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            iterations++;

            if (array[mid] == target) {
                System.out.println("Binary search iterations: " + iterations);
                long endTime = System.currentTimeMillis(); //end timer
                System.out.println(" " + (endTime - startTime) + "ms");
                return mid;
            }

            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("Binary search iterations: " + iterations);
        long endTime = System.currentTimeMillis(); //end timer
        System.out.println(" " + (endTime - startTime) + "ms");
        return -1;  // Target not found
    }
    static int recursiveIteration = 0; //because it is recursive I need to keep track of this outside the method
    public static int recursiveBinarySearch(int[] array, int target) //I successfully implemented it but feel there was an easier ways as this is just longer than binarySearch()
    {
        long startTime = System.currentTimeMillis();
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            recursiveIteration++;

            if (array[mid] == target) {
                System.out.println("recursive Binary search iterations: " + recursiveIteration);
                long endTime = System.currentTimeMillis(); //end timer
                System.out.println(" " + (endTime - startTime) + "ms"); //total time
                return mid;
            }
            int[] newArray;
            if (array[mid] < target) {
                if (((array.length) % 2) == 0) //I needed to be careful with rounding the middle index
                {
                     newArray = new int[(array.length)/2];
                }else
                {
                    newArray = new int[(array.length - 1) / 2];
                }
                int n = 0;
                for (int i = mid + 1; i <= right; i++) {
                    if (array[i] >= array[mid])
                    {
                        newArray[n]= array[i];
                        n++;
                    }
                }

            } else
            {
                newArray = new int[((array.length-1)/2)];
                int n = 0;
                for (int i = 0; i < right; i++) {
                    if (array[i] < array[mid])
                    {
                        newArray[n]= array[i];
                        n++;
                    }
                }

            }
            return recursiveBinarySearch(newArray,target); // recursion
        }

        System.out.println(" Recursive Binary search iterations: " + recursiveIteration);
        long endTime = System.currentTimeMillis(); //end timer
        System.out.println(" " + (endTime - startTime) + "ms"); //total time
        return -1;  // Target not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of elements in array:");
        int n = scanner.nextInt();
        int[] array = new int[n];



        boolean valid = false;
        while (!valid)
        {
            System.out.println("Enter elements:");
            for (int i = 0; i < n; i++)
            {
                if (scanner.hasNextInt())
                {
                    array[i] = scanner.nextInt();
                    valid = true;
                } else
                    {
                        valid = false;
                        System.out.println("Please enter valid integers");
                        scanner.nextLine(); //clears the scanner to prevent infinite loop
                        break;
                    }
            }
        }

        System.out.println("Enter target number to search:");
        int target = scanner.nextInt();

        // Linear Search
        int linearResult = linearSearch(array, target);
        System.out.println((linearResult == -1) ? "Target not found by linear search." :
                "Target found by linear search at index: " + linearResult);

        // Binary Search (Array must be sorted)
        Arrays.sort(array);
        int binaryResult = binarySearch(array, target);
        System.out.println((binaryResult == -1) ? "Target not found by binary search." :
                "Target found by binary search at index: " + binaryResult);

        // Recursive Binary Search (Array must be sorted)
        Arrays.sort(array);
        int recursiveBinaryResult = recursiveBinarySearch(array, target);
        System.out.println((recursiveBinaryResult == -1) ? "Target not found by recursive binary search." :
                "Target found by recursive binary search at index: " + recursiveBinaryResult);

        scanner.close();
    }
}
