package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph {

	public static ArrayList<ArrayList<Integer>> ConCom(int[][] edges) {
		ArrayList<ArrayList<Integer>> mainLs = new ArrayList<>();
		boolean[] visited = new boolean[edges.length];
		for(int i =0;i<edges.length;i++) {
			if(!visited[i]) {
				ArrayList<Integer> ls = new ArrayList<>();
				ConCom(edges,i,visited,ls);
				mainLs.add(ls);
			}
		}
		return mainLs;
	}

	public static ArrayList<Integer> ConCom(int[][] edges, int sv, boolean[] visited, ArrayList<Integer> ls) {
		ls.add(sv);
		visited[sv] = true;
		int n = edges.length;

		for (int i = 0; i < n; i++) {
			if (edges[sv][i] != 0 && !visited[i]) {
				printHelper(edges, i, visited);
			}
		}
		return ls;
	}

	public static boolean isConnected(int[][] edges) {
		boolean[] visited = new boolean[edges.length];
		isConnected(edges, 0, visited);
		for (int i = 0; i < edges.length; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	public static boolean isConnected(int[][] edges, int sv, boolean[] visited) {
		visited[sv] = true;
		for (int i = 0; i < edges.length; i++) {
			if (edges[sv][i] != 0 && !visited[i]) {
				isConnected(edges, i, visited);
			}
		}
		return true;
	}

	public static ArrayList<Integer> getPathBFS(int[][] edges, int sv, int ev) {
		boolean[] visited = new boolean[edges.length];
		Queue<Integer> childNodes = new LinkedList<>();
		childNodes.add(sv);
		visited[sv] = true;

		HashMap<Integer, Integer> map = new HashMap<>();

		childNodes.add(sv);
		boolean isFound = false;

		while (!childNodes.isEmpty()) {
			int svTemp = childNodes.remove();
			if (svTemp == ev) {
				isFound = true;
				break;
			}
			for (int i = 0; i < edges.length; i++) {
				if (edges[svTemp][i] != 0 && !visited[i]) {
					map.put(i, svTemp);
					visited[i] = true;
					childNodes.add(i);
				}
			}
		}

		if (isFound) {
			ArrayList<Integer> ans = new ArrayList<>();
			int index = ev;
			while (index != sv) {
				ans.add(index);
				index = map.get(index);
			}
			ans.add(sv);
			return ans;
		} else {
			return null;
		}

	}

	public static ArrayList<Integer> getPath(int[][] edges, int sv, int ev) {
		boolean[] visited = new boolean[edges.length];
		return getPath(edges, sv, ev, visited);

	}

	public static ArrayList<Integer> getPath(int[][] edges, int sv, int ev, boolean[] visited) {
		if (sv == ev) {
			ArrayList<Integer> arr = new ArrayList<>();
			arr.add(ev);
			return arr;
		}
		visited[sv] = true;
		ArrayList<Integer> ans = null;
		for (int i = 0; i < edges.length; i++) {
			if (edges[sv][i] != 0 && !visited[i]) {
				ArrayList<Integer> temp = getPath(edges, i, ev, visited);
				if (temp != null) {
					ans = temp;
					ans.add(sv);
					return ans;
				}
			}
		}

		return ans;

	}

	public static boolean hasPath(int[][] edges, int sv, int ev) {
		boolean[] visited = new boolean[edges.length];
		visited[sv] = true;
		return hasPath(edges, sv, ev, visited);
	}

	public static boolean hasPath(int[][] edges, int sv, int ev, boolean[] visited) {

		boolean ans = false;
		for (int i = 0; i < edges.length; i++) {
			if (edges[sv][i] != 0 && !visited[i]) {

				if (i == ev) {
					return true;
				} else {
					visited[i] = true;
					boolean temp = hasPath(edges, i, ev, visited);
					if (temp == true) {
						ans = true;
					}
				}

			}
		}
		return ans;

	}

	public static void printBFS(int[][] edges) {
		if (edges.length == 0) {
			return;
		}
		boolean[] visited = new boolean[edges.length];
		Queue<Integer> childNodes = new LinkedList<>();
		visited[0] = true;
		childNodes.add(0);
		while (!childNodes.isEmpty()) {
			int node = childNodes.remove();
			System.out.print(node + " ");
			for (int i = 0; i < edges.length; i++) {
				if (edges[node][i] != 0 && !visited[i]) {
					childNodes.add(i);
					visited[i] = true;

				}
			}
		}
	}

	public static void printHelper(int[][] edges, int sv, boolean[] visited) {
		System.out.println(sv);
		visited[sv] = true;
		int n = edges.length;

		for (int i = 0; i < n; i++) {
			if (edges[sv][i] != 0 && !visited[i]) {
				printHelper(edges, i, visited);
			}
		}

	}

	public static void print(int[][] edges, int sv) {
		boolean[] visited = new boolean[edges.length];
		for (int i = 0; i < edges.length; i++) {

			printHelper(edges, i, visited);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		int e;
		Scanner s = new Scanner(System.in);

		n = s.nextInt();
		e = s.nextInt();

		int[][] edges = new int[n][n];

		for (int i = 0; i < e; i++) {
			int fv = s.nextInt();
			int sv = s.nextInt();
			edges[fv][sv] = 1;
			edges[sv][fv] = 1;
		}

		print(edges, 0);
	}

}
