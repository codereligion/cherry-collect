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
import com.google.common.collect.ImmutableCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class OptimizedIterations {

    private OptimizedIterations() {
        throw new IllegalAccessError("This class is a utility class and must not be instantiated.");
    }

    static <F, T, C extends Collection<T>> C createFrom(final Iterable<F> inputIterable, final Function<? super F, T> function, final C outputCollection) {

        if (inputIterable instanceof ArrayList) {
            final List<F> list = (List<F>) inputIterable;
            for (int i = 0; i < list.size(); i++) {
                outputCollection.add(function.apply(list.get(i)));
            }
        } else {
            for (final F f : inputIterable) {
                outputCollection.add(function.apply(f));
            }
        }
        return outputCollection;
    }

    static <E, C extends Collection<E>> C createFrom(final Iterable<E> inputIterable, final Predicate<? super E> predicate, final C outputCollection) {

        if (inputIterable instanceof ArrayList) {
            final List<E> list = (List<E>) inputIterable;
            for (int i = 0; i < list.size(); i++) {
                final E e = list.get(i);
                if (predicate.apply(e)) {
                    outputCollection.add(e);
                }
            }
        } else {
            for (final E e : inputIterable) {
                if (predicate.apply(e)) {
                    outputCollection.add(e);
                }
            }
        }
        return outputCollection;
    }

    static <F, T, C extends Collection<T>> C createFrom(final Iterable<F> inputIterable,
                                                        final Predicate<? super F> predicate,
                                                        final Function<? super F, T> function,
                                                        final C outputCollection) {
        if (inputIterable instanceof ArrayList) {
            final List<F> list = (List<F>) inputIterable;
            for (int i = 0; i < list.size(); i++) {
                final F f = list.get(i);
                if (predicate.apply(f)) {
                    outputCollection.add(function.apply(f));
                }
            }
        } else {
            for (final F f : inputIterable) {
                if (predicate.apply(f)) {
                    outputCollection.add(function.apply(f));
                }
            }
        }
        return outputCollection;
    }

    static <F, T, B extends ImmutableCollection.Builder<T>> B createFrom(final Iterable<F> inputIterable,
                                                                         final Function<? super F, T> function,
                                                                         final B builder) {

        if (inputIterable instanceof ArrayList) {
            final List<F> list = (List<F>) inputIterable;
            for (int i = 0; i < list.size(); i++) {
                builder.add(function.apply(list.get(i)));
            }
        } else {
            for (final F f : inputIterable) {
                builder.add(function.apply(f));
            }
        }
        return builder;
    }

    static <E, B extends ImmutableCollection.Builder<E>> B createFrom(final Iterable<E> inputIterable, final Predicate<? super E> predicate, final B builder) {

        if (inputIterable instanceof ArrayList) {
            final List<E> list = (List<E>) inputIterable;
            for (int i = 0; i < list.size(); i++) {
                final E e = list.get(i);
                if (predicate.apply(e)) {
                    builder.add(e);
                }
            }
        } else {
            for (final E e : inputIterable) {
                if (predicate.apply(e)) {
                    builder.add(e);
                }
            }
        }
        return builder;
    }

    static <F, T, B extends ImmutableCollection.Builder<T>> B createFrom(final Iterable<F> inputIterable,
                                                                         final Predicate<? super F> predicate,
                                                                         final Function<? super F, T> function,
                                                                         final B builder) {
        if (inputIterable instanceof ArrayList) {
            final List<F> list = (List<F>) inputIterable;
            for (int i = 0; i < list.size(); i++) {
                final F f = list.get(i);
                if (predicate.apply(f)) {
                    builder.add(function.apply(f));
                }
            }
        } else {
            for (final F f : inputIterable) {
                if (predicate.apply(f)) {
                    builder.add(function.apply(f));
                }
            }
        }
        return builder;
    }
}
