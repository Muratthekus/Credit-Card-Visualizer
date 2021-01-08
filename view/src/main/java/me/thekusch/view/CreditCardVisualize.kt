package me.thekusch.view

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GestureDetectorCompat
import me.thekusch.view.databinding.CreditCardVisualizeBinding
import kotlin.math.abs
import kotlin.math.atan2

class CreditCardVisualize @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.creditCardVisualize
): MotionLayout(context, attrs, defStyleAttr){

    private val binding: CreditCardVisualizeBinding = CreditCardVisualizeBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )


    private var _cardNumberHint = "ENTER CART NUMBER"
    private var _cardUserNameHint = "ENTER CART USER'S NAME"

    var cardUser: CharSequence?
        get() = binding.cardUserName.getValue()
        set(value) {
            binding.cardUserName.setText(value)
        }

    var cardNumber: CharSequence?
        get() = binding.cardNumber.getValue()
        set(value) {
            binding.cardNumber.setText(value)
        }

    init {
        obtainStyledAttributes(attrs, defStyleAttr)
        with(binding) {
            cardUserName.hint = _cardUserNameHint
            cardNumber.hint = _cardNumberHint
        }

    }


    private fun obtainStyledAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CreditCardVisualize,
            defStyleAttr,
            0
        )

        try {
            with(typedArray) {
                cardNumber = getString(
                    R.styleable.CreditCardVisualize_cardNumber
                )

                cardUser = getString(
                    R.styleable.CreditCardVisualize_cardUserName
                )
            }
        } catch (e: Exception) {
            // ignored
        } finally {
            typedArray.recycle()
        }
    }
    private fun AppCompatEditText.getValue(): String? {
        return this.text.toString()
    }

}