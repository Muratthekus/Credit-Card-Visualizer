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
  <img width="460" height="920" src="https://user-images.githubusercontent.com/45212967/104377349-6f12d700-5537-11eb-9f83-b22bbf28bf7f.gif">
</p>



Credit card chip icon made by Freepik from www.flaticon.com


```
MIT License

Copyright (c) 2021 Murat Kuş

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
