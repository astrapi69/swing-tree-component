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

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import io.github.astrapi69.component.model.node.NodeModel;
import io.github.astrapi69.gen.tree.TreeNode;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.dialog.DialogExtensions;
import io.github.astrapi69.swing.dialog.JOptionPaneExtensions;
import io.github.astrapi69.swing.menu.factory.JMenuItemFactory;
import io.github.astrapi69.swing.menu.factory.JPopupMenuFactory;
import io.github.astrapi69.swing.renderer.tree.GenericTreeElement;
import io.github.astrapi69.swing.renderer.tree.renderer.GenericTreeNodeCellRenderer;
import io.github.astrapi69.swing.table.GenericJTable;
import io.github.astrapi69.swing.table.model.DynamicPermissionsTableModel;
import io.github.astrapi69.swing.table.model.GenericTableModel;
import io.github.astrapi69.swing.table.model.dynamic.DynamicTableColumnsModel;
import io.github.astrapi69.swing.tree.JTreeExtensions;
import io.github.astrapi69.swing.tree.TreeNodeFactory;
import io.github.astrapi69.swing.tree.factory.DefaultMutableTreeNodeExtensions;
import io.github.astrapi69.swing.tree.panel.PermissionPanel;
import io.github.astrapi69.swing.tree.panel.node.NodePanel;
import io.github.astrapi69.test.object.Permission;

