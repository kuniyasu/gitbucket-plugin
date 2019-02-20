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


class Sender {
	private String login; 		//": "Codertocat",
	private int id; 			//": 21031067
	private String node_id; 	//": "MDQ6VXNlcjIxMDMxMDY3",
	private String avatar_url; 	//": "https://avatars1.githubusercontent.com/u/21031067?v=4",
	private String gravatar_id; //": "",
	private String url; 		//": "https://api.github.com/users/Codertocat",
	private String html_url; 	//": "https://github.com/Codertocat",
	private String followers_url; //": "https://api.github.com/users/Codertocat/followers",
	private  String following_url; //": "https://api.github.com/users/Codertocat/following{/other_user}",
	private String gists_url; //": "https://api.github.com/users/Codertocat/gists{/gist_id}",
	private  String starred_url; //": "https://api.github.com/users/Codertocat/starred{/owner}{/repo}",
	private String subscriptions_url; //": "https://api.github.com/users/Codertocat/subscriptions",
	private String organizations_url; //": "https://api.github.com/users/Codertocat/orgs",
	private String repos_url; //": "https://api.github.com/users/Codertocat/repos",
	private String events_url; //": "https://api.github.com/users/Codertocat/events{/privacy}",
	private String received_events_url; //": "https://api.github.com/users/Codertocat/received_events",
	private String type; //": "User",
	private boolean site_admin; //": false
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public String getGravatar_id() {
		return gravatar_id;
	}
	public void setGravatar_id(String gravatar_id) {
		this.gravatar_id = gravatar_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getFollowers_url() {
		return followers_url;
	}
	public void setFollowers_url(String followers_url) {
		this.followers_url = followers_url;
	}
	public String getFollowing_url() {
		return following_url;
	}
	public void setFollowing_url(String following_url) {
		this.following_url = following_url;
	}
	public String getGists_url() {
		return gists_url;
	}
	public void setGists_url(String gists_url) {
		this.gists_url = gists_url;
	}
	public String getStarred_url() {
		return starred_url;
	}
	public void setStarred_url(String starred_url) {
		this.starred_url = starred_url;
	}
	public String getSubscriptions_url() {
		return subscriptions_url;
	}
	public void setSubscriptions_url(String subscriptions_url) {
		this.subscriptions_url = subscriptions_url;
	}
	public String getOrganizations_url() {
		return organizations_url;
	}
	public void setOrganizations_url(String organizations_url) {
		this.organizations_url = organizations_url;
	}
	public String getRepos_url() {
		return repos_url;
	}
	public void setRepos_url(String repos_url) {
		this.repos_url = repos_url;
	}
	public String getEvents_url() {
		return events_url;
	}
	public void setEvents_url(String events_url) {
		this.events_url = events_url;
	}
	public String getReceived_events_url() {
		return received_events_url;
	}
	public void setReceived_events_url(String received_events_url) {
		this.received_events_url = received_events_url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isSite_admin() {
		return site_admin;
	}
	public void setSite_admin(boolean site_admin) {
		this.site_admin = site_admin;
	}
	
	
}
