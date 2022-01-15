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

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTree;

import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.dialog.DialogExtensions;
import io.github.astrapi69.swing.dialog.JOptionPaneExtensions;
import io.github.astrapi69.swing.listener.RequestFocusListener;
import io.github.astrapi69.swing.listener.mouse.MouseDoubleClickListener;
import io.github.astrapi69.swing.table.GenericJXTable;
import io.github.astrapi69.swing.table.model.DynamicPermissionsTableModel;
import io.github.astrapi69.swing.table.model.GenericTableModel;
import io.github.astrapi69.swing.table.model.dynamic.DynamicTableColumnsModel;
import io.github.astrapi69.swing.tree.GenericTreeElement;
import io.github.astrapi69.swing.tree.JTreeExtensions;
import io.github.astrapi69.swing.tree.TreeNodeFactory;
import io.github.astrapi69.swing.tree.renderer.GenericTreeNodeCellRenderer;
import io.github.astrapi69.test.objects.Permission;
import io.github.astrapi69.tree.TreeNode;

public class DemoTreeNodeGenericTreeElementWithContentPanel
	extends
		TreeNodeGenericTreeElementWithContentPanel<List<Permission>>
{

	private static final long serialVersionUID = 1L;

	public DemoTreeNodeGenericTreeElementWithContentPanel()
	{
		this(BaseModel.of(new TreeNode<>()));
	}

	public DemoTreeNodeGenericTreeElementWithContentPanel(
		final Model<TreeNode<GenericTreeElement<List<Permission>>>> model)
	{
		super(model);
	}

	@Override
	protected JXTree newTree()
	{
		JXTree tree = super.newTree();
		tree.setCellRenderer(new GenericTreeNodeCellRenderer<List<Permission>>());
		return tree;
	}

	@Override
	protected GenericJXTable newJTable()
	{
		GenericTableModel<Permission> permissionsTableModel = new DynamicPermissionsTableModel(
			new DynamicTableColumnsModel<Permission>(Permission.class));
		GenericJXTable<Permission> table = new GenericJXTable<>(permissionsTableModel);
		table.addMouseListener(new MouseDoubleClickListener()
		{
			public void onSingleClick(MouseEvent e)
			{
				System.out.println("single click");
			}

			public void onDoubleClick(MouseEvent e)
			{
				System.out.println("double click");
				Optional<Permission> singleSelectedRowData = table.getSingleSelectedRowData();
				if (singleSelectedRowData.isPresent())
				{
					Permission permission = singleSelectedRowData.get();
					System.out.println(permission);

					DialogExtensions.showInformationDialog(
						DemoTreeNodeGenericTreeElementWithContentPanel.this, "Title",
						permission.toString());
				}
			}
		});
		return table;
	}

	@Override
	protected void onAfterInitializeComponents()
	{
		super.onAfterInitializeComponents();
		// set root
		TreeNode<GenericTreeElement<List<Permission>>> root = (TreeNode<GenericTreeElement<List<Permission>>>)getModelObject()
			.getRoot();
		getTblTreeEntryTable().setModel(newTableModel(root));
	}

	@Override
	protected TreeModel newTreeModel(
		final Model<TreeNode<GenericTreeElement<List<Permission>>>> model)
	{
		TreeNode<GenericTreeElement<List<Permission>>> parentTreeNode = model.getObject();
		TreeModel treeModel;

		// treeModel = new TreeNodeModel(parentTreeNode);

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
				TreeNode<GenericTreeElement<List<Permission>>> selectedTreeNode = (TreeNode<GenericTreeElement<List<Permission>>>)node
					.getUserObject();
				newTableModel(selectedTreeNode);
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

	/**
	 * {@inheritDoc}
	 *
	 * @param model
	 */
	@Override
	protected GenericTableModel newTableModel(TreeNode<GenericTreeElement<List<Permission>>> model)
	{
		GenericTreeElement<List<Permission>> parentTreeNode = model.getValue();
		List<Permission> permissions = parentTreeNode.getDefaultContent();
		// 2. Create a generic table model for the class Permission.
		getTblTreeEntryTable().getGenericTableModel().removeAll();
		getTblTreeEntryTable().getGenericTableModel().addList(permissions);
		return getTblTreeEntryTable().getGenericTableModel();
	}

	@Override
	protected void onSingleLeftClick(MouseEvent mouseEvent)
	{
		Optional<DefaultMutableTreeNode> selectedTreeNode = JTreeExtensions
			.getSelectedDefaultMutableTreeNode(mouseEvent, tree);
		if (selectedTreeNode.isPresent())
		{
			DefaultMutableTreeNode defaultMutableTreeNode = selectedTreeNode.get();
			TreeNode<GenericTreeElement<List<Permission>>> selectedTreeNodeElement = (TreeNode<GenericTreeElement<List<Permission>>>)defaultMutableTreeNode
				.getUserObject();

			GenericTableModel tableModel = newTableModel(selectedTreeNodeElement);

			tableModel.fireTableDataChanged();

		}
	}

	@Override
	protected void onSingleRightClick(MouseEvent mouseEvent)
	{
		int x = mouseEvent.getX();
		int y = mouseEvent.getY();
		TreePath selectionPath = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
		tree.getSelectionModel().setSelectionPath(selectionPath);

		Object lastPathComponent = selectionPath.getLastPathComponent();
		DefaultMutableTreeNode selectedTreeNode = (DefaultMutableTreeNode)lastPathComponent;
		TreeNode<GenericTreeElement<List<Permission>>> parentTreeNode = (TreeNode<GenericTreeElement<List<Permission>>>)selectedTreeNode
			.getUserObject();

		JPopupMenu popup = new JPopupMenu();
		if (parentTreeNode.isNode())
		{
			JMenuItem menuItemAddChild = new JMenuItem("add node...");
			menuItemAddChild.addActionListener(actionEvent -> this.onAddNewChildTreeNode());
			popup.add(menuItemAddChild);
		}

		if (!parentTreeNode.isRoot())
		{
			JMenuItem deleteNode = new JMenuItem("delete");
			deleteNode.addActionListener(actionEvent -> this.onDeleteSelectedTreeNode());
			popup.add(deleteNode);
		}

		JMenuItem menuItemEdit = new JMenuItem("Edit node...");
		menuItemEdit.addActionListener(actionEvent -> this.onEditSelectedTreeNode());
		popup.add(menuItemEdit);

		JMenuItem menuItemCopy = new JMenuItem("Copy node");
		menuItemCopy.addActionListener(actionEvent -> this.onCopySelectedTreeNode());
		popup.add(menuItemCopy);

		JMenuItem menuItemCollapse = new JMenuItem("Collapse node");
		menuItemCollapse.addActionListener(actionEvent -> this.onCollapseSelectedTreeNode());
		popup.add(menuItemCollapse);

		JMenuItem menuItemExpand = new JMenuItem("Expand node");
		menuItemExpand.addActionListener(actionEvent -> this.onExpandSelectedTreeNode());
		popup.add(menuItemExpand);

		popup.show(tree, x, y);
	}

	protected void onAddNewChildTreeNode()
	{
		DefaultMutableTreeNode selectedTreeNode = getSelectedTreeNode();
		TreeNode<GenericTreeElement<List<Permission>>> parentTreeNode = (TreeNode<GenericTreeElement<List<Permission>>>)selectedTreeNode
			.getUserObject();
		JTextField textField1 = new JTextField();
		final JCheckBox checkBox = new JCheckBox();

		checkBox.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent changeEvent)
			{
				if (changeEvent.getSource() == checkBox)
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

		JOptionPane pane = new JOptionPane(panel, JOptionPane.INFORMATION_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION);
		JDialog dialog = pane.createDialog(null, "New node");
		dialog.addWindowFocusListener(new RequestFocusListener(textField1));
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		int option = JOptionPaneExtensions.getSelectedOption(pane);

		if (option == JOptionPane.OK_OPTION)
		{
			boolean allowsChildren = !checkBox.isSelected();
			String userObject = textField1.getText();
			GenericTreeElement<List<Permission>> treeElement = GenericTreeElement
				.<List<Permission>> builder().name(userObject).parent(parentTreeNode.getValue())
				.node(allowsChildren).build();
			TreeNode<GenericTreeElement<List<Permission>>> newTreeNode = TreeNode
				.<GenericTreeElement<List<Permission>>> builder().value(treeElement)
				.parent(parentTreeNode).displayValue(userObject).node(allowsChildren).build();

			DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(newTreeNode,
				allowsChildren);
			selectedTreeNode.add(newChild);
			((DefaultTreeModel)tree.getModel()).reload(selectedTreeNode);
			tree.treeDidChange();
		}
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
