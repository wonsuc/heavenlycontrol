package com.wonsuc.coc.heavenlycontrol.model;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BaseDAO {

    protected SQLiteDatabase db;
    private DataBaseHelper dbHelper;
    private Context mContext;

    public BaseDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if (dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        db = dbHelper.getWritableDatabase();
    }

	/*public void close() {
        dbHelper.close();
		database = null;
	}*/

}