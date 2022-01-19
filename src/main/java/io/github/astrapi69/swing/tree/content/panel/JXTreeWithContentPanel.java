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
package io.github.astrapi69.swing.tree.content.panel;

import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.TableModel;

import lombok.Getter;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.Model;
import io.github.astrapi69.swing.component.factory.SwingContainerFactory;
import io.github.astrapi69.swing.table.GenericJXTable;
import io.github.astrapi69.swing.table.model.GenericTableModel;
import io.github.astrapi69.swing.tree.panel.JXTreePanel;

@Getter
public abstract class JXTreeWithContentPanel<T, C> extends JXTreePanel<T>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	protected JScrollPane scrTreeEntryTable;
	protected GenericJXTable<C> tblTreeEntryTable;

	public JXTreeWithContentPanel()
	{
		this(BaseModel.of());
	}

	public JXTreeWithContentPanel(final Model<T> model)
	{
		super(model);
	}

	/**
	 * Abstract factory callback method that have to be overwritten to provide the specific
	 * {@link TableModel} for the {@link JTable}
	 *
	 * @param model
	 *            the model
	 * @return the table model
	 */
	protected abstract GenericTableModel<C> newTableModel(T model);

	/**
	 * Factory method for creating the new {@link JScrollPane}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a {@link JScrollPane}
	 *
	 * @return the new {@link JScrollPane}
	 */
	protected JScrollPane newTableScrollPane()
	{
		return SwingContainerFactory.newScrollPane();
	}

	/**
	 * Factory method for creating the new {@link JTable}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * {@link JTable}
	 *
	 * @return the new {@link JTable}
	 */
	protected GenericJXTable<C> newJTable()
	{
		return new GenericJXTable<C>(new GenericTableModel<C>()
		{
			@Override
			public int getColumnCount()
			{
				return 0;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex)
			{
				return null;
			}
		})
		{
			protected void onSingleLeftClick(MouseEvent event)
			{
				super.onSingleLeftClick(event);
				JXTreeWithContentPanel.this.onTableSingleLeftClick(event);
			}

			protected void onSingleMiddleClick(MouseEvent event)
			{
				super.onSingleMiddleClick(event);
				JXTreeWithContentPanel.this.onTableSingleMiddleClick(event);
			}

			protected void onSingleRightClick(MouseEvent event)
			{
				super.onSingleRightClick(event);
				JXTreeWithContentPanel.this.onTableSingleRightClick(event);
			}

			protected void onDoubleLeftClick(MouseEvent event)
			{
				super.onDoubleLeftClick(event);
				JXTreeWithContentPanel.this.onTableDoubleLeftClick(event);
			}

			protected void onDoubleMiddleClick(MouseEvent event)
			{
				super.onDoubleMiddleClick(event);
				JXTreeWithContentPanel.this.onTableDoubleMiddleClick(event);
			}

			protected void onDoubleRightClick(MouseEvent event)
			{
				super.onDoubleRightClick(event);
				JXTreeWithContentPanel.this.onTableDoubleRightClick(event);
			}
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitializeComponents()
	{
		scrTreeEntryTable = newTableScrollPane();
		tblTreeEntryTable = newJTable();
		scrTree = newTreeScrollPane();
		tree = newTree();

		setPreferredSize(newPreferredSize(1000, 780));
		scrTree.setViewportView(tree);
		scrTreeEntryTable.setViewportView(tblTreeEntryTable);
	}

	/**
	 * The callback method on the table single left click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableSingleLeftClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on the table single middle click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableSingleMiddleClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on the table single right click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableSingleRightClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on the table double left click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableDoubleLeftClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on the table double middle click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableDoubleMiddleClick(MouseEvent event)
	{
	}

	/**
	 * The callback method on the table double right click.
	 *
	 * @param event
	 *            the mouse event
	 */
	protected void onTableDoubleRightClick(MouseEvent event)
	{
	}

}
