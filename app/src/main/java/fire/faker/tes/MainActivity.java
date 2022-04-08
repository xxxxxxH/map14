package fire.faker.tes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;

@SuppressLint({"NonConstantResourceId", "Lifecycle"})
public class MainActivity extends BasicsPage {

    private MapView mapView;
    private ImageView menu;
    private DrawerLayout drawerLayout;
    private NavigationView nv;

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    void initView() {
        mapView = findViewById(R.id.mapView);
        menu = findViewById(R.id.menu);
        drawerLayout = findViewById(R.id.drawerLayout);
        nv = findViewById(R.id.nv);
        setLocalLocation(mapView);
        MapUtils.loadStyle(mapView, Style.OUTDOORS);
        menu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        nv.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.outDoor:
                    MapUtils.loadStyle(mapView, Style.OUTDOORS);
                    break;
                case R.id.satellite:
                    MapUtils.loadStyle(mapView, Style.SATELLITE);
                    break;
                case R.id.light:
                    MapUtils.loadStyle(mapView, Style.LIGHT);
                    break;
                case R.id.traffic:
                    MapUtils.loadStyle(mapView, Style.TRAFFIC_DAY);
                    break;
                case R.id.nearby:

                    break;
                case R.id.inter:
                    startActivity(new Intent(this, NetPage1.class));
                    break;
                case R.id.search:
                    startActivity(new Intent(this, SearchPage.class));
                    break;
                case R.id.rate:
                    CommonUtils.getInstance().rateUs(this);
                    break;
                case R.id.share:
                    CommonUtils.getInstance().share(this);
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

    }

    @Override
    void initData(Bundle savedInstanceState) {

    }

    @Override
    public void positionChanged(Point point) {
        super.positionChanged(point);
        MapUtils.setCurrentLocation(mapView);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}