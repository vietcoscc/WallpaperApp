package com.example.viet.wallpaperapp.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.viet.wallpaperapp.MyApplication;
import com.example.viet.wallpaperapp.R;
import com.example.viet.wallpaperapp.adapter.CategoryRecyclerViewAdapter;
import com.example.viet.wallpaperapp.model.Category;
import com.example.viet.wallpaperapp.ui.main.item.ItemActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.viet.wallpaperapp.common.Constants.CATEGORY_ID;
import static com.example.viet.wallpaperapp.common.Constants.CATEGORY_SPAN_COUNT;

public class MainActivity extends AppCompatActivity implements MainMvpView {
    @Inject
    MainPresenter<MainMvpView> mMainPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CategoryRecyclerViewAdapter mAdapter;
    private ArrayList<Category> mArrCategory = new ArrayList<>();
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPresenter();
        iniViews();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Get category ");
        mMainPresenter.getCategory(mProgressDialog);
    }

    private void iniViews() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, CATEGORY_SPAN_COUNT));
        mAdapter = new CategoryRecyclerViewAdapter(mArrCategory);
        mAdapter.setmOnItemClickListener(new CategoryRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                intent.putExtra(CATEGORY_ID, mArrCategory.get(position).getcId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    private void initPresenter() {
        MyApplication application = (MyApplication) getApplication();
        application.getmActivityComponent().inject(this);
        mMainPresenter.onAttach(this);
    }

    @Override
    public void showProgress(ProgressDialog dialog) {
        dialog.show();
    }

    @Override
    public void hideProgress(ProgressDialog dialog) {
        dialog.dismiss();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMainPresenter.onDetach();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.onAttach(this);
    }

    @Override
    public void displayCategory(ArrayList<Category> arrCategory) {
        mArrCategory.addAll(arrCategory);
        Category category = new Category("0", "Lastest", "", "", "");
        mArrCategory.add(category);
        mAdapter.notifyDataSetChanged();
    }
}
