
/*
 * Janino - An embedded Java[TM] compiler
 *
 * Copyright (c) 2001-2007, Arno Unkrig
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *    1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *    3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior
 *       written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.codehaus.janino.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link java.io.FilterWriter} that automatically indents lines by looking at
 * trailing opening braces ('{') and leading closing braces ('}').
 */
public class AutoIndentWriter extends FilterWriter {
    public static final char ALIGN         = '\uffff';
    public static final char FLUSH_TABBING = '\ufffe';
    public static final char INDENT        = '\ufffd';
    public static final char UNINDENT      = '\ufffc';

    private int                    previousChar = -1;
    private List/*<StringBuffer>*/ indentation = new ArrayList();
    {
        this.indentation.add(null);
    }
    private String                 prefix = null;

    public AutoIndentWriter(Writer out) {
        super(out);
    }

    public void write(int c) throws IOException {
        if (c == FLUSH_TABBING) {
            this.flushTabbing();
            return;
        }

        if (c == '\r' || c == '\n') {
            if (this.prefix == null && this.previousChar == '{') this.indent();
        }
        StringBuffer indent = (StringBuffer) this.indentation.get(this.indentation.size() - 1);
        if (c == ALIGN && indent == null) {
            indent = new StringBuffer();
            this.indentation.set(this.indentation.size() - 1, indent);
        }
        if (indent == null) {
            if (
                (this.previousChar == '\r' && c != '\n')
                || this.previousChar == '\n'
            ) {
                if (this.prefix == null && c == '}') this.unindent();
                for (int i = this.indentation.size(); i > 0; --i) this.out.write("    ");
                if (this.prefix != null) this.out.write(this.prefix);
            }
            this.out.write(c);
        } else
        {
            indent.append((char) c);
        }
        this.previousChar = c;
    }

    private void unindent() throws IOException {
        this.flushTabbing();
        this.indentation.remove(this.indentation.size() - 1);
    }

    private void flushTabbing() throws IOException {
        StringBuffer sb = (StringBuffer) this.indentation.get(this.indentation.size() - 1);
        if (sb == null) return;

        // Calculate the TAB widths.
        List tabWidths = new ArrayList();
        {
            int col = 0; // TODO
            int idx = 0;
            for (int i = 0; i < sb.length(); ++i) {
                char c = sb.charAt(i);
                if (c == ALIGN) {
                    if (tabWidths.size() <= idx) {
                        tabWidths.add(new Integer(col));
                    } else
                    if (col > ((Integer) tabWidths.get(idx)).intValue()) {
                        tabWidths.set(idx, new Integer(col));
                    }
                    col = 0;
                    ++idx;
                } else
                if (c == '\r' || c == '\n') {
                    col = 0;
                } else
                {
                    ++col;
                }
            }
        }

        {
            int col = 0; // TODO
            int idx = 0;
            for (int i = 0; i < sb.length(); ++i) {
                char c = sb.charAt(i);
                if (c == ALIGN) {
                    for (int j = ((Integer) tabWidths.get(idx)).intValue() - col; j > 0; --j) this.out.write(' ');
                    col = 0;
                    ++idx;
                } else
                if (c == '\r' || c == '\n') {
                    this.out.write(c);
                    col = 0;
                } else
                {
                    this.out.write(c);
                    ++col;
                }
            }
        }
    }

    private void indent() {
        this.indentation.add(null);
    }

    /**
     * The prefix, if non-null, is printed between the indentation space and
     * the line data.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        for (; len > 0; --len) this.write(cbuf[off++]);
    }
    public void write(String str, int off, int len) throws IOException {
        for (; len > 0; --len) this.write(str.charAt(off++));
    }
}
