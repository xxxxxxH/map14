package fire.faker.tes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.hjq.permissions.XXPermissions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mapbox.geojson.Point;
import com.mapbox.search.MapboxSearchSdk;
import com.mapbox.search.SearchEngine;
import com.mapbox.search.SearchOptions;
import com.mapbox.search.ui.view.SearchBottomSheetView;
import com.mapbox.search.ui.view.SearchBottomSheetView.Configuration;
import com.roger.catloadinglibrary.CatLoadingView;
import java.util.function.Consumer;

public class CommonUtils {

    private static CommonUtils instance = null;

    private CommonUtils() {
    }

    public static CommonUtils getInstance() {
        if (instance == null) {
            instance = new CommonUtils();
        }
        return instance;
    }

    public void getPermissions(Context context, Consumer<Boolean> consumer) {
        XXPermissions.with(context).permission(Manifest.permission.ACCESS_FINE_LOCATION)
                .request((permissions, all) -> consumer.accept(all));
    }

    public void share(Context context) {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Music Player");
            String aux = context.getResources().getString(R.string.share_app_msg)
                    + "https://play.google.com/store/apps/details?id=" + context.getPackageName();
            i.putExtra(Intent.EXTRA_TEXT, aux);
            context.startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rateUs(Context context) {
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName()));
        context.startActivity(i);
    }

    public void initBottomSheet(Context context, SearchBottomSheetView sheetView, Bundle savedInstanceState,
            CatLoadingView loading, ICallback callback) {
        SearchEngine searchEngine = MapboxSearchSdk.getSearchEngine();
        sheetView.initializeSearch(savedInstanceState, new Configuration());
        sheetView.addOnCategoryClickListener(category -> {
            loading.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            searchEngine.search(category.getGeocodingCanonicalName(), new SearchOptions(), callback);
        });
        sheetView.addOnFavoriteClickListener(favoriteRecord -> {
            loading.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            startResultPage(context, favoriteRecord.getCoordinate());
        });
        sheetView.addOnHistoryClickListener(historyRecord -> {
            loading.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            if (historyRecord.getCoordinate() != null) {
                startResultPage(context, historyRecord.getCoordinate());
            } else {
                searchEngine.search(historyRecord.getName(), new SearchOptions(), callback);
            }
        });
        sheetView.addOnSearchResultClickListener((searchResult, responseInfo) -> {
            loading.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            if (searchResult.getCoordinate() != null) {
                startResultPage(context, searchResult.getCoordinate());
            } else {
                searchEngine.search(searchResult.getName(), new SearchOptions(), callback);
            }
        });
    }

    public void startResultPage(Context context, Point it) {
        Intent i = new Intent(context, SearchResultPage.class);
        i.putExtra("lat", it.latitude());
        i.putExtra("lng", it.longitude());
        context.startActivity(i);
    }

    public void getNet1Data(NetCallback callback) {
        String url = "https://www.google.com/streetview/feed/gallery/data.json";
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                callback.onSuccess(TextUtils.isEmpty(result) ? "" : result);
            }

            @Override
            public void onError(Response<String> response) {
                callback.onError();
            }
        });
    }

    public void getNet2Data(NetCallback callback, String url) {
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                callback.onSuccess(TextUtils.isEmpty(result) ? "" : result);
            }

            @Override
            public void onError(Response<String> response) {
                callback.onError();
            }
        });
    }

    public void showToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
