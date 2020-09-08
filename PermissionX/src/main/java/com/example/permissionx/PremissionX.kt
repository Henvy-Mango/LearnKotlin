package com.example.permissionx

import androidx.fragment.app.FragmentActivity

/**
 * FileName: PremissionX
 * Author: Naomi
 * Date: 2020/9/8 19:49
 * Description:
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(
        activity: FragmentActivity,
        vararg permission: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permission)
    }
}