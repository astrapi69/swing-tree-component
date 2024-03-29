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

import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import io.github.astrapi69.gen.tree.TreeNode;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.dialog.JOptionPaneExtensions;
import io.github.astrapi69.swing.renderer.tree.renderer.TreeNodeCellRenderer;
import io.github.astrapi69.swing.tree.factory.DefaultMutableTreeNodeFactory;
import io.github.astrapi69.swing.tree.factory.TreeNodeFactory;
import io.github.astrapi69.swing.tree.model.TreeElement;

public class DemoTreeNodeTreeElementPanel extends TreeNodeTreeElementPanel
{

	private static final long serialVersionUID = 1L;

	public DemoTreeNodeTreeElementPanel()
	{
		this(BaseModel.of(new TreeNode<>()));
	}

	public DemoTreeNodeTreeElementPanel(final IModel<TreeNode<TreeElement>> model)
	{
		super(model);
	}

	@Override
	protected JTree newTree()
	{
		JTree tree = super.newTree();
		tree.setCellRenderer(new TreeNodeCellRenderer<TreeElement>());
		return tree;
	}

	@Override
	protected TreeModel newTreeModel(final IModel<TreeNode<TreeElement>> model)
	{
		TreeNode<TreeElement> parentTreeNode = model.getObject();
		TreeModel treeModel;

		DefaultMutableTreeNode rootNode = TreeNodeFactory.newDefaultMutableTreeNode(parentTreeNode);

		treeModel = new DefaultTreeModel(rootNode, true);

		treeModel.addTreeModelListener(new TreeModelListener()
		{
			@Override
			public void treeNodesChanged(TreeModelEvent e)
			{
				Object lastPathComponent = e.getTreePath().getLastPathComponent();
				DefaultMutableTreeNode node;
				node = (DefaultMutableTreeNode)lastPathComponent;
				int index = e.getChildIndices()[0];
				node = (DefaultMutableTreeNode)(node.getChildAt(index));
			}

			@Override
			public void treeNodesInserted(TreeModelEvent e)
			{
				Object lastPathComponent = e.getTreePath().getLastPathComponent();
				DefaultMutableTreeNode node;
				node = (DefaultMutableTreeNode)lastPathComponent;
				System.err.println(node);
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent e)
			{
				Object lastPathComponent = e.getTreePath().getLastPathComponent();
				DefaultMutableTreeNode node;
				node = (DefaultMutableTreeNode)lastPathComponent;
				System.err.println(node);
			}

			@Override
			public void treeStructureChanged(TreeModelEvent e)
			{
				Object lastPathComponent = e.getTreePath().getLastPathComponent();
				DefaultMutableTreeNode node;
				node = (DefaultMutableTreeNode)lastPathComponent;
				System.err.println(node);
			}
		});
		return treeModel;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void onTreeSingleRightClick(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		TreePath selectionPath = tree.getPathForLocation(e.getX(), e.getY());
		tree.getSelectionModel().setSelectionPath(selectionPath);

		Object lastPathComponent = selectionPath.getLastPathComponent();
		DefaultMutableTreeNode selectedTreeNode = (DefaultMutableTreeNode)lastPathComponent;
		TreeNode<TreeElement> parentTreeNode = (TreeNode<TreeElement>)selectedTreeNode
			.getUserObject();

		JPopupMenu popup = new JPopupMenu();
		if (parentTreeNode.isNode())
		{
			JMenuItem addChild = new JMenuItem("add node...");
			addChild.addActionListener(le -> {
				JTextField textField1 = new JTextField();
				final JCheckBox checkBox = new JCheckBox();

				checkBox.addChangeListener(new ChangeListener()
				{
					@Override
					public void stateChanged(ChangeEvent e)
					{
						if (e.getSource() == checkBox)
						{
							if (checkBox.isSelected())
							{

							}
							else
							{

							}
						}
					}
				});
				JPanel panel = new JPanel(new GridLayout(2, 2));
				panel.add(new JLabel("Enter name for node:"));
				panel.add(textField1);
				panel.add(new JLabel("Is leaf:"));
				panel.add(checkBox);

				int option = JOptionPaneExtensions.getSelectedOption(panel,
					JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, "New node",
					textField1);

				if (option == JOptionPane.OK_OPTION)
				{
					boolean allowsChildren = !checkBox.isSelected();
					String userObject = textField1.getText();
					TreeElement treeElement = TreeElement.builder().name(userObject)
						.parent(parentTreeNode.getValue()).node(allowsChildren).build();
					TreeNode<TreeElement> newTreeNode = TreeNode.<TreeElement> builder()
						.value(treeElement).parent(parentTreeNode).displayValue(userObject)
						.leaf(!allowsChildren).build();

					DefaultMutableTreeNodeFactory.newDefaultMutableTreeNode(selectedTreeNode,
						newTreeNode, allowsChildren, true);
					((DefaultTreeModel)tree.getModel()).reload(selectedTreeNode);
					tree.treeDidChange();
				}

			});
			popup.add(addChild);
		}

		if (!parentTreeNode.isRoot())
		{
			JMenuItem deleteNode = new JMenuItem("delete");
			deleteNode.addActionListener(le -> {
				int selectedNodeIndex = selectedTreeNode.getParent().getIndex(selectedTreeNode);
				selectedTreeNode.removeAllChildren();
				((DefaultMutableTreeNode)selectedTreeNode.getParent()).remove(selectedNodeIndex);
				((DefaultTreeModel)tree.getModel()).reload(selectedTreeNode);
				tree.treeDidChange();
				tree.treeDidChange();
				this.repaint();
			});
			popup.add(deleteNode);
		}
		popup.show(tree, x, y);
	}
}
