package baekjoon.b2559;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int K = Integer.valueOf(st.nextToken());

        int[] ts = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ts[i] = Integer.valueOf(st.nextToken());
        }

        int min = Integer.MIN_VALUE;
        for(int i=0; i<=N-K; i++) {
            int sum = 0;
            for(int j=0; j<K; j++) {
                sum += ts[i+j];
            }
            min = Integer.max(min, sum);
        }

        System.out.println(min);
    }
}
