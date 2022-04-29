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

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.lang3.StringUtils;

import io.github.astrapi69.icon.ImageIconFactory;
import io.github.astrapi69.icon.StringIcon;
import io.github.astrapi69.swing.tree.GenericTreeElement;
import io.github.astrapi69.tree.BaseTreeNode;

public class GenericBaseTreeNodeCellRenderer<T, K>
	extends
		BaseTreeNodeCellRenderer<GenericTreeElement<T>, K>
{
	Icon customTreeIcon;
	Icon selectedTreeIcon;

	protected void onSelected(Object value, boolean selected)
	{
		// TODO set selected image
		if(selected){
			this.setIcon(selectedTreeIcon);
		} else {
			this.setIcon(customTreeIcon);
		}
		this.setForeground(selected ? Color.blue : Color.gray);
		this.setBackground(Color.black);

	}

	protected JLabel initialize(BaseTreeNode<GenericTreeElement<T>, K> userObject, boolean selected)
	{
		BaseTreeNode<GenericTreeElement<T>, K> treeNode = userObject;
		String displayValue = treeNode.getDisplayValue();
		GenericTreeElement<T> value = treeNode.getValue();
		if (value != null)
		{
			String iconPath = value.getIconPath();
			if (StringUtils.isNotEmpty(iconPath))
			{
				initializeCustomTreeIcon(value);
				initializeSelectedTreeIcon(value);
				if (value.isWithText())
				{
					this.setText(displayValue);
				}
				else
				{
					this.setText("");
				}
				this.setToolTipText(displayValue);
				this.setIcon(customTreeIcon);
				return this;
			}
		}
		return super.initialize(userObject,selected);
	}

	protected void initializeCustomTreeIcon(GenericTreeElement<T> value)
	{
		String iconPath = value.getIconPath();
		if (StringUtils.isNotEmpty(iconPath))
		{
			try
			{
				customTreeIcon = ImageIconFactory.newImageIcon(iconPath);
			}
			catch (Exception e)
			{
				customTreeIcon = new StringIcon(this, iconPath);
			}
		}
	}

	protected void initializeSelectedTreeIcon(GenericTreeElement<T> value)
	{
		String selectedIconPath = value.getSelectedIconPath();
		if (StringUtils.isNotEmpty(selectedIconPath))
		{
			try
			{
				selectedTreeIcon = ImageIconFactory.newImageIcon(selectedIconPath);
			}
			catch (Exception e)
			{
				JLabel selectedTreeLabel = new JLabel(this.getText());
				selectedTreeLabel.setForeground(Color.blue);
				selectedTreeLabel.setBackground(Color.black);
				selectedTreeIcon = new StringIcon(selectedTreeLabel, selectedIconPath);
			}
		}
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
