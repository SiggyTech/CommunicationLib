package com.siggytech.utils.communication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;


public class CustomAdapterBubble extends BaseAdapter {

    private List<ChatModel> lstChat;
    private Context context;
    private LayoutInflater inflater;

    public CustomAdapterBubble(List<ChatModel> lstChat, Context context) {
        this.lstChat = lstChat;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lstChat.size();
    }

    @Override
    public Object getItem(int position) {
        return lstChat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        ViewHolder holder;

        if (convertView == null) {

            vi = inflater.inflate(R.layout.chat_cell_out, null);

            holder = new ViewHolder();

            holder.chat_out_from = vi.findViewById(R.id.chat_out_name);
            holder.chat_out_text = vi.findViewById(R.id.chat_out_text);
            holder.chat_text_datetime = vi.findViewById(R.id.chat_text_datetime);
            holder.lnAudio = vi.findViewById(R.id.lnAudio);
            holder.ivPlay = vi.findViewById(R.id.ivPlay);
            holder.sbPlay = vi.findViewById(R.id.sbPlay);
            //holder.image = vi.findViewById(R.id.cell_icon);

            vi.setTag(holder);

        } else holder = (ViewHolder)vi.getTag();

        holder.chat_out_from.setText(lstChat.get(position).getFromMessage());
        holder.chat_text_datetime.setText(lstChat.get(position).getDateTimeMessage());
        holder.chat_out_text.setText(lstChat.get(position).getTextMessage());
        if(!Utils.MESSAGE_TYPE.MESSAGE.equals(lstChat.get(position).getMessageType())) {
            holder.chat_out_text.setVisibility(View.GONE);
        }
        if(Utils.MESSAGE_TYPE.AUDIO.equals(lstChat.get(position).getMessageType())) {
            holder.lnAudio.setVisibility(View.VISIBLE);
        }else if(Utils.MESSAGE_TYPE.VIDEO.equals(lstChat.get(position).getMessageType())) {
            //TODO do staff
        } else if(Utils.MESSAGE_TYPE.PHOTO.equals(lstChat.get(position).getMessageType())) {
            //TODO do staff
        }
        holder.chat_out_from.setTextColor(Conf.CHAT_COLOR_FROM);
        holder.chat_out_text.setTextColor(Conf.CHAT_COLOR_TEXT);
        holder.chat_text_datetime.setTextColor(Conf.CHAT_COLOR_DATE);
        //holder.image.setImageResource(R.drawable.ic_launcher_round);

        holder.ivPlay.setTag(false);
        holder.ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((boolean)v.getTag())){
                    v.setTag(true);
                    ((ImageView)v).setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pause));

                    //TODO play audio
                }else{
                    v.setTag(false);
                    ((ImageView)v).setImageDrawable(context.getResources().getDrawable(R.drawable.ic_play_arrow));
                    //TODO stop audio
                }


            }
        });

        //holder.sbPlay.setMax();
        holder.sbPlay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("TAG","onProgressChanged");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("TAG","onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("TAG","onStopTrackingTouch");
            }
        });

        return vi;
    }

    private Bitmap getFromByteArray(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    class ViewHolder {
        TextView chat_out_from;
        TextView chat_out_text;
        TextView chat_text_datetime;
        LinearLayout lnAudio;
        ImageView ivPlay;
        SeekBar sbPlay;
    }

}
