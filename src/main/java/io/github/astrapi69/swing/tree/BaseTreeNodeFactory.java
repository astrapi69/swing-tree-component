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

import io.github.astrapi69.tree.BaseTreeNode;
import lombok.NonNull;
import io.github.astrapi69.swing.tree.factory.DefaultMutableTreeNodeFactory;
import io.github.astrapi69.tree.TreeElement;

/**
 * Factory class for generate {@link DefaultMutableTreeNode} from {@link BaseTreeNode}
 */
public class BaseTreeNodeFactory
{

	/**
	 * Creates a new {@link DefaultMutableTreeNode} object from the given {@link BaseTreeNode}
	 * object
	 *
	 * @param treeNode
	 *            the {@link BaseTreeNode} object
	 * @param <T>
	 *            the generic type of the given {@link BaseTreeNode} object
	 * @return the new {@link DefaultMutableTreeNode} object generated from the given
	 *         {@link BaseTreeNode} object
	 */
	public static <T> DefaultMutableTreeNode newDefaultMutableTreeNode(
		@NonNull BaseTreeNode<T> treeNode)
	{
		BaseTreeNode<T> rootNode = treeNode;
		if (!treeNode.isRoot())
		{
			rootNode = (BaseTreeNode<T>)treeNode.getRoot();
		}
		return traverseAndAdd(null, rootNode);
	}

	/**
	 * Traverses through the given {@link BaseTreeNode} object and return the root
	 * {@link DefaultMutableTreeNode} object
	 *
	 * @param rootDefaultMutableTreeNode
	 *            the {@link DefaultMutableTreeNode} object
	 * @param treeNode
	 *            the {@link BaseTreeNode} object
	 * @param <T>
	 *            the generic type of the given {@link BaseTreeNode} object
	 * @return the root {@link DefaultMutableTreeNode} object
	 */
	public static <T> DefaultMutableTreeNode traverseAndAdd(
		DefaultMutableTreeNode rootDefaultMutableTreeNode, @NonNull BaseTreeNode<T> treeNode)
	{
		DefaultMutableTreeNode parent = rootDefaultMutableTreeNode;
		if (rootDefaultMutableTreeNode == null)
		{
			parent = DefaultMutableTreeNodeFactory.newDefaultMutableTreeNode(null, treeNode);
		}
		for (final BaseTreeNode<T> data : treeNode.getChildren())
		{
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(data);
			parent.add(node);
			traverseAndAdd(node, (BaseTreeNode<TreeElement>)data);
		}
		return parent;
	}

	/**
	 * Factory method that creates a new {@link BaseTreeNode} object from the given
	 * {@link TreeElement} object
	 *
	 * @param treeElement
	 *            the {@link TreeElement} object
	 * @param parentTreeNode
	 *            the parent object
	 * @return the new {@link BaseTreeNode} object
	 */
	public static BaseTreeNode<TreeElement> initializeTreeNodeWithTreeElement(
		final TreeElement treeElement, BaseTreeNode<TreeElement> parentTreeNode)
	{
		BaseTreeNode<TreeElement> treeNode;
		treeNode = new BaseTreeNode<TreeElement>(treeElement)
		{
			@Override
			public boolean isNode()
			{
				return treeElement.isNode();
			}
		};
		treeNode.setDisplayValue(treeElement.getName());
		if (parentTreeNode != null)
		{
			parentTreeNode.addChild(treeNode);
		}
		return treeNode;
	}

	/**
	 * Factory method that creates a new {@link BaseTreeNode} object from the given
	 * {@link TreeElement} object
	 *
	 * @param treeElement
	 *            the {@link TreeElement} object
	 * @param parentTreeNode
	 *            the parent object
	 * @return the new {@link BaseTreeNode} object
	 */
	public static BaseTreeNode<JXTreeElement> initializeTreeNodeWithTreeElement(
		final JXTreeElement treeElement, BaseTreeNode<JXTreeElement> parentTreeNode)
	{
		BaseTreeNode<JXTreeElement> treeNode;
		treeNode = new BaseTreeNode<JXTreeElement>(treeElement)
		{
			@Override
			public boolean isNode()
			{
				return treeElement.isNode();
			}
		};
		treeNode.setDisplayValue(treeElement.getName());
		if (parentTreeNode != null)
		{
			parentTreeNode.addChild(treeNode);
		}
		return treeNode;
	}

	/**
	 * Factory method that creates a new {@link BaseTreeNode} object from the given
	 * {@link TreeElement} object
	 *
	 * @param treeElement
	 *            the {@link TreeElement} object
	 * @param parentTreeNode
	 *            the parent object
	 * @return the new {@link BaseTreeNode} object
	 */
	public static <T> BaseTreeNode<GenericTreeElement<T>> initializeTreeNodeWithTreeElement(
		final GenericTreeElement<T> treeElement, BaseTreeNode<GenericTreeElement<T>> parentTreeNode)
	{
		BaseTreeNode<GenericTreeElement<T>> treeNode = new BaseTreeNode<GenericTreeElement<T>>(
			treeElement)
		{
			@Override
			public boolean isNode()
			{
				return treeElement.isNode();
			}
		};
		treeNode.setDisplayValue(treeElement.getName());
		if (parentTreeNode != null)
		{
			parentTreeNode.addChild(treeNode);
		}
		return treeNode;
	}

}
