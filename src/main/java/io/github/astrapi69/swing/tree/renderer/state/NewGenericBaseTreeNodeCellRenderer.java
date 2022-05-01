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

import javax.swing.Icon;
import javax.swing.JTree;

import org.apache.commons.lang3.StringUtils;

import io.github.astrapi69.icon.ImageIconFactory;
import io.github.astrapi69.icon.StringIcon;
import io.github.astrapi69.swing.tree.GenericTreeElement;
import io.github.astrapi69.tree.BaseTreeNode;
import org.jdesktop.swingx.JXLabel;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

public class NewGenericBaseTreeNodeCellRenderer<T, K>
	extends
	AbstractBaseTreeNodeCellRenderer<GenericTreeElement<T>, K>
{
	Map<String, Icon> iconCacheMap = new LinkedHashMap<>();

	protected JXLabel initialize(JTree tree, BaseTreeNode<GenericTreeElement<T>, K> userObject,
		boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		JXLabel treeLabel = new JXLabel("");
		BaseTreeNode<GenericTreeElement<T>, K> treeNode = userObject;
		String displayValue = treeNode.getDisplayValue();
		treeLabel.setText(displayValue);
		GenericTreeElement<T> value = treeNode.getValue();
		if (value != null)
		{
			TreeCellRendererState renderState = TreeCellRendererState.getRenderState(
				treeNode.isLeaf(), selected, expanded, hasFocus, treeNode.hasChildren());
			Icon customTreeIcon = initializeCustomTreeIcon(value);
			Icon selectedTreeIcon = initializeSelectedTreeIcon(value);
			switch (renderState)
			{
				case LEAF :
				case SELECTED_LEAF :
				case SELECTED_FOCUSED_LEAF :
					treeLabel.setIcon(getLeafIcon());
					break;
				case SELECTED_NODE :
				case SELECTED_FOCUSED_NODE :
				case SELECTED_NODE_WITH_CHILDREN :
				case SELECTED_FOCUSED_NODE_WITH_CHILDREN :
					treeLabel.setText(displayValue);
					treeLabel.setIcon(selectedTreeIcon == null ? customTreeIcon == null ? getOpenIcon() : customTreeIcon  : selectedTreeIcon);
					break;
				case NODE :
				case NODE_WITH_CHILDREN :
				case EXPANDED_NODE :
				case EXPANDED_SELECTED_NODE :
				case EXPANDED_SELECTED_FOCUSED_NODE :
				case EXPANDED_SELECTED_NODE_WITH_CHILDREN :
				case EXPANDED_SELECTED_FOCUSED_NODE_WITH_CHILDREN :
				case EXPANDED_NODE_WITH_CHILDREN :
				default :
					treeLabel.setText(displayValue);
					treeLabel.setIcon(customTreeIcon == null ? getOpenIcon() : customTreeIcon);
			}
			String iconPath = value.getIconPath();
			if (!value.isWithText() && iconPath != null)
			{
				treeLabel.setText("");
			}
			treeLabel.setForeground(selected ? Color.blue : Color.gray);
			treeLabel.setBackground(Color.black);

			treeLabel.setToolTipText(displayValue);
			return treeLabel;
		}
		return super.initialize(tree, userObject, selected, expanded, leaf, row, hasFocus);
	}

	protected Icon initializeCustomTreeIcon(GenericTreeElement<T> value)
	{
		String iconPath = value.getIconPath();
		if(!iconCacheMap.containsKey(iconPath)) {
			if (StringUtils.isNotEmpty(iconPath))
			{
				Icon customTreeIcon = null;
				try
				{
					customTreeIcon = ImageIconFactory.newImageIcon(iconPath);
				}
				catch (Exception e)
				{
					customTreeIcon = new StringIcon(this, value.getName());
				}
				iconCacheMap.put(iconPath, customTreeIcon);
			}
		}
		return iconCacheMap.get(iconPath);
	}

	protected Icon initializeSelectedTreeIcon(GenericTreeElement<T> value)
	{
		String selectedIconPath = value.getSelectedIconPath();
		if(!iconCacheMap.containsKey(selectedIconPath)) {
			if (StringUtils.isNotEmpty(selectedIconPath))
			{
				Icon customTreeIcon = null;
				try
				{
					customTreeIcon = ImageIconFactory.newImageIcon(selectedIconPath);
				}
				catch (Exception e)
				{
					customTreeIcon = new StringIcon(this, value.getName());
				}
				iconCacheMap.put(selectedIconPath, customTreeIcon);
			}
		}
		return iconCacheMap.get(selectedIconPath);
	}

	/**
	 * {@inheritDoc}
	 */
	public Icon getOpenIcon()
	{
		return renderer.getOpenIcon();
	}

	/**
	 * {@inheritDoc}
	 */
	public Icon getLeafIcon()
	{
		return renderer.getLeafIcon();
	}

	/**
	 * {@inheritDoc}
	 */
	public Icon getClosedIcon()
	{
		return renderer.getClosedIcon();
	}
}
