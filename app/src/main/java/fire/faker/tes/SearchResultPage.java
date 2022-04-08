package fire.faker.tes;

import android.os.Bundle;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;

public class SearchResultPage extends BasicsPage {

    private Double lat = 0.0d;
    private Double lng = 0.0d;

    @Override
    int getLayoutId() {
        return R.layout.activity_search_result_page;
    }

    @Override
    void initView() {
        lat = getIntent().getDoubleExtra("lat", 0.0d);
        lng = getIntent().getDoubleExtra("lng", 0.0d);
        MapView mapView = findViewById(R.id.mapView);
        MapUtils.setCurrentLocation(mapView);
        MapUtils.addMarker(mapView, Point.fromLngLat(lng, lat));
        MapUtils.moveMap(mapView, Point.fromLngLat(lng, lat));
    }

    @Override
    void initData(Bundle savedInstanceState) {
    }
}