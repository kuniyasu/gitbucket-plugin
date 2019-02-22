/*
 * The MIT License
 *
 * Copyright 2013 Seiji Sogabe
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JavaIdentifierTransformer;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


class PullRequest {
	private String url; //": "https://api.github.com/repos/Codertocat/Hello-World/pulls/1",
	private int id; //": 191568743,
    private String node_id; //": "MDExOlB1bGxSZXF1ZXN0MTkxNTY4NzQz",
    private String html_url; //": "https://github.com/Codertocat/Hello-World/pull/1",
    private String diff_url; //": "https://github.com/Codertocat/Hello-World/pull/1.diff",
    private String patch_url; //": "https://github.com/Codertocat/Hello-World/pull/1.patch",
    private String issue_url; //": "https://api.github.com/repos/Codertocat/Hello-World/issues/1",
    private int number; //": 1,
    private String state; //": "closed",
    private boolean locked; //": false,
    private String title; //": "Update the README with new information",
    
    private String body; //": "This is a pretty simple change that we need to pull into master.",
    private String created_at; //": "2018-05-30T20:18:30Z",
    private String updated_at; //": "2018-05-30T20:18:50Z",
    private String closed_at;  //": "2018-05-30T20:18:50Z",
    private String merged_at;  //": null,
    private String merge_commit_sha; //": "414cb0069601a32b00bd122a2380cd283626a8e5",
    private String assignee; //": null,
    
    private String milestone; //": null,
    private String commits_url; //": "https://api.github.com/repos/Codertocat/Hello-World/pulls/1/commits",
    private String review_comments_url; //": "https://api.github.com/repos/Codertocat/Hello-World/pulls/1/comments",
    private String review_comment_url; //": "https://api.github.com/repos/Codertocat/Hello-World/pulls/comments{/number}",
    private String comments_url; //": "https://api.github.com/repos/Codertocat/Hello-World/issues/1/comments",
    private String statuses_url; //": "https://api.github.com/repos/Codertocat/Hello-World/statuses/34c5c7793cb3b279e22454cb6750c80560547b3a",

    /*
    "author_association": "OWNER",
    "merged": false,
    "mergeable": true,
    "rebaseable": true,
    "mergeable_state": "clean",
    "merged_by": null,
    "comments": 0,
    "review_comments": 1,
    "maintainer_can_modify": false,
    "commits": 1,
    "additions": 1,
    "deletions": 1,
    "changed_files": 1
    */
    
	private User user;

	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNode_id() {
		return node_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public String getDiff_url() {
		return diff_url;
	}

	public void setDiff_url(String diff_url) {
		this.diff_url = diff_url;
	}

	public String getPatch_url() {
		return patch_url;
	}

	public void setPatch_url(String patch_url) {
		this.patch_url = patch_url;
	}

	public String getIssue_url() {
		return issue_url;
	}

	public void setIssue_url(String issue_url) {
		this.issue_url = issue_url;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getClosed_at() {
		return closed_at;
	}

	public void setClosed_at(String closed_at) {
		this.closed_at = closed_at;
	}

	public String getMerged_at() {
		return merged_at;
	}

	public void setMerged_at(String merged_at) {
		this.merged_at = merged_at;
	}

	public String getMerge_commit_sha() {
		return merge_commit_sha;
	}

	public void setMerge_commit_sha(String merge_commit_sha) {
		this.merge_commit_sha = merge_commit_sha;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public String getCommits_url() {
		return commits_url;
	}

	public void setCommits_url(String commits_url) {
		this.commits_url = commits_url;
	}

	public String getReview_comments_url() {
		return review_comments_url;
	}

	public void setReview_comments_url(String review_comments_url) {
		this.review_comments_url = review_comments_url;
	}

	public String getReview_comment_url() {
		return review_comment_url;
	}

	public void setReview_comment_url(String review_comment_url) {
		this.review_comment_url = review_comment_url;
	}

	public String getComments_url() {
		return comments_url;
	}

	public void setComments_url(String comments_url) {
		this.comments_url = comments_url;
	}

	public String getStatuses_url() {
		return statuses_url;
	}

	public void setStatuses_url(String statuses_url) {
		this.statuses_url = statuses_url;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}