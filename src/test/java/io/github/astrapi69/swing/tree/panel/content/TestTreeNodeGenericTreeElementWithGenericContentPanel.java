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

import java.awt.Frame;
import java.util.List;

import io.github.astrapi69.awt.window.adapter.CloseWindow;
import io.github.astrapi69.gen.tree.TreeNode;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.renderer.tree.GenericTreeElement;
import io.github.astrapi69.swing.tree.panel.JTreePanel;
import io.github.astrapi69.test.instance.TestTreeNodeFactory;
import io.github.astrapi69.test.object.Permission;

/**
 * The test class for {@link JTreePanel}
 */
public class TestTreeNodeGenericTreeElementWithGenericContentPanel
{

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args)
	{
		final Frame frame = new Frame("TestTreeNodeGenericTreeElementWithGenericContentPanel");
		frame.addWindowListener(new CloseWindow());
		TreeNode<GenericTreeElement<List<Permission>>> genericTreeElementTreeNode = TestTreeNodeFactory
			.initializeTestGenericTreeNodeElement();
		IModel<TreeNode<GenericTreeElement<List<Permission>>>> treeNodeModel = BaseModel
			.of(genericTreeElementTreeNode);
		DemoTreeNodeGenericTreeElementWithContentPanel treeNodeGenericTreeElementWithContentPanelTest = new DemoTreeNodeGenericTreeElementWithContentPanel(
			treeNodeModel);
		frame.add(treeNodeGenericTreeElementWithContentPanelTest);
		frame.pack();
		frame.setVisible(true);
	}

}
