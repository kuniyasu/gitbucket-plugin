/*
 * The MIT License
 *
 * Copyright (c) 2013, Seiji Sogabe
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
package org.jenkinsci.plugins.gitbucket;

import hudson.Extension;
import hudson.Util;
import hudson.console.AnnotatedLargeText;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.Item;
import hudson.plugins.git.RevisionParameterAction;
import hudson.triggers.SCMTrigger.SCMTriggerCause;
import hudson.triggers.Trigger;
import hudson.triggers.TriggerDescriptor;
import hudson.util.SequentialExecutionQueue;
import hudson.util.StreamTaskListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jenkins.model.Jenkins.MasterComputer;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JavaIdentifierTransformer;

import org.apache.commons.jelly.XMLOutput;
import org.kohsuke.stapler.DataBoundConstructor;


public class GitBucketRequest {
	
	private Repository repository;
	private Sender sender;
	 
	public static GitBucketRequest create(String payload) {
        if (payload == null) {
            throw new IllegalArgumentException("payload should not be null");
        }
        
        return GitBucketRequest.create(JSONObject.fromObject(payload));    
	}
	
	public static GitBucketRequest create(JSONObject payload) {
        if (payload == null || payload.isNullObject()) {
            throw new IllegalArgumentException("payload should not be null");
        }

        JsonConfig config = createJsonConfig();
        return (GitBucketRequest) JSONObject.toBean(payload, config);		
	}

    private static JsonConfig createJsonConfig() {
        JsonConfig config = new JsonConfig();
        config.setRootClass(GitBucketRequest.class);

        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
        config.setClassMap(classMap);

        config.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {

            @Override
            public String transformToJavaIdentifier(String param) {
                if (param == null) {
                    return null;
                }
                if ("private".equals(param)) {
                    return "private_";
                }
                // TODO: can't we use JavaIdentifierTransformer.CAMEL_CASE ?
                if("clone_url".equals(param)) {
                  return "cloneUrl";
                }
                return param;
            }

        });

        return config;
    }
    
	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}
	
	
}
