package books;

import java.util.Arrays;

public class StoresAndHouses {
    public static int search(int houseLoc, int[] stores, int start, int end, int closestStore) {
        int mid = start + (end - start) / 2;
        if (stores[mid] == houseLoc) {
            return stores[mid];
        } else if (Math.abs(stores[mid] - houseLoc) < Math.abs(houseLoc - closestStore)) {
            closestStore = stores[mid];
        }
        if (start == end)
            return closestStore;
        else if (houseLoc < stores[mid])
            closestStore = search(houseLoc, stores, start, mid - 1, closestStore);
        else
            closestStore = search(houseLoc, stores, mid + 1, end, closestStore);
        return closestStore;
    }

    private static int[] findClosestStoreToMyHouse(int[] stores, int[] houses) {
        Arrays.sort(stores);
        int[] closestStores = new int[houses.length];
        for (int i = 0; i < houses.length; i++) {
            closestStores[i] = search(houses[i], stores, 0, stores.length - 1, Integer.MAX_VALUE);
        }
        return closestStores;
    }

    public static void main(String[] args) {
        int[] houses = {5, 10, 17};
        int[] stores = {1, 5, 20, 11, 16};
        int[] closestStoreToMyHouse = findClosestStoreToMyHouse(stores, houses);
        System.out.println("closestStoreToMyHouse([5, 11, 16]) = " + Arrays.toString(closestStoreToMyHouse));
    }


}