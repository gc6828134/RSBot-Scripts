package simplechop.jobs;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Chop extends Node {

	private final int[] treeId = { 38760, 38782, 38783, 38784, 38785, 38786, 38787, 38788 };
	private SceneObject lastClicked = null;

	private boolean isChopping() {
		if (lastClicked != null && !lastClicked.validate()) {
			lastClicked = null;
			return false;
		}
		return lastClicked != null && Players.getLocal().getAnimation() != -1;
	}

	@Override
	public boolean activate() {
		return !Inventory.isFull() && SceneEntities.getNearest(treeId) != null && !isChopping();
	}

	@Override
	public void execute() {
		SceneObject tree = SceneEntities.getNearest(treeId);
		if (tree != null) {
			if (tree.isOnScreen()) {
				if (tree.interact("Chop")) {
					lastClicked = tree;
					final Timer t = new Timer(1000);
					while (t.isRunning() && !isChopping()) {
						if (Players.getLocal().isMoving()) {
							t.reset();
						}
						Task.sleep(50);
					}
				}
			} else {
				Camera.turnTo(tree);
				if (!tree.isOnScreen()) {
					Walking.walk(tree);
				}
			}
		}
	}

}
