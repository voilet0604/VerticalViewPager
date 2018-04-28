package cn.lei.verticalviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Fragment01 extends Fragment {

    private RecyclerView mListItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_01, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(view.getContext().getResources().getColor(android.R.color.white));
        mListItem = view.findViewById(R.id.list_item);
        mListItem.setNestedScrollingEnabled(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListItem.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListItem.setAdapter(new SimpleAdapter(getString()));
    }

    private List<String> getString() {
        List<String> strings = new ArrayList<>();

        for(int i = 0; i < 40; i++) {
            strings.add("i" + (i+1));
        }
        return strings;
    }

    private class SimpleAdapter extends RecyclerView.Adapter {

        private List<String> items;

        public SimpleAdapter(List<String> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((Holder)holder).refresh(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class Holder extends RecyclerView.ViewHolder {

            TextView mText;
            public Holder(View itemView) {
                super(itemView);
                mText = itemView.findViewById(R.id.text2);
            }

            public void refresh(String text) {
                mText.setText(text);
            }
        }
    }
}
