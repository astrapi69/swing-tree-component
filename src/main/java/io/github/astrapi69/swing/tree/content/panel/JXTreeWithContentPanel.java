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

import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.model.api.IModel;
import io.github.astrapi69.swing.component.factory.SwingContainerFactory;
import io.github.astrapi69.swing.table.GenericJXTable;
import io.github.astrapi69.swing.table.model.GenericTableModel;
import io.github.astrapi69.swing.tree.panel.JXTreePanel;
import lombok.Getter;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.event.MouseEvent;

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

	public JXTreeWithContentPanel(final IModel<T> model)
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
			protected void onSingleLeftClick(MouseEvent mouseEvent)
			{
				super.onSingleLeftClick(mouseEvent);
				JXTreeWithContentPanel.this.onTableSingleLeftClick(mouseEvent);
			}

			protected void onSingleMiddleClick(MouseEvent mouseEvent)
			{
				super.onSingleMiddleClick(mouseEvent);
				JXTreeWithContentPanel.this.onTableSingleMiddleClick(mouseEvent);
			}

			protected void onSingleRightClick(MouseEvent mouseEvent)
			{
				super.onSingleRightClick(mouseEvent);
				JXTreeWithContentPanel.this.onTableSingleRightClick(mouseEvent);
			}

			protected void onDoubleLeftClick(MouseEvent mouseEvent)
			{
				super.onDoubleLeftClick(mouseEvent);
				JXTreeWithContentPanel.this.onTableDoubleLeftClick(mouseEvent);
			}

			protected void onDoubleMiddleClick(MouseEvent mouseEvent)
			{
				super.onDoubleMiddleClick(mouseEvent);
				JXTreeWithContentPanel.this.onTableDoubleMiddleClick(mouseEvent);
			}

			protected void onDoubleRightClick(MouseEvent mouseEvent)
			{
				super.onDoubleRightClick(mouseEvent);
				JXTreeWithContentPanel.this.onTableDoubleRightClick(mouseEvent);
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
	 * @param mouseEvent
	 *            the mouse event
	 */
	protected void onTableSingleLeftClick(MouseEvent mouseEvent)
	{
	}

	/**
	 * The callback method on the table single middle click.
	 *
	 * @param mouseEvent
	 *            the mouse event
	 */
	protected void onTableSingleMiddleClick(MouseEvent mouseEvent)
	{
	}

	/**
	 * The callback method on the table single right click.
	 *
	 * @param mouseEvent
	 *            the mouse event
	 */
	protected void onTableSingleRightClick(MouseEvent mouseEvent)
	{
	}

	/**
	 * The callback method on the table double left click.
	 *
	 * @param mouseEvent
	 *            the mouse event
	 */
	protected void onTableDoubleLeftClick(MouseEvent mouseEvent)
	{
	}

	/**
	 * The callback method on the table double middle click.
	 *
	 * @param mouseEvent
	 *            the mouse event
	 */
	protected void onTableDoubleMiddleClick(MouseEvent mouseEvent)
	{
	}

	/**
	 * The callback method on the table double right click.
	 *
	 * @param mouseEvent
	 *            the mouse event
	 */
	protected void onTableDoubleRightClick(MouseEvent mouseEvent)
	{
	}

}
