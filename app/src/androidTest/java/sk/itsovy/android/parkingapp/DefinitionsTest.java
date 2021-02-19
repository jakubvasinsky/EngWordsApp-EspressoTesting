package sk.itsovy.android.parkingapp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DefinitionsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void definitionsTest() throws InterruptedException {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.inputParameter),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout1),
                                        childAtPosition(
                                                withId(R.id.activity_main_layout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("school"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnSearch),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout1),
                                        childAtPosition(
                                                withId(R.id.activity_main_layout),
                                                0)),
                                0),
                        isDisplayed()));


        appCompatButton.perform(click());
        Thread.sleep(5000);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnAdd), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(R.id.activity_main_layout),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());
    Thread.sleep(5000);
        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btnAdd), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(R.id.activity_main_layout),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatButton4.perform(click());

        Thread.sleep(5000);

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btnView), withText("View Data"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(R.id.activity_main_layout),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.textViewPlate), withText("school"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerView),
                                        1),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("Definition:An institution for educating children.Example:Ryder's children did not go to school at all"),
                        withParent(allOf(withId(R.id.examplesWordView),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Definition:An institution for educating children.Example:Ryder's children did not go to school at all")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("Definition:Any institution at which instruction is given in a particular discipline.Example:a dancing school"),
                        withParent(allOf(withId(R.id.examplesWordView),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("Definition:Any institution at which instruction is given in a particular discipline.Example:a dancing school")));

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.text1), withText("Definition:A group of people particularly writers artists or philosophers sharing the same or similar ideas methods or style.Example:the Frankfurt school of critical theory"),
                        withParent(allOf(withId(R.id.examplesWordView),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView3.check(matches(withText("Definition:A group of people particularly writers artists or philosophers sharing the same or similar ideas methods or style.Example:the Frankfurt school of critical theory")));

        ViewInteraction textView4 = onView(
                allOf(withId(android.R.id.text1), withText("Synonyms  [ group , set , circle , clique , faction , sect ]"),
                        withParent(allOf(withId(R.id.examplesWordView),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView4.check(matches(withText("Synonyms  [ group , set , circle , clique , faction , sect ]")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
