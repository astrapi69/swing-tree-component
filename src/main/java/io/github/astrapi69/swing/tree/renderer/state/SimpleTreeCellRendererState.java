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

	private static Map<Object[][], SimpleTreeCellRendererState> stateMap;

	private static Map<Object[][], SimpleTreeCellRendererState> getStateMap()
	{
		if (stateMap == null)
		{
			stateMap = new LinkedHashMap<>();
			stateMap.put(getKey(false, false, false), SimpleTreeCellRendererState.NODE);
			stateMap.put(getKey(false, false, true),
				SimpleTreeCellRendererState.NODE_WITH_CHILDREN);
			stateMap.put(getKey(false, true, false), SimpleTreeCellRendererState.SELECTED_NODE);
			stateMap.put(getKey(false, true, true),
				SimpleTreeCellRendererState.SELECTED_NODE_WITH_CHILDREN);
			stateMap.put(getKey(true, false, false), SimpleTreeCellRendererState.LEAF);
			stateMap.put(getKey(true, false, true), SimpleTreeCellRendererState.LEAF);
			stateMap.put(getKey(true, true, false), SimpleTreeCellRendererState.SELECTED_LEAF);
			stateMap.put(getKey(true, true, true), SimpleTreeCellRendererState.SELECTED_LEAF);
		}
		return stateMap;
	}

	private static Object[][] getKey(boolean leaf, boolean selected, boolean children)
	{
		final Object[][] currentStateArray = { { "leaf", leaf }, { "selected", selected },
				{ "children", children } };
		return currentStateArray;
	}

	public static SimpleTreeCellRendererState getRenderState(boolean leaf, boolean selected,
		boolean children)
	{
		Object[][] currentKey = getKey(leaf, selected, children);
		Map<Object[][], SimpleTreeCellRendererState> stateMap = getStateMap();
		SimpleTreeCellRendererState currentState = null;
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

	public static SimpleTreeCellRendererState getState(boolean leaf, boolean selected,
		boolean children)
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
