package oreschnix.exampleapp.networking.rest;

import android.content.res.Resources;

import com.squareup.okhttp.OkHttpClient;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Miha on 19.10.2015.
 */
public class OkHttpClientBuilder {

    public static final String BOUNCY_CASTLE = "BKS";

    protected int connectionTimeout;

    protected CookieHandler cookieHandler;

    protected KeyManager[] keymanagers;

    protected TrustManager[] trustManagers;

    protected X509HostnameVerifier hostnameVerifier;

    public OkHttpClientBuilder() {
        hostnameVerifier = SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;
    }


    public OkHttpClientBuilder setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    public OkHttpClientBuilder setCookieStore(CookieHandler cookieHandler) {
        this.cookieHandler = cookieHandler;
        return this;
    }

    public OkHttpClientBuilder pinServerCertificates(InputStream resourceStream, char[] password,
                                                     String keystoreType) throws KeyStoreException,
            CertificateException,
            NoSuchAlgorithmException, IOException,
            UnrecoverableKeyException, KeyManagementException {

        KeyStore keyStore = KeyStore.getInstance(keystoreType);
        keyStore.load(resourceStream, password);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        trustManagers = trustManagerFactory.getTrustManagers();

        hostnameVerifier = SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;

        return this;
    }

    public OkHttpClientBuilder pinServerCertificates(InputStream resourceStream, char[] password)
            throws KeyStoreException, CertificateException,
            NoSuchAlgorithmException, IOException,
            UnrecoverableKeyException, KeyManagementException {

        return pinServerCertificates(resourceStream, password, BOUNCY_CASTLE);
    }

    public OkHttpClientBuilder pinServerCertificates(Resources resources, int certificateRawResource, char[] password)
            throws CertificateException, NoSuchAlgorithmException,
            KeyStoreException, IOException,
            UnrecoverableKeyException, KeyManagementException {

        InputStream in = resources.openRawResource(certificateRawResource);
        return pinServerCertificates(in, password);
    }

    public OkHttpClientBuilder pinServerCertificates(Resources resources, int certificateRawResource,
                                                     char[] password, String keystoreType)
            throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException, IOException {

        InputStream in = resources.openRawResource(certificateRawResource);
        return pinServerCertificates(in, password, keystoreType);
    }

    public OkHttpClientBuilder trustAllServerCertificates() {
        X509TrustManager easyTrustManager = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // Oh, I am easy!
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // Oh, I am easy!
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

        };

        // Create a trust manager that does not validate certificate chains
        trustManagers = new TrustManager[]{
                easyTrustManager
        };

        hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

        return this;
    }

    public OkHttpClientBuilder pinClientCertificate(InputStream resourceStream, char[] password,
                                                    String keystoreType) throws KeyStoreException,
            NoSuchAlgorithmException, UnrecoverableKeyException, IOException, CertificateException {

        KeyStore keyStore = KeyStore.getInstance(keystoreType);
        keyStore.load(resourceStream, password);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        keymanagers = keyManagerFactory.getKeyManagers();

        return this;
    }

    public OkHttpClientBuilder pinClientCertificate(InputStream resourceStream, char[] password)
            throws KeyStoreException, NoSuchAlgorithmException,
            UnrecoverableKeyException, CertificateException,
            IOException, KeyManagementException {

        return pinClientCertificate(resourceStream, password, BOUNCY_CASTLE);
    }

    public OkHttpClientBuilder pinClientCertificate(Resources resources, int certificateRawResource,
                                                    char[] password, String keystoreType)
            throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
            IOException, CertificateException {

        InputStream in = resources.openRawResource(certificateRawResource);
        return pinClientCertificate(in, password, keystoreType);
    }

    public OkHttpClientBuilder pinClientCertificate(Resources resources, int certificateRawResource,
                                                    char[] password) throws CertificateException,
            UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException, IOException {

        InputStream in = resources.openRawResource(certificateRawResource);
        return pinClientCertificate(in, password);
    }

    public OkHttpClient build() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance(SSLSocketFactory.TLS);
        sslContext.init(keymanagers, trustManagers, null);

        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.setConnectTimeout(connectionTimeout, TimeUnit.MILLISECONDS);
//        okHttpClient.setCookieHandler(cookieHandler);
        okHttpClient.setHostnameVerifier(hostnameVerifier);
        okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());

        return okHttpClient;
    }

}