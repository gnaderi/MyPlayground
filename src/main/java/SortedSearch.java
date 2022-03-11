
public class SortedSearch {
    public static int countNumbers(int[] sortedArray, int lessThan) {
        int index =sortedArray.length;

        if(index<100){
            for(int i=0;i<index;i++){
                if (sortedArray[i]>=lessThan){
                    return i;
                }
            }
        }
        int low=0;
        int high=sortedArray.length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid] < lessThan) {
                low = mid + 1;
            } else if (sortedArray[mid] >= lessThan) {
                index = mid;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
    }
}