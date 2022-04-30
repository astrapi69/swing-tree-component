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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TreeCellRendererStateTest
{

	@Test
	void getRenderState()
	{
		TreeCellRendererState actual;
		TreeCellRendererState expected;
		// new scenario...
		actual = TreeCellRendererState.getRenderState(true, false, false, false, false);
		expected = TreeCellRendererState.LEAF;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(true, true, false, false, false);
		expected = TreeCellRendererState.SELECTED_LEAF;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(true, true, false, true, false);
		expected = TreeCellRendererState.SELECTED_FOCUSED_LEAF;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, false, false, false, false);
		expected = TreeCellRendererState.NODE;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, false, true, false, false);
		expected = TreeCellRendererState.EXPANDED_NODE;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, true, true, false, false);
		expected = TreeCellRendererState.EXPANDED_SELECTED_NODE;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, true, true, false, true);
		expected = TreeCellRendererState.EXPANDED_SELECTED_NODE_WITH_CHILDREN;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, true, true, true, true);
		expected = TreeCellRendererState.EXPANDED_SELECTED_FOCUSED_NODE_WITH_CHILDREN;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, true, true, true, false);
		expected = TreeCellRendererState.EXPANDED_SELECTED_FOCUSED_NODE;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, false, false, false, true);
		expected = TreeCellRendererState.NODE_WITH_CHILDREN;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, true, false, false, false);
		expected = TreeCellRendererState.SELECTED_NODE;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, true, false, true, false);
		expected = TreeCellRendererState.SELECTED_FOCUSED_NODE;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, true, false, false, true);
		expected = TreeCellRendererState.SELECTED_NODE_WITH_CHILDREN;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, false, true, false, true);
		expected = TreeCellRendererState.EXPANDED_NODE_WITH_CHILDREN;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererState.getRenderState(false, true, false, true, true);
		expected = TreeCellRendererState.SELECTED_FOCUSED_NODE_WITH_CHILDREN;
		assertEquals(expected, actual);
	}
}
