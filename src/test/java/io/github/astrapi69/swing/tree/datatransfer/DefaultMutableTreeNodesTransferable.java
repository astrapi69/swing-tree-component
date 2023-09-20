package io.github.astrapi69.swing.tree.datatransfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.tree.DefaultMutableTreeNode;

public class DefaultMutableTreeNodesTransferable implements Transferable
{

	DataFlavor nodesFlavor;
	DefaultMutableTreeNode[] nodes;
	DataFlavor[] flavors;

	public DefaultMutableTreeNodesTransferable(DefaultMutableTreeNode[] nodes,
		DataFlavor nodesFlavor, DataFlavor[] flavors)
	{
		this.nodes = nodes;
		this.flavors = flavors;
		this.nodesFlavor = nodesFlavor;
	}

	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
	{
		if (!isDataFlavorSupported(flavor))
			throw new UnsupportedFlavorException(flavor);
		return nodes;
	}

	public DataFlavor[] getTransferDataFlavors()
	{
		return flavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor)
	{
		return nodesFlavor.equals(flavor);
	}
}