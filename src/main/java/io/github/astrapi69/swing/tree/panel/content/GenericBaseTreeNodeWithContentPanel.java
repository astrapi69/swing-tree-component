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

import javax.swing.GroupLayout;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;

public abstract class GenericBaseTreeNodeWithContentPanel<T, K, C>
	extends
		JTreeWithContentPanel<BaseTreeNode<T, K>, C>
{

	/**
	 * Instantiates a new {@link GenericBaseTreeNodeWithContentPanel}
	 */
	public GenericBaseTreeNodeWithContentPanel()
	{
		this(BaseModel.of(new BaseTreeNode<>()));
	}

	/**
	 * Instantiates a new t{@link GenericBaseTreeNodeWithContentPanel}
	 *
	 * @param model
	 *            the model
	 */
	public GenericBaseTreeNodeWithContentPanel(final IModel<BaseTreeNode<T, K>> model)
	{
		super(model);
	}

	/**
	 * On initialize group layout.
	 */
	protected void onInitializeGroupLayout()
	{
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
				.addComponent(scrTree, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(scrTreeEntryTable, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
				.addContainerGap()));
		layout
			.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(
						layout.createSequentialGroup().addContainerGap()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(scrTree, GroupLayout.DEFAULT_SIZE, 756,
									Short.MAX_VALUE)
								.addComponent(scrTreeEntryTable))
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
