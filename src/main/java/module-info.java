/**
 * The MIT License
 *
 * Copyright (C) 2021 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
module swing.tree.component.main
{
	requires java.desktop;
	requires data.api;
	requires gen.tree;
	requires lombok;
	requires swing.renderer.main;
	requires model.data;
	requires swing.base.components.main;
	requires swing.table.components.main;
	requires menu.actions.main;
	requires roboter.main;
	requires component.model.main;
	requires swing.model.components.main;
	requires com.miglayout.core;
	requires com.miglayout.swing;

	exports io.github.astrapi69.swing.tree;
	exports io.github.astrapi69.swing.tree.factory;
	exports io.github.astrapi69.swing.tree.model;
	exports io.github.astrapi69.swing.tree.model.api;
	exports io.github.astrapi69.swing.tree.node;
	exports io.github.astrapi69.swing.tree.panel;
	exports io.github.astrapi69.swing.tree.panel.content;
	exports io.github.astrapi69.swing.tree.panel.node;

}