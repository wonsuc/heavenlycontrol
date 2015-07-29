package com.wonsuc.coc.heavenlycontrol.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HeavenlyControlDB.db";
    private static final int DATABASE_VERSION = 1;

    public static final String CREATE_SEASON_TABLE = "CREATE TABLE season_list ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "start_date TEXT, "+
            "end_date TEXT)";

    public static final String CREATE_WAR_TABLE = "CREATE TABLE war_list ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "season_id INT, " +
            "war_date TEXT, " +
            "outcome INT, " +
            "FOREIGN KEY( season_id ) REFERENCES " +
            "season_list( id )" + ")";

    public static final String CREATE_RECORD_TABLE = "CREATE TABLE record_list ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "war_id INT, " +
            "member_id INT, " +
            "real_star REAL, " +
            "dup_star REAL, " +
            "advantage REAL, " +
            "base REAL, " +
            "FOREIGN KEY( war_id ) REFERENCES " +
            "war_list( id )," +
            "FOREIGN KEY( member_id ) REFERENCES " +
            "member_list( id )" + ")";

    public static final String CREATE_MEMBER_TABLE = "CREATE TABLE member_list ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nick_name TEXT, "+
            "type TEXT, "+
            "join_date TEXT)";

    public static final String CREATE_RECORD_RANK_VIEW = "CREATE VIEW record_rank AS " +
            "SELECT r.id AS id, r.war_id AS war_id, r.member_id as member_id, " +
            "CASE WHEN (r.real_star + r.dup_star + r.advantage + r.base) > 7 " +
            "THEN 7 " +
            "ELSE (r.real_star + r.dup_star + r.advantage + r.base) " +
            "END AS heavenly_star, " +
            "r.real_star real_star, r.dup_star as dup_star, r.advantage as advantage, r.base as base " +
            "FROM record_list r";

    public static final String CREATE_RECORD_RANK_TOTAL_VIEW = "CREATE VIEW record_rank_total AS " +
            "SELECT r.id AS id, r.war_id AS war_id, r.member_id as member_id, " +
            "SUM(r.heavenly_star) as heavenly_star, " +
            "SUM(r.real_star) as real_star, SUM(r.dup_star) as dup_star, SUM(r.advantage) as advantage, SUM(r.base) as base " +
            "FROM record_rank r " +
            "GROUP BY r.member_id";

//    CREATE VIEW record_rank AS
//    SELECT r.id AS id, r.war_id AS war_id, r.member_id as member_id, (r.real_star + r.dup_star + r.advantage + r.base) as heavenly_star
//    FROM record_list r;
//
//    CREATE VIEW record_rank_total AS
//    SELECT r.id AS id, r.war_id AS war_id, r.member_id as member_id, SUM(r.real_star + r.dup_star + r.advantage + r.base) as heavenly_star
//    FROM record_list r
//    GROUP BY r.member_id;


    /*CREATE VIEW view_name AS
    SELECT A.time AS Start, B.time AS Stop
    FROM time A, time B
    WHERE A.id+1=B.id
    AND A.bool=1
    AND B.bool=0*/

    // 전쟁 승리자 리스트 : 보류
    /*public static final String CREATE_WAR_WINNER_TABLE = "CREATE TABLE war_winner_list ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "war_id INT, " +
            "member_id INT, " +
            "FOREIGN KEY( war_id ) REFERENCES " +
            "war_list( id )," +
            "FOREIGN KEY( member_id ) REFERENCES " +
            "member_list( id )" + ")";*/

    private static DataBaseHelper instance;
    private static Context context;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);

        return instance;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SEASON_TABLE);
        db.execSQL(CREATE_WAR_TABLE);
        db.execSQL(CREATE_RECORD_TABLE);
        db.execSQL(CREATE_MEMBER_TABLE);
        db.execSQL(CREATE_RECORD_RANK_VIEW);
        db.execSQL(CREATE_RECORD_RANK_TOTAL_VIEW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*        db.execSQL("DROP TABLE IF EXISTS season_list");
        db.execSQL("DROP TABLE IF EXISTS war_list");
        db.execSQL("DROP TABLE IF EXISTS record_list");
        db.execSQL("DROP TABLE IF EXISTS member_list");*/
        //db.deleteDatabase("sdfsad");
        //File file = new File(context.getDatabasePath(), "jas.pdf");
        //File file = new File();
        //File file = context.getDatabasePath("HeavenlyControlDB.db");
        //db.deleteDatabase(file);
        //onCreate(db);
    }

    /*public boolean deleteDatabase(SQLiteDatabase db, String name) {
        return db.deleteDatabase(this.getDatabasePath(name).getAbsolutePath());
    }

    public File getDatabasePath(String name) {
        return new File(context.getExternalFilesDir(null), "databases/"+name);
    }
*/
}