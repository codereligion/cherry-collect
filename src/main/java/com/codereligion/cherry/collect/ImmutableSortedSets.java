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
package com.codereligion.cherry.collect;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSortedSet;
import java.util.Comparator;
import java.util.SortedSet;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * Provides static factory methods to create {@link com.google.common.collect.ImmutableSortedSet ImmutableSortedSets}.
 *
 * @author Sebastian Gr&oml;bler
 * @since 28.12.2014
 */
public final class ImmutableSortedSets {

    private ImmutableSortedSets() {
        throw new IllegalAccessError("This class is a utility class and must not be instantiated.");
    }

    /**
     * TODO
     *
     * @param iterable the entries to be transformed
     * @param function the function to transform the entries with
     * @param <F>      the type of the entries to be transformed
     * @param <T>      the type of the resulting entries
     * @return a {@link com.google.common.collect.ImmutableSortedSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T extends Comparable<T>> ImmutableSortedSet<T> createFrom(final Iterable<F> iterable, final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");

        final ImmutableSortedSet.Builder<T> builder = ImmutableSortedSet.naturalOrder();

        for (final F f : iterable) {
            final T t = function.apply(f);
            builder.add(t);
        }

        return builder.build();
    }

    /**
     * TODO
     *
     * @param iterable   the entries to be mapped
     * @param function   the function to transform the entries with
     * @param comparator the comparator to sort the resulting set with
     * @param <F>        the type of the entries to be transformed
     * @param <T>        the type of the resulting entries
     * @return a {@link com.google.common.collect.ImmutableSortedSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> ImmutableSortedSet<T> createFrom(final Iterable<F> iterable, final Function<? super F, T> function, final Comparator<T> comparator) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");
        checkArgument(comparator != null, "comparator must not be null.");

        final ImmutableSortedSet.Builder<T> builder = ImmutableSortedSet.orderedBy(comparator);

        for (final F f : iterable) {
            final T t = function.apply(f);
            builder.add(t);
        }

        return builder.build();
    }

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered
     * @param predicate the predicate to filter the entries with
     * @param <E>       the type of the entries to be filtered
     * @return a {@link com.google.common.collect.ImmutableSortedSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E extends Comparable<E>> ImmutableSortedSet<E> createFrom(final Iterable<E> iterable, final Predicate<? super E> predicate) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");

        final ImmutableSortedSet.Builder<E> builder = ImmutableSortedSet.naturalOrder();

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                builder.add(e);
            }
        }

        return builder.build();
    }

    /**
     * TODO
     *
     * @param iterable   the entries to be filtered
     * @param predicate  the predicate to filter the entries with
     * @param comparator the comparator to sort the resulting set with
     * @param <E>        the type of the entries to be filtered
     * @return a {@link com.google.common.collect.ImmutableSortedSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E> SortedSet<E> createFrom(final Iterable<E> iterable, final Predicate<? super E> predicate, final Comparator<E> comparator) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(comparator != null, "comparator must not be null.");

        final ImmutableSortedSet.Builder<E> builder = ImmutableSortedSet.orderedBy(comparator);

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                builder.add(e);
            }
        }

        return builder.build();
    }

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered and transformed
     * @param predicate the predicate to filter the entries with
     * @param function  the function to transform the entries with
     * @param <F>       the type of the entries to be transformed
     * @param <T>       the type of the resulting entries
     * @return a {@link com.google.common.collect.ImmutableSortedSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T extends Comparable<T>> ImmutableSortedSet<T> createFrom(final Iterable<F> iterable,
                                                                                final Predicate<? super F> predicate,
                                                                                final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(function != null, "function must not be null.");

        final ImmutableSortedSet.Builder<T> builder = ImmutableSortedSet.naturalOrder();

        for (final F f : iterable) {
            if (predicate.apply(f)) {
                final T t = function.apply(f);
                builder.add(t);
            }
        }

        return builder.build();
    }

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered and transformed
     * @param predicate the predicate to filter the entries with
     * @param function  the function to transform the entries with
     * @param <F>       the type of the entries to be transformed
     * @param <T>       the type of the resulting entries
     * @return a {@link com.google.common.collect.ImmutableSortedSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> ImmutableSortedSet<T> createFrom(final Iterable<F> iterable,
                                                          final Predicate<? super F> predicate,
                                                          final Function<? super F, T> function,
                                                          final Comparator<T> comparator) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(function != null, "function must not be null.");
        checkArgument(comparator != null, "comparator must not be null.");

        final ImmutableSortedSet.Builder<T> builder = ImmutableSortedSet.orderedBy(comparator);

        for (final F f : iterable) {
            if (predicate.apply(f)) {
                final T t = function.apply(f);
                builder.add(t);
            }
        }

        return builder.build();
    }

}
