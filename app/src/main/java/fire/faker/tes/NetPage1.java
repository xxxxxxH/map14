package fire.faker.tes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class NetPage1 extends BasicsPage implements NetCallback{

    private RecyclerView recycler;

    @Override
    int getLayoutId() {
        return R.layout.layout_net1;
    }

    @Override
    void initView() {
        recycler = findViewById(R.id.recycler);
        CommonUtils.getInstance().getNet1Data(this);
    }

    @Override
    void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onSuccess(String s) {
        Type type = new TypeToken<Map<String,DataEntity>>(){}.getType();
        Map<String,DataEntity>  map = new Gson().fromJson(s, type);
        ArrayList<DataEntity> list = new ArrayList<>(map.values());
        ArrayList<String> keys = new ArrayList<>(map.keySet());
        for (int i = 0;i<list.size();i++){
            DataEntity entity = list.get(i);
            entity.setKey(keys.get(i));
        }
        Adapter1 adapter1 = new Adapter1(R.layout.layout_item1, list);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter1);
        adapter1.setOnItemClickListener((adapter, view, position) -> {
            Intent i = new Intent(this, NetPage2.class);
            DataEntity dataEntity = list.get(position);
            i.putExtra("data", dataEntity);
            startActivity(i);
        });
    }


    @Override
    public void onError() {

    }
}
