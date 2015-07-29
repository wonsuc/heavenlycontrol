package com.wonsuc.coc.heavenlycontrol.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.wonsuc.coc.heavenlycontrol.ui.adapter.RecordRankListAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    MemberDAO mMemberDAO;
    SeasonDAO mSeasonDAO;
    WarDAO mWarDAO;
    RecordDAO mRecordDao;

    RecordRankListAdapter adapter;

    @Bind(R.id.record_rank_list)
    ListView lvRecordRankList;

    @Bind(R.id.btnAdd)
    ImageButton btnAdd;

    @Bind(R.id.container)
    View vContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMemberDAO = new MemberDAO(this);
        mSeasonDAO = new SeasonDAO(this);
        mWarDAO = new WarDAO(this);
        mRecordDao = new RecordDAO(this);

        adapter = new RecordRankListAdapter(this, 0);

        lvRecordRankList.setAdapter(adapter);

        loadRecordRankList();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                takeScreenshot();

//                Intent intent = new Intent(MainActivity.this, MemberListActivity.class);
//                startActivity(intent);
                //overridePendingTransition(0, 0);
            }
        });
        btnAdd.setVisibility(View.VISIBLE);
    }

    public void takeScreenshot() {
        // getWindow().getDecorView().findViewById(android.R.id.content)
        lvRecordRankList.setDrawingCacheEnabled(true);
        Bitmap b = getBitmapFromView(vContainer);
        try {
            b.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "result.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    private void loadRecordRankList() {
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
        ArrayList<RecordRank> recordRankList = mRecordDao.getRecordRank(3);
        ArrayList<RecordRank> totalRecordRankList = mRecordDao.getTotalRecordRank(10, 10);
        ArrayList<Member> memberList = mMemberDAO.getAllMembers();

        adapter.addAll(totalRecordRankList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
