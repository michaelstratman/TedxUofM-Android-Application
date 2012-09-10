package com.Tedx.UofM;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class NotesList extends ListActivity {
	private static final int CREATE = 0;
	private static final int EDIT = 1;
	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	
	private EditNoteDBAdapter dbAdapter;
	private Cursor NoteCursor;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notes_list);
		dbAdapter = new EditNoteDBAdapter(this);
		dbAdapter.open();
		fillData();
		registerForContextMenu(getListView());
		
	}

	private void fillData() {
		// TODO Auto-generated method stub
		NoteCursor = dbAdapter.fetchAllNotes();
		startManagingCursor(NoteCursor);
		//Display these values in from to the textViews in to
		String [] from = new String[]{EditNoteDBAdapter.TITLE};
		int [] to = new int[]{R.id.notes_row};
		//connect them with the adapter
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.edit_notes_row, NoteCursor, from, to);
		setListAdapter(notes);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, "Insert Note");
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item){
		switch(item.getItemId()){
			case INSERT_ID:
				createNote();
				return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, "Delete Note");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		switch(item.getItemId()){
		case DELETE_ID:
				AdapterContextMenuInfo info = (AdapterContextMenuInfo)(item.getMenuInfo());
				dbAdapter.deleteNote(info.id);
				fillData();
				return true;
		
		}
		return super.onContextItemSelected(item);
		
	}
		
	private void createNote() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, EditNote.class);
		startActivityForResult(i, CREATE);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
		Cursor c = NoteCursor;
		c.moveToPosition(position);
		Intent i = new Intent(this, EditNote.class);
		i.putExtra(EditNoteDBAdapter.ROW, id);
		i.putExtra(EditNoteDBAdapter.TITLE, c.getString(c.getColumnIndexOrThrow(EditNoteDBAdapter.TITLE)));
		i.putExtra(EditNoteDBAdapter.BODY, c.getString(c.getColumnIndexOrThrow(EditNoteDBAdapter.BODY)));
		startActivityForResult(i, EDIT);
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		super.onActivityResult(requestCode, resultCode, intent);
		Bundle extras = intent.getExtras();
		switch(requestCode){
			case CREATE:
				String title = extras.getString(EditNoteDBAdapter.TITLE);
				String body = extras.getString(EditNoteDBAdapter.BODY);
				dbAdapter.createNote(title, body);
				fillData();
				break;
			case EDIT:
				Long rowid = extras.getLong(EditNoteDBAdapter.ROW);
				if(rowid != null){
					String editTitle = extras.getString(EditNoteDBAdapter.TITLE);
					String editBody = extras.getString(EditNoteDBAdapter.BODY);
					dbAdapter.updateNote(rowid, editTitle, editBody);
				}
				fillData();
				break;
		}
	}
	
	 
	
}
