package search;

public class BinarySearchMin {
    /*
    Pred: args[] - int array, written as string array &&
           && exists int k in [0, args.length): forall int i, j in [0, k] i < j (int)args[i] > (int)args[j] &&
                                             && forall int i, j in [k+1, args.length) i < j (int)args[i] < (int)args[j] &&
                                             && k+1 < args.length -> (int)args[k] <= (int)args[k+1]
        Post: wrote minimum value in (int)args[] if it exists, an error message otherwise
     */
    public static void main(String[] args) {
        //Pred
        int[] numbers = new int[args.length];
        //Pred && int[] numbers && numbers.length=args.length
        int i = 0;
        // Pred*: int numbers[] && numbers.length == args.length && для всех int j in [0, i - 1]: numbers[j] == (int)args[j]
        // Pred && i == 0 && Pred*
        // Inv: Pred && Pred*
        while (i < args.length) {
            // Pred && Pred* && i < args.length
            numbers[i] = Integer.parseInt(args[i]);
            // Pred && Pred* && i < args.length && numbers[i] == (int)args[i]
            i++;
            // Pred && Pred* && i == i'+ 1
        }
        //Pred && Pred* && i == args.length
        //Data:int numbers[] && numbers.length == args.length && forall int i in [0, arr.length): arr[i] == (int)args[i] &&
        //          && exists int k in [0, arr.length): forall int i, j in [0, k] i < j arr[i] > arr[j] &&
        //                                            && forall int i, j in [k+1, arr.length) i < j arr[i] < arr[j] &&
        //                                            && k+1 < arr.length -> arr[k] <= arr[k+1]
        if (numbers.length > 0) {
            //Data && numbers.length > 0 &&
            // numbers.length > 0 => min value in numbers[] exists
            final int res = recurBinarySearchMin(numbers);

            //Data && res== min value in numbers[]

            System.out.println(res);
            // Data && wrote min value in numbers[]  =>  wrote min value in (int)args[]
        } else {
            //numbers.length==0 && numbers.length==0 => min value in (int)args[] doesn't exist
            System.out.println("Empty Arrays are not allowed");
            //error message
        }
    }

    /*
    Data: int numbers[], numbers.length > 0 &&
            && exists int k in [0, arr.length): forall int i, j in [0, k] i < j arr[i] > arr[j] &&
                                             && forall int i, j in [k+1, arr.length) i < j arr[i] < arr[j] &&
                                             && k+1 < arr.length -> arr[k] <= arr[k+1]

    Pred: Data

    Post: R == arr[k] - min value in arr[]
     */
    public static int iterBinarySearchMin(int[] numbers) {
        // Pred
        int l = -1, r = numbers.length - 1;
        // Pred && l>=0 && r <= numbers.length - 1
        // Inv: Pred && 0 <= l <= k < r <= arr.length

        while (r > l + 1) {
            // Pred && 0 <= l <= k < r <= arr.length && l + 1 < r
            final int m = (l + r) / 2;
            // Pred && 0 <= l <= k < r <= arr.length && l + 1 < r && m == (l + r) / 2


            if (numbers[m] < numbers[m + 1]) {
                // Pred &&  0<= l <= k < r <= arr.length && r>l+1 &&
                // m == (l + r) / 2 && l < m < r && numbers[m] < numbers[m + 1] &&
                // l < m < r && l <= k < r && numbers[m] < numbers[m+1]  =>  0 <= l <=k < mid < r <= numbers.length
                r = m;
                // Pred && 0 < l <= k < r < r` <= numbers.length
                // len(l, r) == r - l == r - (l + r`) / 2 == 1/2 * len(l, r`)
            } else {
                // Pred &&  0<= l <= k < r <= arr.length && r>l+1 &&
                // m == (l + r) / 2 && l < m < r && numbers[m] >= numbers[m+1] &&
                // l < m < r && l <= k < r && numbers[m] >= numbers[m+1]  =>  0 <= l  < mid <=k < r <= numbers.length
                l = m;
                // Pred && 0 <= l` < l <= k < r <= numbers.length && l == r == (l` + r) / 2
                // len(l, r) == r - l == (l` + r) / 2 - l == 1/2 * len(l`, r)
            }
            // Pred && 0 <= l' <= l <= k < r <= r' <= arr.length
            // len(l, r) == 1/2 * len(l', r')
        }
        // Pred && 0 <= l<= k < r <= arr.length && r <= l + 1  =>  r - l == 1  => l==k => R == numbers[l] - min value in numbers[]
        return numbers[l];
    }

    /*
     Pred: Data && 0 <= l <= k < r <= arr.length
     Post: Post: R == numbers[k] - min value in numbers[]
     */
    public static int recurBinarySearchMin(int[] a) {
        // Pred
        int l = -1, r = a.length - 1;
        // l = -1, r = a.length
        return recurBinarySearchMin(a, l, r);
        //Post
    }

    /*
     Pred: Data && 0 <= l< r <= numbers.length
     Post: Post: R == numbers[k] - min value in numbers
     */
    public static int recurBinarySearchMin(int[] a, int l, int r) {
        // Pred && l>=0 && r <= numbers.length - 1
        if (r > l + 1) {
            // Pred && 0 <= l <= k < r <= arr.length && r>l+1
            int m = (l + r) / 2;
            // Pred && 0 <= l <= k < r <= arr.length && r>l+1 &&
            // m == (l + r) / 2

            if (a[m] < a[m + 1]) {
                // Pred &&  0<= l <= k < r <= arr.length && r>l+1 &&
                // m == (l + r) / 2 && l < m < r && numbers[m] < numbers[m + 1] &&
                // l < m < r && l <= k < r && numbers[m] < numbers[m+1]  =>  0 <= l <=k < mid < r <= numbers.length
                r = m;
                // Pred && 0 < l <= k < r < r` <= numbers.length
                // len(l, r) == r - l == r - (l + r`) / 2 == 1/2 * len(l, r`)
            } else {
                // Pred &&  0<= l <= k < r <= arr.length && r>l+1 &&
                // m == (l + r) / 2 && l < m < r && numbers[m] >= numbers[m+1] &&
                // l < m < r && l <= k < r && numbers[m] >= numbers[m+1]  =>  0 <= l  < mid <=k < r <= numbers.length
                l = m;
                // Pred && 0 <= l` < l <= k < r <= numbers.length && l == r == (l` + r) / 2
                // len(l, r) == r - l == (l` + r) / 2 - l == 1/2 * len(l`, r)
            }
            // Input && 0 <= l' <= l <= k < r <= r' <= arr.length
            // len(l, r) == 1/2 * len(l', r')
            return recurBinarySearchMin(a, l, r);
        } else {
            // Pred && 0 <= l <= k < r <= arr.length && r - l <= 1  =>  r - l == 1  =>  l == k  =>  R == arr[l] - min value in numbers[]
            return a[r];

        }

    }
}
