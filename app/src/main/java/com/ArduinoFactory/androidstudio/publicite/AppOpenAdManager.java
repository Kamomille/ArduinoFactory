package com.ArduinoFactory.androidstudio.publicite;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.appopen.AppOpenAd;

public class AppOpenAdManager {

    // ✅ Remplace par ton ID de pub en production
    private static final String AD_UNIT_ID = "ca-app-pub-6767741813773671/9395400712";

    private AppOpenAd appOpenAd = null;
    private boolean isLoadingAd = false;
    private boolean isShowingAd = false;

    private final Application application;

    // ✅ Compteur et limite
    private int adDisplayCount = 0;
    private static final int MAX_ADS_PER_SESSION = 1;

    public AppOpenAdManager(Application application) {
        this.application = application;
        loadAd();
    }

    public void loadAd() {
        if (isLoadingAd || isAdAvailable()) return;

        isLoadingAd = true;

        AppOpenAd.load(
                application,
                AD_UNIT_ID,
                new AdRequest.Builder().build(),
                //AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                new AppOpenAd.AppOpenAdLoadCallback() {

                    @Override
                    public void onAdLoaded(@NonNull AppOpenAd ad) {
                        appOpenAd = ad;
                        isLoadingAd = false;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                        isLoadingAd = false;
                    }
                });
    }

    public boolean isAdAvailable() {
        return appOpenAd != null;
    }

    public void showAdIfAvailable(Activity activity) {
        // ✅ Ne pas afficher si déjà trop de pubs ou si une pub est en cours
        if (!isAdAvailable() || isShowingAd || adDisplayCount >= MAX_ADS_PER_SESSION) {
            loadAd(); // Prépare la pub suivante
            return;
        }

        isShowingAd = true;

        appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {

            @Override
            public void onAdDismissedFullScreenContent() {
                appOpenAd = null;
                isShowingAd = false;
                adDisplayCount++; // ✅ Incrémenter après fermeture
                loadAd(); // Recharge une nouvelle pub
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                isShowingAd = false;
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // La pub s'affiche — pas d'action nécessaire ici
            }
        });

        appOpenAd.show(activity);
    }
}
