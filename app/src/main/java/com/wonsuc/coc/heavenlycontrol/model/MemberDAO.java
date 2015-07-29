package com.wonsuc.coc.heavenlycontrol.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MemberDAO extends BaseDAO {

    private static final String WHERE_ID_EQUALS = "id=?";

    public MemberDAO(Context context) {
        super(context);
    }

    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) notice + get all notices + delete all notices
     */

    // Notice table name
    private static final String TABLE_MEMBER = "member_list";

    // Notice Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NICK_NAME = "nick_name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_JOIN_DATE = "join_date";

    private static final String[] COLUMNS = {KEY_ID,KEY_NICK_NAME,KEY_TYPE,KEY_JOIN_DATE};

    public void addMember(Member member){
        Log.d("addMember", member.toString());
        // 1. get reference to writable DB
        //SQLiteDatabase db = this.getWritableDatabase();
        super.open();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NICK_NAME, member.nickName);
        values.put(KEY_TYPE, member.type);
        values.put(KEY_JOIN_DATE, member.joinDate);

        // 3. insert
        db.insert(TABLE_MEMBER, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Member getMember(int id){

        // 1. get reference to readable DB
        //SQLiteDatabase db = this.getReadableDatabase();
        super.open();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_MEMBER, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build Member object
        Member member = new Member();
        member.id = Integer.parseInt(cursor.getString(0));
        member.nickName = cursor.getString(1);
        member.type = cursor.getInt(2);
        member.joinDate = cursor.getString(3);


        Log.d("getNotice(" + id + ")", member.toString());

        // 5. return member
        return member;
    }

    // Get All Members
    public ArrayList<Member> getAllMembers() {
        ArrayList<Member> members = new ArrayList<Member>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_MEMBER;

        // 2. get reference to writable DB
        //SQLiteDatabase db = this.getWritableDatabase();
        super.open();

        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build notice and add it to list
        Member member = null;
        if (cursor.moveToFirst()) {
            do {
                member = new Member();
                member.id = Integer.parseInt(cursor.getString(0));
                member.nickName = cursor.getString(1);
                member.type = cursor.getInt(2);
                member.joinDate = cursor.getString(3);

                members.add(member);
            } while (cursor.moveToNext());
        }

        Log.d("getAllMembers()", members.toString());

        // return notices
        return members;
    }

    // Updating single member
    public int updateMember(Member member) {

        // 1. get reference to writable DB
        //SQLiteDatabase db = this.getWritableDatabase();
        super.open();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("nick_name", member.nickName);
        values.put("type", member.type);
        values.put("join_date", member.joinDate);

        // 3. updating row
        int i = db.update(TABLE_MEMBER, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(member.id) }); //selection args

        // 4. close
        db.close();

        return i;
    }

    // Deleting single member
    public void deleteMember(Member member) {

        // 1. get reference to writable DB
        //SQLiteDatabase db = this.getWritableDatabase();
        super.open();

        // 2. delete
        db.delete(TABLE_MEMBER,
                KEY_ID+" = ?",
                new String[] { String.valueOf(member.id) });

        // 3. close
        db.close();

        Log.d("deleteNotice", member.toString());
    }

    public void deleteAllMember() {

        // 1. get reference to writable DB
        //SQLiteDatabase db = this.getWritableDatabase();
        super.open();

        // 2. delete
        db.execSQL("delete from "+ TABLE_MEMBER);

        // 3. close
        db.close();

        Log.d("deleteAllNotice", "success");
    }

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