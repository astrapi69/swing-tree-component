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

public enum TreeCellRendererState
{

	/**
	 * Represents the state of a leaf that is not selected
	 */
	LEAF,

	/**
	 * Represents the state of a selected leaf
	 */
	SELECTED_LEAF,

	/**
	 * Represents the state of a selected leaf and has the focus
	 */
	SELECTED_FOCUSED_LEAF,

	/**
	 * Represents the state of a node that is not expanded, not selected but has no focus and has no
	 * children
	 */
	NODE,

	/**
	 * Represents the state of a node that is expanded but not selected but has no focus and has no
	 * children
	 */
	EXPANDED_NODE,

	/**
	 * Represents the state of a node that is expanded and is selected but has no focus and has no
	 * children
	 */
	EXPANDED_SELECTED_NODE,

	/**
	 * Represents the state of a node that is expanded and is selected and has children
	 */
	EXPANDED_SELECTED_NODE_WITH_CHILDREN,

	/**
	 * Represents the state of a node that is expanded and is selected and has the focus and has
	 * children
	 */
	EXPANDED_SELECTED_FOCUSED_NODE_WITH_CHILDREN,

	/**
	 * Represents the state of a node that is expanded and is selected and has the focus but has no
	 * children
	 */
	EXPANDED_SELECTED_FOCUSED_NODE,

	/**
	 * Represents the state of a node that is not selected and has children
	 */
	NODE_WITH_CHILDREN,

	/**
	 * Represents the state of a selected node with no children
	 */
	SELECTED_NODE,

	/**
	 * Represents the state of a selected node and has the focus with no children
	 */
	SELECTED_FOCUSED_NODE,

	/**
	 * Represents the state of a selected node with children
	 */
	SELECTED_NODE_WITH_CHILDREN,

	/**
	 * Represents the state of a node that is expanded but not selected and has children
	 */
	EXPANDED_NODE_WITH_CHILDREN,

	/**
	 * Represents the state of a node that is selected and has the focus with children
	 */
	SELECTED_FOCUSED_NODE_WITH_CHILDREN;

	public static TreeCellRendererState getRenderState(boolean leaf, boolean selected,
		boolean expanded, boolean hasFocus, boolean children)
	{
		if (leaf)
		{
			if (selected)
			{
				if (hasFocus)
				{
					return SELECTED_FOCUSED_LEAF;
				}
				return SELECTED_LEAF;
			}
			return LEAF;
		}
		if (expanded)
		{
			if (selected)
			{
				if (hasFocus)
				{
					if (children)
					{
						return EXPANDED_SELECTED_FOCUSED_NODE_WITH_CHILDREN;
					}
					return EXPANDED_SELECTED_FOCUSED_NODE;
				}
				if (children)
				{
					return EXPANDED_SELECTED_NODE_WITH_CHILDREN;
				}
				return EXPANDED_SELECTED_NODE;
			}
			if (children)
			{
				return EXPANDED_NODE_WITH_CHILDREN;
			}
			return EXPANDED_NODE;
		}
		if (selected)
		{
			if(hasFocus)
			{
				if (children)
				{
					return SELECTED_FOCUSED_NODE_WITH_CHILDREN;
				}
				return SELECTED_FOCUSED_NODE;
			}
			if (children)
			{
				return SELECTED_NODE_WITH_CHILDREN;
			}
			return SELECTED_NODE;
		}
		if (children)
		{
			return NODE_WITH_CHILDREN;
		}
		return NODE;
	}

}
