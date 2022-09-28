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
package io.github.astrapi69.swing.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import lombok.NonNull;
import io.github.astrapi69.swing.tree.factory.DefaultMutableTreeNodeFactory;
import io.github.astrapi69.tree.TreeNode;

import io.github.astrapi69.swing.tree.model.TreeElement;

/**
 * Factory class for generate {@link DefaultMutableTreeNode} from {@link TreeNode}
 */
public class TreeNodeFactory
{

	/**
	 * Creates a new {@link DefaultMutableTreeNode} object from the given {@link TreeNode} object
	 *
	 * @param treeNode
	 *            the {@link TreeNode} object
	 * @param <T>
	 *            the generic type of the given {@link TreeNode} object
	 * @return the new {@link DefaultMutableTreeNode} object generated from the given
	 *         {@link TreeNode} object
	 */
	public static <T> DefaultMutableTreeNode newDefaultMutableTreeNode(
		@NonNull TreeNode<T> treeNode)
	{
		TreeNode<T> rootNode = treeNode;
		if (!treeNode.isRoot())
		{
			rootNode = treeNode.getRoot();
		}
		return traverseAndAdd(null, rootNode);
	}

	/**
	 * Traverses through the given {@link TreeNode} object and return the root
	 * {@link DefaultMutableTreeNode} object
	 *
	 * @param rootDefaultMutableTreeNode
	 *            the {@link DefaultMutableTreeNode} object
	 * @param treeNode
	 *            the {@link TreeNode} object
	 * @param <T>
	 *            the generic type of the given {@link TreeNode} object
	 * @return the root {@link DefaultMutableTreeNode} object
	 */
	public static <T> DefaultMutableTreeNode traverseAndAdd(
		DefaultMutableTreeNode rootDefaultMutableTreeNode, @NonNull TreeNode<T> treeNode)
	{
		DefaultMutableTreeNode parent = rootDefaultMutableTreeNode;
		if (rootDefaultMutableTreeNode == null)
		{
			parent = DefaultMutableTreeNodeFactory.newDefaultMutableTreeNode(null, treeNode);
		}
		for (final TreeNode<T> data : treeNode.getChildren())
		{
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(data);
			parent.add(node);
			traverseAndAdd(node, data);
		}
		return parent;
	}

	/**
	 * Factory method that creates a new {@link TreeNode} object from the given {@link TreeElement}
	 * object
	 *
	 * @param treeElement
	 *            the {@link TreeElement} object
	 * @param parentTreeNode
	 *            the parent object
	 * @return the new {@link TreeNode} object
	 */
	public static TreeNode<TreeElement> initializeTreeNodeWithTreeElement(
		final TreeElement treeElement, TreeNode<TreeElement> parentTreeNode)
	{
		TreeNode<TreeElement> treeNode;
		treeNode = new TreeNode<>(treeElement);
		treeNode.setLeaf(!treeElement.isNode());
		treeNode.setDisplayValue(treeElement.getName());
		if (parentTreeNode != null)
		{
			parentTreeNode.addChild(treeNode);
		}
		return treeNode;
	}

	/**
	 * Factory method that creates a new {@link TreeNode} object from the given {@link TreeElement}
	 * object
	 *
	 * @param treeElement
	 *            the {@link TreeElement} object
	 * @param parentTreeNode
	 *            the parent object
	 * @return the new {@link TreeNode} object
	 */
	public static TreeNode<JXTreeElement> initializeTreeNodeWithTreeElement(
		final JXTreeElement treeElement, TreeNode<JXTreeElement> parentTreeNode)
	{
		TreeNode<JXTreeElement> treeNode;
		treeNode = new TreeNode<>(treeElement);
		treeNode.setLeaf(!treeElement.isNode());
		treeNode.setDisplayValue(treeElement.getName());
		if (parentTreeNode != null)
		{
			parentTreeNode.addChild(treeNode);
		}
		return treeNode;
	}

	/**
	 * Factory method that creates a new {@link TreeNode} object from the given {@link TreeElement}
	 * object
	 * 
	 * @param <T>
	 *            the generic type of the given {@link GenericTreeElement} object
	 *
	 * @param treeElement
	 *            the {@link TreeElement} object
	 * @param parentTreeNode
	 *            the parent object
	 * @return the new {@link TreeNode} object
	 */
	public static <T> TreeNode<GenericTreeElement<T>> initializeTreeNodeWithTreeElement(
		final GenericTreeElement<T> treeElement, TreeNode<GenericTreeElement<T>> parentTreeNode)
	{
		TreeNode<GenericTreeElement<T>> treeNode = new TreeNode<>(treeElement);
		treeNode.setLeaf(treeElement.isLeaf());
		treeNode.setDisplayValue(treeElement.getName());
		if (parentTreeNode != null)
		{
			parentTreeNode.addChild(treeNode);
		}
		return treeNode;
	}

}
