package com.wonsuc.coc.heavenlycontrol.model;

public class Record {

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
    public float realStar;
    public float dupStar;
    public float advantage;
    public float base;

    public Record(){}

    public Record(int id, int warId, int memberId, float realStar, float dupStar, float advantage, float base) {
        super();
        this.id = id;
        this.warId = warId;
        this.memberId = memberId;
        this.realStar = realStar;
        this.dupStar = dupStar;
        this.advantage = advantage;
        this.base = base;
    }

    @Override
    public String toString() {
        return "Record [id=" + id + ", warId=" + warId + ", memberId=" + memberId + ", realStar=" + realStar + ", dupStar=" + dupStar + ", advantage=" + advantage + ", base=" + base + "]";
    }
}
