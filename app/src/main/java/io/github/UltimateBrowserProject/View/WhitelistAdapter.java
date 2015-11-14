package io.github.UltimateBrowserProject.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import io.github.UltimateBrowserProject.Browser.AdBlock;
import io.github.UltimateBrowserProject.R;

public class WhitelistAdapter extends ArrayAdapter<String> {
    private Context context;
    private int layoutResId;
    private List<String> list;

    public WhitelistAdapter(Context context, int layoutResId, List<String> list){
        super(context, layoutResId, list);
        this.context = context;
        this.layoutResId = layoutResId;
        this.list = list;
    }

    private static class Holder {
        TextView domain;
        ImageButton cancel;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(layoutResId, parent, false);
            holder = new Holder();
            holder.domain = (TextView) view.findViewById(R.id.whitelist_item_domain);
            holder.cancel = (ImageButton) view.findViewById(R.id.whitelist_item_cancel);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.domain.setText(list.get(position));
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdBlock adBlock = new AdBlock(context);
                adBlock.removeDomain(list.get(position));
                list.remove(position);
                notifyDataSetChanged();
                UltimateBrowserProjectToast.show(context, R.string.toast_delete_successful);
            }
        });

        return view;
    }
}