public class DemoTreeNodeGenericTreeElementWithContentPanel
	extends
		TreeNodeGenericTreeElementWithContentPanel<List<Permission>, Permission>
{

	private static final long serialVersionUID = 1L;

	public DemoTreeNodeGenericTreeElementWithContentPanel()
	{
		this(BaseModel.of(new TreeNode<>()));
	}

	public DemoTreeNodeGenericTreeElementWithContentPanel(
		final IModel<TreeNode<GenericTreeElement<List<Permission>>>> model)
	{
		super(model);
	}

	@Override
	protected JTree newTree()
	{
		JTree tree = super.newTree();
		tree.setCellRenderer(new GenericTreeNodeCellRenderer<List<Permission>>());
		return tree;
	}

	@Override
	protected GenericJTable<Permission> newJTable()
	{
		GenericTableModel<Permission> permissionsTableModel = new DynamicPermissionsTableModel(
			new DynamicTableColumnsModel<>(Permission.class));
		return new GenericJTable<Permission>(permissionsTableModel)
		{

			protected void onSingleLeftClick(MouseEvent event)
			{
				super.onSingleLeftClick(event);
				DemoTreeNodeGenericTreeElementWithContentPanel.this.onTableSingleLeftClick(event);
			}

			protected void onSingleMiddleClick(MouseEvent event)
			{
				super.onSingleMiddleClick(event);
				DemoTreeNodeGenericTreeElementWithContentPanel.this.onTableSingleMiddleClick(event);
			}

			protected void onSingleRightClick(MouseEvent event)
			{
				super.onSingleRightClick(event);
				DemoTreeNodeGenericTreeElementWithContentPanel.this.onTableSingleRightClick(event);
			}

			protected void onDoubleLeftClick(MouseEvent event)
			{
				super.onDoubleLeftClick(event);
				DemoTreeNodeGenericTreeElementWithContentPanel.this.onTableDoubleLeftClick(event);
			}

			protected void onDoubleMiddleClick(MouseEvent event)
			{
				super.onDoubleMiddleClick(event);
				DemoTreeNodeGenericTreeElementWithContentPanel.this.onTableDoubleMiddleClick(event);
			}

			protected void onDoubleRightClick(MouseEvent event)
			{
				super.onDoubleRightClick(event);
				DemoTreeNodeGenericTreeElementWithContentPanel.this.onTableDoubleRightClick(event);
			}
		};
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
		final IModel<TreeNode<GenericTreeElement<List<Permission>>>> model)
	{
		TreeNode<GenericTreeElement<List<Permission>>> parentTreeNode = model.getObject();

		DefaultMutableTreeNode rootNode = TreeNodeFactory.newDefaultMutableTreeNode(parentTreeNode);

		return new DefaultTreeModel(rootNode, true);
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
			.getSelectedUserObject(mouseEvent, tree);
		if (optionalSelectedTreeNodeElement.isPresent())
		{
			TreeNode<GenericTreeElement<List<Permission>>> selectedTreeNodeElement = optionalSelectedTreeNodeElement
				.get();
			GenericTableModel<Permission> tableModel = newTableModel(selectedTreeNodeElement);

			tableModel.fireTableDataChanged();
		}
	}

	@Override
	protected void onTreeSingleRightClick(final MouseEvent mouseEvent)
	{
		int x = mouseEvent.getX();
		int y = mouseEvent.getY();
		// TreePath selectionPath = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
		Optional<TreeNode<GenericTreeElement<List<Permission>>>> optionalSelectedUserObject = JTreeExtensions
			.getSelectedUserObject(mouseEvent, tree);
		optionalSelectedUserObject.ifPresent(selectedTreeNode -> {
			JPopupMenu popup = JPopupMenuFactory.newJPopupMenu();

			if (selectedTreeNode.isNode())
			{
				popup.add(JMenuItemFactory.newJMenuItem("add node...",
					actionEvent -> this.onAddNewChildTreeNode(mouseEvent)));
			}

			if (!selectedTreeNode.isRoot())
			{
				popup.add(JMenuItemFactory.newJMenuItem("delete",
					actionEvent -> this.onDeleteSelectedTreeNode(mouseEvent)));
			}

			popup.add(JMenuItemFactory.newJMenuItem("Edit node...",
				actionEvent -> this.onEditSelectedTreeNode(mouseEvent)));

			if (!selectedTreeNode.isRoot())
			{
				popup.add(JMenuItemFactory.newJMenuItem("Copy node",
					actionEvent -> this.onCopySelectedTreeNode(mouseEvent)));
			}

			popup.add(JMenuItemFactory.newJMenuItem("Collapse node",
				actionEvent -> this.onCollapseSelectedTreeNode(mouseEvent)));

			popup.add(JMenuItemFactory.newJMenuItem("Expand node",
				actionEvent -> this.onExpandSelectedTreeNode(mouseEvent)));

			popup.show(tree, x, y);
		});
	}

	protected void onAddNewChildTreeNode(MouseEvent mouseEvent)
	{
		JTreeExtensions.getSelectedDefaultMutableTreeNode(mouseEvent, tree)
			.ifPresent(selectedDefaultMutableTreeNode -> {
				TreeNode<GenericTreeElement<List<Permission>>> selectedTreeNode = (TreeNode<GenericTreeElement<List<Permission>>>)selectedDefaultMutableTreeNode
					.getUserObject();
				NodePanel panel = new NodePanel();

				int option = JOptionPaneExtensions.getSelectedOption(panel,
					JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, "New node",
					panel.getTxtName());

				if (option == JOptionPane.OK_OPTION)
				{
					NodeModel modelObject = panel.getModelObject();
					boolean leaf = modelObject.isLeaf();
					String name = modelObject.getName();
					GenericTreeElement<List<Permission>> treeElement = GenericTreeElement
						.<List<Permission>> builder().name(name).leaf(leaf).build();
					TreeNode<GenericTreeElement<List<Permission>>> newTreeNode = TreeNode
						.<GenericTreeElement<List<Permission>>> builder().value(treeElement)
						.parent(selectedTreeNode).displayValue(name).leaf(leaf).build();

					DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(newTreeNode, leaf);
					selectedDefaultMutableTreeNode.add(newChild);
					((DefaultTreeModel)tree.getModel()).reload(selectedDefaultMutableTreeNode);
					tree.treeDidChange();
				}
			});
	}

	protected void onCopySelectedTreeNode(final MouseEvent mouseEvent)
	{
		JTreeExtensions.getSelectedDefaultMutableTreeNode(mouseEvent, tree)
			.ifPresent(selectedDefaultMutableTreeNode -> {

				TreeNode<GenericTreeElement<List<Permission>>> selectedTreeNode = (TreeNode<GenericTreeElement<List<Permission>>>)selectedDefaultMutableTreeNode
					.getUserObject();
				String displayValueCopy = selectedTreeNode.getDisplayValue() + "Copy";
				TreeNode<GenericTreeElement<List<Permission>>> clonedTreeNode = TreeNode
					.<GenericTreeElement<List<Permission>>> builder()
					.children(selectedTreeNode.getChildren()).displayValue(displayValueCopy)
					.parent(selectedTreeNode.getParent()).value(selectedTreeNode.getValue())
					.leaf(selectedTreeNode.isLeaf()).build();

				DefaultMutableTreeNodeExtensions.copyOf(selectedDefaultMutableTreeNode,
					clonedTreeNode);

				((DefaultTreeModel)tree.getModel())
					.reload(selectedDefaultMutableTreeNode.getParent());
				tree.treeDidChange();
			});
	}

	/**
	 * The callback method on edit the selected tree node
	 *
	 * @param mouseEvent
	 *            the mouse event
	 */
	protected void onEditSelectedTreeNode(final MouseEvent mouseEvent)
	{
		JTreeExtensions.getSelectedDefaultMutableTreeNode(mouseEvent, tree)
			.ifPresent(selectedDefaultMutableTreeNode -> {

				TreeNode<GenericTreeElement<List<Permission>>> selectedTreeNode = (TreeNode<GenericTreeElement<List<Permission>>>)selectedDefaultMutableTreeNode
					.getUserObject();
				NodePanel panel = new NodePanel(
					BaseModel.of(NodeModel.builder().name(selectedTreeNode.getValue().getName())
						.leaf(selectedTreeNode.getValue().isLeaf()).build()));

				int option = JOptionPaneExtensions.getSelectedOption(panel,
					JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, "New node",
					panel.getTxtName());

				if (option == JOptionPane.OK_OPTION)
				{
					NodeModel modelObject = panel.getModelObject();
					boolean leaf = modelObject.isLeaf();
					String name = modelObject.getName();
					selectedTreeNode.setLeaf(leaf);
					selectedTreeNode.setDisplayValue(name);

					if (selectedTreeNode.getValue().isLeaf() != leaf)
					{
						// set to leaf only if the node has no children
						if ((leaf) || 0 == selectedDefaultMutableTreeNode.getChildCount())
						{
							selectedTreeNode.getValue().setLeaf(leaf);
						}
					}

					selectedTreeNode.getValue().setName(name);

					((DefaultTreeModel)tree.getModel()).reload(selectedDefaultMutableTreeNode);
					tree.treeDidChange();
				}
			});
	}

	/**
	 * The callback method on the table single left click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableSingleLeftClick(MouseEvent event)
	{
		System.out.println(
			"DemoTreeNodeGenericTreeElementWithContentPanel#\n" + "\tonTableSingleLeftClick");
	}

	/**
	 * The callback method on the table single middle click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableSingleMiddleClick(MouseEvent event)
	{
		System.out.println(
			"DemoTreeNodeGenericTreeElementWithContentPanel#\n" + "\tonTableSingleMiddleClick");
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

		JPopupMenu popup = JPopupMenuFactory.newJPopupMenu();

		popup.add(JMenuItemFactory.newJMenuItem("add permission...",
			actionEvent -> this.onAddTableEntry()));

		JMenuItem menuItem = JMenuItemFactory.newJMenuItem("delete",
			actionEvent -> this.onDeleteTableEntry());
		menuItem.setEnabled(!allSelectedRowData.isEmpty());
		popup.add(menuItem);

		JMenuItem edit = JMenuItemFactory.newJMenuItem("edit",
			actionEvent -> this.onEditTableEntry());
		edit.setEnabled(allSelectedRowData.size() == 1);
		popup.add(edit);

		popup.show(getTblTreeEntryTable(), x, y);

	}

	protected void onEditTableEntry()
	{
		getTblTreeEntryTable().getSingleSelectedRowData().ifPresent(tableEntry -> {
			PermissionPanel panel = new PermissionPanel(BaseModel.of(tableEntry));
			int option = JOptionPaneExtensions.getSelectedOption(panel,
				JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, "New node",
				panel.getTxtName());

			if (option == JOptionPane.OK_OPTION)
			{
				List<Permission> data = getTblTreeEntryTable().getGenericTableModel().getData();
				int index = data.indexOf(tableEntry);
				data.remove(tableEntry);
				Permission modelObject = panel.getModelObject();

				data.add(index, modelObject);
				getTblTreeEntryTable().getGenericTableModel().fireTableDataChanged();
			}
		});
	}

	protected void onDeleteTableEntry()
	{
		int option = DialogExtensions.showConfirmDialog(null, "Confirm deletion",
			"<div width='450'>Are you sure<br></div>"
				+ "<div>The delete action is not recoverable</div>",
			JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		if (option == JOptionPane.OK_OPTION)
		{
			getTblTreeEntryTable().getAllSelectedRowData().forEach(tableEntry -> {
				getTblTreeEntryTable().getGenericTableModel().remove(tableEntry);
			});
			getTblTreeEntryTable().getGenericTableModel().fireTableDataChanged();
		}
	}

	protected void onAddTableEntry()
	{
		PermissionPanel panel = new PermissionPanel();
		int option = JOptionPaneExtensions.getSelectedOption(panel, JOptionPane.INFORMATION_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION, null, "New node", panel.getTxtName());

		if (option == JOptionPane.OK_OPTION)
		{
			Permission permission = Permission.builder()
				.description(panel.getTxtDescription().getText()).name(panel.getTxtName().getText())
				.shortcut(panel.getTxtShortcut().getText()).build();
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
		getTblTreeEntryTable().getSingleSelectedRowData().ifPresent(permission -> {
			PermissionPanel permissionPanel = new PermissionPanel(BaseModel.of(permission));
			permissionPanel.getTxtName().setEnabled(false);
			permissionPanel.getTxtDescription().setEnabled(false);
			permissionPanel.getTxtShortcut().setEnabled(false);
			JOptionPane pane = new JOptionPane(permissionPanel, JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.OK_CANCEL_OPTION);
			JDialog dialog = pane.createDialog(null, "New permission");
			dialog.pack();
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		});
	}

	/**
	 * The callback method on the table double middle click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableDoubleMiddleClick(MouseEvent event)
	{
		System.out.println(
			"DemoTreeNodeGenericTreeElementWithContentPanel#\n" + "\tonTableDoubleMiddleClick");
	}

	/**
	 * The callback method on the table double right click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableDoubleRightClick(MouseEvent event)
	{
		System.out.println(
			"DemoTreeNodeGenericTreeElementWithContentPanel#\n" + "\tonTableDoubleRightClick");
	}

}
