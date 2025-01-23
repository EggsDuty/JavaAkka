public class Main {
    public static void main(String [] args) throws InterruptedException {
        long points = 30_000_000_000L;
        //long points = 1_000_000;

        ApproximatePi.Execute(points, 1);
        ApproximatePi.Execute(points, 6);
        ApproximatePi.Execute(points, 12);
    }
}

/*
Pagrindinė gija siunčia taškus aktoriams po lygiai. Kai jie baigia apdoroti visus taškus esančius
pašto dežutėje, jie atsiunčia rezultatus atgal pagrindiniai gijai. Ji palaukia, kol visi aktoriai
pabaigs darbą. Tada sudeda visus rezultatus ir išveda į ekraną.
 */

/*
1 gija - 612.3s
6 gijos - 502.4s
12 gijos - 398.1s
 */

/*
1 gija - 784.73s
6 gija - 189.13s
12 gija - 149.5s
 */
