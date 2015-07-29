package com.wonsuc.coc.heavenlycontrol.model;

public class RecordRank {

    /*public static final String CREATE_RECORD_TABLE = "CREATE TABLE record_list ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "war_id INT, " +
            "member_id INT, " +
            "real_star INT, " +
            "half_star INT, " +
            "FOREIGN KEY( war_id ) REFERENCES " +
            "war_list( id )," +
            "FOREIGN KEY( member_id ) REFERENCES " +
            "member_list( id )" + ")";*/

    public int id;
    public int warId;
    public int memberId;
    public String nickName;
    public float heavenlyStar;
    public float realStar;
    public float dupStar;
    public float advantage;
    public float base;
    public int rank;

    public RecordRank(){}

    @Override
    public String toString() {
        return "Record [id=" + id + ", warId=" + warId + ", memberId=" + memberId + ", nickName=" + nickName +
                ", heavenlyStar=" + heavenlyStar + ", realStar=" + realStar + ", dupStar=" + dupStar + ", advantage=" + advantage + ", base=" + base + ", rank=" + rank + "]";
    }
}
