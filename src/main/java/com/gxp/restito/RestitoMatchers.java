package com.gxp.restito;

import com.gxp.restito.request.Request;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 *
 * @author juboyd
 */
public class RestitoMatchers {

	public static RequestMatcher requestWithUriThat(Matcher<String> uriMatcher) {
		return new RequestMatcher(uriMatcher, Matchers.any(String.class));
	}

	public static RequestMatcher request(Matcher<String> methodMatcher, Matcher<String> uriMatcher) {
		return new RequestMatcher(uriMatcher, methodMatcher);
	}

	public static class RequestMatcher extends TypeSafeDiagnosingMatcher<Request> {

		private final Matcher<String> uriMatcher;
		private final Matcher<String> methodMatcher;

		public RequestMatcher(Matcher<String> uriMatcher, Matcher<String> methodMatcher) {
			this.uriMatcher = uriMatcher;
			this.methodMatcher = methodMatcher;
		}

		public void describeTo(Description description) {
			description.
				appendText("request method [").
				appendDescriptionOf(methodMatcher).
				appendText("] and uri [").
				appendDescriptionOf(uriMatcher).
				appendText("]");
		}

		@Override
		protected boolean matchesSafely(Request item, Description mismatchDescription) {

			boolean uriMatches = false, methodMatches = false;

			if (uriMatcher.matches(item.uri())) {
				uriMatches = true;
			} else {
				mismatchDescription.appendText("request with uri that ");
				uriMatcher.describeMismatch(item.uri(), mismatchDescription);
			}

			if (methodMatcher.matches(item.method())) {
				methodMatches = true;
			} else {
				mismatchDescription.appendText("method that ");
				methodMatcher.describeMismatch(item.method(), mismatchDescription);
			}

			return uriMatches && methodMatches;
		}
	}
}
