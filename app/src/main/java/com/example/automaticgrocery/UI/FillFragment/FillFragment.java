package com.example.automaticgrocery.UI.FillFragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.automaticgrocery.R;
import com.example.automaticgrocery.UI.MyRecycleView.MyFillAdapter;
import com.example.automaticgrocery.data.Items.FillItem;
import com.example.automaticgrocery.data.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FillFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recycleView2;
    private View view;
    private FillModel fillModel;

    public FillFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FillFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FillFragment newInstance(String param1, String param2) {
        FillFragment fragment = new FillFragment();
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
        view = inflater.inflate(R.layout.fragment_fill, container, false);

        recycleView2 = view.findViewById(R.id.recycleView2);
        fillModel = new FillModel(getActivity());

        List<FillItem> items = new ArrayList<>();

        Cursor cursor = fillModel.getProductsByCategory();

        if(cursor != null)
        {
            int l = cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < l; i++)
            {
                if (fillModel.isFillNeeded(cursor.getInt(7),cursor.getInt(3)))
                    items.add(new FillItem(cursor.getString(1),cursor.getString(0),cursor.getInt(3),cursor.getInt(7)));

                cursor.moveToNext();
            }
        }

//        items.add(new FillItem("milk","11111111",50,200));
//        items.add(new FillItem("beef","22222222",100,300));
//        items.add(new FillItem("apple","33333333",200,400));
//        items.add(new FillItem("bamba","44444444",400,500));
//        items.add(new FillItem("milk","11111111",50,200));
//        items.add(new FillItem("beef","22222222",100,300));
//        items.add(new FillItem("apple","33333333",200,400));
//        items.add(new FillItem("bamba","44444444",400,500));
//        items.add(new FillItem("milk","11111111",50,200));
//        items.add(new FillItem("beef","22222222",100,300));
//        items.add(new FillItem("apple","33333333",200,400));
//        items.add(new FillItem("bamba","44444444",400,500));
//        items.add(new FillItem("milk","11111111",50,200));
//        items.add(new FillItem("beef","22222222",100,300));
//        items.add(new FillItem("apple","33333333",200,400));
//        items.add(new FillItem("bamba","44444444",400,500));
//        items.add(new FillItem("apple","33333333",200,400));
//        items.add(new FillItem("bamba","44444444",400,500));
//        items.add(new FillItem("milk","11111111",50,200));
//        items.add(new FillItem("beef","22222222",100,300));
//        items.add(new FillItem("apple","33333333",200,400));
//        items.add(new FillItem("bamba","44444444",400,500));
        //items.add(new FillItem("bamba","44444444",400));


        recycleView2.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recycleView2.setAdapter(new MyFillAdapter(getActivity().getApplicationContext(),items));


        return view;
    }
}