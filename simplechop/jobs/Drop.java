package simplechop.jobs;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.Item;

public class Drop extends Node {

	private final int logId = 1511;

	@Override
	public boolean activate() {
		return Inventory.isFull();
	}

	@Override
	public void execute() {
		final Item[] items = Inventory.getItems(new Filter<Item>() {
			@Override
			public boolean accept(Item i) {
				return i.getId() == logId;
			}
		});
		for (final Item item : items) {
			item.getWidgetChild().interact("Drop");
		}
	}

}
