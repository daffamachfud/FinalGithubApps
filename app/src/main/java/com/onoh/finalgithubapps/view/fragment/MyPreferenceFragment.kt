package com.onoh.finalgithubapps.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.onoh.finalgithubapps.R
import com.onoh.finalgithubapps.receiver.AlarmReceiver


class MyPreferenceFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var REMINDER : String
    private lateinit var isReminderPref : SwitchPreference
    private lateinit var alarmReceiver: AlarmReceiver

    companion object {
        private const val DEFAULT_VALUE = "Tidak Ada"
    }

    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
        alarmReceiver = AlarmReceiver()
    }

    private fun setSummaries() {
        val pref = preferenceManager.sharedPreferences
        isReminderPref.disableDependentsState = pref.getBoolean(REMINDER,false)
    }

    private fun init(){

        REMINDER = resources.getString(R.string.key_reminder)
        isReminderPref = findPreference<SwitchPreference>(REMINDER) as SwitchPreference
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if(key == REMINDER){
            isReminderPref.disableDependentsState = sharedPreferences.getBoolean(REMINDER,false)
            val reminder = sharedPreferences.getBoolean(REMINDER,false)
            if(reminder){
                alarmReceiver.setRepeatingAlarm(context)
            }else{
                alarmReceiver.cancelAlarm(context)
            }
        }
    }
}