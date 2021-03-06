/*  
 * Copyright (c) 2008, Sun Microsystems, Inc.
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  * Neither the name of Sun Microsystems nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 *  Note:  In order to comply with the binary form redistribution 
 *         requirement in the above license, the licensee may include 
 *         a URL reference to a copy of the required copyright notice, 
 *         the list of conditions and the disclaimer in a human readable 
 *         file with the binary form of the code that is subject to the
 *         above license.  For example, such file could be put on a 
 *         Blu-ray disc containing the binary form of the code or could 
 *         be put in a JAR file that is broadcast via a digital television 
 *         broadcast medium.  In any event, you must include in any end 
 *         user licenses governing any code that includes the code subject 
 *         to the above license (in source and/or binary form) a disclaimer 
 *         that is at least as protective of Sun as the disclaimers in the 
 *         above license.
 * 
 *         A copy of the required copyright notice, the list of conditions and
 *         the disclaimer will be maintained at 
 *         https://hdcookbook.dev.java.net/misc/license.html .
 *         Thus, licensees may comply with the binary form redistribution
 *         requirement with a text file that contains the following text:
 * 
 *             A copy of the license(s) governing this code is located
 *             at https://hdcookbook.dev.java.net/misc/license.html
 */


/** 
 * The extensions parser for the Playground project
 */

import com.hdcookbook.grin.Feature;
import com.hdcookbook.grin.Show;
import com.hdcookbook.grin.commands.Command;
import com.hdcookbook.grin.features.FixedImage;
import com.hdcookbook.grin.features.SEFixedImage;
import com.hdcookbook.grin.features.Modifier;
import com.hdcookbook.grin.io.text.ExtensionParser;
import com.hdcookbook.grin.io.text.Lexer;
import com.hdcookbook.grin.io.text.ShowParser;
import com.hdcookbook.grin.io.text.ForwardReference;

import java.awt.Color;
import java.io.IOException;

public class PlaygroundExtensionParser implements ExtensionParser {


    /**
     * {@inheritDoc}
     **/
    public Feature getFeature(Show show, String typeName,
                              String name, Lexer lexer)
                   throws IOException
    {
        if ("Playground:arc".equals(typeName)) {
            return parseArc(show, name, lexer);
        } else if ("Playground:bouncing_arc".equals(typeName)) {
            return parseBouncingArc(show, name, lexer);
        } else if ("Playground:image_frame".equals(typeName)) {
            return parseFrame(show, name, lexer);
        } else {
            return null;
        }
    }

    private Feature parseArc(Show show, String name, Lexer lexer)
                throws IOException
    {
        Color color = lexer.getParser().parseColor();
        lexer.parseExpected("x");
        int x = lexer.getInt();
        lexer.parseExpected("y");
        int y = lexer.getInt();
        lexer.parseExpected("width");
        int width = lexer.getInt();
        lexer.parseExpected("height");
        int height = lexer.getInt();
        lexer.parseExpected("startAngle");
        int startAngle = lexer.getInt();
        lexer.parseExpected("arcAngle");
        int arcAngle = lexer.getInt();
        lexer.parseExpected(";");
        return new SEArc(show, name, x, y, width, height, startAngle, arcAngle,
                         color);
    }

    private Feature parseBouncingArc(Show show, String name, Lexer lexer)
                throws IOException
    {
        Color color = lexer.getParser().parseColor();
        lexer.parseExpected("x");
        int x = lexer.getInt();
        lexer.parseExpected("y");
        int y = lexer.getInt();
        lexer.parseExpected("width");
        int width = lexer.getInt();
        lexer.parseExpected("height");
        int height = lexer.getInt();
        lexer.parseExpected("startAngle");
        int startAngle = lexer.getInt();
        lexer.parseExpected("arcAngle");
        int arcAngle = lexer.getInt();
        lexer.parseExpected("bounceHeight");
        int bounceHeight = lexer.getInt();
        lexer.parseExpected("bouncePeriod");
        int bouncePeriod = lexer.getInt();
        if (bouncePeriod < 2) {
            lexer.reportError("bouncePeriod must be at least 2");
        }
        lexer.parseExpected(";");
        return new SEBouncingArc(show, name, x, y, width, height, 
                                 startAngle, arcAngle, color, 
                                 bounceHeight, bouncePeriod);
    }

    private Feature parseFrame(Show show, String name, Lexer lexer)
                throws IOException
    {
        final ShowParser parser = lexer.getParser();
        final String fixedImageName = lexer.getString();
        lexer.parseExpected("outline");
        int outlineWidth = lexer.getInt();
        Color color = parser.parseColor();
        lexer.parseExpected(";");
        final SEImageFrame frame 
                = new SEImageFrame(show, name, outlineWidth, color);
        ForwardReference fw = new ForwardReference(lexer) {
            public void resolve() throws IOException {
                Feature f = parser.lookupFeatureOrFail(fixedImageName);
                if (!(f instanceof SEFixedImage)) {
                    reportError(fixedImageName + " is not a fixed_image");
                }
                frame.setFixedImage((SEFixedImage) f);
            }
        };
        parser.addForwardReference(fw, 0);
        return frame;
    }

    /**
     * {@inheritDoc}
     **/
    public Modifier getModifier(Show show, String typeName,
                                String name, Lexer lexer) 
                throws IOException
    {
        return null;
    }

    /**
     * {@inheritDoc}
     **/
    public Command getCommand(Show show, String typeName, Lexer lexer)
                           throws IOException
    {
        return null;
    }

}
