package ziemansoft.ziemapp.chattestapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import ziemansoft.ziemapp.chattestapp.MainActivity;
import ziemansoft.ziemapp.chattestapp.adapters.ChatAdapter;
import ziemansoft.ziemapp.chattestapp.di.modules.ChatAdapterProvide;

@Component(modules = ChatAdapterProvide.class)
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
