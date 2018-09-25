/*
 * Copyright (C) 2012 Square, Inc.
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

package com.lcx.otto

import android.os.Looper
import android.view.View

/**
 * Enforces a thread confinement policy for methods on a particular event bus.
 *
 * @author Jake Wharton
 */
interface ThreadEnforcer {

    /**
     * Enforce a valid thread for the given `bus`. Implementations may throw any runtime exception.
     *
     * @param bus Event bus instance on which an action is being performed.
     */
    fun enforce(bus: Bus)

    companion object {


        /** A [ThreadEnforcer] that does no verification.  */
        val ANY: ThreadEnforcer = object : ThreadEnforcer {
            override fun enforce(bus: Bus) {
                // Allow any thread.
            }
        }

        /** A [ThreadEnforcer] that confines [Bus] methods to the main thread.  */
        val MAIN: ThreadEnforcer = object : ThreadEnforcer {
            override fun enforce(bus: Bus) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    throw IllegalStateException("Event bus " + bus + " accessed from non-main thread " + Looper.myLooper())
                }
            }
        }
    }

}
