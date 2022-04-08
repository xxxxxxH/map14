package fire.faker.tes;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener;
import com.roger.catloadinglibrary.CatLoadingView;

abstract class BasicsPage extends AppCompatActivity {

    private final OnIndicatorPositionChangedListener listener = this::positionChanged;
    protected CatLoadingView loadingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        loadingView = new CatLoadingView();
        initView();
        initData(savedInstanceState);
    }

    abstract int getLayoutId();

    abstract void initView();

    abstract void initData(Bundle savedInstanceState);

    public void positionChanged(Point point) {

    }

    public void showLoading(){
        loadingView.show(this.getSupportFragmentManager(), "");
    }

    public void closeLoading(){
        loadingView.dismiss();
    }

    public void setLocalLocation(MapView mapView){
        MapUtils.setCurrentLocation(mapView);
        MapUtils.setCameraClick(mapView, (lng, lat) -> {
            MapUtils.moveMap(mapView, Point.fromLngLat(lng, lat));
            return null;
        });
    }
}
