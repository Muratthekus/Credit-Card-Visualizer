package me.thekusch.view

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
import androidx.core.view.GestureDetectorCompat
import me.thekusch.view.databinding.CreditCardVisualizeBinding
import kotlin.math.atan2

class CreditCardVisualize @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.creditCardVisualize
): MotionLayout(context, attrs, defStyleAttr),View.OnScrollChangeListener{

    private val binding: CreditCardVisualizeBinding = CreditCardVisualizeBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

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
        binding.detailWrapperFront.setOnScrollChangeListener(this)
        gestureDetectorCompat = GestureDetectorCompat(context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onScroll(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    distanceX: Float,
                    distanceY: Float
                ): Boolean {
                    val angle = Math.toDegrees(
                        atan2(
                            (e1!!.y - e2!!.y).toDouble(),
                            (e2.x - e1.x).toDouble()
                        )
                    ).toFloat()
                    if(angle > -45 && angle < 45) {
                        binding.motionLayout.transitionToEnd()
                        Log.d("DENEME","LEFFTTT")
                        return true
                    }
                    return false
                }
            })

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

    override fun onScrollChange(
        v: View?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        Log.d("DENEME","KAYDIRRRMA")
        when(v?.id) {
            R.id.detailWrapperFront -> {
                if(scrollX > oldScrollX) {
                    Log.d("DENEME","KAYDIRRR")
                }
            }
        }
    }
}