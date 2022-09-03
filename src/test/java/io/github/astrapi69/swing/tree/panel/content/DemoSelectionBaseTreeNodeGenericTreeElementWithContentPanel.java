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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import org.jdesktop.swingx.JXTree;

import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.model.node.NodeModel;
import io.github.astrapi69.swing.dialog.DialogExtensions;
import io.github.astrapi69.swing.dialog.JOptionPaneExtensions;
import io.github.astrapi69.swing.menu.MenuFactory;
import io.github.astrapi69.swing.table.GenericJXTable;
import io.github.astrapi69.swing.table.model.DynamicPermissionsTableModel;
import io.github.astrapi69.swing.table.model.GenericTableModel;
import io.github.astrapi69.swing.table.model.dynamic.DynamicTableColumnsModel;
import io.github.astrapi69.swing.tree.BaseTreeNodeFactory;
import io.github.astrapi69.swing.tree.GenericTreeElement;
import io.github.astrapi69.swing.tree.JTreeExtensions;
import io.github.astrapi69.swing.tree.factory.DefaultMutableTreeNodeExtensions;
import io.github.astrapi69.swing.tree.panel.PermissionPanel;
import io.github.astrapi69.swing.tree.panel.node.NodePanel;
import io.github.astrapi69.swing.tree.renderer.state.NewGenericBaseTreeNodeCellRenderer;
import io.github.astrapi69.test.object.Permission;
import io.github.astrapi69.tree.BaseTreeNode;

