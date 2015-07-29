package com.wonsuc.coc.heavenlycontrol.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wonsuc.coc.heavenlycontrol.R;
import com.wonsuc.coc.heavenlycontrol.model.Member;
import com.wonsuc.coc.heavenlycontrol.model.RecordRank;
import com.wonsuc.coc.heavenlycontrol.util.CommonUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecordRankListAdapter extends ArrayAdapter<RecordRank> {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<RecordRank> recordRankItems = new ArrayList<>();

    public RecordRankListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void add(RecordRank object) {
        super.add(object);
        recordRankItems.add(object);
    }

    @Override
    public void addAll(Collection<? extends RecordRank> collection) {
        super.addAll(collection);
        recordRankItems.addAll(collection);
    }

    @Override
    public RecordRank getItem(int position) {
        return recordRankItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecordRankViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_record_rank, parent, false);
            holder = new RecordRankViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RecordRankViewHolder) convertView.getTag();
        }

        RecordRank item = getItem(position);

        //holder.itemRank.setText((item.rank > 7) ? "7" : String.valueOf(item.rank));
        holder.itemRank.setText(String.valueOf(item.rank));
        holder.itemNick.setText(item.nickName);
        holder.itemHeavenlyStar.setText(CommonUtil.decimalFormat(item.heavenlyStar));
        holder.itemRealStar.setText(CommonUtil.decimalFormat(item.realStar));
        holder.itemDupStar.setText(CommonUtil.decimalFormat(item.dupStar));
        holder.itemAdvantage.setText(CommonUtil.decimalFormat(item.advantage));
        holder.itemBase.setText(CommonUtil.decimalFormat(item.base));

        return convertView;
    }

    // 순위, 닉네임, 인정별, 순수별, 중복별, 어드밴티지, 베이스
    public static class RecordRankViewHolder {
        @Bind(R.id.itemRank)
        TextView itemRank;
        @Bind(R.id.itemNick)
        TextView itemNick;
        @Bind(R.id.itemHeavenlyStar)
        TextView itemHeavenlyStar;
        @Bind(R.id.itemRealStar)
        TextView itemRealStar;
        @Bind(R.id.itemDupStar)
        TextView itemDupStar;
        @Bind(R.id.itemAdvantage)
        TextView itemAdvantage;
        @Bind(R.id.itemBase)
        TextView itemBase;

        public RecordRankViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
