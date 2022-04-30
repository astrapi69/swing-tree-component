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
package io.github.astrapi69.swing.tree.renderer;

public enum SimpleTreeCellRendererState
{

	/**
	 * The state that represents a leaf that is not selected
	 */
	LEAF,

	/**
	 * The state that represents a selected leaf
	 */
	SELECTED_LEAF,

	/**
	 * The state that represents a selected leaf and has the focus
	 */
	SELECTED_FOCUSED_LEAF,

	/**
	 * The state that represents a node that is not selected and has no children
	 */
	NODE,

	/**
	 * The state that represents a node that is not selected and has children
	 */
	NODE_WITH_CHILDREN,

	/**
	 * The state that represents a selected node with no children
	 */
	SELECTED_NODE,

	/**
	 * The state that represents a selected node with children
	 */
	SELECTED_NODE_WITH_CHILDREN;

	public static SimpleTreeCellRendererState getState(boolean leaf, boolean selected, boolean children)
	{
		if (leaf)
		{
			if (selected)
			{

				return SELECTED_LEAF;
			}
			return LEAF;
		}
		else
		{
			if (selected)
			{
				if (children)
				{
					return SELECTED_NODE_WITH_CHILDREN;
				}
				return SELECTED_NODE;
			}
			else
			{
				if (children)
				{
					return NODE_WITH_CHILDREN;
				}
				return NODE;
			}
		}
	}

}
