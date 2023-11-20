package io.github.astrapi69.swing.tree.datatransfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import io.github.astrapi69.swing.tree.factory.DefaultMutableTreeNodeFactory;

public class TreeTransferHandler extends TransferHandler
{
	DataFlavor nodesFlavor;
	DataFlavor[] flavors = new DataFlavor[1];
	DefaultMutableTreeNode[] nodesToRemove;

	public TreeTransferHandler()
	{
		try
		{
			String mimeType = DataFlavor.javaJVMLocalObjectMimeType + ";class=\""
				+ javax.swing.tree.DefaultMutableTreeNode[].class.getName() + "\"";
			nodesFlavor = new DataFlavor(mimeType);
			flavors[0] = nodesFlavor;
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("ClassNotFound: " + e.getMessage());
		}
	}

	public boolean canImport(TransferHandler.TransferSupport support)
	{
		if (!support.isDrop())
		{
			return false;
		}
		support.setShowDropLocation(true);
		if (!support.isDataFlavorSupported(nodesFlavor))
		{
			return false;
		}
		// Do not allow a drop on the drag source selections.
		JTree.DropLocation dropLocation = (JTree.DropLocation)support.getDropLocation();
		JTree tree = (JTree)support.getComponent();
		int dropRow = tree.getRowForPath(dropLocation.getPath());
		int[] selRows = tree.getSelectionRows();
		for (int i = 0; i < selRows.length; i++)
		{
			if (selRows[i] == dropRow)
			{
				return false;
			}
		}
		// Do not allow MOVE-action drops if a non-leaf node is
		// selected unless all of its children are also selected.
		int action = support.getDropAction();
		if (action == MOVE)
		{
			return haveCompleteNode(tree);
		}
		// Do not allow a non-leaf node to be copied to a level
		// which is less than its source level.
		TreePath dest = dropLocation.getPath();
		DefaultMutableTreeNode target = (DefaultMutableTreeNode)dest.getLastPathComponent();
		TreePath path = tree.getPathForRow(selRows[0]);
		DefaultMutableTreeNode firstNode = (DefaultMutableTreeNode)path.getLastPathComponent();
		if (firstNode.getChildCount() > 0 && target.getLevel() < firstNode.getLevel())
		{
			return false;
		}
		return true;
	}

	private boolean haveCompleteNode(JTree tree)
	{
		int[] selRows = tree.getSelectionRows();
		TreePath path = tree.getPathForRow(selRows[0]);
		DefaultMutableTreeNode first = (DefaultMutableTreeNode)path.getLastPathComponent();
		int childCount = first.getChildCount();
		// first has children and no children are selected.
		if (childCount > 0 && selRows.length == 1)
			return false;
		// first may have children.
		for (int i = 1; i < selRows.length; i++)
		{
			path = tree.getPathForRow(selRows[i]);
			DefaultMutableTreeNode next = (DefaultMutableTreeNode)path.getLastPathComponent();
			if (first.isNodeChild(next))
			{
				// Found a child of first.
				if (childCount > selRows.length - 1)
				{
					// Not all children of first are selected.
					return false;
				}
			}
		}
		return true;
	}

	protected Transferable createTransferable(JComponent component)
	{
		if (component instanceof JTree)
		{
			JTree tree = (JTree)component;
			TreePath[] paths = tree.getSelectionPaths();
			if (paths != null)
			{
				// Make up a node array of copies for transfer and
				// another for/of the nodes that will be removed in
				// exportDone after a successful drop.
				List<DefaultMutableTreeNode> copies = new ArrayList<DefaultMutableTreeNode>();
				List<DefaultMutableTreeNode> toRemove = new ArrayList<DefaultMutableTreeNode>();
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths[0]
					.getLastPathComponent();
				DefaultMutableTreeNode copy = DefaultMutableTreeNodeFactory
					.newDefaultMutableTreeNode(node);
				copies.add(copy);
				toRemove.add(node);
				for (int i = 1; i < paths.length; i++)
				{
					DefaultMutableTreeNode next = (DefaultMutableTreeNode)paths[i]
						.getLastPathComponent();
					// Do not allow higher level nodes to be added to list.
					if (next.getLevel() < node.getLevel())
					{
						break;
					}
					else if (next.getLevel() > node.getLevel())
					{ // child node
						copy.add(DefaultMutableTreeNodeFactory.newDefaultMutableTreeNode(next));
						// node already contains child
					}
					else
					{ // sibling
						copies.add(DefaultMutableTreeNodeFactory.newDefaultMutableTreeNode(next));
						toRemove.add(next);
					}
				}
				DefaultMutableTreeNode[] nodes = copies
					.toArray(new DefaultMutableTreeNode[copies.size()]);
				nodesToRemove = toRemove.toArray(new DefaultMutableTreeNode[toRemove.size()]);
				return new DefaultMutableTreeNodesTransferable(nodes, nodesFlavor, flavors);
			}
		}
		return null;
	}

	protected void exportDone(JComponent source, Transferable data, int action)
	{
		if ((action & MOVE) == MOVE)
		{
			JTree tree = (JTree)source;
			DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
			// Remove nodes saved in nodesToRemove in createTransferable.
			for (int i = 0; i < nodesToRemove.length; i++)
			{
				model.removeNodeFromParent(nodesToRemove[i]);
			}
		}
	}

	public int getSourceActions(JComponent c)
	{
		return COPY_OR_MOVE;
	}

	public boolean importData(TransferHandler.TransferSupport support)
	{
		if (!canImport(support))
		{
			return false;
		}
		// Extract transfer data.
		DefaultMutableTreeNode[] nodes = null;
		try
		{
			Transferable transferable = support.getTransferable();
			nodes = (DefaultMutableTreeNode[])transferable.getTransferData(nodesFlavor);
		}
		catch (UnsupportedFlavorException ufe)
		{
			System.out.println("UnsupportedFlavor: " + ufe.getMessage());
		}
		catch (java.io.IOException ioe)
		{
			System.out.println("I/O error: " + ioe.getMessage());
		}
		// Get drop location info.
		JTree.DropLocation dropLocation = (JTree.DropLocation)support.getDropLocation();
		int childIndex = dropLocation.getChildIndex();
		TreePath dest = dropLocation.getPath();
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode)dest.getLastPathComponent();
		JTree tree = (JTree)support.getComponent();
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		// Configure for drop mode.
		int index = childIndex; // DropMode.INSERT
		if (childIndex == -1)
		{ // DropMode.ON
			index = parent.getChildCount();
		}
		// Add data to model.
		for (int i = 0; i < nodes.length; i++)
		{
			model.insertNodeInto(nodes[i], parent, index++);
		}
		return true;
	}

	public String toString()
	{
		return getClass().getName();
	}

}