package ziemansoft.ziemapp.chattestapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ziemansoft.ziemapp.chattestapp.R;
import ziemansoft.ziemapp.chattestapp.pojo.Message;

public class ChatAdapter  extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder>{
    private List<Message> list = new ArrayList<>();

    public void setList(List<Message> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = list.get(position);
        holder.textViewAuthor.setText(message.getAuthor());
        holder.textViewMessage.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewAuthor;
        private TextView textViewMessage;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewMessage = itemView.findViewById(R.id.textViewMessasge);
        }
    }
}
