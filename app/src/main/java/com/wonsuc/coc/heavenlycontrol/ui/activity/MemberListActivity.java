package com.wonsuc.coc.heavenlycontrol.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.wonsuc.coc.heavenlycontrol.R;
import com.wonsuc.coc.heavenlycontrol.model.DataBaseHelper;
import com.wonsuc.coc.heavenlycontrol.model.Member;
import com.wonsuc.coc.heavenlycontrol.model.MemberDAO;
import com.wonsuc.coc.heavenlycontrol.model.Record;
import com.wonsuc.coc.heavenlycontrol.model.RecordDAO;
import com.wonsuc.coc.heavenlycontrol.model.RecordRank;
import com.wonsuc.coc.heavenlycontrol.model.Season;
import com.wonsuc.coc.heavenlycontrol.model.SeasonDAO;
import com.wonsuc.coc.heavenlycontrol.model.War;
import com.wonsuc.coc.heavenlycontrol.model.WarDAO;
import com.wonsuc.coc.heavenlycontrol.ui.adapter.MemberListAdapter;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberListActivity extends BaseActivity {

    private static final int wonsuc = 1;
    private static final int ㅎㅎㅎ = 2;
    private static final int 콩진태 = 3;
    private static final int 미니주니2 = 4;
    private static final int 라파토끼 = 5;
    private static final int 슘니 = 6;
    private static final int Hwan5 = 7;
    private static final int high = 8;
    private static final int 룰루 = 9;
    private static final int 꽁코 = 10;
    private static final int nomute = 11;
    private static final int wonsuc_부 = 12;
    private static final int 랄라 = 13;
    private static final int 신사펭귄 = 14;
    private static final int asabbing = 15;
    private static final int 지상현 = 16;

    MemberDAO mMemberDAO;
    SeasonDAO mSeasonDAO;
    WarDAO mWarDAO;
    RecordDAO mRecordDao;

    MemberListAdapter adapter;

    @Bind(R.id.member_list)
    ListView lvMemberList;

    //ListView lvMemberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        ButterKnife.bind(this);

        mMemberDAO = new MemberDAO(this);
        mSeasonDAO = new SeasonDAO(this);
        mWarDAO = new WarDAO(this);
        mRecordDao = new RecordDAO(this);

        adapter = new MemberListAdapter(this, 0);

        lvMemberList = (ListView) findViewById(R.id.member_list);
        lvMemberList.setAdapter(adapter);

        //db.deleteAllMember();



        /*File file = getDatabasePath("HeavenlyControlDB.db");
        DataBaseHelper dbHelper = DataBaseHelper.getHelper(this);
        dbHelper.getWritableDatabase().deleteDatabase(file);*/

        loadMemberList();
    }

    private void loadMemberList() {
        //Collections.reverse(notice_list);

//        Season season = new Season("20150706");
//        mSeasonDAO.addSeason(season);
//
//        War war = new War(1, "20150706", War.OUTCOME_LOSE);
//        mWarDAO.addWar(war);
//
//        Member member = new Member("wonsuc",Member.TYPE_CO_LEADER);
//        mMemberDAO.addMember(member);
//
//        member = new Member("ㅎㅎㅎ",Member.TYPE_CO_LEADER);
//        mMemberDAO.addMember(member);
//
//        member = new Member("콩진태",Member.TYPE_NORMAL);
//        mMemberDAO.addMember(member);
//
//        member = new Member("미니주니2",Member.TYPE_CO_LEADER);
//        mMemberDAO.addMember(member);
//
//        member = new Member("라파토끼",Member.TYPE_NORMAL);
//        mMemberDAO.addMember(member);
//
//        member = new Member("슘니",Member.TYPE_ELDER);
//        mMemberDAO.addMember(member);
//
//        member = new Member("Hwan5",Member.TYPE_NORMAL);
//        mMemberDAO.addMember(member);
//
//        member = new Member("high",Member.TYPE_NORMAL);
//        mMemberDAO.addMember(member);
//
//        member = new Member("룰루♡",Member.TYPE_NORMAL);
//        mMemberDAO.addMember(member);
//
//        member = new Member("꽁코",Member.TYPE_NORMAL);
//        mMemberDAO.addMember(member);
//
//        Record record;
//
//        record = new Record(0,1,1,0,1,0.5f,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,1,2,1,2,0.5f,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,1,3,5,0,1,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,1,4,1,5,0,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,1,5,3,2,0.5f,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,1,6,4,0,1,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,1,7,0,2,0,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,1,8,5,1,0.5f,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,1,9,4,0,1,0.5f);
//        mRecordDao.addRecord(record);
//        record = new Record(0,1,10,0,0,0,3);
//        mRecordDao.addRecord(record);
//
//        war = new War(1, "20150711", War.OUTCOME_WIN);
//        mWarDAO.addWar(war);
//
//        record = new Record(0,2,2,4,2,0.5f,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,2,3,5,0,1,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,2,4,2,0,0.5f,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,2,5,3,1,0.5f,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,2,7,0,5,0,0);
//        mRecordDao.addRecord(record);
//        record = new Record(0,2,8,6,0,1,0);
//        mRecordDao.addRecord(record);
//
//        member = new Member("nomute",Member.TYPE_ELDER);
//        mMemberDAO.addMember(member);
//
//        record = new Record(0,2,11,0,3,0,0);
//        mRecordDao.addRecord(record);
//
//        record = new Record(0,2,9,3,0,0.5f,0);
//        mRecordDao.addRecord(record);
//
//        member = new Member("wonsuc(부)",Member.TYPE_LEADER);
//        mMemberDAO.addMember(member);
//
//        record = new Record(0,2,12,6,0,1,0.5f);
//        mRecordDao.addRecord(record);
//
//        record = new Record(0,2,10,0,1,0,3);
//        mRecordDao.addRecord(record);

//        War war = new War(1, "20150726", War.OUTCOME_WIN);
//        mWarDAO.addWar(war);

          // 시즌1 - 3회

//        Record record = new Record(0, 3, 미니주니2, 3, 1, 0.5f, 0);
//        mRecordDao.addRecord(record);
//
//        record = new Record(0, 3, high, 3, 1, 0.5f, 0);
//        mRecordDao.addRecord(record);
//
//        record = new Record(0, 3, Hwan5, 2, 1, 0.5f, 0);
//        mRecordDao.addRecord(record);
//
//        Member member = new Member("랄라♡", Member.TYPE_NORMAL);
//        mMemberDAO.addMember(member);
//
//        record = new Record(0, 3, 랄라, 2, 0, 1f, 0);
//        mRecordDao.addRecord(record);
//
//        record = new Record(0, 3, nomute, 2, 1, 0, 0);
//        mRecordDao.addRecord(record);
//
//        record = new Record(0, 3, 룰루, 6, 0, 1f, 0);
//        mRecordDao.addRecord(record);
//
//        record = new Record(0, 3, wonsuc_부, 0, 4, 0, 0);
//        mRecordDao.addRecord(record);
//
//        member = new Member("신사펭귄", Member.TYPE_NORMAL);
//        mMemberDAO.addMember(member);
//
//        record = new Record(0, 3, 신사펭귄, 3, 0, 1f, 0);
//        mRecordDao.addRecord(record);
//
//        member = new Member("asabbing", Member.TYPE_NORMAL);
//        mMemberDAO.addMember(member);
//
//        record = new Record(0, 3, asabbing, 0, 3, 0, 0.5f);
//        mRecordDao.addRecord(record);
//
//        member = new Member("지상현", Member.TYPE_NORMAL);
//        mMemberDAO.addMember(member);
//
//        record = new Record(0, 3, 지상현, 3, 0, 0.5f, 3);
//        mRecordDao.addRecord(record);

        ArrayList<Season> seasonList = mSeasonDAO.getAllSeasons();
        ArrayList<War> warList = mWarDAO.getAllWars();
        ArrayList<Record> recordList = mRecordDao.getAllRecords();
        ArrayList<RecordRank> recordRankList = mRecordDao.getRecordRank(1);
        ArrayList<Member> memberList = mMemberDAO.getAllMembers();

        adapter.addAll(memberList);
    }
}
