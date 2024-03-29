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
package io.github.astrapi69.test.instance;

import static io.github.astrapi69.swing.tree.factory.TreeNodeFactory.initializeTreeNodeWithTreeElement;

import java.util.List;

import io.github.astrapi69.gen.tree.TreeNode;
import io.github.astrapi69.swing.renderer.tree.GenericTreeElement;
import io.github.astrapi69.swing.renderer.tree.JTreeElement;
import io.github.astrapi69.swing.tree.factory.TreeNodeFactory;
import io.github.astrapi69.swing.tree.model.TreeElement;
import io.github.astrapi69.test.object.Permission;
import io.github.astrapi69.test.object.factory.TestPermissionFactory;

public class TestTreeNodeFactory
{

	public static TreeNode<TreeElement> initializeTestTreeNodeElement()
	{
		TreeNode<TreeElement> firstChildTreeNode;
		TreeNode<TreeElement> firstGrandChildTreeNodeLeaf;
		TreeNode<TreeElement> secondGrandChildTreeNodeLeaf;
		TreeElement firstGrandGrandChild;
		TreeNode<TreeElement> firstGrandGrandChildTreeNode;
		TreeNode<TreeElement> parentTreeNode;
		TreeNode<TreeElement> secondChildTreeNode;
		List<TreeNode<TreeElement>> list;
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
		parentTreeNode = initializeTreeNodeWithTreeElement(parent, null);

		firstChildTreeNode = initializeTreeNodeWithTreeElement(firstChild, parentTreeNode);

		secondChildTreeNode = initializeTreeNodeWithTreeElement(secondChild, parentTreeNode);

		firstGrandChildTreeNodeLeaf = initializeTreeNodeWithTreeElement(firstGrandChild,
			firstChildTreeNode);
		secondGrandChildTreeNodeLeaf = initializeTreeNodeWithTreeElement(secondGrandChild,
			secondChildTreeNode);

		firstGrandGrandChildTreeNode = initializeTreeNodeWithTreeElement(firstGrandGrandChild,
			firstChildTreeNode);
		return parentTreeNode;
	}


	public static TreeNode<JTreeElement> initializeTestJXTreeNodeElement()
	{
		// 1. Create a list with data.
		final List<Permission> permissions;
		List<Permission> permissions1;
		TreeNode<JTreeElement> firstChildTreeNode;
		TreeNode<JTreeElement> firstGrandChildTreeNodeLeaf;
		TreeNode<JTreeElement> secondGrandChildTreeNodeLeaf;
		JTreeElement firstGrandGrandChild;
		TreeNode<JTreeElement> firstGrandGrandChildTreeNode;
		TreeNode<JTreeElement> parentTreeNode;
		TreeNode<JTreeElement> secondChildTreeNode;
		List<TreeNode<JTreeElement>> list;
		JTreeElement parent;
		JTreeElement firstChild;
		JTreeElement firstGrandChild;
		JTreeElement secondChild;
		JTreeElement secondGrandChild;

		permissions = TestPermissionFactory.getPermissionsInGerman();
		permissions1 = TestPermissionFactory.getPermissions();

		parent = JTreeElement.builder().name("parent")
			.iconPath("io/github/astrapi69/silk/icons/disk.png").withText(true).parent(null)
			.node(true).build().setDefaultContent(permissions1);
		firstChild = JTreeElement.builder().name("firstChild/search").parent(parent)
			.iconPath("io/github/astrapi69/silk/icons/magnifier.png").withText(true).node(true)
			.build().setDefaultContent(permissions);
		firstGrandChild = JTreeElement.builder().name("firstGrandChild")
			.iconPath("io/github/astrapi69/silk/icons/lock.png").withText(false).parent(firstChild)
			.node(true).build().setDefaultContent(permissions);
		firstGrandGrandChild = JTreeElement.builder().name("firstGrandGrandChild")
			.parent(firstGrandChild).node(false).build().setDefaultContent(permissions);
		secondChild = JTreeElement.builder().name("secondChild").parent(parent).node(true).build()
			.setDefaultContent(permissions);
		secondGrandChild = JTreeElement.builder().name("secondGrandChild").parent(firstChild)
			.node(false).build().setDefaultContent(permissions);
		parentTreeNode = TreeNodeFactory.initializeTreeNodeWithTreeElement(parent, null);

		firstChildTreeNode = TreeNodeFactory.initializeTreeNodeWithTreeElement(firstChild,
			parentTreeNode);

		secondChildTreeNode = TreeNodeFactory.initializeTreeNodeWithTreeElement(secondChild,
			parentTreeNode);

		firstGrandChildTreeNodeLeaf = TreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandChild, firstChildTreeNode);
		secondGrandChildTreeNodeLeaf = TreeNodeFactory
			.initializeTreeNodeWithTreeElement(secondGrandChild, secondChildTreeNode);

