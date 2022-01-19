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
package io.github.astrapi69.swing.tree.panel.node;

import java.awt.*;

import javax.swing.*;

import io.github.astrapi69.model.LambdaModel;
import io.github.astrapi69.swing.JMCheckBox;
import io.github.astrapi69.swing.JMTextField;
import lombok.Getter;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.base.BasePanel;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

@Getter
public class NodePanel extends BasePanel<NodeModelBean>
{
	JMCheckBox cbxNode;
	JMTextField txtName;
	JLabel lblName;
	JLabel lblNode;

	public NodePanel()
	{
		this(BaseModel.of(NodeModelBean.builder().build()));
	}

	public NodePanel(final Model<NodeModelBean> model)
	{
		super(model);
	}

	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblName = new JLabel("Enter name for node:");
		txtName = new JMTextField();
		lblNode = new JLabel("Is node:");
		cbxNode = new JMCheckBox();

		NodeModelBean modelObject = getModelObject();

		txtName.setPropertyModel(LambdaModel.of(modelObject::getName, modelObject::setName));
		cbxNode.setPropertyModel(LambdaModel.of(modelObject::isNode, modelObject::setNode));

	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();
		onInitializeMigLayout();
	}

	protected void onInitializeGridLayout()
	{
		GridLayout layout = new GridLayout(2, 2);
		this.setLayout(layout);

		add(lblName);
		add(txtName);
		add(lblNode);
		add(cbxNode);
	}

	protected void onInitializeMigLayout()
	{
		MigLayout layout = new MigLayout(
			new LC().fillX().wrapAfter(2),
			new AC().align("left").gap("10").grow().fill(),
			new AC().fill().gap("10"));
		this.setLayout(layout);

		add(lblName);
		add(txtName, new CC().grow().width("120px"));
		add(lblNode, "grow");
		add(cbxNode, "grow");
	}
}
