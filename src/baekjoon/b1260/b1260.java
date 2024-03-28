package baekjoon.b1260;

import java.util.*;
import java.io.*;

public class b1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()) + 1;
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        int[][] input = new int[N][N];
        boolean[] visit = new boolean[N];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            input[x][y] = 1;
            input[y][x] = 1;
        }

        dfs(input, visit, start);
        System.out.println();
        visit = new boolean[N];
        bfs(input, visit, start);
    }

    public static void dfs(int[][] input, boolean[] visit, int start) {
        visit[start] = true;
        System.out.print(start + " ");

        for(int i=0; i<input[start].length; i++) {
            if(input[start][i] == 1 && !visit[i]) {
                dfs(input, visit, i);
            }
        }
    }

    public static void bfs(int[][] input, boolean[] visit, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visit[start] = true;

        while(!q.isEmpty()) {
            start = q.poll();
            System.out.print(start + " ");
            for(int i=0; i< input[start].length; i++) {
                if(input[start][i] == 1 && !visit[i]) {
                    q.offer(i);
                    visit[i] = true;
                }
            }
        }
    }
}