		firstGrandGrandChildTreeNode = TreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandGrandChild, firstChildTreeNode);
		return parentTreeNode;
	}

	public static TreeNode<GenericTreeElement<List<Permission>>> initializeTestGenericTreeNodeElement()
	{
		return initializeTestGenericTreeNodeElement(TestPermissionFactory.getPermissionsInGerman(),
			TestPermissionFactory.getPermissions());
	}

	public static <T> TreeNode<GenericTreeElement<List<T>>> initializeTestGenericTreeNodeElement(
		List<T> first, List<T> second)
	{
		TreeNode<GenericTreeElement<List<T>>> parentTreeNode;
		TreeNode<GenericTreeElement<List<T>>> firstChildTreeNode;
		TreeNode<GenericTreeElement<List<T>>> firstGrandChildTreeNodeLeaf;
		TreeNode<GenericTreeElement<List<T>>> secondGrandChildTreeNodeLeaf;
		GenericTreeElement<List<T>> firstGrandGrandChild;
		TreeNode<GenericTreeElement<List<T>>> firstGrandGrandChildTreeNode;
		TreeNode<GenericTreeElement<List<T>>> secondChildTreeNode;
		List<TreeNode<GenericTreeElement<List<T>>>> list;
		GenericTreeElement<List<T>> parent;
		GenericTreeElement<List<T>> firstChild;
		GenericTreeElement<List<T>> firstGrandChild;
		GenericTreeElement<List<T>> secondChild;
		GenericTreeElement<List<T>> secondGrandChild;

		parent = GenericTreeElement.<List<T>> builder().name("parent")
			.iconPath("io/github/astrapi69/silk/icons/disk.png").withText(true).build()
			.setDefaultContent(first);
		firstChild = GenericTreeElement.<List<T>> builder().name("firstChild/search")
			.iconPath("io/github/astrapi69/silk/icons/magnifier.png").withText(true).build()
			.setDefaultContent(second);
		firstGrandChild = GenericTreeElement.<List<T>> builder().name("firstGrandChild")
			.iconPath("io/github/astrapi69/silk/icons/lock.png").withText(false).build()
			.setDefaultContent(second);
		firstGrandGrandChild = GenericTreeElement.<List<T>> builder().name("firstGrandGrandChild")
			.leaf(true).build().setDefaultContent(second);
		secondChild = GenericTreeElement.<List<T>> builder().name("secondChild").build()
			.setDefaultContent(second);
		secondGrandChild = GenericTreeElement.<List<T>> builder().name("secondGrandChild")
			.leaf(true).build().setDefaultContent(second);
		parentTreeNode = TreeNodeFactory.initializeTreeNodeWithTreeElement(parent, null);

		firstChildTreeNode = TreeNodeFactory.initializeTreeNodeWithTreeElement(firstChild,
			parentTreeNode);

		secondChildTreeNode = TreeNodeFactory.initializeTreeNodeWithTreeElement(secondChild,
			parentTreeNode);

		firstGrandChildTreeNodeLeaf = TreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandChild, firstChildTreeNode);
		secondGrandChildTreeNodeLeaf = TreeNodeFactory
			.initializeTreeNodeWithTreeElement(secondGrandChild, secondChildTreeNode);

		firstGrandGrandChildTreeNode = TreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandGrandChild, firstChildTreeNode);
		return parentTreeNode;
	}
}
