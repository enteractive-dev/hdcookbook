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

package net.java.bd.tools.logger;

import java.awt.event.KeyEvent;
import java.io.File;
import net.java.bd.tools.logger.Logger.Observer;

public class XletLogDialog extends BaseLogDialog implements Observer {

    private final static String rcLogLegend =
        "1 | 2 | 3 Reload From File | 4 | 5 | 6 | 7 | 8 | 9 Clear Log | "
        + "0 Hide/Show";
    /**
     * 
     */
    public XletLogDialog() {
        this(true);
    }

    public XletLogDialog(boolean remoteControlEnabled) {
        super("XLET LOG", (remoteControlEnabled ? rcLogLegend : ""),
              BOTTOM_POSITION, remoteControlEnabled);
    }
    
    
    /**
     * 
     */
    protected void loadData() {
        File[] ff = Logger.getLogFiles();
        if (ff != null) {
            data.clear();
            for (int i = ff.length - 1; i >= 0; i--) {
                if (ff[i] != null && ff[i].exists() && ff[i].canRead()) {
                    loadFromFile(ff[i]);
                }
            }
        } else {
            Logger.log("Error in loading data from file.  No log file location set.");
        }
    }
    
    
    /**
     * Invoked when a key has been pressed.
     */
    public synchronized void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
        case KeyEvent.VK_3:
        case KeyEvent.VK_NUMPAD3:
            loadData();
            lLog.initPosition();
            break;
            
        case KeyEvent.VK_9:
        case KeyEvent.VK_NUMPAD9:
            Logger.clearLog(); // Will invoke clearLog() of this class.
            break;
            
        default:
            super.keyPressed(e);
            break;
        }
        notifyAll();
    }

    public void output(String s) {
        addString(s);
    }

    public void clearLog() {
        data.clear();
        lLog.initPosition();
    }
}
