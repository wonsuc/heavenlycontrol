package com.wonsuc.coc.heavenlycontrol.model;

public class Member {

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_ELDER = 1;
    public static final int TYPE_CO_LEADER = 2;
    public static final int TYPE_LEADER = 3;

    public int id;
    public String nickName;
    public int type;
    public String joinDate;

    public Member(){}

    public Member(String nickName, int type) {
        super();
        this.nickName = nickName;
        this.type = type;
    }

    public Member(String nickName, int type, String joinDate) {
        super();
        this.nickName = nickName;
        this.type = type;
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "Notice [id=" + id + ", nickName=" + nickName + ", type=" + type + ", joinDate=" + joinDate + "]";
    }
}