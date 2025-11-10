package K23cnt.qxt.day2.K23cnt.qxt.day2.tight_toosely_coupling;
import java.util.Arrays;
class BubbleSortAlgorithm{
    public void sort(int[] arr){
        System.out.println("Sắp xếp theo giải thuật BubbleSort");
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
// Perform a pass of bubble sort
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
// Swap arr[j] and arr[j+1]

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
// If no elements were swapped, the array is already
            sorted
            if (!swapped) {
                break;
            }
        }
    }
}
public class TightCouplingService
{
    private BubbleSortAlgorithm bubbleSortAlgorithm = new
            BubbleSortAlgorithm();
    public TightCouplingService(){}
    public TightCouplingService(BubbleSortAlgorithm
                                        bubbleSortAlgorithm) {
        this.bubbleSortAlgorithm = bubbleSortAlgorithm;
    }
    public void complexBusinessSort(int[] arr){
        bubbleSortAlgorithm.sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
    public static void main(String[] args) {
        TightCouplingService tCouplingService = new
                TightCouplingService();
        tCouplingService.complexBusinessSort(new
                int[]{11,21,13,42,15});
    }
}

