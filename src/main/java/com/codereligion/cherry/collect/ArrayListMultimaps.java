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
import com.google.common.collect.ArrayListMultimap;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * TODO
 *
 * @author Sebastian Gr&oml;bler
 * @since 28.12.2014
 */
public final class ArrayListMultimaps {

    private ArrayListMultimaps() {
        throw new IllegalAccessError("This class is a utility class and must not be instantiated.");
    }

    /**
     * TODO
     *
     * @param iterable    the entries to be mapped
     * @param keyFunction the function to retrieve the map key from an entry
     * @param <K>         the type of the keys of the resulting map
     * @param <V>         the type of the values of the resulting map
     * @return a {@link com.google.common.collect.ArrayListMultimap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V> ArrayListMultimap<K, V> createFrom(final Iterable<V> iterable, final Function<? super V, K> keyFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");

        final ArrayListMultimap<K, V> map = ArrayListMultimap.create();

        for (final V value : iterable) {
            final K key = keyFunction.apply(value);
            map.put(key, value);
        }

        return map;
    }

    /**
     * TODO
     *
     * @param iterable      the entries to be mapped
     * @param keyFunction   the function to retrieve the key from the entry
     * @param valueFunction the function to retrieve the value from the entry
     * @param <E>           the type of the entries provided by the given {@code iterable}
     * @param <K>           the type of the keys of the resulting map
     * @param <V>           the type of the values of the resulting map
     * @return a {@link com.google.common.collect.ArrayListMultimap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V, E> ArrayListMultimap<K, V> createFrom(final Iterable<E> iterable,
                                                         final Function<? super E, K> keyFunction,
                                                         final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        final ArrayListMultimap<K, V> map = ArrayListMultimap.create();

        for (final E entry : iterable) {
            final K key = keyFunction.apply(entry);
            final V value = valueFunction.apply(entry);
            map.put(key, value);
        }

        return map;
    }

    /**
     * TODO
     *
     * @param iterable    the entries to be filtered and transformed
     * @param predicate   the predicate to filter the entries with
     * @param keyFunction the function to retrieve the key from the entry
     * @param <K>         the type of the keys of the resulting map
     * @param <V>         the type of the values of the resulting map
     * @return a {@link com.google.common.collect.ArrayListMultimap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V> ArrayListMultimap<K, V> createFrom(final Iterable<V> iterable,
                                                      final Predicate<? super V> predicate,
                                                      final Function<? super V, K> keyFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");

        final ArrayListMultimap<K, V> map = ArrayListMultimap.create();

        for (final V entry : iterable) {
            if (predicate.apply(entry)) {
                final K key = keyFunction.apply(entry);
                map.put(key, entry);
            }
        }

        return map;
    }

    /**
     * TODO
     *
     * @param iterable      the entries to be filtered and transformed
     * @param predicate     the predicate to filter the entries with
     * @param keyFunction   the function to retrieve the key from the entry
     * @param valueFunction the function to retrieve the value from the entry
     * @param <E>           the type of the entries provided by the given {@code iterable}
     * @param <K>           the type of the keys of the resulting map
     * @param <V>           the type of the values of the resulting map
     * @return a {@link com.google.common.collect.ArrayListMultimap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E, K, V> ArrayListMultimap<K, V> createFrom(final Iterable<E> iterable,
                                                         final Predicate<? super E> predicate,
                                                         final Function<? super E, K> keyFunction,
                                                         final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        final ArrayListMultimap<K, V> map = ArrayListMultimap.create();

        for (final E entry : iterable) {
            if (predicate.apply(entry)) {
                final K key = keyFunction.apply(entry);
                final V value = valueFunction.apply(entry);
                map.put(key, value);
            }
        }

        return map;
    }
}
