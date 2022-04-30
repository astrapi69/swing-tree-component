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

class TreeCellRendererExpandedStateTest
{

	@Test
	void getRenderState()
	{
		TreeCellRendererExpandedState actual;
		TreeCellRendererExpandedState expected;
		// new scenario...
		actual = TreeCellRendererExpandedState.getRenderState(true, false, false, false, false);
		expected = TreeCellRendererExpandedState.LEAF;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererExpandedState.getRenderState(true, true, false, false, false);
		expected = TreeCellRendererExpandedState.SELECTED_LEAF;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererExpandedState.getRenderState(true, true, false, true, false);
		expected = TreeCellRendererExpandedState.SELECTED_FOCUSED_LEAF;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererExpandedState.getRenderState(false, false, false, false, false);
		expected = TreeCellRendererExpandedState.NODE;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererExpandedState.getRenderState(false, true, false, false, false);
		expected = TreeCellRendererExpandedState.SELECTED_NODE;
		assertEquals(expected, actual);
		// new scenario...
		actual = TreeCellRendererExpandedState.getRenderState(false, true, false, false, true);
		expected = TreeCellRendererExpandedState.SELECTED_NODE_WITH_CHILDREN;
		assertEquals(expected, actual);
	}
}