package com.codereligion.cherry.collect;

import com.codereligion.cherry.matcher.IsNotInstantiatable;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class OptimizedIterationsTest {

    @Test
    public void isNotInstantiateable() {
        assertThat(OptimizedIterations.class, IsNotInstantiatable.isNotInstantiatable());
    }
}