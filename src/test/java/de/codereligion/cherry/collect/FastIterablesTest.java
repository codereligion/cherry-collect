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

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Map;
import org.junit.Test;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;

/**
 * TODO
 *
 * @author Sebastian Gr&ouml;bler
 * @since 28.12.2014
 */
public class FastIterablesTest {

    // TODO test contract (parameter validation, return value)


    @Test
    public void transformsToMap() {

        // given
        final Collection<Integer> integers = Lists.newArrayList(1, 2, 3);

        // when
        final Map<String, Integer> map = FastIterables.transformToHashMap(integers, Functions.toStringFunction());

        // then
        assertThat(map, hasEntry("1", 1));
        assertThat(map, hasEntry("2", 2));
        assertThat(map, hasEntry("3", 3));
    }

}