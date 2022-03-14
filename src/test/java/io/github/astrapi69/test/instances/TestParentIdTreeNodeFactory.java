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
package io.github.astrapi69.test.instances;

import java.util.List;

import io.github.astrapi69.swing.tree.GenericTreeElement;
import io.github.astrapi69.swing.tree.JXTreeElement;
import io.github.astrapi69.swing.tree.ParentIdTreeNodeFactory;
import io.github.astrapi69.test.instance.TestPermissionFactory;
import io.github.astrapi69.test.object.Permission;
import io.github.astrapi69.tree.TreeElement;
import io.github.astrapi69.tree.ParentIdTreeNode;

public class TestParentIdTreeNodeFactory
{

	public static ParentIdTreeNode<TreeElement, Long> initializeTestTreeNodeElement()
	{
		ParentIdTreeNode<TreeElement, Long> firstChildTreeNode;
		ParentIdTreeNode<TreeElement, Long> firstGrandChildTreeNodeLeaf;
		ParentIdTreeNode<TreeElement, Long> secondGrandChildTreeNodeLeaf;
		TreeElement firstGrandGrandChild;
		ParentIdTreeNode<TreeElement, Long> firstGrandGrandChildTreeNode;
		ParentIdTreeNode<TreeElement, Long> parentTreeNode;
		ParentIdTreeNode<TreeElement, Long> secondChildTreeNode;
		List<ParentIdTreeNode<TreeElement, Long>> list;
		TreeElement parent;
		TreeElement firstChild;
		TreeElement firstGrandChild;
		TreeElement secondChild;
		TreeElement secondGrandChild;
		parent = TreeElement.builder().name("parent").parent(null).node(true).build();
		firstChild = TreeElement.builder().name("firstChild").parent(parent).node(true).build();
		firstGrandChild = TreeElement.builder().name("firstGrandChild").parent(firstChild)
			.node(true).build();
		firstGrandGrandChild = TreeElement.builder().name("firstGrandGrandChild")
			.parent(firstGrandChild).node(false).build();
		secondChild = TreeElement.builder().name("secondChild").parent(parent).node(true).build();
		secondGrandChild = TreeElement.builder().name("secondGrandChild").parent(firstChild)
			.node(false).build();
		parentTreeNode = ParentIdTreeNodeFactory.initializeTreeNodeWithTreeElement(parent, null);

		firstChildTreeNode = ParentIdTreeNodeFactory.initializeTreeNodeWithTreeElement(firstChild,
			parentTreeNode);

		secondChildTreeNode = ParentIdTreeNodeFactory.initializeTreeNodeWithTreeElement(secondChild,
			parentTreeNode);

		firstGrandChildTreeNodeLeaf = ParentIdTreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandChild, firstChildTreeNode);
		secondGrandChildTreeNodeLeaf = ParentIdTreeNodeFactory
			.initializeTreeNodeWithTreeElement(secondGrandChild, secondChildTreeNode);

