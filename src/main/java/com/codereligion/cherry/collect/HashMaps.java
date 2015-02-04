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
import java.util.HashMap;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * Factory for {@link java.util.HashMap HashMaps}.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 28.12.2014
 */
public final class HashMaps {

    private HashMaps() {
        throw new IllegalAccessError("This class is a utility class and must not be instantiated.");
    }

    /**
     * Creates a new instance from the given {@code iterable}. Each iterable entry is mapped by its {@code keyFunction} result.
     *
     * @param iterable    the entries to be mapped
     * @param keyFunction the function to retrieve the map key from an entry
     * @param <K>         the type of the keys of the resulting map
     * @param <V>         the type of the values of the resulting map
     * @return a {@link java.util.HashMap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V> HashMap<K, V> createFrom(final Iterable<V> iterable, final Function<? super V, K> keyFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");

        return OptimizedIterations.createFrom(iterable, keyFunction, new HashMap<K, V>());
    }

    /**
     * Creates a new instance from the given {@code iterable}. Each iterable entry is mapped from its {@code keyFunction} result to its {@code valueFunction}
     * result.
     *
     * @param iterable      the entries to be mapped
     * @param keyFunction   the function to retrieve the map key from an entry
     * @param valueFunction the function to retrieve the map value from an entry
     * @param <E>           the type of the entries of the given {@code iterable}
     * @param <K>           the type of the keys of the resulting map
     * @param <V>           the type of the values of the resulting map
     * @return a {@link java.util.HashMap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E, K, V> HashMap<K, V> createFrom(final Iterable<E> iterable,
                                                     final Function<? super E, K> keyFunction,
                                                     final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        return OptimizedIterations.createFrom(iterable, keyFunction, valueFunction, new HashMap<K, V>());
    }


    /**
     * Creates a new instance from the given {@code iterable}. Each iterable entry is mapped by its {@code keyFunction} result, if the given {@code predicate}
     * applies.
     *
     * @param iterable    the entries to be filtered and mapped
     * @param predicate   the predicate to filter the entries with
     * @param keyFunction the function to retrieve the map key from an entry
     * @param <K>         the type of the keys of the resulting map
     * @param <V>         the type of the values of the resulting map
     * @return a {@link java.util.HashMap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <K, V> HashMap<K, V> createFrom(final Iterable<V> iterable, final Predicate<? super V> predicate, final Function<? super V, K> keyFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");

        return OptimizedIterations.createFrom(iterable, predicate, keyFunction, new HashMap<K, V>());
    }

    /**
     * Creates a new instance from the given {@code iterable}. Each iterable entry is mapped from its {@code keyFunction} result to its {@code valueFunction}
     * result, if the given {@code predicate} applies.
     *
     * @param iterable      the entries to be filtered and mapped
     * @param predicate     the predicate to filter the entries with
     * @param keyFunction   the function to retrieve the map key from an entry
     * @param valueFunction the function to retrieve the map value from an entry
     * @param <E>           the type of the entries of the given {@code iterable}
     * @param <K>           the type of the keys of the resulting map
     * @param <V>           the type of the values of the resulting map
     * @return a {@link java.util.HashMap}, might be empty
     * @throws IllegalArgumentException when any of the given parameters are {@code null}
     */
    public static <E, K, V> HashMap<K, V> createFrom(final Iterable<E> iterable,
                                                     final Predicate<? super E> predicate,
                                                     final Function<? super E, K> keyFunction,
                                                     final Function<? super E, V> valueFunction) {

        checkArgument(iterable != null, "iterable must not be null.");
        checkArgument(predicate != null, "predicate must not be null.");
        checkArgument(keyFunction != null, "keyFunction must not be null.");
        checkArgument(valueFunction != null, "valueFunction must not be null.");

        return OptimizedIterations.createFrom(iterable, predicate, keyFunction, valueFunction, new HashMap<K, V>());
    }
}
