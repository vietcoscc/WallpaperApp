package com.example.viet.wallpaperapp.ui.main.item;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.viet.wallpaperapp.MyApplication;
import com.example.viet.wallpaperapp.R;
import com.example.viet.wallpaperapp.adapter.ImageRecyclerViewAdapter;
import com.example.viet.wallpaperapp.model.Image;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.viet.wallpaperapp.common.Constants.CATEGORY_ID;
import static com.example.viet.wallpaperapp.common.Constants.IMAGE_SPAN_COUNT;

public class ItemActivity extends AppCompatActivity implements ItemMvpView {
    @Inject
    ItemPresenter<ItemMvpView> mItemPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ProgressDialog mProgressDiaglog;
    private String mCId;
    private ImageRecyclerViewAdapter mAdapter;
    private ArrayList<Image> mArrImage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        getExtras();
        initPresenter();
        initViews();
        initProgress();
        if (!mCId.equals("0")) {
            mItemPresenter.getCategoryItem(mProgressDiaglog, mCId);
        } else {
            mItemPresenter.getLastest(mProgressDiaglog);
        }
    }

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, IMAGE_SPAN_COUNT));
        mAdapter = new ImageRecyclerViewAdapter(mArrImage);
        mAdapter.setmOnItemClickListener(new ImageRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                mItemPresenter.createImageViewer(ItemActivity.this, view, position, mArrImage);
            }
        });
        mAdapter.setmOnItemLongClickListener(new ImageRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(View view, int position) {
                mItemPresenter.createPopupMenu(ItemActivity.this, view, position, mArrImage);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    private void initPresenter() {
        MyApplication application = (MyApplication) getApplication();
        application.getmActivityComponent().inject(this);
        mItemPresenter.onAttach(this);
    }

    private void getExtras() {
        mCId = getIntent().getExtras().getString(CATEGORY_ID);
    }

    private void initProgress() {
        mProgressDiaglog = new ProgressDialog(this);
        mProgressDiaglog.setMessage("Get images ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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
    public void displayImage(ArrayList<Image> arrImage) {
        mArrImage.addAll(arrImage);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayImageViewer(ImageViewer imageViewer) {
        imageViewer.show();
    }

    @Override
    public void displayPopupMenu(PopupMenu popupMenu) {
        popupMenu.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mItemPresenter.onDetach();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mItemPresenter.onAttach(this);
    }
}
