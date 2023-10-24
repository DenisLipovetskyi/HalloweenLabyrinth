package com.example.halloweenlabyrinth.utils

import android.content.Context

object PreferencesHelper {
    private const val PREFS_NAME = "user_prefs"
    private const val POLICY_ACCEPTED_KEY = "policy_accepted"

    fun isPolicyAccepted(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(POLICY_ACCEPTED_KEY, false)
    }

    fun setPolicyAccepted(context: Context, accepted: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putBoolean(POLICY_ACCEPTED_KEY, accepted)
            apply()
        }
    }
}