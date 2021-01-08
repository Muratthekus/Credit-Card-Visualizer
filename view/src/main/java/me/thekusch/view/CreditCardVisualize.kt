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
): ConstraintLayout(context, attrs, defStyleAttr),View.OnTouchListener{

    private val binding: CreditCardVisualizeBinding = CreditCardVisualizeBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    private var x1=0
    private var x2 = 0

    private lateinit var  gestureDetectorCompat: GestureDetectorCompat

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

        gestureDetectorCompat = GestureDetectorCompat(context,GestureListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("DENEMELER","NABERSİN")
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x.toInt()
                return true
            }
            MotionEvent.ACTION_UP -> {
                x2 = event.x.toInt()
                performClick()
                return true
            }
        }
        return false
    }

    override fun performClick(): Boolean {
        super.performClick()
        if(x2 > x1) {
            swipeLeft()
        }
        return true
    }


   inner class GestureListener: GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var diffY: Float? = e2?.y?.minus(e1?.y!!)
            var diffX: Float? = e2?.x?.minus(e1?.x!!)
            if(abs(diffX!!) > 100 && velocityX > 100) {
                if(diffX >0) {
                    swipeLeft()
                    return true
                }
            }
            return false
        }
    }
    private fun swipeLeft() {
        ObjectAnimator.ofFloat(binding.detailWrapperFront,"scaleX",-1f).apply {
            duration = 2000
            start()
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

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetectorCompat.onTouchEvent(event)
    }
}