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

import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTree;

import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.dialog.JOptionPaneExtensions;
import io.github.astrapi69.swing.listener.RequestFocusListener;
import io.github.astrapi69.swing.tree.GenericTreeElement;
import io.github.astrapi69.swing.tree.JTreeExtensions;
import io.github.astrapi69.swing.tree.JXTreeElement;
import io.github.astrapi69.swing.tree.TreeNodeFactory;
import io.github.astrapi69.swing.tree.factory.DefaultMutableTreeNodeFactory;
import io.github.astrapi69.swing.tree.panel.node.NodeModelBean;
import io.github.astrapi69.swing.tree.panel.node.NodePanel;
import io.github.astrapi69.swing.tree.renderer.JXTreeNodeCellRenderer;
import io.github.astrapi69.test.object.Permission;
import io.github.astrapi69.tree.TreeNode;

public class DemoTreeNodeJXTreeElementPanel extends TreeNodeJXTreeElementPanel
{

	private static final long serialVersionUID = 1L;

	public DemoTreeNodeJXTreeElementPanel()
	{
		this(BaseModel.of(new TreeNode<>()));
	}

	public DemoTreeNodeJXTreeElementPanel(final IModel<TreeNode<JXTreeElement>> model)
	{
		super(model);
	}

	@Override
	protected JXTree newTree()
	{
		JXTree tree = super.newTree();
		tree.setCellRenderer(new JXTreeNodeCellRenderer());
		return tree;
	}

	@Override
	protected TreeModel newTreeModel(final IModel<TreeNode<JXTreeElement>> model)
	{
		TreeNode<JXTreeElement> parentTreeNode = model.getObject();
		TreeModel treeModel;

		DefaultMutableTreeNode rootNode = TreeNodeFactory.newDefaultMutableTreeNode(parentTreeNode);

		return new DefaultTreeModel(rootNode, true);
	}

	@Override
	protected void onTreeSingleRightClick(MouseEvent mouseEvent)
	{
		int x = mouseEvent.getX();
		int y = mouseEvent.getY();
		TreePath selectionPath = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
		tree.getSelectionModel().setSelectionPath(selectionPath);

		Object lastPathComponent = selectionPath.getLastPathComponent();
		DefaultMutableTreeNode selectedTreeNode = (DefaultMutableTreeNode)lastPathComponent;
		TreeNode<JXTreeElement> parentTreeNode = (TreeNode<JXTreeElement>)selectedTreeNode
			.getUserObject();

		JPopupMenu popup = new JPopupMenu();
		if (parentTreeNode.isNode())
		{
			JMenuItem menuItemAddChild = new JMenuItem("add node...");
			menuItemAddChild
				.addActionListener(actionEvent -> this.onAddNewChildTreeNode(mouseEvent));
			popup.add(menuItemAddChild);
		}

		if (!parentTreeNode.isRoot())
		{
			JMenuItem deleteNode = new JMenuItem("delete");
			deleteNode.addActionListener(actionEvent -> this.onDeleteSelectedTreeNode(mouseEvent));
			popup.add(deleteNode);
		}

		JMenuItem menuItemEdit = new JMenuItem("Edit node...");
		menuItemEdit.addActionListener(actionEvent -> this.onEditSelectedTreeNode());
		popup.add(menuItemEdit);

		JMenuItem menuItemCopy = new JMenuItem("Copy node");
		menuItemCopy.addActionListener(actionEvent -> this.onCopySelectedTreeNode());
		popup.add(menuItemCopy);

		JMenuItem menuItemCollapse = new JMenuItem("Collapse node");
		menuItemCollapse
			.addActionListener(actionEvent -> this.onCollapseSelectedTreeNode(mouseEvent));
		popup.add(menuItemCollapse);

		JMenuItem menuItemExpand = new JMenuItem("Expand node");
		menuItemExpand.addActionListener(actionEvent -> this.onExpandSelectedTreeNode(mouseEvent));
		popup.add(menuItemExpand);

		popup.show(tree, x, y);
	}

	protected void onAddNewChildTreeNode(MouseEvent mouseEvent)
	{
		JTreeExtensions.getSelectedDefaultMutableTreeNode(mouseEvent, tree)
			.ifPresent(selectedTreeNode -> {
				TreeNode<GenericTreeElement<java.util.List<Permission>>> parentTreeNode = (TreeNode<GenericTreeElement<List<Permission>>>)selectedTreeNode
					.getUserObject();
				NodePanel nodePanel = new NodePanel();
				JOptionPane pane = new JOptionPane(nodePanel, JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.OK_CANCEL_OPTION);
				JDialog dialog = pane.createDialog(null, "New node");
				dialog.addWindowFocusListener(new RequestFocusListener(nodePanel.getTxtName()));
				dialog.pack();
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				int option = JOptionPaneExtensions.getSelectedOption(pane);

				if (option == JOptionPane.OK_OPTION)
				{
					NodeModelBean modelObject = nodePanel.getModelObject();
					boolean node = modelObject.isNode();
					String name = modelObject.getName();
					GenericTreeElement<java.util.List<Permission>> treeElement = GenericTreeElement.<java.util.List<Permission>> builder()
						.name(name).node(node).build();
					TreeNode<GenericTreeElement<java.util.List<Permission>>> newTreeNode = TreeNode
						.<GenericTreeElement<List<Permission>>> builder().value(treeElement)
						.parent(parentTreeNode).displayValue(name).leaf(!node).build();

					DefaultMutableTreeNodeFactory.newDefaultMutableTreeNode(selectedTreeNode,
						newTreeNode, node, true);
					((DefaultTreeModel)tree.getModel()).reload(selectedTreeNode);
					tree.treeDidChange();
				}
			});
	}

	protected void onCopySelectedTreeNode()
	{
		System.out.println("onCopySelectedTreeNode");
	}

	protected void onEditSelectedTreeNode()
	{
		System.out.println("onEditSelectedTreeNode");
	}
}
