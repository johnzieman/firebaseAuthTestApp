package ziemansoft.ziemapp.chattestapp;

import android.app.Application;

import ziemansoft.ziemapp.chattestapp.di.components.AppComponent;
import ziemansoft.ziemapp.chattestapp.di.components.DaggerAppComponent;

public class App extends Application {
    private AppComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent
                .builder()
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
