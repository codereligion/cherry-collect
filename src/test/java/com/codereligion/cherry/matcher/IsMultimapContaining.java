/**
 * Copyright 2014 www.codereligion.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codereligion.cherry.matcher;

import com.google.common.collect.Multimap;
import java.util.Map;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import static org.hamcrest.core.IsAnything.anything;
import static org.hamcrest.core.IsEqual.equalTo;

public class IsMultimapContaining<K,V> extends TypeSafeMatcher<Multimap<? extends K, ? extends V>> {

    private final Matcher<? super K> keyMatcher;
    private final Matcher<? super V> valueMatcher;

    public IsMultimapContaining(final Matcher<? super K> keyMatcher, Matcher<? super V> valueMatcher) {
        this.keyMatcher = keyMatcher;
        this.valueMatcher = valueMatcher;
    }

    @Override
    public boolean matchesSafely(final Multimap<? extends K, ? extends V> multimap) {
        for (Map.Entry<? extends K, ? extends V> entry : multimap.entries()) {
            if (keyMatcher.matches(entry.getKey()) && valueMatcher.matches(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeMismatchSafely(final Multimap<? extends K, ? extends V> multimap, Description mismatchDescription) {
      mismatchDescription.appendText("multimap was ").appendValueList("[", ", ", "]", multimap.entries());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("multimap containing [")
                   .appendDescriptionOf(keyMatcher)
                   .appendText("->")
                   .appendDescriptionOf(valueMatcher)
                   .appendText("]");
    }

    /**
     * Creates a matcher for {@link com.google.common.collect.Multimap}s matching when the examined {@link com.google.common.collect.Multimap} contains
     * at least one entry whose key satisfies the specified <code>keyMatcher</code> <b>and</b> whose
     * value satisfies the specified <code>valueMatcher</code>.
     * <p/>
     * For example:
     * <pre>assertThat(myMap, hasEntry(equalTo("bar"), equalTo("foo")))</pre>
     * 
     * @param keyMatcher
     *     the key matcher that, in combination with the valueMatcher, must be satisfied by at least one entry
     * @param valueMatcher
     *     the value matcher that, in combination with the keyMatcher, must be satisfied by at least one entry
     */
    public static <K,V> Matcher<Multimap<? extends K,? extends V>> hasEntry(final Matcher<? super K> keyMatcher, Matcher<? super V> valueMatcher) {
        return new IsMultimapContaining<K,V>(keyMatcher, valueMatcher);
    }

    /**
     * Creates a matcher for {@link com.google.common.collect.Multimap}s matching when the examined {@link com.google.common.collect.Multimap} contains
     * at least one entry whose key equals the specified <code>key</code> <b>and</b> whose value equals the
     * specified <code>value</code>.
     * <p/>
     * For example:
     * <pre>assertThat(myMap, hasEntry("bar", "foo"))</pre>
     *  
     * @param key
     *     the key that, in combination with the value, must be describe at least one entry
     * @param value
     *     the value that, in combination with the key, must be describe at least one entry
     */
    public static <K,V> Matcher<Multimap<? extends K,? extends V>> hasEntry(final K key, final V value) {
        return new IsMultimapContaining<K,V>(equalTo(key), equalTo(value));
    }
    
    /**
     * Creates a matcher for {@link com.google.common.collect.Multimap}s matching when the examined {@link com.google.common.collect.Multimap} contains
     * at least one key that satisfies the specified matcher.
     * <p/>
     * For example:
     * <pre>assertThat(myMap, hasKey(equalTo("bar")))</pre>
     * 
     * @param keyMatcher
     *     the matcher that must be satisfied by at least one key
     */
    public static <K> Matcher<Multimap<? extends K, ?>> hasKey(final Matcher<? super K> keyMatcher) {
        return new IsMultimapContaining<K,Object>(keyMatcher, anything());
    }

    /**
     * Creates a matcher for {@link com.google.common.collect.Multimap}s matching when the examined {@link com.google.common.collect.Multimap} contains
     * at least one key that is equal to the specified key.
     * <p/>
     * For example:
     * <pre>assertThat(myMap, hasKey("bar"))</pre>
     * 
     * @param key
     *     the key that satisfying maps must contain
     */
    public static <K> Matcher<Multimap<? extends K, ?>> hasKey(final K key) {
        return new IsMultimapContaining<K,Object>(equalTo(key), anything());
    }

    /**
     * Creates a matcher for {@link com.google.common.collect.Multimap}s matching when the examined {@link com.google.common.collect.Multimap} contains
     * at least one value that satisfies the specified valueMatcher.
     * <p/>
     * For example:
     * <pre>assertThat(myMap, hasValue(equalTo("foo")))</pre>
     * 
     * @param valueMatcher
     *     the matcher that must be satisfied by at least one value
     */
    public static <V> Matcher<Multimap<?, ? extends V>> hasValue(final Matcher<? super V> valueMatcher) {
        return new IsMultimapContaining<Object,V>(anything(), valueMatcher);
    }

    /**
     * Creates a matcher for {@link com.google.common.collect.Multimap}s matching when the examined {@link com.google.common.collect.Multimap} contains
     * at least one value that is equal to the specified value.
     * <p/>
     * For example:
     * <pre>assertThat(myMap, hasValue("foo"))</pre>
     * 
     * @param value
     *     the value that satisfying maps must contain
     */
    public static <V> Matcher<Multimap<?, ? extends V>> hasValue(final V value) {
        return new IsMultimapContaining<Object,V>(anything(), equalTo(value));
    }
}
