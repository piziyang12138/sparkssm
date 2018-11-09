package com.neusoft.controller.hello;


import com.alibaba.fastjson.JSON;
import com.neusoft.AnlysisJianshu;
import com.neusoft.bean.NameValue;
import com.neusoft.bean.SecondPart;
import com.neusoft.bean.User;
import com.neusoft.bean.UserInfo;
import com.neusoft.mapper.*;
import com.neusoft.util.Spider;
import com.neusoft.util.WordCount;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import org.apache.spark.sql.catalyst.expressions.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.*;

@Controller
public class HelloController {

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
    @Autowired
    Spider spider;

    @RequestMapping("/")
    public String hello(Model model){
        return "index";
    }

    @RequestMapping("submit")
    public String sumbit(String slug,Model model){
        User user1 = new User();
        user1.setSlug(slug);
        User user = userMapper.selectBySlugName(user1);
        if (user == null){
            spider.getUser(slug);
            spider.getDync(slug,0,1);
            user = userMapper.selectBySlugName(user1);
        }

        List list = AnlysisJianshu.anlysisData(slug);
        GenericRowWithSchema row = (GenericRowWithSchema) list.get(0);
        List<Row> list1 = AnlysisJianshu.dynamic(user.getId().toString());
        UserInfo res = new UserInfo(row.getInt(0),row.getString(1),row.getString(2),row.getString(3),row.getString(4),row.getString(5),
                row.getString(6),row.getInt(7),row.getInt(8),row.getInt(9),row.getInt(10),
                row.getInt(11),row.getInt(12),row.getInt(13));

        List<NameValue> dynamiccount = new ArrayList<>();
        SecondPart secondPart = new SecondPart();
        for (Row row1 : list1){
            switch (row1.getString(0)){
                case "like_colls":res.setLikecollscount(row1.getLong(1));secondPart.setFirstlikecolls(row1.getString(2));dynamiccount.add(new NameValue("关注专题",row1.getLong(1)));break;
                case "like_comment":res.setLikecommentcount(row1.getLong(1));secondPart.setFirstlikecomment(row1.getString(2));dynamiccount.add(new NameValue("点赞评论",row1.getLong(1)));break;
                case "like_notebooks":res.setLikenotebookscount(row1.getLong(1));secondPart.setFirstlikenotebooks(row1.getString(2));dynamiccount.add(new NameValue("关注文集",row1.getLong(1)));break;
                case "like_notes":res.setLikenotescount(row1.getLong(1));secondPart.setFirstlikenote(row1.getString(2));dynamiccount.add(new NameValue("喜欢文章",row1.getLong(1)));break;
                case "like_users":res.setLikeusercount(row1.getLong(1));secondPart.setFirstlikeuser(row1.getString(2));dynamiccount.add(new NameValue("关注用户",row1.getLong(1)));break;
                case "reward_notes":res.setRewardnotescount(row1.getLong(1));secondPart.setFirstrewardnote(row1.getString(2));dynamiccount.add(new NameValue("赞赏文章",row1.getLong(1)));break;
                case "share_notes":res.setSharenotecount(row1.getLong(1));secondPart.setFirstsharenote(row1.getString(2)); dynamiccount.add(new NameValue("发表文章",row1.getLong(1)));break;
                case "comment_notes":res.setCommentnotescount(row1.getLong(1));secondPart.setFirstcommentnote(row1.getString(2));dynamiccount.add(new NameValue("发表评论",row1.getLong(1)));break;
            }
        }
        model.addAttribute("user",res);

        model.addAttribute("secondPart",secondPart);

        model.addAttribute("jsonCateNum",JSON.toJSONString(dynamiccount));

        List<Row> monthlist = AnlysisJianshu.countByMonth(user.getId().toString());
        List<String> month = new ArrayList<>();
        List<Long> month_count = new ArrayList<>();
        for(Row monthrow:monthlist){
            month.add(monthrow.getString(0));
            month_count.add(monthrow.getLong(1));
        }
        model.addAttribute("months",JSON.toJSONString(month));
        model.addAttribute("monthsfreq",JSON.toJSONString(month_count));

        List<Row> daylist = AnlysisJianshu.countByDay(user.getId().toString());
        List<String> day = new ArrayList<>();
        List<Long> day_count = new ArrayList<>();
        for(Row dayrow:daylist){
            day.add(dayrow.getString(0));
            day_count.add(dayrow.getLong(1));
        }
        model.addAttribute("days",JSON.toJSONString(day));
        model.addAttribute("daysfreq",JSON.toJSONString(day_count));

        int[] hourcount = new int[24];
        List<Row> hourlist = AnlysisJianshu.countByHour(user.getId().toString());
        for(Row hourrow:hourlist){
            hourcount[Integer.parseInt(hourrow.getString(0))] = (int)hourrow.getLong(1);
        }
        model.addAttribute("hourfreq",JSON.toJSONString(hourcount));

        int[] weekcount = new int[7];
        List<Row> weeklist = AnlysisJianshu.countByWeek(user.getId().toString());
        for(Row weekrow:weeklist){
            weekcount[weekrow.getInt(0)] = (int)weekrow.getLong(1);
        }
        model.addAttribute("weekfreq",JSON.toJSONString(weekcount));

        int[] monthsharenote = new int[12];
        List<Row> monthsharenotelist = AnlysisJianshu.countByMonthShareNote(user.getId().toString());
        for(Row monthsharenoterow:monthsharenotelist){
            monthsharenote[Integer.parseInt(monthsharenoterow.getString(0))-1] = (int)monthsharenoterow.getLong(1);
        }
        model.addAttribute("monthsharenote",JSON.toJSONString(monthsharenote));

        List<List<Integer>> weektimesharenotesres = new ArrayList<>();
        List<Row> weektimesharenotecountlist = AnlysisJianshu.countByWeekTimeShareNote(user.getId().toString());
        for(Row weektimecountrow:weektimesharenotecountlist){
            List<Integer> weektimecount = new ArrayList<>();
            weektimecount.add(weektimecountrow.getInt(0));
            weektimecount.add(Integer.parseInt(weektimecountrow.getString(1)));
            weektimecount.add((int)weektimecountrow.getLong(2));
            weektimesharenotesres.add(weektimecount);
        }

        model.addAttribute("weektimesharenotefreq",JSON.toJSONString(weektimesharenotesres));

        List<List<Integer>> weektimelikenotesres = new ArrayList<>();
        List<Row> weektimelikenotecountlist = AnlysisJianshu.countByWeekTimeLikeNotes(user.getId().toString());
        for(Row weektimelikenotecountrow:weektimelikenotecountlist){
            List<Integer> weektimecount = new ArrayList<>();
            weektimecount.add(weektimelikenotecountrow.getInt(0));
            weektimecount.add(Integer.parseInt(weektimelikenotecountrow.getString(1)));
            weektimecount.add((int)weektimelikenotecountrow.getLong(2));
            weektimelikenotesres.add(weektimecount);
        }

        model.addAttribute("weektimelikenotefreq",JSON.toJSONString(weektimelikenotesres));

        List<List<Integer>> weektimelikeusersres = new ArrayList<>();
        List<Row> weektimelikeuserscountlist = AnlysisJianshu.countByWeekTimeLikeUsers(user.getId().toString());
        for(Row weektimelikeusersrow:weektimelikeuserscountlist){
            List<Integer> weektimecount = new ArrayList<>();
            weektimecount.add(weektimelikeusersrow.getInt(0));
            weektimecount.add(Integer.parseInt(weektimelikeusersrow.getString(1)));
            weektimecount.add((int)weektimelikeusersrow.getLong(2));
            weektimelikeusersres.add(weektimecount);
        }

        model.addAttribute("weektimelikeuserfreq",JSON.toJSONString(weektimelikeusersres));

        List<List<Integer>> weektimecommentnoteres = new ArrayList<>();
        List<Row> weektimecommentnotecountlist = AnlysisJianshu.countByWeekTimeComment(user.getId().toString());
        for(Row weektimecommentnoterow:weektimecommentnotecountlist){
            List<Integer> weektimecount = new ArrayList<>();
            weektimecount.add(weektimecommentnoterow.getInt(0));
            weektimecount.add(Integer.parseInt(weektimecommentnoterow.getString(1)));
            weektimecount.add((int)weektimecommentnoterow.getLong(2));
            weektimecommentnoteres.add(weektimecount);
        }

        model.addAttribute("weektimecommentnotefreq",JSON.toJSONString(weektimecommentnoteres));

        List<List<Integer>> weektimerewardnotesres = new ArrayList<>();
        List<Row> weektimerewardnotescountlist = AnlysisJianshu.countByWeekTimeRewardNotes(user.getId().toString());
        for(Row weektimerewardnotesrow:weektimerewardnotescountlist){
            List<Integer> weektimecount = new ArrayList<>();
            weektimecount.add(weektimerewardnotesrow.getInt(0));
            weektimecount.add(Integer.parseInt(weektimerewardnotesrow.getString(1)));
            weektimecount.add((int)weektimerewardnotesrow.getLong(2));
            weektimerewardnotesres.add(weektimecount);
        }

        model.addAttribute("weektimerewardnotesfreq",JSON.toJSONString(weektimerewardnotesres));

        List<Row> cnsplitlist = AnlysisJianshu.cnsplitComment(user.getId().toString());
        if (cnsplitlist.size() != 0) {
            GenericRowWithSchema cnsplitrow = (GenericRowWithSchema) cnsplitlist.get(0);
            List<NameValue> wcNameValueList = new ArrayList<>();
            Map<String, Integer> map = WordCount.wc(cnsplitrow.getString(1));
            map.remove("");
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                NameValue nameValue = new NameValue(entry.getKey(), (long) entry.getValue());
                wcNameValueList.add(nameValue);
            }
            int len = wcNameValueList.size() > 100 ? 100 : wcNameValueList.size();
            wcNameValueList.sort((o1, o2) -> (int) (o2.getValue() - o1.getValue()));
            model.addAttribute("comment_num", cnsplitrow.getLong(0));
            model.addAttribute("wordfreq", JSON.toJSONString(wcNameValueList.subList(0, len)));
        }else {
            model.addAttribute("comment_num", 0);
            model.addAttribute("wordfreq", "{['无':0]}");
        }
        return "timeline";
    }
}
