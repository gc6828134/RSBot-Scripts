package simplechop.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import org.powerbot.game.api.methods.input.Mouse;

public class Paint {

	public final static void drawPaint(final Graphics render) {
		Graphics2D g = (Graphics2D) render;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		g.setColor(Color.GREEN);
		g.drawLine(Mouse.getX() - 6, Mouse.getY(), Mouse.getX() + 6,
				Mouse.getY());
		g.drawLine(Mouse.getX(), Mouse.getY() - 6, Mouse.getX(),
				Mouse.getY() + 6);
	}

}
