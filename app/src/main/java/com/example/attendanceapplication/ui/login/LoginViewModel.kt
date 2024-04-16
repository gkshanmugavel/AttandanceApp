package com.example.attendanceapplication.ui.login

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.attendanceapplication.R
import com.example.attendanceapplication.utils.AppUtils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Field
import java.util.Objects
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var userName = MutableLiveData("")
    var password = MutableLiveData("")

    companion object{
    /*    @JvmStatic
        @BindingAdapter("cursorColor")
        fun setCursorDrawable(textInputEditText: TextInputEditText, color: String?) {
            try {
                textInputEditText.highlightColor =
                    Color.parseColor("#1A" + "#3EA3DC".replace("#", ""))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val cursorDrawable = ContextCompat.getDrawable(
                        textInputEditText.context,
                        R.drawable.custom_cursor
                    )
                    val colorFilterCursor =
                        BlendModeColorFilter(Color.parseColor(color), BlendMode.SRC_IN)
                    Objects.requireNonNull(cursorDrawable)!!.colorFilter =
                        colorFilterCursor
                    textInputEditText.textCursorDrawable = cursorDrawable
                    val left = textInputEditText.textSelectHandleLeft
                    val colorFilterLeft =
                        BlendModeColorFilter(Color.parseColor(color), BlendMode.SRC_IN)
                    Objects.requireNonNull(left!!).colorFilter =
                        colorFilterLeft
                    textInputEditText.setTextSelectHandleLeft(left!!)
                    val center = textInputEditText.textSelectHandle
                    val colorFilter =
                        BlendModeColorFilter(Color.parseColor(color), BlendMode.SRC_IN)
                    Objects.requireNonNull(center!!).colorFilter =
                        colorFilter
                    textInputEditText.setTextSelectHandle(center!!)
                    val right = textInputEditText.textSelectHandleRight
                    val colorFilterRight =
                        BlendModeColorFilter(Color.parseColor(color), BlendMode.SRC_IN)
                    Objects.requireNonNull(right!!).colorFilter =
                        colorFilterRight
                    textInputEditText.setTextSelectHandleRight(right!!)
                } else {
                    setCursorDrawableToEt(textInputEditText, color)
                    setSelectionHandleColor(textInputEditText, color)
                }
            } catch (e: Exception) {
            }
        }

        fun setCursorDrawableToEt(textInputEditText: TextInputEditText, color: String?) {
            try {
                val f: Field = MaterialTextView::class.java.getDeclaredField("mCursorDrawableRes")
                f.setAccessible(true)
                val drawableResId: Int = f.getInt(textInputEditText)
                val cursorDrawable =
                    ContextCompat.getDrawable(textInputEditText.context, drawableResId)
                cursorDrawable?.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
                val drawables = arrayOf(cursorDrawable, cursorDrawable)
                f.set(textInputEditText, drawables)
            } catch (e: java.lang.Exception) {
            }
        }

        fun setSelectionHandleColor(editText: TextInputEditText, color: String?) {
            try {
                val fCursorDrawableCenterRes: Field =
                    MaterialTextView::class.java.getDeclaredField("mSelectHandleCenterRes")
                fCursorDrawableCenterRes.setAccessible(true)
                val mCursorDrawableRes: Int = fCursorDrawableCenterRes.getInt(editText)

                // Left
                val fCursorDrawableLeftRes: Field =
                    MaterialTextView::class.java.getDeclaredField("mTextSelectHandleLeftRes")
                fCursorDrawableLeftRes.setAccessible(true)
                val mCursorDrawableLeftRes: Int = fCursorDrawableLeftRes.getInt(editText)

                // Right
                val fCursorDrawableRightRes: Field =
                    MaterialTextView::class.java.getDeclaredField("mTextSelectHandleRightRes")
                fCursorDrawableRightRes.setAccessible(true)
                val mCursorDrawableRightRes: Int = fCursorDrawableRightRes.getInt(editText)
                val fEditor: Field = MaterialTextView::class.java.getDeclaredField("mEditor")
                fEditor.setAccessible(true)
                val editor: Any = fEditor.get(editText)
                val clazz: Class<*> = Objects.requireNonNull(editor).javaClass
                val fCursorDrawable: Field = clazz.getDeclaredField("mCursorDrawable")
                fCursorDrawable.setAccessible(true)
                val drawables = arrayOfNulls<Drawable>(3)
                drawables[0] = ContextCompat.getDrawable(editText.context, mCursorDrawableRes)
                drawables[1] = ContextCompat.getDrawable(editText.context, mCursorDrawableLeftRes)
                drawables[2] = ContextCompat.getDrawable(editText.context, mCursorDrawableRightRes)
                Objects.requireNonNull(drawables[0]!!)
                    .setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
                Objects.requireNonNull(drawables[1]!!)
                    .setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
                Objects.requireNonNull(drawables[2]!!)
                    .setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
                fCursorDrawable.set(editor, drawables)
            } catch (e: java.lang.Exception) {
            }
        }*/
    }
}