		firstGrandGrandChildTreeNode = ParentIdTreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandGrandChild, firstChildTreeNode);
		return parentTreeNode;
	}


	public static ParentIdTreeNode<JXTreeElement, Long> initializeTestJXTreeNodeElement()
	{
		// 1. Create a list with data.
		final List<Permission> permissions;
		List<Permission> permissions1;
		ParentIdTreeNode<JXTreeElement, Long> firstChildTreeNode;
		ParentIdTreeNode<JXTreeElement, Long> firstGrandChildTreeNodeLeaf;
		ParentIdTreeNode<JXTreeElement, Long> secondGrandChildTreeNodeLeaf;
		JXTreeElement firstGrandGrandChild;
		ParentIdTreeNode<JXTreeElement, Long> firstGrandGrandChildTreeNode;
		ParentIdTreeNode<JXTreeElement, Long> parentTreeNode;
		ParentIdTreeNode<JXTreeElement, Long> secondChildTreeNode;
		List<ParentIdTreeNode<JXTreeElement, Long>> list;
		JXTreeElement parent;
		JXTreeElement firstChild;
		JXTreeElement firstGrandChild;
		JXTreeElement secondChild;
		JXTreeElement secondGrandChild;

		permissions = TestPermissionFactory.getPermissionsInGerman();
		permissions1 = TestPermissionFactory.getPermissions();

		parent = JXTreeElement.builder().name("parent")
			.iconPath("io/github/astrapi69/silk/icons/disk.png").withText(true).parent(null)
			.node(true).build().setDefaultContent(permissions1);
		firstChild = JXTreeElement.builder().name("firstChild/search").parent(parent)
			.iconPath("io/github/astrapi69/silk/icons/magnifier.png").withText(true).node(true)
			.build().setDefaultContent(permissions);
		firstGrandChild = JXTreeElement.builder().name("firstGrandChild")
			.iconPath("io/github/astrapi69/silk/icons/lock.png").withText(false).parent(firstChild)
			.node(true).build().setDefaultContent(permissions);
		firstGrandGrandChild = JXTreeElement.builder().name("firstGrandGrandChild")
			.parent(firstGrandChild).node(false).build().setDefaultContent(permissions);
		secondChild = JXTreeElement.builder().name("secondChild").parent(parent).node(true).build()
			.setDefaultContent(permissions);
		secondGrandChild = JXTreeElement.builder().name("secondGrandChild").parent(firstChild)
			.node(false).build().setDefaultContent(permissions);
		parentTreeNode = ParentIdTreeNodeFactory.initializeTreeNodeWithTreeElement(parent, null);

		firstChildTreeNode = ParentIdTreeNodeFactory.initializeTreeNodeWithTreeElement(firstChild,
			parentTreeNode);

		secondChildTreeNode = ParentIdTreeNodeFactory.initializeTreeNodeWithTreeElement(secondChild,
			parentTreeNode);

		firstGrandChildTreeNodeLeaf = ParentIdTreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandChild, firstChildTreeNode);
		secondGrandChildTreeNodeLeaf = ParentIdTreeNodeFactory
			.initializeTreeNodeWithTreeElement(secondGrandChild, secondChildTreeNode);

		firstGrandGrandChildTreeNode = ParentIdTreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandGrandChild, firstChildTreeNode);
		return parentTreeNode;
	}

	public static <K> ParentIdTreeNode<GenericTreeElement<List<Permission>>, K> initializeTestGenericTreeNodeElement()
	{
		return initializeTestGenericTreeNodeElement(TestPermissionFactory.getPermissionsInGerman(),
			TestPermissionFactory.getPermissions());
	}

	public static <T, K> ParentIdTreeNode<GenericTreeElement<List<T>>, K> initializeTestGenericTreeNodeElement(
		List<T> first, List<T> second)
	{
		ParentIdTreeNode<GenericTreeElement<List<T>>, K> parentTreeNode;
		ParentIdTreeNode<GenericTreeElement<List<T>>, K> firstChildTreeNode;
		ParentIdTreeNode<GenericTreeElement<List<T>>, K> firstGrandChildTreeNodeLeaf;
		ParentIdTreeNode<GenericTreeElement<List<T>>, K> secondGrandChildTreeNodeLeaf;
		GenericTreeElement<List<T>> firstGrandGrandChild;
		ParentIdTreeNode<GenericTreeElement<List<T>>, K> firstGrandGrandChildTreeNode;
		ParentIdTreeNode<GenericTreeElement<List<T>>, K> secondChildTreeNode;
		List<ParentIdTreeNode<GenericTreeElement<List<T>>, K>> list;
		GenericTreeElement<List<T>> parent;
		GenericTreeElement<List<T>> firstChild;
		GenericTreeElement<List<T>> firstGrandChild;
		GenericTreeElement<List<T>> secondChild;
		GenericTreeElement<List<T>> secondGrandChild;

		parent = GenericTreeElement.<List<T>> builder().name("parent")
			.iconPath("io/github/astrapi69/silk/icons/disk.png").withText(true).parent(null)
			.node(true).build().setDefaultContent(first);
		firstChild = GenericTreeElement.<List<T>> builder().name("firstChild/search").parent(parent)
			.iconPath("io/github/astrapi69/silk/icons/magnifier.png").withText(true).node(true)
			.build().setDefaultContent(second);
		firstGrandChild = GenericTreeElement.<List<T>> builder().name("firstGrandChild")
			.iconPath("io/github/astrapi69/silk/icons/lock.png").withText(false).parent(firstChild)
			.node(true).build().setDefaultContent(second);
		firstGrandGrandChild = GenericTreeElement.<List<T>> builder().name("firstGrandGrandChild")
			.parent(firstGrandChild).node(false).build().setDefaultContent(second);
		secondChild = GenericTreeElement.<List<T>> builder().name("secondChild").parent(parent)
			.node(true).build().setDefaultContent(second);
		secondGrandChild = GenericTreeElement.<List<T>> builder().name("secondGrandChild")
			.parent(firstChild).node(false).build().setDefaultContent(second);
		parentTreeNode = ParentIdTreeNodeFactory.initializeTreeNodeWithTreeElement(parent, null);

		firstChildTreeNode = ParentIdTreeNodeFactory.initializeTreeNodeWithTreeElement(firstChild,
			parentTreeNode);

		secondChildTreeNode = ParentIdTreeNodeFactory.initializeTreeNodeWithTreeElement(secondChild,
			parentTreeNode);

		firstGrandChildTreeNodeLeaf = ParentIdTreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandChild, firstChildTreeNode);
		secondGrandChildTreeNodeLeaf = ParentIdTreeNodeFactory
			.initializeTreeNodeWithTreeElement(secondGrandChild, secondChildTreeNode);

		firstGrandGrandChildTreeNode = ParentIdTreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandGrandChild, firstChildTreeNode);
		return parentTreeNode;
	}
}
