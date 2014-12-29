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
package de.codereligion.cherry.collect;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import de.codereligion.cherry.function.ToStringFunction;
import de.codereligion.cherry.matcher.IsNotInstantiatable;
import org.junit.Test;
import static de.codereligion.cherry.matcher.IsMultimapContaining.hasEntry;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Tests common logic for all map factory methods.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 29.12.2014
 */
public abstract class AbstractMultimapFactoryMethodTest {

    // TODO test multi map functionality

    @Test
    public void isNotInstantiateable() {
        assertThat(getFactoryClass(), IsNotInstantiatable.isNotInstantiatable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyFunctionDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        from(iterable, keyFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyFunctionDoesNotAllowNullKeyFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = null;

        // when
        from(iterable, keyFunction);
    }

    @Test
    public void fromMethodWithKeyFunctionTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        final Multimap<String, Integer> result = from(iterable, keyFunction);

        // then
        assertThat(result, hasEntry("1", 1));
        assertThat(result, hasEntry("2", 2));
        assertThat(result, hasEntry("3", 3));
        assertThat(result, hasEntry("4", 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyFunctionAndValueFunctionDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        from(iterable, keyFunction, valueFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyFunctionAndValueFunctionDoesNotAllowNullKeyFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = null;
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        from(iterable, keyFunction, valueFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromMethodWithKeyFunctionAndValueFunctionDoesNotAllowNullValueFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = null;

        // when
        from(iterable, keyFunction, valueFunction);
    }

    @Test
    public void fromMethodWithKeyFunctionAndValueFunctionTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        final Multimap<String, String> result = from(iterable, keyFunction, valueFunction);

        // then
        assertThat(result, hasEntry("1", "1"));
        assertThat(result, hasEntry("2", "2"));
        assertThat(result, hasEntry("3", "3"));
        assertThat(result, hasEntry("4", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        from(iterable, predicate, keyFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionDoesNotAllowNullPredicate() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        from(iterable, predicate, keyFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionDoesNotAllowNullKeyFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = null;

        // when
        from(iterable, predicate, keyFunction);
    }

    @Test
    public void filteringFromMethodWithKeyFunctionTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        final Multimap<String, Integer> result = from(iterable, predicate, keyFunction);

        // then
        assertThat(result, hasEntry("1", 1));
        assertThat(result, hasEntry("2", 2));
        assertThat(result, hasEntry("3", 3));
        assertThat(result, hasEntry("4", 4));
    }

    @Test
    public void filteringFromMethodWithKeyFunctionFiltersOutUnwantedEntries() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();

        // when
        final Multimap<String, Integer> result = from(iterable, predicate, keyFunction);

        // then
        assertThat(result, not(hasEntry("2", 2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionAndValueFunctionDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        from(iterable, predicate, keyFunction, valueFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionAndValueFunctionDoesNotAllowNullPredicate() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        ;
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        from(iterable, predicate, keyFunction, valueFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionAndValueFunctionDoesNotAllowNullKeyFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = null;
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        from(iterable, predicate, keyFunction, valueFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithKeyFunctionAndValueFunctionDoesNotAllowNullValueFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = null;

        // when
        from(iterable, predicate, keyFunction, valueFunction);
    }

    @Test
    public void filteringFromMethodWithKeyFunctionAndValueFunctionTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        final Multimap<String, String> result = from(iterable, predicate, keyFunction, valueFunction);

        // then
        assertThat(result, hasEntry("1", "1"));
        assertThat(result, hasEntry("2", "2"));
        assertThat(result, hasEntry("3", "3"));
        assertThat(result, hasEntry("4", "4"));
    }

    @Test
    public void filteringFromMethodWithKeyFunctionAndValueFunctionFiltersOutUnwantedEntries() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Function<Integer, String> keyFunction = ToStringFunction.toStringFunction();
        final Function<Integer, String> valueFunction = ToStringFunction.toStringFunction();

        // when
        final Multimap<String, String> result = from(iterable, predicate, keyFunction, valueFunction);

        // then
        assertThat(result, not(hasEntry("2", "2")));
    }

    protected abstract Multimap<String, Integer> from(Iterable<Integer> iterable, Function<Integer, String> keyFunction);

    protected abstract Multimap<String, String> from(Iterable<Integer> iterable,
                                                     Function<Integer, String> keyFunction,
                                                     Function<Integer, String> valueFunction);

    protected abstract Multimap<String, Integer> from(Iterable<Integer> iterable, Predicate<Integer> predicate, Function<Integer, String> keyFunction);

    protected abstract Multimap<String, String> from(Iterable<Integer> iterable,
                                                     Predicate<Integer> predicate,
                                                     Function<Integer, String> keyFunction,
                                                     Function<Integer, String> valueFunction);

    protected abstract Class<?> getFactoryClass();
}
