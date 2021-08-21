package com.cybereast.tmdbapi.base

import android.text.TextUtils
import android.widget.EditText

abstract class BaseValidationRecyclerviewFragment : RecyclerViewBaseFragment() {
    companion object {
        private var TAG: String = BaseValidationRecyclerviewFragment::class.java.name
    }

    protected fun validateTextField(vararg textField: EditText) {
        var validation = true
        for (textF in textField) {
            if (TextUtils.isEmpty(textF.text)) {
                validation = false
                onValidationError(textF)
            }
        }
        if (validation) {
            onValidationSuccess()
        }

    }

    abstract fun onValidationError(editText: EditText)
    abstract fun onValidationSuccess()
}