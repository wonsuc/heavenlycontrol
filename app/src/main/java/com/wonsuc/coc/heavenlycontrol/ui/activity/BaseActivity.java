package com.wonsuc.coc.heavenlycontrol.ui.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wonsuc.coc.heavenlycontrol.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseActivity extends ActionBarActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private MenuItem menuItem;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setupToolbar();
    }

    protected void setupToolbar() {
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.toolbar_title));
            toolbar.setTitleTextColor(0xFFFFFFFF);
            setSupportActionBar(toolbar);
            //toolbar.setNavigationIcon(R.drawable.ic_menu_white);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

/*    @Override
    public void onBackPressed() {
        backButtonHandler();
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        // 다이얼로그의 타이틀
        alertDialog.setTitle("어플리케이션 종료");
        // 다이얼로그의 메세지
        alertDialog.setMessage("어플리케이션을 정말로 종료하시겠습니까?");
        // 다이얼로그에 아이콘을 설정하고 싶을 때는 다음을 추가한다.
        //alertDialog.setIcon(R.drawable.dialog_icon);
        // "네"를 터치했을 때.
        alertDialog.setPositiveButton("네",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        // "아니오"를 터치했을 때.
        alertDialog.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // 다이얼로그를 보여준다.
        alertDialog.show();
    }*/
}
