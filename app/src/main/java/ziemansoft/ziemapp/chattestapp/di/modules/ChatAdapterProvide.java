package ziemansoft.ziemapp.chattestapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ziemansoft.ziemapp.chattestapp.adapters.ChatAdapter;

@Module
public class ChatAdapterProvide {

    @Provides
    @Singleton
    ChatAdapter provideAdapter(){
        return new ChatAdapter();
    }

}
