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

import javax.swing.GroupLayout;
import javax.swing.JLabel;

import lombok.Getter;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.LambdaModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.base.BasePanel;
import io.github.astrapi69.swing.component.JMTextField;
import io.github.astrapi69.test.objects.Permission;

@Getter
public class PermissionPanel extends BasePanel<Permission>
{
	private JLabel lblDescription;
	private JLabel lblName;
	private JLabel lblShortcut;
	private JMTextField txtDescription;
	private JMTextField txtName;
	private JMTextField txtShortcut;

	public PermissionPanel()
	{
		this(BaseModel.of(Permission.builder().build()));
	}

	public PermissionPanel(final IModel<Permission> model)
	{
		super(model);
	}


	@Override
	protected void onInitializeComponents()
	{
		super.onInitializeComponents();
		lblName = new JLabel();
		lblDescription = new JLabel();
		lblShortcut = new JLabel();
		Permission modelObject = getModelObject();

		txtName = new JMTextField();
		txtShortcut = new JMTextField();
		txtDescription = new JMTextField();

		txtName.setPropertyModel(LambdaModel.of(modelObject::getName, modelObject::setName));
		txtShortcut
			.setPropertyModel(LambdaModel.of(modelObject::getShortcut, modelObject::setShortcut));
		txtDescription.setPropertyModel(
			LambdaModel.of(modelObject::getDescription, modelObject::setDescription));

		lblName.setText("Name");

		lblDescription.setText("Description");

		lblShortcut.setText("Shortcut");

	}

	@Override
	protected void onInitializeLayout()
	{
		super.onInitializeLayout();

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 120,
						GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18).addComponent(txtName, GroupLayout.PREFERRED_SIZE, 220,
						GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 120,
						GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18).addComponent(txtDescription, GroupLayout.PREFERRED_SIZE,
						220, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup()
					.addComponent(lblShortcut, GroupLayout.PREFERRED_SIZE, 120,
						GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18).addComponent(txtShortcut, GroupLayout.PREFERRED_SIZE, 220,
						GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout
			.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblName).addComponent(txtName, GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblShortcut)
						.addComponent(txtShortcut, GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblDescription).addComponent(txtDescription,
							GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	}

}
