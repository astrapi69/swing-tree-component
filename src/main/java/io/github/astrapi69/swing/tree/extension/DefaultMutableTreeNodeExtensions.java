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

import javax.swing.tree.DefaultMutableTreeNode;

import io.github.astrapi69.swing.tree.factory.DefaultMutableTreeNodeFactory;

/**
 * The class {@link DefaultMutableTreeNodeExtensions} provides methods for copy of
 * {@link DefaultMutableTreeNode} objects
 */
public class DefaultMutableTreeNodeExtensions
{

	/**
	 * Makes a exact copy of the given {@link DefaultMutableTreeNode} object with the copy of the
	 * given user object and all descendants {@link DefaultMutableTreeNode} objects
	 *
	 * @param <T>
	 *            the generic type of the given user object
	 * @param selectedDefaultMutableTreeNode
	 *            the {@link DefaultMutableTreeNode} object to copy
	 * @param copyOfUserObject
	 *            a copy of the user object
	 */
	public static <T> void copyOf(DefaultMutableTreeNode selectedDefaultMutableTreeNode,
		T copyOfUserObject)
	{
		// get parent
		DefaultMutableTreeNode parentTreeNode = (DefaultMutableTreeNode)selectedDefaultMutableTreeNode
			.getParent();
		// create a copy of the given selectedDefaultMutableTreeNode with the parent
		DefaultMutableTreeNode copyDefaultMutableTreeNode = DefaultMutableTreeNodeFactory
			.newDefaultMutableTreeNode(parentTreeNode, copyOfUserObject);
		// copy all tree structure
		DefaultMutableTreeNodeExtensions.copy(selectedDefaultMutableTreeNode,
			copyDefaultMutableTreeNode);
	}


	/**
	 * Copies the given source {@link DefaultMutableTreeNode} object to the given target
	 * {@link DefaultMutableTreeNode} object
	 *
	 * @param <T>
	 *            the generic type
	 * @param source
	 *            the source {@link DefaultMutableTreeNode} object
	 * @param target
	 *            the target {@link DefaultMutableTreeNode} object
	 *
	 * @return the copied target {@link DefaultMutableTreeNode} object
	 */
	public static <T extends DefaultMutableTreeNode> T copy(T source, T target)
	{
		if (source == null)
		{
			return target;
		}
		for (int i = 0; i < source.getChildCount(); i++)
		{
			DefaultMutableTreeNode child = (DefaultMutableTreeNode)source.getChildAt(i);
			DefaultMutableTreeNode clone = new DefaultMutableTreeNode(child.getUserObject());
			target.add(clone);
			copy(child, clone);
		}
		return target;
	}
}
