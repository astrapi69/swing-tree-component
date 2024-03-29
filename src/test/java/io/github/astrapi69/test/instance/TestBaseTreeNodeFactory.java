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

import java.util.ArrayList;
import java.util.List;

import io.github.astrapi69.data.identifiable.IdGenerator;
import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.id.generate.LongIdGenerator;
import io.github.astrapi69.swing.renderer.tree.GenericTreeElement;
import io.github.astrapi69.swing.renderer.tree.JTreeElement;
import io.github.astrapi69.swing.tree.factory.BaseTreeNodeFactory;
import io.github.astrapi69.swing.tree.model.TreeElement;
import io.github.astrapi69.test.object.Permission;
import io.github.astrapi69.test.object.factory.TestPermissionFactory;
import lombok.NonNull;

public class TestBaseTreeNodeFactory
{

	public static BaseTreeNode<TreeElement, Long> initializeTestTreeNodeElement()
	{
		LongIdGenerator idGenerator = LongIdGenerator.of(0L);
		BaseTreeNode<TreeElement, Long> firstChildTreeNode;
		BaseTreeNode<TreeElement, Long> firstGrandChildTreeNodeLeaf;
		BaseTreeNode<TreeElement, Long> secondGrandChildTreeNodeLeaf;
		TreeElement firstGrandGrandChild;
		BaseTreeNode<TreeElement, Long> firstGrandGrandChildTreeNode;
		BaseTreeNode<TreeElement, Long> parentTreeNode;
		BaseTreeNode<TreeElement, Long> secondChildTreeNode;
		List<BaseTreeNode<TreeElement, Long>> list;
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
		parentTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(parent, null,
			idGenerator);

		firstChildTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(firstChild,
			parentTreeNode, idGenerator);

		secondChildTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(secondChild,
			parentTreeNode, idGenerator);

		firstGrandChildTreeNodeLeaf = BaseTreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandChild, firstChildTreeNode, idGenerator);
		secondGrandChildTreeNodeLeaf = BaseTreeNodeFactory
			.initializeTreeNodeWithTreeElement(secondGrandChild, secondChildTreeNode, idGenerator);

		firstGrandGrandChildTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(
			firstGrandGrandChild, firstChildTreeNode, idGenerator);
		return parentTreeNode;
	}


	public static BaseTreeNode<JTreeElement, Long> initializeTestJXTreeNodeElement()
	{
		LongIdGenerator idGenerator = LongIdGenerator.of(0L);
		// 1. Create a list with data.
		final List<Permission> permissions;
		List<Permission> permissions1;
		BaseTreeNode<JTreeElement, Long> firstChildTreeNode;
		BaseTreeNode<JTreeElement, Long> firstGrandChildTreeNodeLeaf;
		BaseTreeNode<JTreeElement, Long> secondGrandChildTreeNodeLeaf;
		JTreeElement firstGrandGrandChild;
		BaseTreeNode<JTreeElement, Long> firstGrandGrandChildTreeNode;
		BaseTreeNode<JTreeElement, Long> parentTreeNode;
		BaseTreeNode<JTreeElement, Long> secondChildTreeNode;
		List<BaseTreeNode<JTreeElement, Long>> list;
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
		parentTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(parent, null,
			idGenerator);

		firstChildTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(firstChild,
			parentTreeNode, idGenerator);

		secondChildTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(secondChild,
			parentTreeNode, idGenerator);

		firstGrandChildTreeNodeLeaf = BaseTreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandChild, firstChildTreeNode, idGenerator);
		secondGrandChildTreeNodeLeaf = BaseTreeNodeFactory
			.initializeTreeNodeWithTreeElement(secondGrandChild, secondChildTreeNode, idGenerator);

		firstGrandGrandChildTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(
			firstGrandGrandChild, firstChildTreeNode, idGenerator);
		return parentTreeNode;
	}

	public static BaseTreeNode<GenericTreeElement<List<Permission>>, Long> initializeTestGenericTreeNodeElement()
	{
		LongIdGenerator idGenerator = LongIdGenerator.of(0L);
		return initializeTestGenericTreeNodeElement(TestPermissionFactory.getPermissionsInGerman(),
			TestPermissionFactory.getPermissions(), idGenerator);
	}

	public static <T, K> BaseTreeNode<GenericTreeElement<List<T>>, K> initializeTestGenericTreeNodeElement(
		List<T> first, List<T> second, final @NonNull IdGenerator<K> idGenerator)
	{
		BaseTreeNode<GenericTreeElement<List<T>>, K> parentTreeNode;
		BaseTreeNode<GenericTreeElement<List<T>>, K> firstChildTreeNode;
		BaseTreeNode<GenericTreeElement<List<T>>, K> firstGrandChildTreeNodeLeaf;
		BaseTreeNode<GenericTreeElement<List<T>>, K> secondGrandChildTreeNodeLeaf;
		GenericTreeElement<List<T>> firstGrandGrandChild;
		BaseTreeNode<GenericTreeElement<List<T>>, K> firstGrandGrandChildTreeNode;
		BaseTreeNode<GenericTreeElement<List<T>>, K> secondChildTreeNode;
		List<BaseTreeNode<GenericTreeElement<List<T>>, K>> list;
		GenericTreeElement<List<T>> parent;
		GenericTreeElement<List<T>> firstChild;
		GenericTreeElement<List<T>> firstGrandChild;
		GenericTreeElement<List<T>> secondChild;
		GenericTreeElement<List<T>> secondGrandChild;

		parent = GenericTreeElement.<List<T>> builder().name("parent")
			.iconPath("io/github/astrapi69/silk/icons/disk.png")
			.selectedIconPath("io/github/astrapi69/silk/icons/door_open.png").withText(true).build()
			.setDefaultContent(first);
		firstChild = GenericTreeElement.<List<T>> builder().name("firstChild/search")
			.iconPath("io/github/astrapi69/silk/icons/magnifier.png").withText(true).build()
			.setDefaultContent(new ArrayList<>(second));
		firstGrandChild = GenericTreeElement.<List<T>> builder().name("firstGrandChild")
			.iconPath("io/github/astrapi69/silk/icons/lock.png")
			.selectedIconPath("io/github/astrapi69/silk/icons/lock_edit.png").withText(false)
			.build().setDefaultContent(new ArrayList<>(second));
		firstGrandGrandChild = GenericTreeElement.<List<T>> builder().name("firstGrandGrandChild")
			.leaf(true).build().setDefaultContent(new ArrayList<>(second));
		secondChild = GenericTreeElement.<List<T>> builder().name("secondChild").build()
			.setDefaultContent(new ArrayList<>(second));
		secondGrandChild = GenericTreeElement.<List<T>> builder().name("secondGrandChild")
			.leaf(true).build().setDefaultContent(new ArrayList<>(second));
		parentTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(parent, null,
			idGenerator);

		firstChildTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(firstChild,
			parentTreeNode, idGenerator);

		secondChildTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(secondChild,
			parentTreeNode, idGenerator);

		firstGrandChildTreeNodeLeaf = BaseTreeNodeFactory
			.initializeTreeNodeWithTreeElement(firstGrandChild, firstChildTreeNode, idGenerator);
		secondGrandChildTreeNodeLeaf = BaseTreeNodeFactory
			.initializeTreeNodeWithTreeElement(secondGrandChild, secondChildTreeNode, idGenerator);

		firstGrandGrandChildTreeNode = BaseTreeNodeFactory.initializeTreeNodeWithTreeElement(
			firstGrandGrandChild, firstChildTreeNode, idGenerator);
		return parentTreeNode;
	}
}
