package com.onoh.finalgithubapps.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.onoh.finalgithubapps.R
import com.onoh.finalgithubapps.view.FollowersFragment
import com.onoh.finalgithubapps.view.FollowingFragment

class SectionPageAdapter (private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var username :String? = null

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_following, R.string.tab_followers)
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = username?.let { FollowingFragment.newInstance(it) }
            1 -> fragment = username?.let { FollowersFragment.newInstance(it) }
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
    override fun getCount(): Int {
        return TAB_TITLES.size
    }

}