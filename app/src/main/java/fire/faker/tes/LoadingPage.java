package fire.faker.tes;

import android.content.Intent;
import android.os.Bundle;

public class LoadingPage extends BasicsPage{

    @Override
    int getLayoutId() {
        return R.layout.layout_loading;
    }

    @Override
    void initView() {
        CommonUtils.getInstance().getPermissions(this, result -> {
            if (result){
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }else {
                CommonUtils.getInstance().showToast(this, "no permisson app will close");
                finish();
            }
        });
    }

    @Override
    void initData(Bundle savedInstanceState) {

    }
}
