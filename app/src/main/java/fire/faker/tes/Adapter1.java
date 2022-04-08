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

public class Adapter1 extends BaseQuickAdapter<DataEntity, BaseViewHolder> {

    public Adapter1(int layoutResId, @Nullable List<DataEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, DataEntity dataEntity) {
        String imgUrl = "https://geo0.ggpht.com/cbk?output=thumbnail&thumb=2&panoid=" + (
                TextUtils.isEmpty(dataEntity.getPanoid()) ? "LiAWseC5n46JieDt9Dkevw" : dataEntity.getPanoid());
        ImageView imageView = baseViewHolder.getView(R.id.image);
        TextView textView = baseViewHolder.getView(R.id.textview);
        TextView title = baseViewHolder.getView(R.id.title);
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
        String titleString = TextUtils.isEmpty(dataEntity.getTitle()) ? "" : dataEntity.getTitle();
        String desc = TextUtils.isEmpty(dataEntity.getDescription()) ? titleString : dataEntity.getDescription();
        textView.setText(desc);
        title.setText(titleString);
    }
}
