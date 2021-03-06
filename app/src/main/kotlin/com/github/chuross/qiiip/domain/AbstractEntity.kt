package com.github.chuross.qiiip.domain

import org.apache.commons.lang3.SerializationUtils

abstract class AbstractEntity<I : Identity<*>, E : Entity<I, E>>(override val identity: I) : Entity<I, E> {

    override fun sameIdentity(entity: E): Boolean = identity == entity.identity

    override fun equals(that: E): Boolean = this == that || (that is E && sameIdentity(that))

    @Suppress("UNCHECKED_CAST")
    override fun clone(): E = SerializationUtils.clone(this) as E
}