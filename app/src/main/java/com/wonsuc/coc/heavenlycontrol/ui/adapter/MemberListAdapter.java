package com.wonsuc.coc.heavenlycontrol.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wonsuc.coc.heavenlycontrol.R;
import com.wonsuc.coc.heavenlycontrol.model.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberListAdapter extends ArrayAdapter<Member> {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<Member> memberItems = new ArrayList<>();

    public MemberListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void add(Member object) {
        super.add(object);
        memberItems.add(object);
    }

    @Override
    public void addAll(Collection<? extends Member> collection) {
        super.addAll(collection);
        memberItems.addAll(collection);
    }

    @Override
    public Member getItem(int position) {
        return memberItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MemberViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_member, parent, false);
            holder = new MemberViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MemberViewHolder) convertView.getTag();
        }

        Member item = getItem(position);

        holder.itemNick.setText(item.nickName);
        holder.itemID.setText(String.valueOf(item.id));

        return convertView;
    }

    public static class MemberViewHolder {
        @Bind(R.id.itemNick)
        TextView itemNick;
        @Bind(R.id.itemID)
        TextView itemID;

        public MemberViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
