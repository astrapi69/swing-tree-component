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
import io.github.astrapi69.tree.ParentIdTreeNode;
import io.github.astrapi69.tree.TreeElement;

/**
 * Factory class for generate {@link DefaultMutableTreeNode} from {@link ParentIdTreeNode}
 */
public class ParentIdTreeNodeFactory
{

	/**
	 * Creates a new {@link DefaultMutableTreeNode} object from the given {@link ParentIdTreeNode}
	 * object
	 *
	 * @param treeNode
	 *            the {@link ParentIdTreeNode} object
	 * @param <T>
	 *            the generic type of the given {@link ParentIdTreeNode} object
	 * @return the new {@link DefaultMutableTreeNode} object generated from the given
	 *         {@link ParentIdTreeNode} object
	 */
	public static <T, K> DefaultMutableTreeNode newDefaultMutableTreeNode(
		@NonNull ParentIdTreeNode<T, K> treeNode)
	{
		ParentIdTreeNode<T, K> rootNode = treeNode;
		// if (!treeNode.isRoot())
		// {
		// rootNode = (ParentIdTreeNode<T, K>)treeNode.getRoot();
		// }
		return traverseAndAdd(null, rootNode);
	}

	/**
	 * Traverses through the given {@link ParentIdTreeNode} object and return the root
	 * {@link DefaultMutableTreeNode} object
	 *
	 * @param rootDefaultMutableTreeNode
	 *            the {@link DefaultMutableTreeNode} object
	 * @param treeNode
	 *            the {@link ParentIdTreeNode} object
	 * @param <T>
	 *            the generic type of the given {@link ParentIdTreeNode} object
	 * @return the root {@link DefaultMutableTreeNode} object
	 */
	public static <T, K> DefaultMutableTreeNode traverseAndAdd(
		DefaultMutableTreeNode rootDefaultMutableTreeNode, @NonNull ParentIdTreeNode<T, K> treeNode)
	{
		DefaultMutableTreeNode parent = rootDefaultMutableTreeNode;
		if (rootDefaultMutableTreeNode == null)
		{
			parent = DefaultMutableTreeNodeFactory.newDefaultMutableTreeNode(null, treeNode);
		}
		for (final ParentIdTreeNode<T, K> data : treeNode.getChildren())
		{
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(data);
			parent.add(node);
			traverseAndAdd(node, (ParentIdTreeNode<TreeElement, K>)data);
		}
		return parent;
	}

	/**
	 * Factory method that creates a new {@link ParentIdTreeNode} object from the given
	 * {@link TreeElement} object
	 *
	 * @param treeElement
	 *            the {@link TreeElement} object
	 * @param parentTreeNode
	 *            the parent object
	 * @return the new {@link ParentIdTreeNode} object
	 */
	public static <K> ParentIdTreeNode<TreeElement, K> initializeTreeNodeWithTreeElement(
		final TreeElement treeElement, ParentIdTreeNode<TreeElement, K> parentTreeNode)
	{
		ParentIdTreeNode<TreeElement, K> treeNode;
		treeNode = new ParentIdTreeNode<>(treeElement);
		treeElement.setNode(treeElement.isNode());
		treeNode.setDisplayValue(treeElement.getName());
		if (parentTreeNode != null)
		{
			parentTreeNode.addChild(treeNode);
		}
		return treeNode;
	}

	/**
	 * Factory method that creates a new {@link ParentIdTreeNode} object from the given
	 * {@link TreeElement} object
	 *
	 * @param treeElement
	 *            the {@link TreeElement} object
	 * @param parentTreeNode
	 *            the parent object
	 * @return the new {@link ParentIdTreeNode} object
	 */
	public static <K> ParentIdTreeNode<JXTreeElement, K> initializeTreeNodeWithTreeElement(
		final JXTreeElement treeElement, ParentIdTreeNode<JXTreeElement, K> parentTreeNode)
	{
		ParentIdTreeNode<JXTreeElement, K> treeNode;
		treeNode = new ParentIdTreeNode<>(treeElement);
		treeElement.setNode(treeElement.isNode());
		treeNode.setDisplayValue(treeElement.getName());
		if (parentTreeNode != null)
		{
			parentTreeNode.addChild(treeNode);
		}
		return treeNode;
	}

	/**
	 * Factory method that creates a new {@link ParentIdTreeNode} object from the given
	 * {@link TreeElement} object
	 *
	 * @param treeElement
	 *            the {@link TreeElement} object
	 * @param parentTreeNode
	 *            the parent object
	 * @return the new {@link ParentIdTreeNode} object
	 */
	public static <T, K> ParentIdTreeNode<GenericTreeElement<T>, K> initializeTreeNodeWithTreeElement(
		final GenericTreeElement<T> treeElement,
		ParentIdTreeNode<GenericTreeElement<T>, K> parentTreeNode)
	{
		ParentIdTreeNode<GenericTreeElement<T>, K> treeNode = new ParentIdTreeNode<>(treeElement);
		treeElement.setNode(treeElement.isNode());
		treeNode.setDisplayValue(treeElement.getName());
		if (parentTreeNode != null)
		{
			parentTreeNode.addChild(treeNode);
		}
		return treeNode;
	}

}
