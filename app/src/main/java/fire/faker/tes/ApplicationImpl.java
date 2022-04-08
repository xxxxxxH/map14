package fire.faker.tes;

import android.app.Application;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.search.MapboxSearchSdk;

public class ApplicationImpl extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MapboxSearchSdk.initialize(
                this,
                getString(R.string.mapbox_access_token),
                LocationEngineProvider.getBestLocationEngine(this)
        );
    }
}
