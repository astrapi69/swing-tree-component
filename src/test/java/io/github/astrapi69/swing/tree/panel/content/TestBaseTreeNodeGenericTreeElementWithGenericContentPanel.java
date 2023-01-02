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

import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.tree.GenericTreeElement;
import io.github.astrapi69.swing.tree.panel.JXTreePanel;
import io.github.astrapi69.test.instance.TestBaseTreeNodeFactory;
import io.github.astrapi69.test.object.Permission;
import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.window.adapter.CloseWindow;

/**
 * The test class for {@link JXTreePanel}
 */
public class TestBaseTreeNodeGenericTreeElementWithGenericContentPanel
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
		BaseTreeNode<GenericTreeElement<List<Permission>>, Long> genericTreeElementTreeNode = TestBaseTreeNodeFactory
			.initializeTestGenericTreeNodeElement();
		IModel<BaseTreeNode<GenericTreeElement<List<Permission>>, Long>> treeNodeModel = BaseModel
			.of(genericTreeElementTreeNode);
		DemoBaseTreeNodeGenericTreeElementWithContentPanel treeNodeGenericTreeElementWithContentPanelTest = new DemoBaseTreeNodeGenericTreeElementWithContentPanel(
			treeNodeModel);
		frame.add(treeNodeGenericTreeElementWithContentPanelTest);
		frame.pack();
		frame.setVisible(true);
	}

}
