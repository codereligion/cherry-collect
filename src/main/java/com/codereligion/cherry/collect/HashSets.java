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
import com.google.common.collect.Sets;
import java.util.HashSet;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * Factory for {@link java.util.HashSet HashSets}.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 28.12.2014
 */
public final class HashSets {

    private HashSets() {
        throw new IllegalAccessError("This class is a utility class and must not be instantiated.");
    }

    /**
     * Creates a new instance from the given {@code iterable} by converting each entry with the given {@code function}.
     *
     * @param iterable the entries to be transformed
     * @param function the function to transform the entries with
     * @param <F>      the type of the entries to be transformed
     * @param <T>      the type of the resulting entries
     * @return a {@link java.util.HashSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> HashSet<T> createFrom(final Iterable<F> iterable, final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");

        final HashSet<T> transformed = Sets.newHashSet();

        for (final F f : iterable) {
            final T t = function.apply(f);
            transformed.add(t);
        }

        return transformed;
    }

    /**
     * Creates a new instance from the given {@code iterable} by only using the entries to which the given {@code predicate} applies.
     *
     * @param iterable  the entries to be filtered
     * @param predicate the predicate to filter the entries with
     * @param <E>       the type of the entries to be filtered
     * @return a {@link java.util.HashSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E> HashSet<E> createFrom(final Iterable<E> iterable, final Predicate<? super E> predicate) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");

        final HashSet<E> filtered = Sets.newHashSet();

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                filtered.add(e);
            }
        }

        return filtered;
    }

    /**
     * Creates a new instance from the given {@code iterable} by converting each entry with the given {@code function} if the given {@code predicate} applies.
     *
     * @param iterable  the entries to be filtered and transformed
     * @param predicate the predicate to filter the entries with
     * @param function  the function to transform the entries with
     * @param <F>       the type of the entries to be transformed
     * @param <T>       the type of the resulting entries
     * @return a {@link java.util.HashSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> HashSet<T> createFrom(final Iterable<F> iterable, final Predicate<? super F> predicate, final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(function != null, "function must not be null.");

        final HashSet<T> transformed = Sets.newHashSet();

        for (final F f : iterable) {
            if (predicate.apply(f)) {
                final T t = function.apply(f);
                transformed.add(t);
            }
        }

        return transformed;
    }

}
