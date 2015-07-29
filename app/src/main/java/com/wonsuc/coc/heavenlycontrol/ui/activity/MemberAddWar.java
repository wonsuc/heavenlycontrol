package com.wonsuc.coc.heavenlycontrol.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.wonsuc.coc.heavenlycontrol.R;
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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberAddWar extends ActionBarActivity {

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
        setContentView(R.layout.activity_add_member);
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

        /*Member member = new Member("wonsuc",Member.TYPE_LEADER,"아주 옛날");
        mMemberDAO.addMember(member);

        member = new Member("미니주니2",Member.TYPE_CO_LEADER,"아주 옛날");
        mMemberDAO.addMember(member);

        member = new Member("ㅎㅎㅎ",Member.TYPE_CO_LEADER,"아주 옛날");
        mMemberDAO.addMember(member);

        Season season = new Season("20150706");
        mSeasonDAO.addSeason(season);

        War war = new War(1, "20150706");
        mWarDAO.addWar(war);

        Record record = new Record(1,1,6,0,0.5f,0.5f);
        mRecordDao.addRecord(record);
        record = new Record(1,2,6,0,1,0);
        mRecordDao.addRecord(record);
        record = new Record(1,3,4,0,0,0);
        mRecordDao.addRecord(record);*/

        ArrayList<Season> seasonList = mSeasonDAO.getAllSeasons();
        ArrayList<War> warList = mWarDAO.getAllWars();
        ArrayList<Record> recordList = mRecordDao.getAllRecords();
        ArrayList<RecordRank> recordRankList = mRecordDao.getRecordRank(1);
        ArrayList<Member> memberList = mMemberDAO.getAllMembers();

        adapter.addAll(memberList);
    }
}
