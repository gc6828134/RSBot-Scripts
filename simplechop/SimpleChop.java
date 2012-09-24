package simplechop;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.util.Random;

import simplechop.jobs.Chop;
import simplechop.jobs.Drop;
import simplechop.util.Paint;

@Manifest(authors = { "ArcaneSanity" }, name = "Simple Chop", description = "Simple woodcutting script")
public class SimpleChop extends ActiveScript implements PaintListener {

	private final List<Node> jobsCollection = Collections
			.synchronizedList(new ArrayList<Node>());
	private Tree jobContainer = null;

	public final void provide(final Node... jobs) {
		for (final Node job : jobs) {
			jobsCollection.add(job);
		}
		jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection
				.size()]));
	}

	@Override
	public void onStart() {
		provide(new Chop(), new Drop());
	}

	@Override
	public int loop() {
		if (jobContainer != null) {
			final Node job = jobContainer.state();
			if (job != null) {
				jobContainer.set(job);
				getContainer().submit(job);
				job.join();
			}
		}
		return Random.nextInt(10, 50);
	}

	@Override
	public void onRepaint(Graphics render) {
		Paint.drawPaint(render);
	}

}
