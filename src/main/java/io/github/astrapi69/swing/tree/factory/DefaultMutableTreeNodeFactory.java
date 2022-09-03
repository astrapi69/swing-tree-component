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
package io.github.astrapi69.swing.tree.factory;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * The factory class {@link DefaultMutableTreeNodeFactory} holds methods for creating
 * {@link DefaultMutableTreeNode} objects.
 */
public final class DefaultMutableTreeNodeFactory
{
	private DefaultMutableTreeNodeFactory()
	{
	}

	/**
	 * Factory method that creates a new {@link DefaultMutableTreeNode} object
	 *
	 * @param parent
	 *            the parent {@link DefaultMutableTreeNode} object
	 * @param userObject
	 *            the user object
	 * @param <T>
	 *            the generic type of the given user object
	 * @return the new {@link DefaultMutableTreeNode} object
	 */
	public static <T> DefaultMutableTreeNode newDefaultMutableTreeNode(
		DefaultMutableTreeNode parent, T userObject)
	{
		return newDefaultMutableTreeNode(parent, userObject, true);
	}

	/**
	 * Factory method that creates a new {@link DefaultMutableTreeNode} object
	 *
	 * @param parent
	 *            the parent {@link DefaultMutableTreeNode} object
	 * @param userObject
	 *            the user object
	 * @param <T>
	 *            the generic type of the given user object
	 * @return the new {@link DefaultMutableTreeNode} object
	 */
	public static <T> DefaultMutableTreeNode newDefaultMutableTreeNode(
		DefaultMutableTreeNode parent, T userObject, boolean addToParent)
	{
		return newDefaultMutableTreeNode(parent, userObject, true, addToParent);
	}

	/**
	 * Factory method that creates a new {@link DefaultMutableTreeNode} object
	 *
	 * @param parent
	 *            the parent {@link DefaultMutableTreeNode} object
	 * @param userObject
	 *            the user object
	 * @param <T>
	 *            the generic type of the given user object
	 * @return the new {@link DefaultMutableTreeNode} object
	 */
	public static <T> DefaultMutableTreeNode newDefaultMutableTreeNode(
		DefaultMutableTreeNode parent, T userObject, boolean allowsChildren, boolean addToParent)
	{
		DefaultMutableTreeNode node = newDefaultMutableTreeNode(userObject, allowsChildren);
		if (parent != null && addToParent)
		{
			parent.add(node);
		}
		return node;
	}

	/**
	 * Factory method that creates a new {@link DefaultMutableTreeNode} object
	 *
	 * @param userObject
	 *            the user object
	 * @param allowsChildren
	 *            if true, the node is allowed to have child nodes -- otherwise, it is always a leaf
	 *            node
	 * @param <T>
	 *            the generic type of the given user object
	 * @return the new {@link DefaultMutableTreeNode} object
	 */
	public static <T> DefaultMutableTreeNode newDefaultMutableTreeNode(T userObject,
		boolean allowsChildren)
	{
		return new DefaultMutableTreeNode(userObject, allowsChildren);
	}

	/**
	 * Factory method that creates a new {@link DefaultMutableTreeNode} object
	 *
	 * @param userObject
	 *            the user object
	 * @param <T>
	 *            the generic type of the given user object
	 * @return the new {@link DefaultMutableTreeNode} object
	 */
	public static <T> DefaultMutableTreeNode newDefaultMutableTreeNode(T userObject)
	{
		return new DefaultMutableTreeNode(userObject);
	}

}
