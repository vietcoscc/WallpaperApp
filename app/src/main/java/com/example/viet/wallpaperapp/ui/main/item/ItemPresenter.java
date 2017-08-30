package com.example.viet.wallpaperapp.ui.main.item;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.viet.wallpaperapp.R;
import com.example.viet.wallpaperapp.api.WallpaperApi;
import com.example.viet.wallpaperapp.base.BasePresenter;
import com.example.viet.wallpaperapp.model.Image;
import com.example.viet.wallpaperapp.model.ImageList;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by viet on 30/08/2017.
 */

public class ItemPresenter<V extends ItemMvpView> extends BasePresenter<V> implements ItemMvpPresenter<V> {
    private static final String TAG = "ItemPresenter";
    private Retrofit mRetrofit;

    @Inject
    public ItemPresenter(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    @Override
    public void getLastest(final ProgressDialog dialog) {
        getmMvpView().showProgress(dialog);
        Call<ImageList> call = mRetrofit.create(WallpaperApi.class).getLastest();
        call.enqueue(new Callback<ImageList>() {
            @Override
            public void onResponse(Call<ImageList> call, Response<ImageList> response) {
                getmMvpView().hideProgress(dialog);
                ImageList imageList = response.body();
                getmMvpView().displayImage(imageList.getArrImage());
            }

            @Override
            public void onFailure(Call<ImageList> call, Throwable t) {
                getmMvpView().hideProgress(dialog);
            }
        });
    }

    @Override
    public void getCategoryItem(final ProgressDialog dialog, String catId) {
        getmMvpView().showProgress(dialog);
        Call<ImageList> call = mRetrofit.create(WallpaperApi.class).getCategoryItem(catId);
        call.enqueue(new Callback<ImageList>() {
            @Override
            public void onResponse(Call<ImageList> call, Response<ImageList> response) {
                getmMvpView().hideProgress(dialog);
                ImageList imageList = response.body();
                getmMvpView().displayImage(imageList.getArrImage());
            }

            @Override
            public void onFailure(Call<ImageList> call, Throwable t) {
                getmMvpView().hideProgress(dialog);
            }
        });
    }

    @Override
    public void createImageViewer(final Context context, View view, int position, ArrayList<Image> arrImage) {
        GenericDraweeHierarchyBuilder hierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(context.getResources())
                .setFailureImage(android.R.drawable.screen_background_light)
                .setProgressBarImage(android.R.drawable.screen_background_dark_transparent)
                .setPlaceholderImage(android.R.drawable.screen_background_dark);
        View v = LayoutInflater.from(context).inflate(R.layout.view_image_viewer_overlay, (ViewGroup) view.getParent(), false);
        ImageView ivBack = v.findViewById(R.id.ivBack);
        ImageViewer.Formatter<Image> formatter = new ImageViewer.Formatter<Image>() {
            @Override
            public String format(Image image) {
                return image.getWallpaperImage();
            }
        };
        final ImageViewer imageViewer = new ImageViewer.Builder(context, arrImage)
                .setFormatter(formatter)
                .hideStatusBar(false)
                .setStartPosition(position)
                .setCustomDraweeHierarchyBuilder(hierarchyBuilder)
                .setOverlayView(v)
                .build();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageViewer.onDismiss();
                    }
                }, 500);

            }
        });

        getmMvpView().displayImageViewer(imageViewer);
    }

    @Override
    public void createPopupMenu(final Context context, final View view, final int position, final ArrayList<Image> arrImage) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_item_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.action_open) {
                    createImageViewer(context, view, position, arrImage);
                } else if (id == R.id.action_save) {
                    Image image = arrImage.get(position);
                    String path = Environment.getExternalStorageDirectory().getPath()
                            + "/" + Environment.DIRECTORY_DCIM + "/" + image.getCatId() + image.getId() + ".png";
                    getDownloadObservable(image.getWallpaperImage(), path)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Boolean>() {
                                @Override
                                public void accept(Boolean isSuccessful) throws Exception {
                                    if (isSuccessful) {
                                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else if (id == R.id.action_set_as_background) {
                    final ProgressDialog dialog = new ProgressDialog(context);
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.setMessage("Setting...");
                    Image image = arrImage.get(position);
                    getImageBitmapObservable(image.getWallpaperImage())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Bitmap>() {
                                @Override
                                public void accept(Bitmap bitmap) {
                                    try {
                                        WallpaperManager manager = WallpaperManager.getInstance(context.getApplicationContext());
                                        manager.setBitmap(bitmap);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        dialog.dismiss();
                                    }
                                    dialog.dismiss();
                                }
                            });
                }
                return false;
            }
        });
        getmMvpView().displayPopupMenu(popupMenu);
    }

    private Observable<Boolean> getDownloadObservable(final String urlImage, final String path) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    URL url = new URL(urlImage);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    File file = new File(path);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream outputStream = new FileOutputStream(file);
                    byte b[] = new byte[1024];
                    int count = inputStream.read(b);
                    while (count != -1) {
                        outputStream.write(b, 0, count);
                        count = inputStream.read(b);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });
    }

    private Observable<Bitmap> getImageBitmapObservable(final String urlImage) {
        return Observable.fromCallable(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                URL url = new URL(urlImage);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitMap = BitmapFactory.decodeStream(inputStream);
                return bitMap;
            }
        });
    }
}
