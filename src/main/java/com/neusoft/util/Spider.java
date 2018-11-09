package com.neusoft.util;

import com.neusoft.bean.*;
import com.neusoft.mapper.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Spider {
    @Autowired
    UserMapper userMapper;
    @Autowired
    LikeNotebooksMapper likeNotebooksMapper;
    @Autowired
    LikeCollsMapper likeCollsMapper;
    @Autowired
    LikeUsersMapper likeUsersMapper;
    @Autowired
    ShareNoteMapper shareNoteMapper;
    @Autowired
    RewardNotesMapper rewardNotesMapper;
    @Autowired
    LikeNotesMapper likeNotesMapper;
    @Autowired
    CommentNotesMapper commentNotesMapper;
    @Autowired
    LikeCommentMapper likeCommentMapper;
    public void getUser(String slug) {
        User user1 = new User();
        user1.setSlug(slug);
        User user = userMapper.selectBySlugName(user1);
        Date date = new Date();
        if (user == null){
            user = new User("","","","","","",0,0,0,0,0,0,0);
            user.setSlug(slug);
            userMapper.insert(user);
            user =userMapper.selectBySlugName(user);
        }
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        user.setLatestTime(dateFormat.format(date));
        Document document = null;
        try {
            document = Jsoup.connect("https://www.jianshu.com/u/" + slug).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 得到Document对象
        Elements elements = document.select(".avatar img");
        String strHead = elements.attr("abs:src");


        elements = document.select(".title .name");
        String name = elements.text();

        elements = document.select(".info li");
        int follow = Integer.parseInt(elements.get(0).select("p").text());
        int followers = Integer.parseInt(elements.get(1).select("p").text());
        int article_num = Integer.parseInt(elements.get(2).select("p").text());
        int words_num = Integer.parseInt(elements.get(3).select("p").text());
        int love_num = Integer.parseInt(elements.get(4).select("p").text());


        user.setArticlesNum(article_num);
        user.setHeadPic(strHead);
        user.setNickname(name);
        user.setBeLikedNum(love_num);
        user.setFollowersNum(followers);
        user.setFollowingNum(follow);
        user.setWordsNum(words_num);
        userMapper.updateByPrimaryKey(user);

    }

    public void getDync(String slug, int maxid, int pageno) {
        User user1 = new User();
        user1.setSlug(slug);
        User user = userMapper.selectBySlugName(user1);
        if (user == null){
            user = user1;
        }
        String query = "?max_id=" + maxid + "&page=" + pageno;

        if (maxid == 0) {
            query = "";
        }
        Connection connect = Jsoup.connect("http://www.jianshu.com/users/" + user.getSlug() + "/timeline" + query);
        try {
            // 得到Document对象
            Document document = connect.get();

            Elements elements2 = document.select(".note-list li");
            if (elements2.last() == null) {
                System.out.println(pageno);
                return;
            }
            String id = elements2.last().id();
            maxid = Integer.parseInt(id.split("-")[1]) - 1;

            System.out.println(id);
            for (Element element : elements2) {
                Element first = element.select("span[data-type]").first();
                Element join = element.select("span[data-type=join_jianshu]").first();
                user.setUpdateTime(first.attr("data-datetime").replace("T"," ").replace("+08:00",""));
                user.setJoinTime(first.attr("data-datetime").replace("T"," ").replace("+08:00",""));
                userMapper.updateByPrimaryKey(user);
                Elements elements3 = element.select("span[data-type=like_comment]");
                if (elements3.size() > 0) {
                    System.out.println("喜欢了评论");
                    System.out.println(elements3.attr("data-datetime"));
                    LikeComment likeComment = new LikeComment();
                    likeComment.setUserid(user.getId());
                    likeComment.setTime(elements3.attr("data-datetime"));
                    likeComment.setType("like_comment");
                    likeCommentMapper.insertSelective(likeComment);
                    System.out.println();
                }

                elements3 = element.select("span[data-type=comment_note]");
                if (elements3.size() > 0) {
                    System.out.println("发表评论");
                    System.out.println(element.select(".comment").first().ownText());
                    System.out.println(element.select("a.title").first().attr("href").replace("/p/", ""));
                    System.out.println(elements3.attr("data-datetime"));
                    CommentNotes commentNotes = new CommentNotes();
                    commentNotes.setUserid(user.getId());
                    commentNotes.setTime(elements3.attr("data-datetime"));
                    commentNotes.setCommentText(element.select("p.comment").first().text());
                    commentNotes.setType("comment_notes");

                    commentNotesMapper.insertSelective(commentNotes);
                    System.out.println();
                }
                elements3 = element.select("span[data-type=like_note]");
                if (elements3.size() > 0) {
                    System.out.println("喜欢文章");
                    System.out.println(elements3.attr("data-datetime"));
                    LikeNotes likeNotes = new LikeNotes();
                    likeNotes.setUserid(user.getId());
                    likeNotes.setTime(elements3.attr("data-datetime"));
                    likeNotes.setType("like_notes");
                    likeNotesMapper.insertSelective(likeNotes);
                    System.out.println();
                }
                elements3 = element.select("span[data-type=reward_note]");
                if (elements3.size() > 0) {
                    System.out.println("赞赏文章");
                    System.out.println(elements3.attr("data-datetime"));
                    RewardNotes rewardNotes = new RewardNotes();
                    rewardNotes.setUserid(user.getId());
                    rewardNotes.setTime(elements3.attr("data-datetime"));
                    rewardNotes.setType("reward_notes");
                    rewardNotesMapper.insertSelective(rewardNotes);
                    System.out.println();
                }

                elements3 = element.select("span[data-type=share_note]");
                if (elements3.size() > 0) {
                    System.out.println("发表文章");
                    Element element1 = elements3.get(0).parent().parent().nextElementSibling();
                    System.out.println(element1.attr("href").replace("/p/", ""));
                    System.out.println(elements3.attr("data-datetime"));

                    ShareNote shareNote = new ShareNote();
                    shareNote.setUserid(user.getId());
                    shareNote.setNoteId(element1.attr("href").replace("/p/", ""));
                    shareNote.setTime(elements3.attr("data-datetime"));
                    shareNote.setType("share_notes");
                    shareNoteMapper.insertSelective(shareNote);
                    System.out.println();
                }
                elements3 = element.select("span[data-type=like_user]");
                if (elements3.size() > 0) {
                    System.out.println("关注作者");
                    System.out.println(elements3.attr("data-datetime"));
                    LikeUsers likeUsers = new LikeUsers();
                    likeUsers.setUserid(user.getId());
                    likeUsers.setTime(elements3.attr("data-datetime"));
                    likeUsers.setType("like_users");
                    likeUsersMapper.insertSelective(likeUsers);
                    System.out.println();
                }
                elements3 = element.select("span[data-type=like_collection]");
                if (elements3.size() > 0) {
                    System.out.println("关注专题");
                    System.out.println(elements3.attr("data-datetime"));
                    LikeColls likeColls = new LikeColls();
                    likeColls.setUserid(user.getId());
                    likeColls.setTime(elements3.attr("data-datetime"));
                    likeColls.setType("like_colls");
                    likeCollsMapper.insertSelective(likeColls);
                    System.out.println();
                }
                elements3 = element.select("span[data-type=like_notebook]");
                if (elements3.size() > 0) {
                    System.out.println("关注文集");
                    System.out.println(elements3.attr("data-datetime"));
                    LikeNotebooks likeNotebooks = new LikeNotebooks();
                    likeNotebooks.setUserid(user.getId());
                    likeNotebooks.setTime(elements3.attr("data-datetime"));
                    likeNotebooks.setType("like_notebooks");
                    likeNotebooksMapper.insertSelective(likeNotebooks);
                    System.out.println();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        getDync(slug, maxid, ++pageno);
    }
}
