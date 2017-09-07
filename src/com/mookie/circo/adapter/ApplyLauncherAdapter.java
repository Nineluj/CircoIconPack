package com.mookie.circo.adapter;

import java.util.List;

import com.mookie.circo.R;
import com.mookie.circo.util.*;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplyLauncherAdapter extends BaseAdapter
{
    private ColorMatrixColorFilter grayscaleFilter;
    private Context context;
    private List<Integer> gridItem;
    private Resources res;

    // Flag Constants
    public static final int APEX = 0;
    public static final int NOVA = 1;
    public static final int AVIATE = 2;
    public static final int ADW = 3;
    public static final int ACTION = 4;
    public static final int SMART = 5;
    public static final int NEXT = 6;
    public static final int GO = 7;
    public static final int HOLO = 8;

    public ApplyLauncherAdapter(Context context, List<Integer> gridItem) {
        this.gridItem = gridItem;
        this.context = context;
        this.res = context.getResources();

        // Set up color filter
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0); //0 means grayscale
        this.grayscaleFilter = new ColorMatrixColorFilter(matrix);
    }

    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder;
        int entry = gridItem.get(position);

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.apply_launcher_layout, null);

            holder = new ViewHolder();
            holder.title = (TextView) v.findViewById(R.id.title);
            holder.launcher_Image = (ImageView) v.findViewById(R.id.list_image);
            holder.txtInstalled = (TextView) v.findViewById(R.id.txtInstalled);

            v.setTag(holder);
        }
        else
            holder = (ViewHolder) v.getTag();

        switch(entry)
        {
            case APEX:
                holder.title.setText(res.getString(R.string.launcher_apex));
                setInstalledStatus(holder, res.getString(R.string.launcher_apex_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.apex_banner);
                break;
            case NOVA:
                holder.title.setText(res.getString(R.string.launcher_nova));
                setInstalledStatus(holder, res.getString(R.string.launcher_nova_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.nova_banner);
                break;
            case AVIATE:
                holder.title.setText(res.getString(R.string.launcher_aviate));
                setInstalledStatus(holder, res.getString(R.string.launcher_aviate_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.aviate_banner);
                break;
            case ADW:
                holder.title.setText(res.getString(R.string.launcher_adw));
                setInstalledStatus(holder, res.getString(R.string.launcher_adw_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.adw_banner);
                break;
            case ACTION:
                holder.title.setText(res.getString(R.string.launcher_al));
                setInstalledStatus(holder, res.getString(R.string.launcher_al_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.action_banner);
                break;
            case SMART:
                holder.title.setText(res.getString(R.string.launcher_smart));
                setInstalledStatus(holder, res.getString(R.string.launcher_smart_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.smart_banner);
                break;
            case NEXT:
                holder.title.setText(res.getString(R.string.launcher_next));
                setInstalledStatus(holder, res.getString(R.string.not_supported));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.next_banner);
                break;
            case GO:
                holder.title.setText(res.getString(R.string.launcher_go));
                setInstalledStatus(holder, res.getString(R.string.not_supported));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.go_banner);
                break;
            case HOLO:
                holder.title.setText(res.getString(R.string.launcher_holo));
                setInstalledStatus(holder, res.getString(R.string.launcher_holo_package));
                holder.title.setTextColor(res.getColor(R.color.white));
                holder.launcher_Image.setImageResource(R.mipmap.holo_banner);
                break;
        }

        return v;
    }

    private void setInstalledStatus(ViewHolder holder, String packageName)
    {
        // Set installed status
        if(Utils.isPackageInstalled(packageName, context))
        {
            holder.txtInstalled.setText(res.getString(R.string.installed));
            holder.txtInstalled.setTextColor(res.getColor(R.color.icon_green));
            holder.launcher_Image.clearColorFilter();
        }
        else if (packageName.equals(res.getString(R.string.not_supported))) {
            holder.txtInstalled.setText(res.getString(R.string.not_supported));
            holder.txtInstalled.setTextColor(res.getColor(R.color.icon_red));
            holder.launcher_Image.setColorFilter(grayscaleFilter);
        }
        else
        {
            holder.txtInstalled.setText(res.getString(R.string.not_installed));
            holder.txtInstalled.setTextColor(res.getColor(R.color.icon_red));
            holder.launcher_Image.setColorFilter(grayscaleFilter);
        }
    }

    @Override
    public int getCount() {
        return gridItem.size();
    }

    @Override
    public Integer getItem(int position) {
        return gridItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Code added for better efficiency and less redraws
    private class ViewHolder {
        public TextView title;
        public ImageView launcher_Image;
        public TextView txtInstalled;
    }
}