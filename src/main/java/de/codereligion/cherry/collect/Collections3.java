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
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * Provides static utility methods for collections.
 * This class is thread-safe.
 *
 * @author sgroebler
 * @since 29.12.2011
 */
public final class Collections3 {

    private Collections3() {
        throw new IllegalAccessError("This class is a utility class and must not be instantiated.");
    }

    /**
     * Transforms the given {@link Iterable} to a {@link java.util.Map} using the given {@link com.google.common.base.Function}
     * whereas the function's {@code V} is used as value and the function's {@code K}
     * is used as the key of the resulting map.
     * <p/>
     * <p>The resulting {@link java.util.Map} is hash based.
     *
     * @param iterable a {@link Iterable} of values to be mapped
     * @param function the function to retrieve the key for the value
     * @return a {@link java.util.Map}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V> Map<K, V> transformToMap(final Iterable<V> iterable, final Function<? super V, K> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");

        final Map<K, V> map = Maps.newHashMap();

        for (final V value : iterable) {
            final K key = function.apply(value);
            map.put(key, value);
        }

        return map;
    }

    /**
     * Transforms the given {@link Iterable} to a {@link java.util.Map} using the given {@code keyFunction}
     * and {@code valueFunction} to retrieve the map key and value from the iterable entry.
     * <p/>
     * <p>The resulting {@link java.util.Map} is hash based.
     *
     * @param iterable      a {@link Iterable} of entries to be mapped
     * @param keyFunction   the function to retrieve the key from the entry
     * @param valueFunction the function to retrieve the value from the entry
     * @return a {@link java.util.Map}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V, E> Map<K, V> transformToMap(final Iterable<E> iterable,
                                                     final Function<? super E, K> keyFunction,
                                                     final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        final Map<K, V> map = Maps.newHashMap();

        for (final E entry : iterable) {
            final K key = keyFunction.apply(entry);
            final V value = valueFunction.apply(entry);
            map.put(key, value);
        }

        return map;
    }

    /**
     * Transforms the given {@link Iterable} to a {@link com.google.common.collect.Multimap} using the given {@link com.google.common.base.Function}
     * whereas the function's {@code V} is used as value and the function's {@code K}
     * is used as the key of the resulting {@link java.util.Map}.
     *
     * @param iterable a {@link Iterable} of values to be mapped
     * @param function the function to retrieve the key for the value
     * @return a {@link com.google.common.collect.SetMultimap}, build from the values, by the given function
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V> SetMultimap<K, V> transformToSetMultimap(final Iterable<V> iterable, final Function<? super V, K> function) {

        checkArgument(iterable != null, "Iterable must not be null.");
        checkArgument(function != null, "function must not be null.");

        final SetMultimap<K, V> map = HashMultimap.create();

        for (final V value : iterable) {
            final K key = function.apply(value);
            map.put(key, value);
        }

        return map;
    }

    /**
     * Transforms the given {@link Iterable} to a {@link com.google.common.collect.SetMultimap} using the given {@code keyFunction}
     * and {@code valueFunction} to retrieve the map key and value from the iterable entry.
     * <p/>
     * <p>The resulting {@link com.google.common.collect.SetMultimap} is hash based.
     *
     * @param iterable      a {@link Iterable} of entries to be mapped
     * @param keyFunction   the function to retrieve the key from the entry
     * @param valueFunction the function to retrieve the value from the entry
     * @return a {@link com.google.common.collect.SetMultimap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V, E> SetMultimap<K, V> transformToSetMultimap(final Iterable<E> iterable,
                                                                     final Function<? super E, K> keyFunction,
                                                                     final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        final SetMultimap<K, V> map = HashMultimap.create();

        for (final E entry : iterable) {
            final K key = keyFunction.apply(entry);
            final V value = valueFunction.apply(entry);
            map.put(key, value);
        }

        return map;
    }

    /**
     * Transforms the given {@link Iterable} to a {@link com.google.common.collect.SetMultimap} using the given {@code keyFunction}
     * and {@code valueFunction} to retrieve the map key and value from the iterable entry if the predicate used for filtering matches.
     * <p/>
     * <p>The resulting {@link com.google.common.collect.SetMultimap} is hash based.
     *
     * @param iterable      a {@link Iterable} of entries to be mapped
     * @param keyFunction   the function to retrieve the key from the entry
     * @param valueFunction the function to retrieve the value from the entry
     * @return a {@link com.google.common.collect.SetMultimap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V, E> SetMultimap<K, V> filterAndTransformToSetMultimap(final Iterable<E> iterable,
                                                                              final Predicate<? super E> predicate,
                                                                              final Function<? super E, K> keyFunction,
                                                                              final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        final SetMultimap<K, V> map = HashMultimap.create();

        for (final E entry : iterable) {
            if (predicate.apply(entry)) {
                final K key = keyFunction.apply(entry);
                final V value = valueFunction.apply(entry);
                map.put(key, value);
            }
        }

        return map;
    }

    /**
     * Transforms the given {@link Iterable} to a {@link com.google.common.collect.ListMultimap ListMultimap}
     * using the given {@link com.google.common.base.Function} whereas the function's {@code V} is used as
     * value and the function's {@code K} is used as the key of the resulting map.
     *
     * @param iterable    a {@link Iterable} of values to be mapped
     * @param keyFunction the function to retrieve the key for the value
     * @return a {@link com.google.common.collect.ListMultimap}, build from the values by the given function
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V> ListMultimap<K, V> transformToListMultimap(final Iterable<V> iterable, final Function<? super V, K> keyFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");

        final ListMultimap<K, V> map = ArrayListMultimap.create();

        for (final V value : iterable) {
            final K key = keyFunction.apply(value);
            map.put(key, value);
        }

        return map;
    }

    /**
     * Filters the given {@link Iterable} and returns a new {@link java.util.List}
     * containing only those entries which apply to the given {@link com.google.common.base.Predicate}.
     * <p/>
     * <p>If {@code unfiltered} implements {@link java.util.RandomAccess}, so will the returned {@link java.util.List}.
     *
     * @param iterable  the {@link Iterable} to be filtered
     * @param predicate the {@link com.google.common.base.Predicate} to filter the given {@link Iterable} with
     * @return a {@link java.util.List}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E> List<E> filterToList(final Iterable<E> iterable, final Predicate<? super E> predicate) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");


        final List<E> filtered;

        if (iterable instanceof RandomAccess) {
            filtered = new ArrayList<E>();
        } else {
            filtered = new LinkedList<E>();
        }

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                filtered.add(e);
            }
        }

        return filtered;
    }

    /**
     * Filters the given {@link Iterable} and returns a new {@link java.util.Set}
     * containing only those entries which apply to the given {@link com.google.common.base.Predicate}.
     * <p/>
     * <p>The resulting {@link java.util.Set} is hash based.
     *
     * @param iterable  the {@link Iterable} to be filtered
     * @param predicate the {@link com.google.common.base.Predicate} to filter the given {@link Iterable} with
     * @return a {@link java.util.List}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E> Set<E> filterToSet(final Iterable<E> iterable, final Predicate<? super E> predicate) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");

        final Set<E> filtered = new HashSet<E>();

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                filtered.add(e);
            }
        }

        return filtered;
    }

    /**
     * Filters the given {@link Iterable} and returns a new {@link java.util.SortedSet}
     * containing only those entries which apply to the given {@link com.google.common.base.Predicate}.
     *
     * @param iterable  the {@link Iterable} to be filtered
     * @param predicate the {@link com.google.common.base.Predicate} to filter the given {@link Iterable} with
     * @return a {@link java.util.List}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E extends Comparable<E>> SortedSet<E> filterToSortedSet(final Iterable<E> iterable, final Predicate<? super E> predicate) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");

        final SortedSet<E> filtered = new TreeSet<E>();

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                filtered.add(e);
            }
        }

        return filtered;
    }

    /**
     * The given {@link Iterable} is filtered and sorted by applying the given
     * {@link com.google.common.base.Predicate} and {@link java.util.Comparator}.
     *
     * @param iterable   the {@link Iterable} to be filtered
     * @param predicate  the {@link com.google.common.base.Predicate} to filter the given {@link Iterable} with
     * @param comparator the {@link java.util.Comparator} to sort the given {@link Iterable} with
     * @return a {@link java.util.List}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E> SortedSet<E> filterToSortedSet(final Iterable<E> iterable, final Predicate<? super E> predicate, final Comparator<E> comparator) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(comparator != null, "comparator must not be null.");

        final SortedSet<E> filtered = new TreeSet<E>(comparator);

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                filtered.add(e);
            }
        }

        return filtered;
    }

    /**
     * Returns a new {@link java.util.List} that applies {@code function} to each element of
     * {@code iterable}.
     * <p/>
     * <p>If {@code iterable} implements {@link java.util.RandomAccess},
     * so will the returned list.
     *
     * @param iterable the {@link Iterable} to be transformed
     * @param function the {@link com.google.common.base.Function} to transform the given {@link Iterable} with
     * @return a {@link java.util.List}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> List<T> transformToList(final Iterable<F> iterable, final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");

        final List<T> transformed;
        if (iterable instanceof RandomAccess) {
            transformed = Lists.newArrayList();
        } else {
            transformed = new LinkedList<T>();
        }

        for (final F f : iterable) {
            final T t = function.apply(f);
            transformed.add(t);
        }

        return transformed;
    }

    /**
     * Returns a new {@link java.util.Set} that applies {@code function} to each element of
     * {@code iterable}.
     * <p/>
     * <p>The resulting {@link java.util.Set} is hash based.
     *
     * @param iterable the {@link Iterable} to be transformed
     * @param function the {@link com.google.common.base.Function} to transform the given {@link Iterable} with
     * @return a {@link java.util.Set}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> Set<T> transformToSet(final Iterable<F> iterable, final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");

        final Set<T> transformed = Sets.newHashSet();

        for (final F f : iterable) {
            final T t = function.apply(f);
            transformed.add(t);
        }

        return transformed;
    }

    /**
     * Returns a new {@link java.util.Set} that applies {@code function} to each element of
     * {@code iterable} if the predicate used for filtering matches.
     * <p/>
     * <p>The resulting {@link java.util.Set} is hash based.
     *
     * @param iterable  the {@link Iterable} to be transformed
     * @param predicate the {@link com.google.common.base.Predicate} to filter the given {@link Iterable} with
     * @param function  the {@link com.google.common.base.Function} to transform the given {@link Iterable} with
     * @return a {@link java.util.Set}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> Set<T> filterAndTransformToSet(final Iterable<F> iterable,
                                                        final Predicate<? super F> predicate,
                                                        final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");

        final Set<T> transformed = Sets.newHashSet();

        for (final F f : iterable) {
            if (predicate.apply(f)) {
                final T t = function.apply(f);
                transformed.add(t);
            }
        }

        return transformed;
    }

    /**
     * Returns a new {@link java.util.List} that applies {@code function} to each element of
     * {@code iterable} if the predicate used for filtering matches.
     * <p/>
     * <p>The resulting {@link java.util.List} is hash based.
     *
     * @param iterable  the {@link Iterable} to be transformed
     * @param predicate the {@link com.google.common.base.Predicate} to filter the given {@link Iterable} with
     * @param function  the {@link com.google.common.base.Function} to transform the given {@link Iterable} with
     * @return a {@link java.util.List}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> List<T> filterAndTransformToList(final Iterable<F> iterable,
                                                          final Predicate<? super F> predicate,
                                                          final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");

        final List<T> transformed = Lists.newArrayList();

        for (final F f : iterable) {
            if (predicate.apply(f)) {
                final T t = function.apply(f);
                transformed.add(t);
            }
        }

        return transformed;
    }
}
