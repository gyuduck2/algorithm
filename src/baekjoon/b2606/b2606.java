package baekjoon.b2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2606 {
    public static int count = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] g = new int[C+1][C+1];
        boolean[] visit = new boolean[C+1];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.valueOf(st.nextToken());
            int y = Integer.valueOf(st.nextToken());
            g[x][y] = g[y][x] = 1;
        }
        dfs(g, visit, 1);
        System.out.println(count);

        visit = new boolean[C+1];
        System.out.println(bfs(g, visit));
    }

    public static void dfs(int[][] g, boolean[] visit, int now) {
        visit[now] = true;
        count++;

        for(int i=0; i<g[now].length; i++) {
            if(g[now][i] == 1 && visit[i] == false) {
                dfs(g, visit, i);
            }
        }
    }

    public static int bfs(int[][] g, boolean[] visit) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visit[1] = true;
        int sum = 0;

        while(!queue.isEmpty()) {
            int c = queue.poll();
            for(int i=0; i<g[c].length; i++) {
                if(g[c][i] == 1 && visit[i] == false) {
                    queue.offer(i);
                    visit[i] = true;
                    sum++;
                }
            }
        }

        return sum;
    }
}
