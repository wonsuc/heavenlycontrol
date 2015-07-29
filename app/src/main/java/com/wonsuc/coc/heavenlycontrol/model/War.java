package com.wonsuc.coc.heavenlycontrol.model;

public class War {

    public static final int OUTCOME_WIN = 0;
    public static final int OUTCOME_LOSE = 1;
    public static final int OUTCOME_DRAW = 2;

    public int id;
    public int seasonId;
    public String warDate;
    public int outcome;

    public War(){}

    public War(int seasonId, String warDate) {
        super();
        this.seasonId = seasonId;
        this.warDate = warDate;
    }

    public War(int seasonId, String warDate, int outcome) {
        super();
        this.seasonId = seasonId;
        this.warDate = warDate;
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return "War [id=" + id + ", seasonId=" + seasonId + ", warDate=" + warDate + ", outcome=" + outcome + "]";
    }
}
