package ie.gmit.sw.ai.traversers;

import java.util.Deque;
import java.util.LinkedList;

import ie.gmit.sw.ai.maze.Nade;

public class BruteForceTraversator implements Traversator{
	private boolean dfs = false;

	public BruteForceTraversator(boolean depthFirst){
		this.dfs = depthFirst;
	}

	public void traverse1(Nade[][] maze, Nade node) {
        long time = System.currentTimeMillis();
    	int visitCount = 0;

		Deque<Nade> queue = new LinkedList<Nade>();
		queue.offer(node);

		while (!queue.isEmpty()){
			node = queue.poll();
			node.setVisited(true);
			visitCount++;

			if (node.isGoalNode()){
		        time = System.currentTimeMillis() - time; //Stop the clock
		        TraversatorStats.printStats(node, time, visitCount);
				break;
			}

			try { //Simulate processing each expanded node
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Nade[] children = node.children(maze);
			for (int i = 0; i < children.length; i++) {
				if (children[i] != null && !children[i].isVisited()){
					children[i].setParent(node);
					if (dfs){
						queue.addFirst(children[i]);
					}else{
						queue.addLast(children[i]);
					}
				}
			}
		}
	}

	@Override
	public void traverse(Nade[][] maze, Nade start) {
		// TODO Auto-generated method stub

	}
}