package com.wonsuc.coc.heavenlycontrol.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class RecordDAO extends BaseDAO {

    public RecordDAO(Context context) {
        super(context);
    }

    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) notice + get all notices + delete all notices
     */

    // Notice table name
    private static final String TABLE_RECORD = "record_list";

    // Notice Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_WAR_ID = "war_id";
    private static final String KEY_MEMBER_ID = "member_id";
    private static final String KEY_REAL_STAR = "real_star";
    private static final String KEY_DUP_STAR = "dup_star";
    private static final String KEY_ADVANTAGE = "advantage";
    private static final String KEY_BASE = "base";

    private static final String[] COLUMNS = {KEY_ID,KEY_WAR_ID,KEY_MEMBER_ID,KEY_REAL_STAR,KEY_DUP_STAR,KEY_ADVANTAGE,KEY_BASE};

    public void addRecord(Record record){
        Log.d("addRecord", record.toString());
        super.open();

        ContentValues values = new ContentValues();
        values.put(KEY_WAR_ID, record.warId);
        values.put(KEY_MEMBER_ID, record.memberId);
        values.put(KEY_REAL_STAR, record.realStar);
        values.put(KEY_DUP_STAR, record.dupStar);
        values.put(KEY_ADVANTAGE, record.advantage);
        values.put(KEY_BASE, record.base);

        db.insert(TABLE_RECORD,
                null,
                values);

        db.close();
    }

    public Record getRecord(int id){
        super.open();

        Cursor cursor =
                db.query(TABLE_RECORD, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Record record = new Record();
        record.id = Integer.parseInt(cursor.getString(0));
        record.warId = cursor.getInt(1);
        record.memberId = cursor.getInt(2);
        record.realStar = cursor.getFloat(3);
        record.dupStar = cursor.getFloat(4);
        record.advantage = cursor.getFloat(5);
        record.base = cursor.getFloat(6);

        Log.d("getRecord(" + id + ")", record.toString());

        return record;
    }

    public ArrayList<RecordRank> getRecordRank(int warId) {
        ArrayList<RecordRank> records = new ArrayList<RecordRank>();

        String query = "SELECT rr1.id, rr1.war_id, rr1.member_id, m.nick_name, rr1.heavenly_star, r.real_star, r.dup_star, r.advantage, r.base, COUNT(rr2.heavenly_star) " +
                "FROM record_rank rr1, record_rank rr2 " +
                "INNER JOIN record_list r ON rr1.member_id = r.member_id AND rr1.war_id = r.war_id " +
                "INNER JOIN member_list m ON rr1.member_id = m.id " +
                "WHERE (rr1.heavenly_star < rr2.heavenly_star OR (rr1.heavenly_star = rr2.heavenly_star AND rr1.member_id = rr2.member_id)) AND rr1.war_id = rr2.war_id AND rr1.war_id = ? " +
                "GROUP BY rr1.member_id, rr1.heavenly_star " +
                "ORDER BY rr1.heavenly_star DESC, m.nick_name ASC";

        super.open();

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(warId)});

        RecordRank record = null;
        if (cursor.moveToFirst()) {
            do {
                record = new RecordRank();
                record.id = Integer.parseInt(cursor.getString(0));
                record.warId = cursor.getInt(1);
                record.memberId = cursor.getInt(2);
                record.nickName = cursor.getString(3);
                record.heavenlyStar = cursor.getFloat(4);
                record.realStar = cursor.getFloat(5);
                record.dupStar = cursor.getFloat(6);
                record.advantage = cursor.getFloat(7);
                record.base = cursor.getFloat(8);
                record.rank = cursor.getInt(9);
                records.add(record);
            } while (cursor.moveToNext());
        }

        Log.d("getRecordRank()", records.toString());

        return records;
    }

    public ArrayList<RecordRank> getTotalRecordRank(int limit, int offset) {
        ArrayList<RecordRank> records = new ArrayList<RecordRank>();

        String query = "SELECT rrt1.id, rrt1.member_id, m.nick_name, rrt1.heavenly_star, rrt1.real_star, rrt1.dup_star, rrt1.advantage, rrt1.base, COUNT(rrt2.heavenly_star) " +
                "FROM record_rank_total rrt1, record_rank_total rrt2 " +
                "INNER JOIN record_list r ON rrt1.member_id = r.member_id AND rrt1.war_id = r.war_id " +
                "INNER JOIN member_list m ON rrt1.member_id = m.id " +
                "WHERE (rrt1.heavenly_star < rrt2.heavenly_star OR (rrt1.heavenly_star = rrt2.heavenly_star AND rrt1.member_id = rrt2.member_id)) " +
                "GROUP BY rrt1.member_id, rrt1.heavenly_star " +
                "ORDER BY rrt1.heavenly_star DESC, m.nick_name ASC " +
                "LIMIT ? OFFSET ?";

        super.open();

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(limit), String.valueOf(offset)});

        RecordRank record = null;
        if (cursor.moveToFirst()) {
            do {
                record = new RecordRank();
                record.id = Integer.parseInt(cursor.getString(0));
                record.memberId = cursor.getInt(1);
                record.nickName = cursor.getString(2);
                record.heavenlyStar = cursor.getFloat(3);
                record.realStar = cursor.getFloat(4);
                record.dupStar = cursor.getFloat(5);
                record.advantage = cursor.getFloat(6);
                record.base = cursor.getFloat(7);
                record.rank = cursor.getInt(8);

                records.add(record);
            } while (cursor.moveToNext());
        }

        Log.d("getTotalRecordRank()", records.toString());

        return records;
    }

    public ArrayList<Record> getAllRecords() {
        ArrayList<Record> records = new ArrayList<Record>();

        String query = "SELECT  * FROM " + TABLE_RECORD;

        super.open();

        Cursor cursor = db.rawQuery(query, null);

        Record record = null;
        if (cursor.moveToFirst()) {
            do {
                record = new Record();
                record.id = Integer.parseInt(cursor.getString(0));
                record.warId = cursor.getInt(1);
                record.memberId = cursor.getInt(2);
                record.realStar = cursor.getFloat(3);
                record.dupStar = cursor.getFloat(4);
                record.advantage = cursor.getFloat(5);
                record.base = cursor.getFloat(6);

                records.add(record);
            } while (cursor.moveToNext());
        }

        Log.d("getAllRecords()", records.toString());

        return records;
    }

    public int updateRecord(Record record) {
        super.open();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, record.id);
        values.put(KEY_WAR_ID, record.warId);
        values.put(KEY_MEMBER_ID, record.memberId);
        values.put(KEY_REAL_STAR, record.realStar);
        values.put(KEY_DUP_STAR, record.dupStar);
        values.put(KEY_ADVANTAGE, record.advantage);
        values.put(KEY_BASE, record.base);

        int i = db.update(TABLE_RECORD, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(record.id) }); //selection args

        db.close();

        return i;
    }

    public void deleteSeason(Season season) {
        super.open();

        db.delete(TABLE_RECORD,
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