package com.wonsuc.coc.heavenlycontrol.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class WarDAO extends BaseDAO {

    public WarDAO(Context context) {
        super(context);
    }

    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) notice + get all notices + delete all notices
     */

    // Notice table name
    private static final String TABLE_WAR = "war_list";

    // Notice Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SEASON_ID = "season_id";
    private static final String KEY_WAR_DATE = "war_date";
    private static final String KEY_OUTCOME = "outcome";

    private static final String[] COLUMNS = {KEY_ID,KEY_SEASON_ID,KEY_WAR_DATE,KEY_OUTCOME};

    public void addWar(War war){
        Log.d("addWar", war.toString());
        super.open();

        ContentValues values = new ContentValues();
        values.put(KEY_SEASON_ID, war.seasonId);
        values.put(KEY_WAR_DATE, war.warDate);
        values.put(KEY_OUTCOME, war.outcome);

        db.insert(TABLE_WAR,
                null,
                values);

        db.close();
    }

    public War getWar(int id){
        super.open();

        Cursor cursor =
                db.query(TABLE_WAR, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        War war = new War();
        war.id = Integer.parseInt(cursor.getString(0));
        war.seasonId = cursor.getInt(1);
        war.warDate = cursor.getString(2);
        war.outcome = cursor.getInt(3);

        Log.d("getWar(" + id + ")", war.toString());

        return war;
    }

    public ArrayList<War> getAllWars() {
        ArrayList<War> wars = new ArrayList<War>();

        String query = "SELECT  * FROM " + TABLE_WAR;

        super.open();

        Cursor cursor = db.rawQuery(query, null);

        War war = null;
        if (cursor.moveToFirst()) {
            do {
                war = new War();
                war.id = Integer.parseInt(cursor.getString(0));
                war.seasonId = cursor.getInt(1);
                war.warDate = cursor.getString(2);
                war.outcome = cursor.getInt(3);

                wars.add(war);
            } while (cursor.moveToNext());
        }

        Log.d("getAllWars()", wars.toString());

        return wars;
    }

    public int updateWar(War war) {
        super.open();

        ContentValues values = new ContentValues();
        values.put(KEY_SEASON_ID, war.seasonId);
        values.put(KEY_WAR_DATE, war.warDate);
        values.put(KEY_OUTCOME, war.outcome);

        int i = db.update(TABLE_WAR, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(war.id) }); //selection args

        db.close();

        return i;
    }

    public void deleteSeason(Season season) {
        super.open();

        db.delete(TABLE_WAR,
                KEY_ID + " = ?",
                new String[]{String.valueOf(season.id)});

        db.close();

        Log.d("deleteSeason", season.toString());
    }

/*    public void deleteAllMember() {

        // 1. get reference to writable DB
        //SQLiteDatabase db = this.getWritableDatabase();
        super.open();

        // 2. delete
        db.execSQL("delete from "+ TABLE_MEMBER);

        // 3. close
        db.close();

        Log.d("deleteAllNotice", "success");
    }*/

/*    public long save(Member member) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.NAME_COLUMN, department.getName());

        return database.insert(DataBaseHelper.MEMBER_TABLE, null, values);
    }

    public long update(Department department) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.NAME_COLUMN, department.getName());

        long result = database.update(DataBaseHelper.DEPARTMENT_TABLE, values,
                WHERE_ID_EQUALS,
                new String[]{String.valueOf(department.getId())});
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int deleteDept(Department department) {
        return database.delete(DataBaseHelper.DEPARTMENT_TABLE,
                WHERE_ID_EQUALS, new String[]{department.getId() + ""});
    }

    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<Department>();
        Cursor cursor = database.query(DataBaseHelper.DEPARTMENT_TABLE,
                new String[]{DataBaseHelper.ID_COLUMN,
                        DataBaseHelper.NAME_COLUMN}, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Department department = new Department();
            department.setId(cursor.getInt(0));
            department.setName(cursor.getString(1));
            departments.add(department);
        }
        return departments;
    }

    public void loadDepartments() {
        Department department = new Department("Development");
        Department department1 = new Department("R and D");
        Department department2 = new Department("Human Resource");
        Department department3 = new Department("Financial");
        Department department4 = new Department("Marketing");
        Department department5 = new Department("Sales");

        List<Department> departments = new ArrayList<Department>();
        departments.add(department);
        departments.add(department1);
        departments.add(department2);
        departments.add(department3);
        departments.add(department4);
        departments.add(department5);
        for (Department dept : departments) {
            ContentValues values = new ContentValues();
            values.put(DataBaseHelper.NAME_COLUMN, dept.getName());
            database.insert(DataBaseHelper.DEPARTMENT_TABLE, null, values);
        }
    }*/

}