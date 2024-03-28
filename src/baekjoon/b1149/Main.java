package baekjoon.b1149;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());

        int[][] arr = new int[N][N];
        int[][] dp = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.valueOf(st.nextToken());
            int g = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());

            arr[i][0] = r;
            arr[i][1] = g;
            arr[i][2] = b;
        }

        for(int i=1; i<arr.length; i++) {
            arr[i][0] = Integer.min(arr[i][0] + arr[i-1][1], arr[i][0] + arr[i-1][2]);
            arr[i][1] = Integer.min(arr[i][1] + arr[i-1][0], arr[i][1] + arr[i-1][2]);
            arr[i][2] = Integer.min(arr[i][2] + arr[i-1][0], arr[i][2] + arr[i-1][1]);
        }

        int min = Integer.min(arr[N - 1][0], arr[N - 1][1]);
        System.out.println(Integer.min(min, arr[N-1][2]));
    }
}
