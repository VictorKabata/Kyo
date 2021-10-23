package com.vickikbt.kyoskinterview.ui.fragments.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.vickikbt.commons.Constants.BUG_REPORT_EMAIL
import com.vickikbt.commons.Constants.BUG_REPORT_SUBJECT
import com.vickikbt.commons.Constants.SOURCE_CODE_URL
import com.vickikbt.kyoskinterview.R


class ProfileFragment : PreferenceFragmentCompat(),
    PreferenceManager.OnPreferenceTreeClickListener {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.profile_fragment, rootKey)

    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        when (preference?.key) {
            "report bug" -> {
                reportBug()
            }

            "source code" -> {
                openSourceCode()
            }
        }

        return super.onPreferenceTreeClick(preference)
    }

    private fun reportBug() {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            type = "text/plain"
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(BUG_REPORT_EMAIL))
            putExtra(Intent.EXTRA_SUBJECT, BUG_REPORT_SUBJECT)
        }

        startActivity(emailIntent)
    }

    private fun openSourceCode() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(SOURCE_CODE_URL)
        startActivity(intent)
    }

}