package com.mefrreex.chestcreator.utils;

public interface TriConsumer<K, V, S> {

    void accept(K k, V v, S s);
}
