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
package io.github.astrapi69.swing.tree.renderer.state;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

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

	private static Map<Object[][], TreeCellRendererState> stateMap;

	public static TreeCellRendererState getState(boolean leaf, boolean selected, boolean expanded,
		boolean hasFocus, boolean children)
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
			if (hasFocus)
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

	private static Map<Object[][], TreeCellRendererState> getStateMap()
	{
		if (stateMap == null)
		{
			stateMap = new LinkedHashMap<>();
			stateMap.put(getKey(false, false, false, false, false), TreeCellRendererState.NODE);
			stateMap.put(getKey(false, false, false, false, true),
				TreeCellRendererState.NODE_WITH_CHILDREN);
			stateMap.put(getKey(false, false, false, true, false), TreeCellRendererState.NODE);
			stateMap.put(getKey(false, false, false, true, true),
				TreeCellRendererState.NODE_WITH_CHILDREN);
			stateMap.put(getKey(false, false, true, false, false),
				TreeCellRendererState.EXPANDED_NODE);
			stateMap.put(getKey(false, false, true, false, true),
				TreeCellRendererState.EXPANDED_NODE_WITH_CHILDREN);
			stateMap.put(getKey(false, false, true, true, false),
				TreeCellRendererState.EXPANDED_NODE);
			stateMap.put(getKey(false, false, true, true, true),
				TreeCellRendererState.EXPANDED_NODE_WITH_CHILDREN);
			stateMap.put(getKey(false, true, false, false, false),
				TreeCellRendererState.SELECTED_NODE);
			stateMap.put(getKey(false, true, false, false, true),
				TreeCellRendererState.SELECTED_NODE_WITH_CHILDREN);
			stateMap.put(getKey(false, true, false, true, false),
				TreeCellRendererState.SELECTED_FOCUSED_NODE);
			stateMap.put(getKey(false, true, false, true, true),
				TreeCellRendererState.SELECTED_FOCUSED_NODE_WITH_CHILDREN);
			stateMap.put(getKey(false, true, true, false, false),
				TreeCellRendererState.EXPANDED_SELECTED_NODE);
			stateMap.put(getKey(false, true, true, false, true),
				TreeCellRendererState.EXPANDED_SELECTED_NODE_WITH_CHILDREN);
			stateMap.put(getKey(false, true, true, true, false),
				TreeCellRendererState.EXPANDED_SELECTED_FOCUSED_NODE);
			stateMap.put(getKey(false, true, true, true, true),
				TreeCellRendererState.EXPANDED_SELECTED_FOCUSED_NODE_WITH_CHILDREN);

			stateMap.put(getKey(true, false, false, false, false), TreeCellRendererState.LEAF);
			stateMap.put(getKey(true, false, false, false, true), TreeCellRendererState.LEAF);
			stateMap.put(getKey(true, false, false, true, false), TreeCellRendererState.LEAF);
			stateMap.put(getKey(true, false, false, true, true), TreeCellRendererState.LEAF);
			stateMap.put(getKey(true, false, true, false, false), TreeCellRendererState.LEAF);
			stateMap.put(getKey(true, false, true, false, true), TreeCellRendererState.LEAF);
			stateMap.put(getKey(true, false, true, true, false), TreeCellRendererState.LEAF);
			stateMap.put(getKey(true, false, true, true, true), TreeCellRendererState.LEAF);
			stateMap.put(getKey(true, true, false, false, false),
				TreeCellRendererState.SELECTED_LEAF);
			stateMap.put(getKey(true, true, false, false, true),
				TreeCellRendererState.SELECTED_LEAF);
			stateMap.put(getKey(true, true, false, true, false),
				TreeCellRendererState.SELECTED_FOCUSED_LEAF);
			stateMap.put(getKey(true, true, false, true, true),
				TreeCellRendererState.SELECTED_FOCUSED_LEAF);
			stateMap.put(getKey(true, true, true, false, false),
				TreeCellRendererState.SELECTED_LEAF);
			stateMap.put(getKey(true, true, true, false, true),
				TreeCellRendererState.SELECTED_LEAF);
			stateMap.put(getKey(true, true, true, true, false),
				TreeCellRendererState.SELECTED_FOCUSED_LEAF);
			stateMap.put(getKey(true, true, true, true, true),
				TreeCellRendererState.SELECTED_FOCUSED_LEAF);
		}
		return stateMap;
	}

	private static Object[][] getKey(boolean leaf, boolean selected, boolean expanded,
		boolean hasFocus, boolean children)
	{
		final Object[][] currentStateArray = { { "leaf", leaf }, { "selected", selected },
				{ "expanded", expanded }, { "hasFocus", hasFocus }, { "children", children } };
		return currentStateArray;
	}

	public static TreeCellRendererState getRenderState(boolean leaf, boolean selected,
		boolean expanded, boolean hasFocus, boolean children)
	{
		Object[][] currentKey = getKey(leaf, selected, expanded, hasFocus, children);
		Map<Object[][], TreeCellRendererState> stateMap = getStateMap();
		TreeCellRendererState currentState = null;
		for (Object[][] key : stateMap.keySet())
		{
			if (Arrays.deepEquals(key, currentKey))
			{
				currentState = stateMap.get(key);
				break;
			}
		}
		return currentState;
	}

}
