package K23cnt.qxt.day2.K23cnt.qxt.day2.tight_toosely_coupling;
import java.util.Arrays;
import java.util.stream.Stream;
// Loosely coupling
interface SortAlgorithm {
    void sort(int[] array);
}
class LooselyBubbleSortAlgorithm implements SortAlgorithm{
    @Override
    public void sort(int[] array) {
        System.out.println("Sorted using bubble sort algorithm");
                Arrays.stream(array).sorted().forEach(System.out::println);
    }
}
public class LooselyCoupledService {
    private SortAlgorithm sortAlgorithm;
    public LooselyCoupledService() {}
    public LooselyCoupledService(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }
    public void complexBusiness(int[] array) {
        sortAlgorithm.sort(array);
    }
    public static void main(String[] args) {
        LooselyCoupledService sortAlgorithm =
                new LooselyCoupledService(new
                        LooselyBubbleSortAlgorithm());
        sortAlgorithm.complexBusiness(new
                int[]{11,21,13,42,15});
    }
}
