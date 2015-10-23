package oreschnix.exampleapp.networking.modules;

import com.squareup.okhttp.OkHttpClient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import dagger.Module;
import dagger.Provides;
import oreschnix.exampleapp.networking.rest.OkHttpClientBuilder;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by Miha on 19.10.2015.
 */
@Module
public class UnsecuredClientModule {

    private static final int CONNECTION_TIMEOUT = 6000;

    @Provides
    public Client provideClient() {
        try {
            OkHttpClient client = new OkHttpClientBuilder()
                    .trustAllServerCertificates()
                    .setConnectionTimeout(CONNECTION_TIMEOUT)
                    .build();

            return new OkClient(client);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (KeyManagementException e) {
            e.printStackTrace();
            return null;
        }
    }

}
