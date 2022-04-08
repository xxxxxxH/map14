package fire.faker.tes;


import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class NetPage2 extends BasicsPage implements NetCallback {

    private RecyclerView recycler;
    private MapView mapView;

    @Override
    int getLayoutId() {
        return R.layout.layout_net2;
    }

    @Override
    void initView() {
        showLoading();
        DataEntity dataEntity = (DataEntity) getIntent().getSerializableExtra("data");
        String url = "https://www.google.com/streetview/feed/gallery/collection/" + dataEntity.getKey() + ".json";
        mapView = findViewById(R.id.mapView);
        recycler = findViewById(R.id.recycler);
        CommonUtils.getInstance().getNet2Data(this, url);
    }

    @Override
    void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onSuccess(String s) {
        Type type = new TypeToken<Map<String, DataEntity>>() {
        }.getType();
        Map<String, DataEntity> map = new Gson().fromJson(s, type);
        ArrayList<DataEntity> list = new ArrayList<>(map.values());
        ArrayList<String> keys = new ArrayList<>(map.keySet());
        for (int i = 0; i < list.size(); i++) {
            DataEntity entity = list.get(i);
            entity.setKey(keys.get(i));
        }
        Adapter2 adapter2 = new Adapter2(R.layout.layout_item2, list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter2);
        adapter2.setOnItemClickListener((adapter, view, position) -> {
            DataEntity entity = list.get(position);
            MapUtils.moveMap(mapView,
                    Point.fromLngLat(Double.parseDouble(entity.getLng()), Double.parseDouble(entity.getLat())));
            MapUtils.addMarker(mapView,
                    Point.fromLngLat(Double.parseDouble(entity.getLng()), Double.parseDouble(entity.getLat())));
        });
        closeLoading();
    }

    @Override
    public void onError() {
        closeLoading();
        CommonUtils.getInstance().showToast(this,"no data");
        finish();
    }
}
