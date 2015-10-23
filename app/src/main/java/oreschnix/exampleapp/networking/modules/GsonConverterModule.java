package oreschnix.exampleapp.networking.modules;

import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Created by Miha on 19.10.2015.
 */
@Module
public class GsonConverterModule {

    @Provides
    public Converter provideConverter() {
        return new GsonConverter(new GsonBuilder().create());
    }

}
