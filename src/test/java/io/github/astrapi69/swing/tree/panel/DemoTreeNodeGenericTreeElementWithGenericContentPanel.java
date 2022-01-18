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
import java.util.Optional;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTree;

import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.dialog.JOptionPaneExtensions;
import io.github.astrapi69.swing.listener.RequestFocusListener;
import io.github.astrapi69.swing.menu.MenuFactory;
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

public class DemoTreeNodeGenericTreeElementWithGenericContentPanel
	extends
		TreeNodeGenericTreeElementWithGenericContentPanel<List<Permission>, Permission>
{

	private static final long serialVersionUID = 1L;

	public DemoTreeNodeGenericTreeElementWithGenericContentPanel()
	{
		this(BaseModel.of(new TreeNode<>()));
	}

	public DemoTreeNodeGenericTreeElementWithGenericContentPanel(
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
	protected GenericJXTable<Permission> newJTable()
	{
		GenericTableModel<Permission> permissionsTableModel = new DynamicPermissionsTableModel(
			new DynamicTableColumnsModel<>(Permission.class));
		GenericJXTable<Permission> table = new GenericJXTable<Permission>(permissionsTableModel)
		{

			protected void onSingleLeftClick(MouseEvent event)
			{
				super.onSingleLeftClick(event);
				DemoTreeNodeGenericTreeElementWithGenericContentPanel.this
					.onTableSingleLeftClick(event);
			}

			protected void onSingleMiddleClick(MouseEvent event)
			{
				super.onSingleMiddleClick(event);
				DemoTreeNodeGenericTreeElementWithGenericContentPanel.this
					.onTableSingleMiddleClick(event);
			}

			protected void onSingleRightClick(MouseEvent event)
			{
				super.onSingleRightClick(event);
				DemoTreeNodeGenericTreeElementWithGenericContentPanel.this
					.onTableSingleRightClick(event);
			}

			protected void onDoubleLeftClick(MouseEvent event)
			{
				super.onDoubleLeftClick(event);
				DemoTreeNodeGenericTreeElementWithGenericContentPanel.this
					.onTableDoubleLeftClick(event);
			}

			protected void onDoubleMiddleClick(MouseEvent event)
			{
				super.onDoubleMiddleClick(event);
				DemoTreeNodeGenericTreeElementWithGenericContentPanel.this
					.onTableDoubleMiddleClick(event);
			}

			protected void onDoubleRightClick(MouseEvent event)
			{
				super.onDoubleRightClick(event);
				DemoTreeNodeGenericTreeElementWithGenericContentPanel.this
					.onTableDoubleRightClick(event);
			}
		};
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
	protected GenericTableModel<Permission> newTableModel(
		TreeNode<GenericTreeElement<List<Permission>>> model)
	{
		GenericTreeElement<List<Permission>> parentTreeNode = model.getValue();
		List<Permission> permissions = parentTreeNode.getDefaultContent();
		// 2. Create a generic table model for the class Permission.
		getTblTreeEntryTable().getGenericTableModel().removeAll();
		getTblTreeEntryTable().getGenericTableModel().addList(permissions);
		return getTblTreeEntryTable().getGenericTableModel();
	}

	@Override
	protected void onTreeSingleLeftClick(MouseEvent mouseEvent)
	{
		Optional<TreeNode<GenericTreeElement<List<Permission>>>> optionalSelectedTreeNodeElement = JTreeExtensions
			.getSelectedUserObject(tree);
		if (optionalSelectedTreeNodeElement.isPresent())
		{
			TreeNode<GenericTreeElement<List<Permission>>> selectedTreeNodeElement = optionalSelectedTreeNodeElement
				.get();
			GenericTableModel tableModel = newTableModel(selectedTreeNodeElement);

			tableModel.fireTableDataChanged();
		}
	}

	@Override
	protected void onTreeSingleRightClick(MouseEvent mouseEvent)
	{
		int x = mouseEvent.getX();
		int y = mouseEvent.getY();
		TreePath selectionPath = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
		if (selectionPath != null)
		{
			tree.getSelectionModel().setSelectionPath(selectionPath);

			Object lastPathComponent = selectionPath.getLastPathComponent();
			DefaultMutableTreeNode selectedTreeNode = (DefaultMutableTreeNode)lastPathComponent;
			TreeNode<GenericTreeElement<List<Permission>>> parentTreeNode = (TreeNode<GenericTreeElement<List<Permission>>>)selectedTreeNode
				.getUserObject();

			JPopupMenu popup = MenuFactory.newJPopupMenu();

			if (parentTreeNode.isNode())
			{
				popup.add(MenuFactory.newJMenuItem("add node...",
					actionEvent -> this.onAddNewChildTreeNode()));
			}

			if (!parentTreeNode.isRoot())
			{
				popup.add(MenuFactory.newJMenuItem("delete",
					actionEvent -> this.onDeleteSelectedTreeNode()));
			}

			popup.add(MenuFactory.newJMenuItem("Edit node...",
				actionEvent -> this.onEditSelectedTreeNode()));

			popup.add(MenuFactory.newJMenuItem("Copy node",
				actionEvent -> this.onCopySelectedTreeNode()));

			popup.add(MenuFactory.newJMenuItem("Collapse node",
				actionEvent -> this.onCollapseSelectedTreeNode()));

			popup.add(MenuFactory.newJMenuItem("Expand node",
				actionEvent -> this.onExpandSelectedTreeNode()));

			popup.show(tree, x, y);
		}
	}

	protected void onAddNewChildTreeNode()
	{
		DefaultMutableTreeNode selectedTreeNode = getSelectedTreeNode();
		if (selectedTreeNode != null)
		{
			TreeNode<GenericTreeElement<List<Permission>>> parentTreeNode = (TreeNode<GenericTreeElement<List<Permission>>>)selectedTreeNode
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
				GenericTreeElement<List<Permission>> treeElement = GenericTreeElement
					.<List<Permission>> builder().name(name).parent(parentTreeNode.getValue())
					.node(node).build();
				TreeNode<GenericTreeElement<List<Permission>>> newTreeNode = TreeNode
					.<GenericTreeElement<List<Permission>>> builder().value(treeElement)
					.parent(parentTreeNode).displayValue(name).node(node).build();

				DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(newTreeNode,
					node);
				selectedTreeNode.add(newChild);
				((DefaultTreeModel)tree.getModel()).reload(selectedTreeNode);
				tree.treeDidChange();
			}
		}
	}

	protected void onCopySelectedTreeNode()
	{
		System.out.println("onCopySelectedTreeNode");
	}

	protected void onEditSelectedTreeNode()
	{
		System.out.println("onEditSelectedTreeNode");

		DefaultMutableTreeNode selectedTreeNode = getSelectedTreeNode();
		if (selectedTreeNode != null)
		{
			TreeNode<GenericTreeElement<List<Permission>>> parentTreeNode = (TreeNode<GenericTreeElement<List<Permission>>>)selectedTreeNode
				.getUserObject();
			NodePanel nodePanel = new NodePanel(BaseModel.of(NodeModelBean.builder()
					.name(parentTreeNode.getValue().getName())
					.node(parentTreeNode.getValue().isNode())
				.build()));
			JOptionPane pane = new JOptionPane(nodePanel, JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.OK_CANCEL_OPTION);
			JDialog dialog = pane.createDialog(null, "Edit node");
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
				parentTreeNode.setNode(node);
				parentTreeNode.setDisplayValue(name);

				if(parentTreeNode.getValue().isNode() != node) {
					// set to leaf only if the node has no children
					if((node) || !node && 0 == selectedTreeNode.getChildCount()) {
						parentTreeNode.getValue().setNode(node);
					}
				}

				parentTreeNode.getValue().setName(name);

				((DefaultTreeModel)tree.getModel()).reload(selectedTreeNode);
				tree.treeDidChange();
			}
		}
	}

	/**
	 * The callback method on the table single left click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableSingleLeftClick(MouseEvent event)
	{
		System.out.println("onTableSingleLeftClick");
	}

	/**
	 * The callback method on the table single middle click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableSingleMiddleClick(MouseEvent event)
	{
		System.out.println("onTableSingleMiddleClick");
	}

	/**
	 * The callback method on the table single right click.
	 *
	 * @param mouseEvent
	 *            the mouse event
	 */
	protected void onTableSingleRightClick(MouseEvent mouseEvent)
	{
		int x = mouseEvent.getX();
		int y = mouseEvent.getY();

		List<Permission> allSelectedRowData = getTblTreeEntryTable().getAllSelectedRowData();

		JPopupMenu popup = MenuFactory.newJPopupMenu();

		popup.add(MenuFactory.newJMenuItem("add permission...",
			actionEvent -> this.onAddNewPermission()));

		JMenuItem menuItem = MenuFactory.newJMenuItem("delete",
			actionEvent -> this.onDeletePermission());
		menuItem.setEnabled(!allSelectedRowData.isEmpty());
		popup.add(menuItem);

		JMenuItem edit = MenuFactory.newJMenuItem("edit", actionEvent -> this.onEditPermission());
		edit.setEnabled(!allSelectedRowData.isEmpty() && allSelectedRowData.size() == 1);
		popup.add(edit);

		popup.show(getTblTreeEntryTable(), x, y);

	}

	protected void onEditPermission()
	{
		getTblTreeEntryTable().getSingleSelectedRowData().ifPresent(permission -> {
			PermissionPanel editNodePanel = new PermissionPanel(BaseModel.of(permission));
			JOptionPane pane = new JOptionPane(editNodePanel, JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.OK_CANCEL_OPTION);
			JDialog dialog = pane.createDialog(null, "New permission");
			dialog.addWindowFocusListener(new RequestFocusListener(editNodePanel.getTxtName()));
			dialog.pack();
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			int option = JOptionPaneExtensions.getSelectedOption(pane);

			if (option == JOptionPane.OK_OPTION)
			{
				List<Permission> data = getTblTreeEntryTable().getGenericTableModel().getData();
				int index = data.indexOf(permission);
				data.remove(permission);
				Permission perm = editNodePanel.getModelObject();

				data.add(index, perm);
				getTblTreeEntryTable().getGenericTableModel().fireTableDataChanged();
			}
		});
	}

	protected void onDeletePermission()
	{
		getTblTreeEntryTable().getAllSelectedRowData().forEach(permission -> {
			getTblTreeEntryTable().getGenericTableModel().remove(permission);
		});
		getTblTreeEntryTable().getGenericTableModel().fireTableDataChanged();
	}

	protected void onAddNewPermission()
	{
		PermissionPanel addNodePanel = new PermissionPanel();
		JOptionPane pane = new JOptionPane(addNodePanel, JOptionPane.INFORMATION_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION);
		JDialog dialog = pane.createDialog(null, "New permission");
		dialog.addWindowFocusListener(new RequestFocusListener(addNodePanel.getTxtName()));
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		int option = JOptionPaneExtensions.getSelectedOption(pane);

		if (option == JOptionPane.OK_OPTION)
		{
			Permission permission = Permission.builder()
				.description(addNodePanel.getTxtDescription().getText())
				.name(addNodePanel.getTxtName().getText())
				.shortcut(addNodePanel.getTxtShortcut().getText()).build();
			getTblTreeEntryTable().getGenericTableModel().add(permission);
			getTblTreeEntryTable().getGenericTableModel().fireTableDataChanged();
		}
	}

	/**
	 * The callback method on the table double left click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableDoubleLeftClick(MouseEvent event)
	{
		System.out.println("onTableDoubleLeftClick");
	}

	/**
	 * The callback method on the table double middle click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableDoubleMiddleClick(MouseEvent event)
	{
		System.out.println("onTableDoubleMiddleClick");
	}

	/**
	 * The callback method on the table double right click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableDoubleRightClick(MouseEvent event)
	{
		System.out.println("onTableDoubleRightClick");
	}

}
