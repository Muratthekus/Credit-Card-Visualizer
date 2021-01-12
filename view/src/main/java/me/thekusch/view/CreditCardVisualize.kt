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
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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

    private var _cardNumberHint = "ENTER CARD NUMBER"
    private var _cardUserNameHint = "OWNER NAME"
    private var _cardValidateDateHint = "VALID DATE"
    @ColorInt private var _defaultCardColor = getDefaultColor()
    @ColorInt private var _defaultHintColor = getDefaultHintColor()

    var cardUser: CharSequence?
        get() = binding.cardUserName.getValue()
        set(value) {
            binding.cardUserName.setText(value)
        }

    var cardNumber: CharSequence?
        get() = binding.cardNumber.unMasked
        set(value) {
            binding.cardNumber.setText(value)
        }

    var cardValidDate: CharSequence?
        get() = binding.cardValidationDate.unMasked
        set(value) {
            binding.cardValidationDate.setText(value)
        }

    var cardCvcNumber: CharSequence?
        get() = binding.cardCvc.getValue()
        set(value) {
            binding.cardCvc.setText(value)
        }

    var cardBackgroundColor: Int
        @ColorInt get() = _defaultCardColor
        set(@ColorInt value) {
            _defaultCardColor = value
            binding.creditCard.setCardBackgroundColor(_defaultCardColor)
        }

    var cardTextHintColor: Int
        @ColorInt get() = _defaultHintColor
        set(@ColorInt value) {
            _defaultHintColor = value
            with(binding) {
                cardNumber.setHintTextColor(_defaultHintColor)
                cardUserName.setHintTextColor(_defaultHintColor)
                cardValidationDate.setHintTextColor(_defaultHintColor)
                cardType.setHintTextColor(_defaultHintColor)
                creditCard.setCardBackgroundColor(cardBackgroundColor)
            }
        }
    init {
        obtainStyledAttributes(attrs, defStyleAttr)
        with(binding) {
            cardUserName.hint = _cardUserNameHint
            cardNumber.hint = _cardNumberHint
            cardValidationDate.hint = _cardValidateDateHint
            cardNumber.setOnFocusChangeListener { v, hasFocus ->
                if(!hasFocus) {
                    cardType.text = getCardType()
                }
            }
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
                cardValidDate = getString(
                    R.styleable.CreditCardVisualize_cardValidDate
                )
                cardCvcNumber = getString(
                    R.styleable.CreditCardVisualize_cardCvcNumber
                )
                cardBackgroundColor = getColor(
                    R.styleable.CreditCardVisualize_cardBackgroundColor,
                    _defaultCardColor
                )
                cardTextHintColor = getColor(
                    R.styleable.CreditCardVisualize_cardTextHintColor,
                    _defaultHintColor
                )

            }
        } catch (e: Exception) {
            // ignored
        } finally {
            typedArray.recycle()
        }
    }
    private fun AppCompatEditText.getValue(): String { return this.text.toString() }
    private fun getCardType(): String {
        val types = enumValues<CardRegex>()
        types.forEach {
            val pattern = it.regex.toRegex()
            if(pattern.matches(cardNumber.toString())) {
                return it.cartName
            }
        }
        return ""
    }
    private fun getDefaultColor(): Int {
        return ContextCompat.getColor(context,R.color.cardDefaultColor)
    }
    private fun getDefaultHintColor(): Int {
        return ContextCompat.getColor(context,R.color.defaultHintColor)
    }
    fun getCardValue(): Entity {
        return Entity(
            cardNumber = cardNumber.toString(),
            cardCvcNumber = cardCvcNumber.toString(),
            cardUsersName = cardUser.toString(),
            cardValidDate = cardValidDate.toString()
        )
    }

    data class Entity(
        val cardNumber: String?,
        val cardUsersName: String?,
        val cardValidDate: String?,
        val cardCvcNumber: String?
    )
}