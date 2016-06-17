package com.example.yora.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.yora.activities.BaseActivity;
import com.example.yora.services.entities.ContactRequest;
import com.example.yora.services.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter {
    private List<Message> _messages;
    private List<ContactRequest> _contactRequests;
    private BaseActivity _activity;
    private LayoutInflater _inflator;
    private MainActivityListener _listener;

    public MainActivityAdapter(BaseActivity activity, MainActivityListener listener) {
        _activity = activity;
        _listener = listener;
        _inflator = _activity.getLayoutInflater();
        _messages = new ArrayList<>();
        _contactRequests = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return _messages;
    }

    public List<ContactRequest> getContactRequests() {
        return _contactRequests;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface MainActivityListener {
        void onMessageClicked(Message message);
        void onContactRequestClicked(ContactRequest request, int position);
    }
}
