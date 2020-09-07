package com.ui.dao;

import java.util.List;

import com.ui.model.MemberPost;
import com.ui.model.MemberPostComments;

public interface MemberPostDAO {
	public List<MemberPost> getAllMemberPost();
	public List<MemberPost> getLastThreeMemberPost();
	public void addNewMemberPost(MemberPost mp);
	public List<MemberPostComments> getPostCommentsById(int postid);
	public void addComment(MemberPostComments mpc);
}
