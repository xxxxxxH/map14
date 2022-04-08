package fire.faker.tes;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.mapbox.search.MapboxSearchSdk;
import com.mapbox.search.ResponseInfo;
import com.mapbox.search.SearchEngine;
import com.mapbox.search.result.SearchResult;
import com.mapbox.search.result.SearchSuggestion;
import com.mapbox.search.ui.view.SearchBottomSheetView;
import java.util.List;

public class SearchPage extends BasicsPage implements ICallback {

    private SearchBottomSheetView bottomView;

    private SearchEngine searchEngine;

    @Override
    int getLayoutId() {
        return R.layout.activity_search_page;
    }

    @Override
    void initView() {
    }

    @Override
    void initData(Bundle savedInstanceState) {
        bottomView = findViewById(R.id.bottomView);
        searchEngine = MapboxSearchSdk.getSearchEngine();
        CommonUtils.getInstance().initBottomSheet(this, bottomView, savedInstanceState, loadingView, this);
    }


    @Override
    public void onResult(@NonNull List<? extends SearchSuggestion> list, @NonNull List<? extends SearchResult> list1,
            @NonNull ResponseInfo responseInfo) {
        closeLoading();
        if (list1.size() > 0) {
            if (list1.get(0).getCoordinate() != null) {
                CommonUtils.getInstance().startResultPage(this, list1.get(0).getCoordinate());
            } else {
                CommonUtils.getInstance().showToast(this, "No suggestions");
            }
        }
    }

    @Override
    public void onCategoryResult(@NonNull SearchSuggestion searchSuggestion, @NonNull List<? extends SearchResult> list,
            @NonNull ResponseInfo responseInfo) {
        closeLoading();
    }

    @Override
    public void onResult(@NonNull SearchSuggestion searchSuggestion, @NonNull SearchResult searchResult,
            @NonNull ResponseInfo responseInfo) {
        closeLoading();
    }

    @Override
    public void onError(@NonNull Exception e) {
        closeLoading();
    }

    @Override
    public void onSuggestions(@NonNull List<? extends SearchSuggestion> list, @NonNull ResponseInfo responseInfo) {
        closeLoading();
        if (list.size() > 0) {
            searchEngine.select(list, this);
        } else {
            CommonUtils.getInstance().showToast(this, "No suggestions");
        }
    }

}