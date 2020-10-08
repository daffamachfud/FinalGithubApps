package com.onoh.finalgithubapps.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.onoh.finalgithubapps.R
import com.onoh.finalgithubapps.view.fragment.MyPreferenceFragment
import kotlinx.android.synthetic.main.activity_detail_user.*
import kotlinx.android.synthetic.main.activity_detail_user.toolbar_detail
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        setSupportActionBar(toolbar_detail)
        toolbar_setting.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_back)
        toolbar_setting.setNavigationOnClickListener { onBackPressed() }

        supportFragmentManager.beginTransaction().add(R.id.setting_holder,MyPreferenceFragment()).commit()
    }
}
