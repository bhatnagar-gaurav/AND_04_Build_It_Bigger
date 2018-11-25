
package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndPointsAsyncTaskTestUnit {
    String TAG = EndPointsAsyncTaskTest.class.getSimpleName();

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    public EndPointsAsyncTaskTestUnit() {
    }

    @Test
    public void testJokeIsNotEmpty() throws Exception {
        EndPointsAsyncTaskTest asyncTaskTest = new EndPointsAsyncTaskTest();
        asyncTaskTest.execute(InstrumentationRegistry.getContext());
        String joke = asyncTaskTest.get(5, TimeUnit.SECONDS);
        Assert.assertFalse(TextUtils.isEmpty(joke));
    }

    @Test
    public void testVerifyResponse() {
        onView(withId(R.id.showJokeButton)).perform(click());
        onView(withId(R.id.textView_joke)).check(matches(isDisplayed()));
    }

}

