package fire.faker.tes;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import java.util.List;

public class Adapter2 extends BaseQuickAdapter<DataEntity, BaseViewHolder> {

    public Adapter2(int layoutResId, @Nullable List<DataEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, DataEntity dataEntity) {
        ImageView image = baseViewHolder.getView(R.id.image);
        TextView title = baseViewHolder.getView(R.id.title);
        String imageUrl = "https://geo0.ggpht.com/cbk?output=thumbnail&thumb=2&panoid=" + (
                TextUtils.isEmpty(dataEntity.getPanoid()) ? "LiAWseC5n46JieDt9Dkevw" : dataEntity.getPanoid());
        String titleString = TextUtils.isEmpty(dataEntity.getTitle()) ? "" : dataEntity.getTitle();
        Glide.with(image.getContext()).load(imageUrl).into(image);
        title.setText(titleString);
    }
}
