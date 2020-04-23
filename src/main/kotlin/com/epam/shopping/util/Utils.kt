package com.epam.shopping.util

import org.slf4j.LoggerFactory

fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz)