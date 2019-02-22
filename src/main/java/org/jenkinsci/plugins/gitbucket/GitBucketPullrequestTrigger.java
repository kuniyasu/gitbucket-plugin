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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jenkins.model.Jenkins.MasterComputer;
import org.apache.commons.jelly.XMLOutput;
//import org.jenkinsci.plugins.gitbucket.GitBucketPushRequest.Commit;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * Triggers a build when we receive a GitBucket WebHook.
 *
 * @author sogabe
 */

public class GitBucketPullrequestTrigger extends Trigger<AbstractProject<?, ?>> {

    private boolean passThroughGitCommit;

    @DataBoundConstructor
    public GitBucketPullrequestTrigger(boolean passThroughGitCommit) {
        this.passThroughGitCommit = passThroughGitCommit;
    }

    public boolean isPassThroughGitCommit() {
        return passThroughGitCommit;
    }

    public void onPost( GitBucketRequest _req) {
    	final GitBucketPullrequestRequest req = (GitBucketPullrequestRequest)_req;
    	
        getDescriptor().queue.execute(new Runnable() {
            private boolean polling() {
                try {
                    StreamTaskListener listener = new StreamTaskListener(getLogFile());

                    try {
                        PrintStream logger = listener.getLogger();

                        long start = System.currentTimeMillis();
                        logger.println("Started on " + DateFormat.getDateTimeInstance().format(new Date()));
                        boolean result = job.poll(listener).hasChanges();
                        logger.println("Done. Took " + Util.getTimeSpanString(System.currentTimeMillis() - start));

                        if (result) {
                            logger.println("Changes found");
                        } else {
                            logger.println("No changes");
                        }

                        return result;
                    } catch (Error e) {
                        e.printStackTrace(listener.error("Failed to record SCM polling"));
                        LOGGER.log(Level.SEVERE, "Failed to record SCM polling", e);
                        throw e;
                    } catch (RuntimeException e) {
                        e.printStackTrace(listener.error("Failed to record SCM polling"));
                        LOGGER.log(Level.SEVERE, "Failed to record SCM polling", e);
                        throw e;
                    } finally {
                        listener.closeQuietly();
                    }
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Failed to record SCM polling", e);
                }

                return false;
            }

            @Override
            public void run() {
                LOGGER.log(Level.INFO, "{0} triggered.", job.getName());
                
                if (polling()) {
                    String name = " #" + job.getNextBuildNumber();
                    GitBucketPullrequsetCause cause = createGitBucketPullrequsetCause(req);
                    Action[] actions = createActions(req);
                    
                    if (job.scheduleBuild(job.getQuietPeriod(), cause, actions)) {
                        LOGGER.log(Level.INFO, "SCM changes detected in {0}. Triggering {1}", new String[]{job.getName(), name});
                    } else {
                        LOGGER.log(Level.INFO, "SCM changes detected in {0}. Job is already in the queue.", job.getName());
                    }
                }
            }

            private GitBucketPullrequsetCause createGitBucketPullrequsetCause(GitBucketPullrequestRequest req) {
                GitBucketPullrequsetCause cause;
                //String triggeredByUser = req.getPusher().getName();
                
                
                try {
        //            cause = new GitBucketPullrequsetCause(triggeredByUser, getLogFile());
                    cause = new GitBucketPullrequsetCause(getLogFile());
                    
                } catch (IOException ex) {
                	cause = new GitBucketPullrequsetCause("");
                    
         //           cause = new GitBucketPullrequsetCause(triggeredByUser);
                }
               
                
                return cause;
            }

            private Action[] createActions(GitBucketPullrequestRequest req) {
                List<Action> actions = new ArrayList<Action>();

                
                if (passThroughGitCommit) {
                    //Commit lastCommit = req.getLastCommit();
                    //actions.add(new RevisionParameterAction(lastCommit.getId(), false));
                }

                return actions.toArray(new Action[0]);
            }
        });
    }

    public static class GitBucketPullrequsetCause extends SCMTriggerCause {
        
        public GitBucketPullrequsetCause(File logFile) throws IOException {
            super(logFile);
        }

        public GitBucketPullrequsetCause(String pollingLog) {
            super(pollingLog);
        }

        @Override
        public String getShortDescription() {
        	return "Started by GitBucket pull request";
        }
    }

    @Override
    public Collection<? extends Action> getProjectActions() {
        return Collections.singletonList(new GitBucketWebHookPollingAction());
    }

    public class GitBucketWebHookPollingAction implements Action {

        public AbstractProject<?, ?> getOwner() {
            return job;
        }

        @Override
        public String getIconFileName() {
            return "/plugin/gitbucket/images/24x24/gitbucket-log.png";
        }

        @Override
        public String getDisplayName() {
            return "GitBucket Hook Log";
        }

        @Override
        public String getUrlName() {
            return "GitBucketPollLog";
        }

        public String getLog() throws IOException {
            return Util.loadFile(getLogFile());
        }

        public void writeLogTo(XMLOutput out) throws IOException {
            new AnnotatedLargeText<GitBucketWebHookPollingAction>(
                    getLogFile(), Charset.defaultCharset(), true, this).writeHtmlTo(0, out.asWriter());
        }
    }

    @Override
    public GitBucketPushTriggerDescriptor getDescriptor() {
        return (GitBucketPushTriggerDescriptor) super.getDescriptor();
    }

    public File getLogFile() {
        return new File(job.getRootDir(), "gitbucket-polling.log");
    }

    @Extension
    public static class GitBucketPushTriggerDescriptor extends TriggerDescriptor {

        private transient final SequentialExecutionQueue queue
                = new SequentialExecutionQueue(MasterComputer.threadPoolForRemoting);

        @Override
        public boolean isApplicable(Item item) {
            return item instanceof AbstractProject;
        }

        @Override
        public String getDisplayName() {
            return "Build when a change is pushed to GitBucket";
        }

        @Override
        public String getHelpFile() {
            return "/plugin/gitbucket/help/help-trigger.html";
        }

    }

    private static final Logger LOGGER = Logger.getLogger(GitBucketPushTrigger.class.getName());
}
