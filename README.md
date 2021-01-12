# Credit Card Visualizer

[![](https://jitpack.io/v/Muratthekus/Credit-Card-Visualizer.svg)](https://jitpack.io/#Muratthekus/Credit-Card-Visualizer)

Light-weight custom Credit card visualizer with smooth animations. For better UI/UX applications

## Usage

```xml
<me.thekusch.view.CreditCardVisualize
     android:id="@+id/creditCard"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    android:layout_height="match_parent"
    android:layout_width="match_parent"
    app:cardBackgroundColor=""
    app:cardCvcNumber=""
    app:cardNumber=""
    app:cardTextHintColor=""
    app:cardUserName=""
    app:cardValidDate=""/>

```


## Setup

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        implementation 'com.github.Muratthekus:Credit-Card-Visualizer:v1.0'
	}

```

### Getting information

Use getCardValue() method to access card number, cvc, validation date and user's name
 > creditCard.getCardValue()

### Result
**Card user's name must be written uppercase**

<p align="center">
  <img width="1920" height="300" src="https://user-images.githubusercontent.com/45212967/104377349-6f12d700-5537-11eb-9f83-b22bbf28bf7f.gif">
</p>

