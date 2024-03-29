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
package io.github.astrapi69.swing.tree.extension;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import io.github.astrapi69.roboter.MouseExtensions;
import lombok.NonNull;

/**
 * The class {@link JTreeExtensions}.
 */
public class JTreeExtensions
{

	/**
	 * Gets the selected tree node as {@link DefaultMutableTreeNode} object
	 *
	 * @param <T>
	 *            the generic type
	 * @param mouseEvent
	 *            the mouse event
	 * @param tree
	 *            the tree
	 * @return the selected tree node
	 */
	public static <T extends DefaultMutableTreeNode> Optional<T> getSelectedDefaultMutableTreeNode(
		@NonNull MouseEvent mouseEvent, @NonNull JTree tree)
	{
		return getSelectedDefaultMutableTreeNode(tree, mouseEvent.getX(), mouseEvent.getY());
	}

	/**
	 * Gets the selected tree node as {@link DefaultMutableTreeNode} object from the given
	 * {@link Point} object
	 *
	 * @param <T>
	 *            the generic type
	 * @param tree
	 *            the tree
	 * @param point
	 *            the point
	 * @return the selected tree node
	 */
	public static <T extends DefaultMutableTreeNode> Optional<T> getSelectedDefaultMutableTreeNode(
		@NonNull JTree tree, @NonNull Point point)
	{
		return getSelectedDefaultMutableTreeNode(tree, point.x, point.y);
	}

	/**
	 * Gets the selected tree node as {@link DefaultMutableTreeNode} object
	 *
	 * @param <T>
	 *            the generic type
	 * @param tree
	 *            the tree
	 * @param x
	 *            the horizontal x position
	 * @param y
	 *            the vertical y position
	 * @return the selected tree node
	 */
	@SuppressWarnings("unchecked")
	public static <T extends DefaultMutableTreeNode> Optional<T> getSelectedDefaultMutableTreeNode(
		@NonNull JTree tree, int x, int y)
	{
		TreePath selectionPath = tree.getPathForLocation(x, y);
		if (selectionPath == null)
		{
			return Optional.empty();
		}
		T lastPathComponent = (T)selectionPath.getLastPathComponent();
		return Optional.of(lastPathComponent);
	}

	/**
	 * Expand all nodes recursive
	 *
	 * @param tree
	 *            the tree
	 * @param path
	 *            the path
	 * @param expand
	 *            the flag to expand or collapse
	 */
	public static void expandAll(@NonNull JTree tree, @NonNull TreePath path, boolean expand)
	{
		TreeNode node = (TreeNode)path.getLastPathComponent();

		if (node.getChildCount() >= 0)
		{
			Enumeration<?> enumeration = node.children();
			while (enumeration.hasMoreElements())
			{
				TreeNode n = (TreeNode)enumeration.nextElement();
				TreePath p = path.pathByAddingChild(n);

				expandAll(tree, p, expand);
			}
		}

		if (expand)
		{
			tree.expandPath(path);
		}
		else
		{
			tree.collapsePath(path);
		}
	}

	/**
	 * Expand all nodes but non-recursive
	 *
	 * @param tree
	 *            the tree
	 */
	public static void expandNodes(final @NonNull JTree tree)
	{
		for (int i = 0; i < tree.getRowCount(); i++)
		{
			tree.expandRow(i);
		}
	}

	/**
	 * Gets the selected user object from the given {@link JTree} object
	 *
	 * @param <T>
	 *            the generic type
	 * @param mouseEvent
	 *            the mouse event
	 * @param tree
	 *            the tree
	 * @return the selected user object from the given {@link JTree} object
	 */
	public static <T> Optional<T> getSelectedUserObject(final @NonNull MouseEvent mouseEvent,
		final @NonNull JTree tree)
	{
		Optional<DefaultMutableTreeNode> selectedTreeNode = getSelectedDefaultMutableTreeNode(
			mouseEvent, tree);
		return getOptionalTreeNode(selectedTreeNode);
	}

	/**
	 * Gets the selected user object from the given {@link JTree} object
	 *
	 * @param <T>
	 *            the generic type
	 * @param tree
	 *            the tree
	 * @return the selected user object from the given {@link JTree} object
	 */
	public static <T> Optional<T> getSelectedUserObject(final @NonNull JTree tree)
	{
		Optional<DefaultMutableTreeNode> selectedTreeNode = getSelectedTreeNode(tree);
		return getOptionalTreeNode(selectedTreeNode);
	}

	@SuppressWarnings("unchecked")
	private static <T> Optional<T> getOptionalTreeNode(
		Optional<DefaultMutableTreeNode> selectedTreeNode)
	{
		if (selectedTreeNode.isPresent())
		{
			DefaultMutableTreeNode defaultMutableTreeNode = selectedTreeNode.get();
			Object userObject = defaultMutableTreeNode.getUserObject();
			if (userObject != null)
			{
				T uo = (T)userObject;
				return Optional.of(uo);
			}
		}
		return Optional.empty();
	}

	/**
	 * Gets the selected tree node as {@link DefaultMutableTreeNode} object
	 *
	 * @param <T>
	 *            the generic type
	 * @param tree
	 *            the tree
	 * @return the selected user object from the given {@link JTree} object
	 */
	@SuppressWarnings("unchecked")
	public static <T extends DefaultMutableTreeNode> Optional<T> getSelectedTreeNode(
		final @NonNull JTree tree)
	{
		T selectedTreeNode = (T)tree.getLastSelectedPathComponent();
		if (selectedTreeNode != null)
		{
			return Optional.of(selectedTreeNode);
		}
		else
		{
			if (MouseExtensions.isMouseWithin(tree))
			{
				Point location = MouseInfo.getPointerInfo().getLocation();
				return getSelectedDefaultMutableTreeNode(tree, location.x, location.y);
			}
		}
		return Optional.empty();
	}

	/**
	 * Creates a {@link List} object with all parent and the given {@link TreeNode} object in the
	 * parent child order
	 *
	 * @param treeNode
	 *            the tree node
	 * @return the {@link List} object with all parent and the given {@link TreeNode} object in the
	 *         parent child order
	 */
	public static List<Object> getTreeNodes(final @NonNull TreeNode treeNode)
	{
		List<Object> treeNodes = new ArrayList<>();
		treeNodes.add(treeNode);
		TreeNode parenTreeNode = treeNode.getParent();
		while (parenTreeNode != null)
		{
			treeNodes.add(0, parenTreeNode);
			parenTreeNode = parenTreeNode.getParent();
		}
		return treeNodes;
	}

	/**
	 * Creates a {@link TreePath} object from the given {@link TreeNode} object
	 *
	 * @param treeNode
	 *            the tree node
	 * @return the {@link TreePath} object from the given {@link TreeNode} object
	 */
	public static TreePath getTreePath(TreeNode treeNode)
	{
		List<Object> treeNodes = getTreeNodes(treeNode);
		return treeNodes.isEmpty() ? null : new TreePath(treeNodes.toArray());
	}
}
