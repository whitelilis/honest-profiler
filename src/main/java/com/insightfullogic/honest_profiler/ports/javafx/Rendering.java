/**
 * Copyright (c) 2014 Richard Warburton (richard.warburton@gmail.com)
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 **/
package com.insightfullogic.honest_profiler.ports.javafx;

import com.insightfullogic.honest_profiler.core.collector.FlatProfileEntry;
import com.insightfullogic.honest_profiler.core.collector.Frame;
import com.insightfullogic.honest_profiler.core.parser.Method;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;

import java.text.MessageFormat;

import static javafx.scene.control.TableColumn.CellDataFeatures;

public class Rendering
{

    public static String renderTimeShare(double timeShare)
    {
        return MessageFormat.format("{0,number,0.00%}", timeShare);
    }

    public static SimpleObjectProperty<String> method(CellDataFeatures<FlatProfileEntry, String> features)
    {
        Frame method = features.getValue().getFrameInfo();
        String representation = renderMethod(method);
        return new ReadOnlyObjectWrapper<>(representation);
    }

    public static String renderMethod(Frame method)
    {
        if (method == null)
            return "unknown";

        return method.getClassName() + "." + method.getMethodName();
    }

    public static String renderShortMethod(Method method)
    {
        String className = method.getClassName();
        int index = className.lastIndexOf('.');
        String shortClassName = index == -1 ? className : className.substring(index + 1);
        return shortClassName + "." + method.getMethodName();
    }

}
