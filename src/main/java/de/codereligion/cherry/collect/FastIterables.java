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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * TODO class documentation
 * TODO method documentation (text, param, generic types, return values, throws declaration)
 * TODO when to use class references
 *
 * @author Sebastian Gr&oml;bler
 * @since 28.12.2014
 */
public final class FastIterables {

    private FastIterables() {
        throw new IllegalAccessError("This class is a utility class and must not be instantiated.");
    }

    /**
     * TODO
     *
     * @param iterable    the entries to be mapped
     * @param keyFunction the function to retrieve the map key from an entry
     * @param <K>         the type of the keys of the resulting map
     * @param <V>         the type of the values of the resulting map
     * @return a {@link java.util.HashMap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V> HashMap<K, V> transformToHashMap(final Iterable<V> iterable, final Function<? super V, K> keyFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");

        final HashMap<K, V> map = Maps.newHashMap();

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
     * @param keyFunction   the function to retrieve the map key from an entry
     * @param valueFunction the function to retrieve the map value from an entry
     * @param <E>           the type of the entries provided by the given {@code iterable}
     * @param <K>           the type of the keys of the resulting map
     * @param <V>           the type of the values of the resulting map
     * @return a {@link java.util.HashMap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E, K, V> HashMap<K, V> transformToHashMap(final Iterable<E> iterable,
                                                             final Function<? super E, K> keyFunction,
                                                             final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        final HashMap<K, V> map = Maps.newHashMap();

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
     * @param iterable    the entries to be mapped
     * @param keyFunction the function to retrieve the map key from an entry
     * @param <K>         the type of the keys of the resulting map
     * @param <V>         the type of the values of the resulting map
     * @return a {@link com.google.common.collect.HashMultimap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V> HashMultimap<K, V> transformToHashMultimap(final Iterable<V> iterable, final Function<? super V, K> keyFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");

        final HashMultimap<K, V> map = HashMultimap.create();

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
     * @return a {@link com.google.common.collect.HashMultimap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E, K, V> HashMultimap<K, V> transformToHashMultimap(final Iterable<E> iterable,
                                                                       final Function<? super E, K> keyFunction,
                                                                       final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        final HashMultimap<K, V> map = HashMultimap.create();

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
     * @param iterable    the entries to be mapped
     * @param keyFunction the function to retrieve the map key from an entry
     * @param <K>         the type of the keys of the resulting map
     * @param <V>         the type of the values of the resulting map
     * @return a {@link com.google.common.collect.ArrayListMultimap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V> ArrayListMultimap<K, V> transformToArrayListMultimap(final Iterable<V> iterable, final Function<? super V, K> keyFunction) {

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
    public static <K, V, E> ArrayListMultimap<K, V> transformToArrayListMultimap(final Iterable<E> iterable,
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
     * @param iterable the entries to be mapped
     * @param function the function to transform the entries with
     * @param <F>      the type of the entries to be transformed
     * @param <T>      the type of the resulting entries
     * @return a {@link java.util.ArrayList}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> ArrayList<T> transformToArrayList(final Iterable<F> iterable, final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");

        final ArrayList<T> transformed = Lists.newArrayList();

        for (final F f : iterable) {
            final T t = function.apply(f);
            transformed.add(t);
        }

        return transformed;
    }

    /**
     * TODO
     *
     * @param iterable the entries to be mapped
     * @param function the function to transform the entries with
     * @param <F>      the type of the entries to be transformed
     * @param <T>      the type of the resulting entries
     * @return a {@link java.util.LinkedList}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> LinkedList<T> transformToLinkedList(final Iterable<F> iterable, final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");

        final LinkedList<T> transformed = Lists.newLinkedList();

        for (final F f : iterable) {
            final T t = function.apply(f);
            transformed.add(t);
        }

        return transformed;
    }

    /**
     * TODO
     *
     * @param iterable the entries to be mapped
     * @param function the function to transform the entries with
     * @param <F>      the type of the entries to be transformed
     * @param <T>      the type of the resulting entries
     * @return a {@link java.util.HashSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> HashSet<T> transformToHashSet(final Iterable<F> iterable, final Function<? super F, T> function) {

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
     * TODO
     *
     * @param iterable the entries to be mapped
     * @param function the function to transform the entries with
     * @param <F>      the type of the entries to be transformed
     * @param <T>      the type of the resulting entries
     * @return a {@link java.util.TreeSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T extends Comparable<T>> TreeSet<T> transformToTreeSet(final Iterable<F> iterable, final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");

        final TreeSet<T> transformed = new TreeSet<T>();

        for (final F f : iterable) {
            final T t = function.apply(f);
            transformed.add(t);
        }

        return transformed;
    }

    /**
     * TODO
     *
     * @param iterable   the entries to be mapped
     * @param function   the function to transform the entries with
     * @param comparator the comparator to sort the resulting set with
     * @param <F>        the type of the entries to be transformed
     * @param <T>        the type of the resulting entries
     * @return a {@link java.util.TreeSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> TreeSet<T> transformToTreeSet(final Iterable<F> iterable, final Function<? super F, T> function, final Comparator<T> comparator) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(function != null, "function must not be null.");
        checkArgument(comparator != null, "comparator must not be null.");

        final TreeSet<T> transformed = new TreeSet<T>(comparator);

        for (final F f : iterable) {
            final T t = function.apply(f);
            transformed.add(t);
        }

        return transformed;
    }

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered
     * @param predicate the predicate to filter the entries with
     * @param <E>       the type of the entries to be filtered
     * @return a {@link java.util.ArrayList}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E> ArrayList<E> filterToArrayList(final Iterable<E> iterable, final Predicate<? super E> predicate) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");


        final ArrayList<E> filtered = Lists.newArrayList();

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                filtered.add(e);
            }
        }

        return filtered;
    }

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered
     * @param predicate the predicate to filter the entries with
     * @param <E>       the type of the entries to be filtered
     * @return a {@link java.util.LinkedList}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E> LinkedList<E> filterToLinkedList(final Iterable<E> iterable, final Predicate<? super E> predicate) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");


        final LinkedList<E> filtered = Lists.newLinkedList();

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                filtered.add(e);
            }
        }

        return filtered;
    }

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered
     * @param predicate the predicate to filter the entries with
     * @param <E>       the type of the entries to be filtered
     * @return a {@link java.util.HashSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E> Set<E> filterToHashSet(final Iterable<E> iterable, final Predicate<? super E> predicate) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");

        final Set<E> filtered = Sets.newHashSet();

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                filtered.add(e);
            }
        }

        return filtered;
    }

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered
     * @param predicate the predicate to filter the entries with
     * @param <E>       the type of the entries to be filtered
     * @return a {@link java.util.TreeSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E extends Comparable<E>> TreeSet<E> filterToTreeSet(final Iterable<E> iterable, final Predicate<? super E> predicate) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");

        final TreeSet<E> filtered = new TreeSet<E>();

        for (final E e : iterable) {
            if (predicate.apply(e)) {
                filtered.add(e);
            }
        }

        return filtered;
    }

    /**
     * TODO
     *
     * @param iterable   the entries to be filtered
     * @param predicate  the predicate to filter the entries with
     * @param comparator the comparator to sort the resulting set with
     * @param <E>        the type of the entries to be filtered
     * @return a {@link java.util.TreeSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E> SortedSet<E> filterToTreeSet(final Iterable<E> iterable, final Predicate<? super E> predicate, final Comparator<E> comparator) {

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
     * TODO
     *
     * @param iterable      the entries to be filtered and transformed
     * @param predicate     the predicate to filter the entries with
     * @param keyFunction   the function to retrieve the key from the entry
     * @param valueFunction the function to retrieve the value from the entry
     * @param <E>           the type of the entries provided by the given {@code iterable}
     * @param <K>           the type of the keys of the resulting map
     * @param <V>           the type of the values of the resulting map
     * @return a {@link java.util.HashMap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E, K, V> HashMap<K, V> filterAndTransformToHashMap(final Iterable<E> iterable,
                                                                      final Predicate<? super E> predicate,
                                                                      final Function<? super E, K> keyFunction,
                                                                      final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        final HashMap<K, V> map = Maps.newHashMap();

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
     * TODO
     *
     * @param iterable      the entries to be filtered and transformed
     * @param predicate     the predicate to filter the entries with
     * @param keyFunction   the function to retrieve the key from the entry
     * @param valueFunction the function to retrieve the value from the entry
     * @param <E>           the type of the entries provided by the given {@code iterable}
     * @param <K>           the type of the keys of the resulting map
     * @param <V>           the type of the values of the resulting map
     * @return a {@link com.google.common.collect.HashMultimap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E, K, V> HashMultimap<K, V> filterAndTransformToHashMultimap(final Iterable<E> iterable,
                                                                                final Predicate<? super E> predicate,
                                                                                final Function<? super E, K> keyFunction,
                                                                                final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        final HashMultimap<K, V> map = HashMultimap.create();

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
    public static <E, K, V> ArrayListMultimap<K, V> filterAndTransformToArrayListMultimap(final Iterable<E> iterable,
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

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered and transformed
     * @param predicate the predicate to filter the entries with
     * @param function  the function to transform the entries with
     * @param <F>       the type of the entries to be transformed
     * @param <T>       the type of the resulting entries
     * @return a {@link java.util.ArrayList}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> ArrayList<T> filterAndTransformToArrayList(final Iterable<F> iterable,
                                                                    final Predicate<? super F> predicate,
                                                                    final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(function != null, "function must not be null.");

        final ArrayList<T> transformed = Lists.newArrayList();

        for (final F f : iterable) {
            if (predicate.apply(f)) {
                final T t = function.apply(f);
                transformed.add(t);
            }
        }

        return transformed;
    }

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered and transformed
     * @param predicate the predicate to filter the entries with
     * @param function  the function to transform the entries with
     * @param <F>       the type of the entries to be transformed
     * @param <T>       the type of the resulting entries
     * @return a {@link java.util.LinkedList}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> LinkedList<T> filterAndTransformToLinkedList(final Iterable<F> iterable,
                                                                      final Predicate<? super F> predicate,
                                                                      final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(function != null, "function must not be null.");

        final LinkedList<T> transformed = Lists.newLinkedList();

        for (final F f : iterable) {
            if (predicate.apply(f)) {
                final T t = function.apply(f);
                transformed.add(t);
            }
        }

        return transformed;
    }

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered and transformed
     * @param predicate the predicate to filter the entries with
     * @param function  the function to transform the entries with
     * @param <F>       the type of the entries to be transformed
     * @param <T>       the type of the resulting entries
     * @return a {@link java.util.HashSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> HashSet<T> filterAndTransformToHashSet(final Iterable<F> iterable,
                                                                final Predicate<? super F> predicate,
                                                                final Function<? super F, T> function) {

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

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered and transformed
     * @param predicate the predicate to filter the entries with
     * @param function  the function to transform the entries with
     * @param <F>       the type of the entries to be transformed
     * @param <T>       the type of the resulting entries
     * @return a {@link java.util.TreeSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T extends Comparable<T>> TreeSet<T> filterAndTransformToTreeSet(final Iterable<F> iterable,
                                                                                      final Predicate<? super F> predicate,
                                                                                      final Function<? super F, T> function) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(function != null, "function must not be null.");

        final TreeSet<T> transformed = new TreeSet<T>();

        for (final F f : iterable) {
            if (predicate.apply(f)) {
                final T t = function.apply(f);
                transformed.add(t);
            }
        }

        return transformed;
    }

    /**
     * TODO
     *
     * @param iterable  the entries to be filtered and transformed
     * @param predicate the predicate to filter the entries with
     * @param function  the function to transform the entries with
     * @param <F>       the type of the entries to be transformed
     * @param <T>       the type of the resulting entries
     * @return a {@link java.util.TreeSet}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <F, T> TreeSet<T> filterAndTransformToTreeSet(final Iterable<F> iterable,
                                                                final Predicate<? super F> predicate,
                                                                final Function<? super F, T> function,
                                                                final Comparator<T> comparator) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(function != null, "function must not be null.");
        checkArgument(comparator != null, "comparator must not be null.");

        final TreeSet<T> transformed = new TreeSet<T>();

        for (final F f : iterable) {
            if (predicate.apply(f)) {
                final T t = function.apply(f);
                transformed.add(t);
            }
        }

        return transformed;
    }
}
