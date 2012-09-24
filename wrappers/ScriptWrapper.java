package wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;

public abstract class ScriptWrapper extends
		org.powerbot.core.script.ActiveScript {

	public final Timer runTime;
	private final List<Node> jobCollection;
	private Tree jobContainer = null;

	public ScriptWrapper() {
		runTime = new Timer(0);
		jobCollection = Collections.synchronizedList(new ArrayList<Node>());
	}

	public final void provide(final Node... jobs) {
		for (final Node job : jobs) {
			if (!jobCollection.contains(job)) {
				jobCollection.add(job);
			}
		}
		jobContainer = new Tree(jobCollection.toArray(new Node[jobCollection
				.size()]));
	}

	public final void revoke(final Node... jobs) {
		for (final Node job : jobs) {
			if (jobCollection.contains(job)) {
				jobCollection.remove(job);
			}
		}
		jobContainer = new Tree(jobCollection.toArray(new Node[jobCollection
				.size()]));
	}

	public final void submit(final Job job) {
		getContainer().submit(job);
	}

	public final ScriptWrapper getScriptWrapper() {
		return this;
	}

	public abstract void onStart();

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

}