package br.snt.app.commands.clicks;

import android.graphics.Color;
import android.view.View;
import br.snt.app.commands.Command;

/**
 * Represents a mark click on the verse from the context menu.
 * 
 * @author mvalencaa
 * @since 01/07/2011
 * 
 */
public class MarkVerseItemCmd implements Command {

	/**
	 * The view for which the context menu is being displayed.
	 */
	private View mVerseItem;

	/**
	 * Default constructor.
	 * 
	 * @param verseItem
	 *            The view for which the context menu is being displayed.
	 */
	public MarkVerseItemCmd(View verseItem) {
		mVerseItem = verseItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.snt.app.commands.Command#execute()
	 */
	@Override
	public void execute() {
		mVerseItem.setBackgroundColor(Color.DKGRAY);
	}

}
