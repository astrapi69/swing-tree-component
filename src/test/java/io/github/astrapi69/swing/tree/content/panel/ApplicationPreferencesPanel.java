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
package io.github.astrapi69.swing.tree.content.panel;

import javax.swing.JLabel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.tree.JTreeExtensions;
import io.github.astrapi69.swing.tree.node.ComponentTreeNode;
import io.github.astrapi69.swing.tree.panel.content.PreferencesPanel;
import io.github.astrapi69.tree.TreeElement;

public class ApplicationPreferencesPanel extends PreferencesPanel<TreeElement>
{
	public ApplicationPreferencesPanel()
	{

		this(BaseModel.of(TreeElement.builder().build()));
	}

	public ApplicationPreferencesPanel(IModel<TreeElement> model)
	{
		super(model);
	}

	@Override
	protected TreeModel newTreeModel(IModel<TreeElement> model)
	{
		ComponentTreeNode rootNode = new ComponentTreeNode(new JLabel("Main Preferences"),
			"Preferences");
		rootNode.add(new ComponentTreeNode(new JLabel("Test 1"), "Test 1"));
		rootNode.add(new ComponentTreeNode(new JLabel("Test 2"), "Test 2"));
		TreeModel treeModel = new DefaultTreeModel(rootNode, true);

		return treeModel;
	}

	@Override
	protected void onAfterInitialize()
	{
		super.onAfterInitialize();
		TreeNode root = (TreeNode)tree.getModel().getRoot();
		JTreeExtensions.expandAll(tree, new TreePath(root), true);
		DefaultMutableTreeNode firstLeaf = ((DefaultMutableTreeNode)tree.getModel().getRoot())
			.getFirstLeaf();
		tree.setSelectionPath(new TreePath(firstLeaf.getPath()));
		// dont show root
		tree.setRootVisible(false);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();

	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
					.addComponent(splitPane, javax.swing.GroupLayout.PREFERRED_SIZE, 700,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
				.addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
				.addContainerGap()));
	}

}
