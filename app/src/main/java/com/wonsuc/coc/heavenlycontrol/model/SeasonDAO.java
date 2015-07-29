package com.wonsuc.coc.heavenlycontrol.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class SeasonDAO extends BaseDAO {

    public SeasonDAO(Context context) {
        super(context);
    }

    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) notice + get all notices + delete all notices
     */

    // Notice table name
    private static final String TABLE_SEASON = "season_list";

    // Notice Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_START_DATE = "start_date";
    private static final String KEY_END_DATE = "end_date";

    private static final String[] COLUMNS = {KEY_ID,KEY_START_DATE,KEY_END_DATE};

    public void addSeason(Season season){
        Log.d("addSeason", season.toString());
        super.open();

        ContentValues values = new ContentValues();
        values.put(KEY_START_DATE, season.startDate);
        values.put(KEY_END_DATE, season.endDate);

        db.insert(TABLE_SEASON,
                null,
                values);

        db.close();
    }

    public Season getSeason(int id){
        super.open();

        Cursor cursor =
                db.query(TABLE_SEASON, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Season season = new Season();
        season.id = Integer.parseInt(cursor.getString(0));
        season.startDate = cursor.getString(1);
        season.endDate = cursor.getString(2);

        Log.d("getSeason(" + id + ")", season.toString());

        // 5. return member
        return season;
    }

    public ArrayList<Season> getAllSeasons() {
        ArrayList<Season> seasons = new ArrayList<Season>();

        String query = "SELECT  * FROM " + TABLE_SEASON;

        super.open();

        Cursor cursor = db.rawQuery(query, null);

        Season season = null;
        if (cursor.moveToFirst()) {
            do {
                season = new Season();
                season.id = Integer.parseInt(cursor.getString(0));
                season.startDate = cursor.getString(1);
                season.endDate = cursor.getString(2);

                seasons.add(season);
            } while (cursor.moveToNext());
        }

        Log.d("getAllSeasons()", seasons.toString());

        return seasons;
    }

    public int updateSeason(Season season) {
        super.open();

        ContentValues values = new ContentValues();
        values.put("start_date", season.startDate);
        values.put("end_date", season.endDate);

        int i = db.update(TABLE_SEASON, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(season.id) }); //selection args

        db.close();

        return i;
    }

    public void deleteSeason(Season season) {
        super.open();

        db.delete(TABLE_SEASON,
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