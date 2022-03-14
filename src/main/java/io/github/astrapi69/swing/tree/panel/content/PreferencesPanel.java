/**
 * The MIT License
 *
 * Copyright (C) 2021 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.swing.tree.panel.content;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Optional;

import javax.swing.JSplitPane;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jdesktop.swingx.JXTree;

import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.component.factory.JComponentFactory;
import io.github.astrapi69.swing.tree.JTreeExtensions;
import io.github.astrapi69.swing.tree.panel.JXTreePanel;

public abstract class PreferencesPanel<T> extends JXTreePanel<T>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The {@link JSplitPane} for the tree in the left side and the corresponding value at teh right
	 * side.
	 */
	protected JSplitPane splitPane;

	/**
	 * Instantiates a new {@link PreferencesPanel} object panel
	 *
	 * @param model
	 *            the model
	 */
	public PreferencesPanel(final IModel<T> model)
	{
		super(model);
	}

	protected Component getSelectedComponent()
	{
		Optional<DefaultMutableTreeNode> selectedTreeNode = JTreeExtensions
			.getSelectedTreeNode(tree);
		if (selectedTreeNode.isPresent())
		{
			DefaultMutableTreeNode defaultMutableTreeNode = selectedTreeNode.get();
			return (Component)defaultMutableTreeNode.getUserObject();
		}
		return null;
	}

	protected JSplitPane newJSplitPane()
	{
		return JComponentFactory.newJSplitPane(scrTree, getSelectedComponent());
	}

	/**
	 *
	 * Factory method for creating the new {@link Dimension}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a {@link Dimension}
	 *
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 *
	 * @return the new {@link Dimension}
	 */
	protected Dimension newPreferredSize(int width, int height)
	{
		return new Dimension(width, height);
	}

	protected JXTree newTree()
	{
		JXTree tree = super.newTree();

		tree.addTreeSelectionListener(treeSelectionEvent -> splitPane
			.setRightComponent(PreferencesPanel.this.getSelectedComponent()));

		return tree;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

		scrTree = newTreeScrollPane();
		tree = newTree();

		setPreferredSize(newPreferredSize(1000, 780));
		scrTree.setViewportView(tree);
		splitPane = newJSplitPane();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
	}

}
