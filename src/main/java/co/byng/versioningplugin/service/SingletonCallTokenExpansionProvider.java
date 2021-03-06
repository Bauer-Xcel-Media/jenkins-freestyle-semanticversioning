/**
 * SingletonCallTokenExpansionProvider.java
 * Created 31-Jul-2015 10:00:52
 *
 * @author M.D.Ward <matthew.ward@byng.co>
 * The MIT License
 *
 * Copyright 2015 M.D.Ward <matthew.ward@byng.co>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package co.byng.versioningplugin.service;

import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.tokenmacro.TokenMacro;
import org.jenkinsci.plugins.tokenmacro.impl.EnvironmentVariableMacro;



/**
 * SingletonCallTokenExpansionProvider
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 */
public class SingletonCallTokenExpansionProvider implements TokenExpansionProvider {

    protected TokenMacro tokenMacro;

    public SingletonCallTokenExpansionProvider(TokenMacro tokenMacro) {
        this.tokenMacro = tokenMacro;
    }
    
    public SingletonCallTokenExpansionProvider() {
        this(new EnvironmentVariableMacro());
    }

    @Override
    public String expand(String tokenizedString, AbstractBuild build, TaskListener listener) {
        try {
            return this.tokenMacro.expand(build, listener, tokenizedString);
        } catch (Throwable t) {
            listener.error(
                "Unable to set build description; "
                        + t.getClass().getName()
                        + " - "
                        + t.getMessage()
            );
        }
        
        return null;
    }
    
}
