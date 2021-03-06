/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package cn.com.nightfield.jvm.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * This class defines the class loader for loading applet classes and
 * resources. It extends URLClassLoader to search the applet code base
 * for the class or resource after checking any loaded JAR files.
 */
public class CustomizedClassLoader extends URLClassLoader {

    public CustomizedClassLoader(URL[] urls) {
        super(urls);
    }

    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            Class<?> c;
            if (!name.startsWith("jvm.classloader")) {
                return super.loadClass(name, resolve);
            }
            else {
                c = findClass(name);
                if (resolve) {
                    resolveClass(c);
                }
            }
            return c;
        }
    }
}