public class DemoSelectionBaseTreeNodeGenericTreeElementWithContentPanel
	extends
		BaseTreeNodeGenericTreeElementWithContentPanel<List<Permission>, Long, Permission>
{

	private static final long serialVersionUID = 1L;

	public DemoSelectionBaseTreeNodeGenericTreeElementWithContentPanel()
	{
		this(BaseModel.of(new BaseTreeNode<>()));
	}

	public DemoSelectionBaseTreeNodeGenericTreeElementWithContentPanel(
		final IModel<BaseTreeNode<GenericTreeElement<List<Permission>>, Long>> model)
	{
		super(model);
	}

	@Override
	protected JXTree newTree()
	{
		JXTree tree = super.newTree();
		tree.setCellRenderer(new NewGenericBaseTreeNodeCellRenderer<List<Permission>, Long>());
		return tree;
	}

	@Override
	protected JScrollPane newTreeScrollPane()
	{
		JScrollPane scroller = super.newTreeScrollPane();
		scroller.getViewport().setOpaque(false);
		scroller.setOpaque(false);
		return scroller;
	}

	@Override
	protected GenericJXTable<Permission> newJTable()
	{
		GenericTableModel<Permission> permissionsTableModel = new DynamicPermissionsTableModel(
			new DynamicTableColumnsModel<>(Permission.class));
		return new GenericJXTable<Permission>(permissionsTableModel)
		{

			protected void onSingleLeftClick(MouseEvent event)
			{
				super.onSingleLeftClick(event);
				DemoSelectionBaseTreeNodeGenericTreeElementWithContentPanel.this
					.onTableSingleLeftClick(event);
			}

			protected void onSingleMiddleClick(MouseEvent event)
			{
				super.onSingleMiddleClick(event);
				DemoSelectionBaseTreeNodeGenericTreeElementWithContentPanel.this
					.onTableSingleMiddleClick(event);
			}

			protected void onSingleRightClick(MouseEvent event)
			{
				super.onSingleRightClick(event);
				DemoSelectionBaseTreeNodeGenericTreeElementWithContentPanel.this
					.onTableSingleRightClick(event);
			}

			protected void onDoubleLeftClick(MouseEvent event)
			{
				super.onDoubleLeftClick(event);
				DemoSelectionBaseTreeNodeGenericTreeElementWithContentPanel.this
					.onTableDoubleLeftClick(event);
			}

			protected void onDoubleMiddleClick(MouseEvent event)
			{
				super.onDoubleMiddleClick(event);
				DemoSelectionBaseTreeNodeGenericTreeElementWithContentPanel.this
					.onTableDoubleMiddleClick(event);
			}

			protected void onDoubleRightClick(MouseEvent event)
			{
				super.onDoubleRightClick(event);
				DemoSelectionBaseTreeNodeGenericTreeElementWithContentPanel.this
					.onTableDoubleRightClick(event);
			}
		};
	}

	@Override
	protected void onAfterInitializeComponents()
	{
		super.onAfterInitializeComponents();
		// set root
		BaseTreeNode<GenericTreeElement<List<Permission>>, Long> root = getModelObject().getRoot();
		getTblTreeEntryTable().setModel(newTableModel(root));
	}

	@Override
	protected TreeModel newTreeModel(
		final IModel<BaseTreeNode<GenericTreeElement<List<Permission>>, Long>> model)
	{
		BaseTreeNode<GenericTreeElement<List<Permission>>, Long> parentTreeNode = model.getObject();

		DefaultMutableTreeNode rootNode = BaseTreeNodeFactory
			.newDefaultMutableTreeNode(parentTreeNode);

		return new DefaultTreeModel(rootNode, true);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param model
	 */
	@Override
	protected GenericTableModel<Permission> newTableModel(
		BaseTreeNode<GenericTreeElement<List<Permission>>, Long> model)
	{
		GenericTreeElement<List<Permission>> parentTreeNode = model.getValue();
		List<Permission> permissions = parentTreeNode.getDefaultContent();
		if (permissions == null)
		{
			permissions = new ArrayList<>();
		}
		// 2. Create a generic table model for the class Permission.
		getTblTreeEntryTable().getGenericTableModel().removeAll();
		getTblTreeEntryTable().getGenericTableModel().addList(permissions);
		return getTblTreeEntryTable().getGenericTableModel();
	}

	@Override
	protected void onTreeSingleLeftClick(MouseEvent mouseEvent)
	{
		Optional<BaseTreeNode<GenericTreeElement<List<Permission>>, Long>> optionalSelectedTreeNodeElement = JTreeExtensions
			.getSelectedUserObject(mouseEvent, tree);
		if (optionalSelectedTreeNodeElement.isPresent())
		{
			BaseTreeNode<GenericTreeElement<List<Permission>>, Long> selectedTreeNodeElement = optionalSelectedTreeNodeElement
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
		Optional<BaseTreeNode<GenericTreeElement<List<Permission>>, Long>> optionalSelectedUserObject = JTreeExtensions
			.getSelectedUserObject(mouseEvent, tree);
		optionalSelectedUserObject.ifPresent(selectedTreeNode -> {
			JPopupMenu popup = MenuFactory.newJPopupMenu();

			if (!selectedTreeNode.isLeaf())
			{
				popup.add(MenuFactory.newJMenuItem("add node...",
					actionEvent -> this.onAddNewChildTreeNode(mouseEvent)));
			}

			if (!selectedTreeNode.isRoot())
			{
				popup.add(MenuFactory.newJMenuItem("delete",
					actionEvent -> this.onDeleteSelectedTreeNode(mouseEvent)));
			}

			popup.add(MenuFactory.newJMenuItem("Edit node...",
				actionEvent -> this.onEditSelectedTreeNode(mouseEvent)));

			if (!selectedTreeNode.isRoot())
			{
				popup.add(MenuFactory.newJMenuItem("Copy node",
					actionEvent -> this.onCopySelectedTreeNode(mouseEvent)));
			}

			popup.add(MenuFactory.newJMenuItem("Collapse node",
				actionEvent -> this.onCollapseSelectedTreeNode(mouseEvent)));

			popup.add(MenuFactory.newJMenuItem("Expand node",
				actionEvent -> this.onExpandSelectedTreeNode(mouseEvent)));

			popup.show(tree, x, y);
		});
	}

	protected void onAddNewChildTreeNode(MouseEvent mouseEvent)
	{
		JTreeExtensions.getSelectedDefaultMutableTreeNode(mouseEvent, tree)
			.ifPresent(selectedDefaultMutableTreeNode -> {
				Object userObject = selectedDefaultMutableTreeNode.getUserObject();
				BaseTreeNode<GenericTreeElement<List<Permission>>, Long> selectedTreeNode = (BaseTreeNode<GenericTreeElement<List<Permission>>, Long>)userObject;
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
					BaseTreeNode<GenericTreeElement<List<Permission>>, Long> newTreeNode = BaseTreeNode
						.<GenericTreeElement<List<Permission>>, Long> builder().value(treeElement)
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
				Object userObject = selectedDefaultMutableTreeNode.getUserObject();
				BaseTreeNode<GenericTreeElement<List<Permission>>, Long> selectedTreeNode = (BaseTreeNode<GenericTreeElement<List<Permission>>, Long>)userObject;
				String displayValueCopy = selectedTreeNode.getDisplayValue() + "Copy";
				BaseTreeNode<GenericTreeElement<List<Permission>>, Long> clonedTreeNode = BaseTreeNode
					.<GenericTreeElement<List<Permission>>, Long> builder()
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
				Object userObject = selectedDefaultMutableTreeNode.getUserObject();
				BaseTreeNode<GenericTreeElement<List<Permission>>, Long> selectedTreeNode = (BaseTreeNode<GenericTreeElement<List<Permission>>, Long>)userObject;
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

		JPopupMenu popup = MenuFactory.newJPopupMenu();

		popup.add(
			MenuFactory.newJMenuItem("add permission...", actionEvent -> this.onAddTableEntry()));

		JMenuItem menuItem = MenuFactory.newJMenuItem("delete",
			actionEvent -> this.onDeleteTableEntry());
		menuItem.setEnabled(!allSelectedRowData.isEmpty());
		popup.add(menuItem);

		JMenuItem edit = MenuFactory.newJMenuItem("edit", actionEvent -> this.onEditTableEntry());
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
			DefaultMutableTreeNode selectedTreeNode = getSelectedTreeNode();
			BaseTreeNode<GenericTreeElement<List<Permission>>, Long> selectedBaseTreeNode;
			if (selectedTreeNode == null)
			{
				selectedBaseTreeNode = getModelObject().getRoot();
			}
			else
			{
				Object userObject = selectedTreeNode.getUserObject();
				selectedBaseTreeNode = (BaseTreeNode<GenericTreeElement<List<Permission>>, Long>)userObject;
			}
			GenericTreeElement<List<Permission>> value = selectedBaseTreeNode.getValue();
			if (value.getDefaultContent() == null)
			{
				value.setDefaultContent(ListFactory.newArrayList());
			}
			value.getDefaultContent().add(permission);
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
