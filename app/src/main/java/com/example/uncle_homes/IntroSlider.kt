package com.example.uncle_homes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.uncle_homes.authentication.SignupActivity
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType

class IntroSlider : AppIntro2() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Slide 1: Highlighting the need and introducing Uncle Homes
        addSlide(AppIntroFragment.createInstance(
            title = "Welcome to Uncle Homes",
            description = "Empowering Africa by providing accessible home services while creating jobs. Find trusted professionals and freelancers in your community.",
            imageDrawable = R.drawable.first,
            backgroundDrawable = R.color.white,
            titleColorRes = R.color.black,
            descriptionColorRes = R.color.black,
            backgroundColorRes = R.color.white,
            titleTypefaceFontRes = R.font.oxanium_bold,
            descriptionTypefaceFontRes = R.font.oxanium_regular,
        ))

        // Slide 2: Explaining services and convenience
        addSlide(AppIntroFragment.createInstance(
            title = "Empowering Local Talent",
            description = "Connect with skilled Africans for home maintenance services, from plumbing and gardening to electrical work and repairs.",
            imageDrawable = R.drawable.yellow,
            backgroundDrawable = R.color.glossy_yellow,
            titleColorRes = R.color.black,
            descriptionColorRes = R.color.black,
            backgroundColorRes = R.color.white,
            titleTypefaceFontRes = R.font.oxanium_bold,
            descriptionTypefaceFontRes = R.font.oxanium_regular,
        ))

        // Slide 3: Highlighting skill-building opportunities
        addSlide(AppIntroFragment.createInstance(
            title = "Creating Employment Opportunities",
            description = "Join our growing network of workers. Whether you're a plumber, cleaner, electrician, or carpenter, we help you build a sustainable income.",
            imageDrawable = R.drawable.worker,
            backgroundDrawable = R.color.glossy_red,
            titleColorRes = R.color.black,
            descriptionColorRes = R.color.black,
            backgroundColorRes = R.color.white,
            titleTypefaceFontRes = R.font.oxanium_bold,
            descriptionTypefaceFontRes = R.font.oxanium_regular,
        ))

        // Slide 4: Emphasizing community impact and easy booking
        addSlide(AppIntroFragment.createInstance(
            title = "Transforming Lives, One Job at a Time",
            description = "Book services instantly, support local talent, and contribute to reducing unemployment. Together, we build a better future for Africa.",
            imageDrawable = R.drawable.book,
            backgroundDrawable = R.color.glossy_green,
            titleColorRes = R.color.black,
            descriptionColorRes = R.color.black,
            backgroundColorRes = R.color.white,
            titleTypefaceFontRes = R.font.oxanium_bold,
            descriptionTypefaceFontRes = R.font.oxanium_regular,
        ))

        // Set slide transitions and other behaviors
        setTransformer(AppIntroPageTransformerType.Flow)
        isWizardMode = true
        setImmersiveMode()
        isSystemBackButtonLocked = true
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Navigate when Skip is pressed
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Navigate to main activity when Done is pressed
        val intent = Intent(this, SignupActivity ::class.java)
        startActivity(intent)
        finish() // Close the intro activity
    }
}
