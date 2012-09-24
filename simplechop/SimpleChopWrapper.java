package simplechop;

import java.awt.Graphics;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.game.api.Manifest;

import simplechop.jobs.Chop;
import simplechop.jobs.Drop;
import simplechop.util.Paint;

import wrappers.ScriptWrapper;

@Manifest(authors = { "ArcaneSanity" }, name = "Simple Chop Wrapper Edition", 
		description = "Simple woodcutting script utilizing ScriptWrapper")
public class SimpleChopWrapper extends ScriptWrapper implements PaintListener {

	@Override
	public void onStart() {
		provide(new Chop(), new Drop());
	}

	@Override
	public void onRepaint(Graphics render) {
		Paint.drawPaint(render);
	}

}
