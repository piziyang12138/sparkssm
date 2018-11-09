package com.neusoft.bean;

public class UserInfo extends User{

    Long sharenotecount;
    Long rewardnotescount;
    Long likeusercount;
    Long likenotescount;
    Long likenotebookscount;
    Long likecommentcount;
    Long likecollscount;
    Long commentnotescount;


    public UserInfo(int id, String slug, String nickname, String updateTime, String latestTime, String joinTime, String headPic, int gender, int isContract, int followingNum, int followersNum, int articlesNum, int wordsNum, int beLikedNum) {
        super(id, slug, nickname, updateTime, latestTime, joinTime, headPic, gender, isContract, followingNum, followersNum, articlesNum, wordsNum, beLikedNum);
    }

    public Long getSharenotecount() {
        return sharenotecount;
    }

    public void setSharenotecount(Long sharenotecount) {
        this.sharenotecount = sharenotecount;
    }

    public Long getRewardnotescount() {
        return rewardnotescount;
    }

    public void setRewardnotescount(Long rewardnotescount) {
        this.rewardnotescount = rewardnotescount;
    }

    public Long getLikeusercount() {
        return likeusercount;
    }

    public void setLikeusercount(Long likeusercount) {
        this.likeusercount = likeusercount;
    }

    public Long getLikenotescount() {
        return likenotescount;
    }

    public void setLikenotescount(Long likenotescount) {
        this.likenotescount = likenotescount;
    }

    public Long getLikenotebookscount() {
        return likenotebookscount;
    }

    public void setLikenotebookscount(Long likenotebookscount) {
        this.likenotebookscount = likenotebookscount;
    }

    public Long getLikecommentcount() {
        return likecommentcount;
    }

    public void setLikecommentcount(Long likecommentcount) {
        this.likecommentcount = likecommentcount;
    }

    public Long getLikecollscount() {
        return likecollscount;
    }

    public void setLikecollscount(Long likecollscount) {
        this.likecollscount = likecollscount;
    }

    public Long getCommentnotescount() {
        return commentnotescount;
    }

    public void setCommentnotescount(Long commentnotescount) {
        this.commentnotescount = commentnotescount;
    }
}
