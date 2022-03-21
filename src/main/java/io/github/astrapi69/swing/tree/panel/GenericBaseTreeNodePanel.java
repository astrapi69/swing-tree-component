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
package io.github.astrapi69.swing.tree.panel;

import javax.swing.tree.TreeModel;

import org.jdesktop.swingx.JXTree;

import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.tree.BaseTreeNode;
import io.github.astrapi69.tree.element.TreeElement;

/**
 * The abstract class {@link GenericBaseTreeNodePanel} a given {@link BaseTreeNode} parameterized
 * with {@link TreeElement}
 */
public abstract class GenericBaseTreeNodePanel<T> extends JXTreePanel<BaseTreeNode<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link GenericBaseTreeNodePanel}
	 */
	public GenericBaseTreeNodePanel()
	{
	}

	/**
	 * Instantiates a new t{@link GenericBaseTreeNodePanel}
	 *
	 * @param model
	 *            the model
	 */
	public GenericBaseTreeNodePanel(final IModel<BaseTreeNode<T>> model)
	{
		super(model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JXTree newTree()
	{
		JXTree tree = super.newTree();
		return tree;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected abstract TreeModel newTreeModel(final IModel<BaseTreeNode<T>> model);

	/**
	 * On initialize group layout.
	 */
	protected void onInitializeGroupLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
					.addComponent(scrTree, javax.swing.GroupLayout.PREFERRED_SIZE, 384,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
				.addComponent(scrTree, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
				.addContainerGap()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
		onInitializeGroupLayout();
	}

}
