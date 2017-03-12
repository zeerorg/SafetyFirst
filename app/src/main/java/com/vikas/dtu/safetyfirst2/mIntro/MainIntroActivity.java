package com.vikas.dtu.safetyfirst2.mIntro;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.vikas.dtu.safetyfirst2.R;

public class MainIntroActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtonBackVisible(false);
        setButtonNextVisible(false);
        setButtonCtaVisible(true);
        setButtonCtaTintMode(BUTTON_CTA_TINT_MODE_BACKGROUND);
        TypefaceSpan labelSpan = new TypefaceSpan(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? "sans-serif-medium" : "sans serif");
        SpannableString label = SpannableString.valueOf(getString(R.string.label_button_cta_canteen_intro));
        label.setSpan(labelSpan, 0, label.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        setButtonCtaLabel(label);

        setPageScrollDuration(500);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setPageScrollInterpolator(android.R.interpolator.fast_out_slow_in);
        }

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_welcome_intro1)
                .description(R.string.description_welcome_intro1)
                .image(R.drawable.art_intro_news)
                .background(R.color.color_intro)
                .backgroundDark(R.color.color_dark_intro)
                .layout(R.layout.slide_intro)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_welcome_intro2)
                .description(R.string.description_welcome_intro2)
                .image(R.drawable.art_intro_discussion)
                .background(R.color.color_intro)
                .backgroundDark(R.color.color_dark_intro)
                .layout(R.layout.slide_intro)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_welcome_intro3)
                .description(R.string.description_welcome_intro3)
                .image(R.drawable.art_intro_laws)
                .background(R.color.color_intro)
                .backgroundDark(R.color.color_dark_intro)
                .layout(R.layout.slide_intro)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_welcome_intro4)
                .description(R.string.description_welcome_intro4)
                .image(R.drawable.art_intro_laws)
                .background(R.color.color_intro)
                .backgroundDark(R.color.color_dark_intro)
                .layout(R.layout.slide_intro)
                .build());

        autoplay(2500, INFINITE);
    }


}
