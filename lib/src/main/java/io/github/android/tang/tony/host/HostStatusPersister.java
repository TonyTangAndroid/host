package io.github.android.tang.tony.host;

import android.content.SharedPreferences;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

@HostScope
@DebugLog
public class HostStatusPersister {

    public static String PREF_KEY_ENABLED = "pref_key_host_status";

    private final SharedPreferences prefs;

    @Inject
    public HostStatusPersister(SharedPreferences sharedPreferences) {
        this.prefs = sharedPreferences;
    }

    public InternalStatus status() {
        if (prefs.contains(PREF_KEY_ENABLED)) {
            return prefs.getBoolean(PREF_KEY_ENABLED, false)
                    ? InternalStatus.ACTIVATED
                    : InternalStatus.ON_CALL;
        } else {
            return InternalStatus.NONE;
        }
    }

    public void update(InternalStatus newStatus) {
        switch (newStatus) {
            case NONE:
                prefs.edit().remove(PREF_KEY_ENABLED).apply();
                break;
            case ON_CALL:
                prefs.edit().putBoolean(PREF_KEY_ENABLED, false).apply();
                break;
            case ACTIVATED:
                prefs.edit().putBoolean(PREF_KEY_ENABLED, true).apply();
        }
    }

    enum InternalStatus {
        NONE,
        ON_CALL,
        ACTIVATED
    }
}