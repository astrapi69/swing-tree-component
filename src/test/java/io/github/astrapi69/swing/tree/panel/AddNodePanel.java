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
package io.github.astrapi69.swing.tree.panel;

import java.awt.*;

import javax.swing.*;

import lombok.Getter;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.base.BasePanel;

@Getter
public class AddNodePanel extends BasePanel<AddNodeModelBean>
{
	JCheckBox cbxNode;
	JTextField txtName;
	JLabel lblName;
	JLabel lblNode;

	public AddNodePanel()
	{
		this(BaseModel.of(AddNodeModelBean.builder().build()));
	}

	public AddNodePanel(final Model<AddNodeModelBean> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblName = new JLabel("Enter name for node:");
		txtName = new JTextField();
		lblNode = new JLabel("Is leaf:");
		cbxNode = new JCheckBox();
		add(lblName);
		add(txtName);
		add(lblNode);
		add(cbxNode);
	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
		GridLayout layout = new GridLayout(2, 2);
		this.setLayout(layout);
	}
}
