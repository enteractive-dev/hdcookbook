/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*  
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


package com.hdcookbook.grin.fontstrip.xml;

import java.awt.Color;
import java.awt.Font;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Java representation of the xml configuration file which
 * the fontstrip image generator expects from the user.
 */
public class FontDescription {

    private String name;
    private int charactorColorValue;
    private int boundingRectColorValue;
    private int pixelRectColorValue;
    private int backgroundColorValue;
    private int fontSize;
    private int margin;
    private String fontFileName; // "ttf" or "otf" file
    private String fontName;     // Logical or physical font name for 
                                 // jdk to use for finding font.
                                 // Ignored if "fontFileName" is set.
    Font font = null;    // loaded by the InputData, not from xml.
    
    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public Color getCharactorColor() {
        return new Color(charactorColorValue);
    }

    public Color getBoundingRectColor() {
        return new Color(boundingRectColorValue);
    }

    public Color getPixelRectColor() {
        return new Color(pixelRectColorValue);
    }

    public Color getBackgroundColor() {
        return new Color(backgroundColorValue);
    }
    
    public Integer getCharactorColorValue() {
        return charactorColorValue;
    }

    @XmlJavaTypeAdapter(HexStringIntegerAdapter.class)
    public void setCharactorColorValue(Integer charactorColorValue) {
        this.charactorColorValue = charactorColorValue;
    }

    public Integer getBoundingRectColorValue() {
        return boundingRectColorValue;
    }

    @XmlJavaTypeAdapter(HexStringIntegerAdapter.class)
    public void setBoundingRectColorValue(Integer boundingRectColorValue) {
        this.boundingRectColorValue = boundingRectColorValue;
    }

    public Integer getPixelRectColorValue() {
        return pixelRectColorValue;
    }

    @XmlJavaTypeAdapter(HexStringIntegerAdapter.class)
    public void setPixelRectColorValue(Integer pixelRectColorValue) {
        this.pixelRectColorValue = pixelRectColorValue;
    }

    public Integer getBackgroundColorValue() {
        return backgroundColorValue;
    }

    @XmlJavaTypeAdapter(HexStringIntegerAdapter.class)
    public void setBackgroundColorValue(Integer backgroundColorValue) {
        this.backgroundColorValue = backgroundColorValue;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getPhysicalName() {
        return fontName;
    }

    public void setPhysicalName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontFileName() {
        return fontFileName;
    }

    public void setFontFileName(String fontFileName) {
        this.fontFileName = fontFileName;
    }
    
    public Font getFont() {
        return font;
    }
  
}
