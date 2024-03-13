package com.example.automaticgrocery.UI.ExpiredFragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.MyRecycleView.MyAdapter;
import com.example.automaticgrocery.data.Items.ExpiredItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpiredFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpiredFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private View view;

    private ExpiredModel expiredModel;

    public ExpiredFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpiredFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpiredFragment newInstance(String param1, String param2) {
        ExpiredFragment fragment = new ExpiredFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_expired, container, false);

        recyclerView = view.findViewById(R.id.recycleView);
        expiredModel = new ExpiredModel(getActivity());

        List<ExpiredItem> items = new ArrayList<ExpiredItem>();


        Cursor cursor = expiredModel.getProductsByCategory();
        cursor.moveToFirst();
        int l = cursor.getCount();
        for (int i = 0; i < l; i++)
        {
            //להוסיף בדיקה שהמוצר אכן לא בתוקף
            items.add(new ExpiredItem(cursor.getString(1),cursor.getString(5),cursor.getInt(3)));
            cursor.moveToNext();
        }






        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));
        items.add(new ExpiredItem("milk","24/8/26",50));
        items.add(new ExpiredItem("beef","1/2/80",800));
        items.add(new ExpiredItem("apple","18/7/06",12));







        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter(getActivity().getApplicationContext(),items));





        return view;
    }
}