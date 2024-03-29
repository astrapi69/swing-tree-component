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

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.gen.tree.TreeNode;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.renderer.tree.JTreeElement;

/**
 * The abstract class {@link BaseTreeNodeJTreeElementPanel} a given parameterized {@link TreeNode}
 */
public abstract class BaseTreeNodeJTreeElementPanel
	extends
		GenericBaseTreeNodePanel<JTreeElement, Long>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link BaseTreeNodeJTreeElementPanel}
	 */
	public BaseTreeNodeJTreeElementPanel()
	{
		this(BaseModel.of(BaseTreeNode.<JTreeElement, Long> builder().build()));
	}

	/**
	 * Instantiates a new t{@link BaseTreeNodeJTreeElementPanel}
	 *
	 * @param model
	 *            the model
	 */
	public BaseTreeNodeJTreeElementPanel(final IModel<BaseTreeNode<JTreeElement, Long>> model)
	{
		super(model);
	}

}
