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
import com.google.common.collect.Ordering;
import de.codereligion.cherry.function.ToStringFunction;
import java.util.Comparator;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Tests {@link de.codereligion.cherry.collect.TreeSets} contract.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 29.12.2014
 */
public class TreeSetsTest extends AbstractIterableFactoryMethodTest {

    // TODO test that natural ordering is applied
    // TODO test that comparator is applied

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithComparatorDoesNotAllowNullIterable() {
        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Comparator<Integer> comparator = Ordering.natural().reverse();

        // when
        TreeSets.from(iterable, predicate, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithComparatorDoesNotAllowNullPredicate() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Comparator<Integer> comparator = Ordering.natural().reverse();

        // when
        TreeSets.from(iterable, predicate, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringFromMethodWithComparatorDoesNotAllowNullComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Comparator<Integer> comparator = null;

        // when
        TreeSets.from(iterable, predicate, comparator);
    }

    @Test
    public void filteringFromMethodWithComparatorDoesFiltersOutUnwantedElements() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Comparator<Integer> comparator = Ordering.natural().reverse();

        // when
        final Iterable<Integer> result = TreeSets.from(iterable, predicate, comparator);

        // then
        assertThat(result, not(hasItem(2)));
    }
    @Test(expected = IllegalArgumentException.class)
    public void transformingFromMethodWithComparatorDoesNotAllowNullIterable() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> function = null;
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        TreeSets.from(iterable, function, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformingFromMethodWithComparatorDoesNotAllowNullFunction() {

        // given
        final Iterable<Integer> iterable = null;
        final Function<Integer, String> function = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();
        // when
        TreeSets.from(iterable, function, comparator);
    }


    @Test(expected = IllegalArgumentException.class)
    public void transformingFromMethodWithComparatorDoesNotAllowNullComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Function<Integer, String> function = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = null;
        // when
        TreeSets.from(iterable, function, comparator);
    }

    @Test
    public void transformingFromMethodWithComparatorTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Function<Integer, String> function = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Iterable<String> result = TreeSets.from(iterable, function, comparator);

        // then
        assertThat(result, hasItems("1", "2", "3", "4"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringAndTransformingFromMethodWithComparatorDoesNotAllowNullIterable() {

        // given
        final Iterable<Integer> iterable = null;
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> function = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        TreeSets.from(iterable, predicate, function, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringAndTransformingFromMethodWithComparatorDoesNotAllowNullPredicate() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = null;
        final Function<Integer, String> function = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        TreeSets.from(iterable, predicate, function, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringAndTransformingFromMethodWithComparatorDoesNotAllowNullFunction() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> function = null;
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        TreeSets.from(iterable, predicate, function, comparator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filteringAndTransformingFromMethodWithComparatorDoesNotAllowNullComparator() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList();
        final Predicate<Integer> predicate = Predicates.alwaysTrue();
        final Function<Integer, String> function = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = null;

        // when
        TreeSets.from(iterable, predicate, function, comparator);
    }

    @Test
    public void filteringAndTransformingFromMethodWithComparatorFiltersOutUnwantedElements() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.not(Predicates.equalTo(2));
        final Function<Integer, String> function = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Iterable<String> result = TreeSets.from(iterable, predicate, function, comparator);

        // then
        assertThat(result, not(hasItem("2")));
    }

    @Test
    public void filteringAndTransformingFromMethodWithComparatorTransformsGivenEntriesToExpectedResult() {

        // given
        final Iterable<Integer> iterable = Lists.newArrayList(1, null, 2, 3, 4);
        final Predicate<Integer> predicate = Predicates.notNull();
        final Function<Integer, String> function = ToStringFunction.toStringFunction();
        final Comparator<String> comparator = Ordering.natural().reverse();

        // when
        final Iterable<String> result = TreeSets.from(iterable, predicate, function, comparator);

        // then
        assertThat(result, hasItems("1", "2", "3", "4"));
    }

    @Override
    protected Iterable<Integer> from(final Iterable<Integer> iterable, final Predicate<Integer> predicate) {
        return TreeSets.from(iterable, predicate);
    }

    @Override
    protected Iterable<String> from(final Iterable<Integer> iterable, final Function<Integer, String> function) {
        return TreeSets.from(iterable, function);
    }

    @Override
    protected Iterable<String> from(final Iterable<Integer> iterable, final Predicate<Integer> predicate, final Function<Integer, String> function) {
        return TreeSets.from(iterable, predicate, function);
    }

    @Override
    protected Class<?> getFactoryClass() {
        return TreeSets.class;
    }
}
